package com.pixelutilitys.arcade.emulators.AEPgb;
/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information
 * Copyright (C) 2000-2001 Ben Mazur
 */


/**
 * PgbBasicVideo extends PgbVideo by providing an array of
 * pixels mapped to color index values.
 * 
 * It keeps an array of indexed pixels and an array of 
 * color bytes.  Shouldn't be too hard to implement.
 */
public class PgbBasicVideo extends PgbVideo {
	// the gameboy screen is a 4 or 6-bit value indicating:
	// PPP	: CGB palette
	// N	: 1 = OBJ1, 0 = OBJ0 or BG
	// O	: 1 = OBJ, 0 = BG
	// CC	: pixel color value
	// mono gameboy		: \\NOCC
	// super gameboy	: SSNOCC
	// color gameboy	: PPPOCC
	
	// the SGB border is a 7-bit value indicating:
	// SS	= SGB palette
	// BBB	= SGB Border Palette
	// SGB border		= BBBSSCC
	
	byte[]					screenMemory;
	byte[]					borderPixels;
	
	byte[]					screenRPal;
	byte[]					screenGPal;
	byte[]					screenBPal;
	int[]					screenPalette;
	
	int[]					borderPalette;
	
	public PgbBasicVideo() {
		// memory
		screenMemory = new byte[160 * 144];
		borderPixels = new byte[256 * 224];
		
		screenRPal = new byte[64];
		screenGPal = new byte[64];
		screenBPal = new byte[64];
		screenPalette = new int[64];
		borderPalette = new int[128];
	}

	public byte[] getScreenMemory() {
		return screenMemory;
	}
	public byte getScreenMemory(int position) {
		return screenMemory[position];
	}
	public byte getScreenRed(byte index) {
		return screenRPal[index & 0x3F];
	}
	public byte getScreenGreen(byte index) {
		return screenGPal[index & 0x3F];
	}
	public byte getScreenBlue(byte index) {
		return screenBPal[index & 0x3F];
	}
	public int getScreenColor(byte index) {
		return screenPalette[index & 0x3F];
	}

	public void hblank() {
		super.hblank();
		if(lcd_on && curframe == 0) {
			// background
			if(bg_on) {
				doBgLine(ly);
			}
			// window
			if(win_on) {
				doWinLine(ly);
			}
			// sprites
			if(obj_on) {
				doObjLine(ly);
			}
			// go thru each pixel, add SGB palette settings
			if(PgbSettings.system == PgbSettings.SYS_SGB) {
				doSgbPalette(ly);
			}
		}
	}
	
	void doBgLine(int line) {
		int x, ps, tx, ty, tl, sxo, isxo;
		sxo = scx & 0x07;
		isxo = 8 - (scx & 0x07);
		ps = line * 160;
		tx = scx / 8 + 1;
		ty = (line + scy) >> 3 & 0x1F;
		tl = (line + scy) & 0x07;
		System.arraycopy(getTileLineArray(tx - 1, ty, tl, bg_mode), sxo, screenMemory, ps, isxo);
		for(x = 0; x < 19; x++) {
			copyTileLineArray((tx + x) & 0x1F, ty, tl, bg_mode, screenMemory, ps + x * 8 + isxo);
			//System.arraycopy(getTileLineArray((tx + x) % 32, ty, tl, bg_mode), 0, screenMemory, ps + x * 8 + isxo, 8);
		}
		System.arraycopy(getTileLineArray((tx + 19) & 0x1F, ty, tl, bg_mode), 0, screenMemory, ps + 152 + isxo, sxo);
	}
	void doWinLine(int line) {
		int x, ps, ty, tl;
		if(wx < 0 || wx > 166 || wy > line) {
			return;
		}
		ps = line * 160;
		ty = (line - wy) >> 3 & 0x1F;
		tl = (line - wy) & 0x07;
		System.arraycopy(getTileLineArray(0, ty, tl, win_mode), 0, screenMemory, ps, 8);
		for(x = 0; x < 19; x++) {
			System.arraycopy(getTileLineArray(x + 1, ty, tl, win_mode), 0, screenMemory, ps + x * 8 + 8, 8);
		}
		//System.arraycopy(getWinArray(20, ty, tl), 0, screenMemory, ps + 152 + isxo, 8);
	}
	
	void doObjLine(int line) {
		byte[] ta;
		int s, so, sx, sy, p, spx;
		byte sp, pal;
		boolean sflipx, sflipy, shidden;
		for(s = 39; s >= 0; s--) {
			so = s * 4;
			sx = oam[so + 1] & 0xFF;
			sy = oam[so] & 0xFF;
			if((sx != 0 && sy != 0) && sy <= line + 16 && sy > line + (16 - obj_siz)) {
				sflipx = (oam[so + 3] & 0x20) == 0x20;
				sflipy = (oam[so + 3] & 0x40) == 0x40;
				shidden = ((oam[so + 3] | getBackgroundAttr(sx - 8, line)) & 0x80) == 0x80;
				if(PgbSettings.system == PgbSettings.SYS_GBC) {
					pal = (byte)(0x04 | ((oam[so + 3] & 0x07) << 3));
				} else {
					pal = (byte)(0x04 | ((oam[so + 3] & 0x10) >> 1));
				}
				ta = getTileLineArray((oam[so + 3] & 0x08) >> 3, oam[so + 2] & (obj_mode ? 0xFE : 0xFF), sflipy ? obj_siz - (line - sy + 17) : line - sy + 16, sflipx, pal);
				for(p = 0; p < 8; p++) {
					// grrr... transparency...
					spx = sx - 8 + p;
					sp = ta[p];
					if((sp & 0x03) != 0  && spx < 160 && spx >= 0 
					   && (!shidden || ((screenMemory[line * 160 + spx] & 0x03) == 0))) {
						screenMemory[line * 160 + spx] = sp;
					}
				}
			}
		}
	}
	
	void doSgbBorder() {
		int x, y, l;
		boolean vflip, hflip;
		byte flags, pal;
		for(y = 0; y < 28; y++) {
			for(x = 0; x < 32; x++) {
				flags = sgbPicture[x * 2 + y * 64 + 1];
				vflip = (flags & 0x80) == 0x80;
				hflip = (flags & 0x40) == 0x40;
				pal = (byte)((flags & 0x1C) << 2);
				System.arraycopy(getSgbBorderArray(x, y, 0, vflip, hflip, pal), 0, borderPixels, x * 8 + (y * 8 + 0) * 256 , 8);
				System.arraycopy(getSgbBorderArray(x, y, 1, vflip, hflip, pal), 0, borderPixels, x * 8 + (y * 8 + 1) * 256 , 8);
				System.arraycopy(getSgbBorderArray(x, y, 2, vflip, hflip, pal), 0, borderPixels, x * 8 + (y * 8 + 2) * 256 , 8);
				System.arraycopy(getSgbBorderArray(x, y, 3, vflip, hflip, pal), 0, borderPixels, x * 8 + (y * 8 + 3) * 256 , 8);
				System.arraycopy(getSgbBorderArray(x, y, 4, vflip, hflip, pal), 0, borderPixels, x * 8 + (y * 8 + 4) * 256 , 8);
				System.arraycopy(getSgbBorderArray(x, y, 5, vflip, hflip, pal), 0, borderPixels, x * 8 + (y * 8 + 5) * 256 , 8);
				System.arraycopy(getSgbBorderArray(x, y, 6, vflip, hflip, pal), 0, borderPixels, x * 8 + (y * 8 + 6) * 256 , 8);
				System.arraycopy(getSgbBorderArray(x, y, 7, vflip, hflip, pal), 0, borderPixels, x * 8 + (y * 8 + 7) * 256 , 8);
			}
		}
	}
	byte[] getSgbBorderArray(int tx, int ty, int tl, boolean vflip, boolean hflip, byte pal) {
		byte[] ta = new byte[8];
		byte td0, td1, td2, td3;
		int ts;
		if(vflip) {
			tl = 7 - tl;
		}
		ts = (sgbPicture[tx * 2 + ty * 64] & 0xFF) * 32 + tl * 2;
		td0 = sgbCharset[ts + 0];
		td1 = sgbCharset[ts + 1];
		td2 = sgbCharset[ts + 16];
		td3 = sgbCharset[ts + 17];
		if(hflip) {
			ta[7] = (byte)(pal | (td2 >> 5 & 4) | (td3 >> 4 & 8) | (td0 >> 7 & 1) | (td1 >> 6 & 2));
			ta[6] = (byte)(pal | (td2 >> 4 & 4) | (td3 >> 3 & 8) | (td0 >> 6 & 1) | (td1 >> 5 & 2));
			ta[5] = (byte)(pal | (td2 >> 3 & 4) | (td3 >> 2 & 8) | (td0 >> 5 & 1) | (td1 >> 4 & 2));
			ta[4] = (byte)(pal | (td2 >> 2 & 4) | (td3 >> 1 & 8) | (td0 >> 4 & 1) | (td1 >> 3 & 2));
			ta[3] = (byte)(pal | (td2 >> 1 & 4) | (td3 >> 0 & 8) | (td0 >> 3 & 1) | (td1 >> 2 & 2));
			ta[2] = (byte)(pal | (td2 >> 0 & 4) | (td3 << 1 & 8) | (td0 >> 2 & 1) | (td1 >> 1 & 2));
			ta[1] = (byte)(pal | (td2 << 1 & 4) | (td3 << 2 & 8) | (td0 >> 1 & 1) | (td1 >> 0 & 2));
			ta[0] = (byte)(pal | (td2 << 2 & 4) | (td3 << 3 & 8) | (td0 >> 0 & 1) | (td1 << 1 & 2));
		} else {
			ta[0] = (byte)(pal | (td2 >> 5 & 4) | (td3 >> 4 & 8) | (td0 >> 7 & 1) | (td1 >> 6 & 2));
			ta[1] = (byte)(pal | (td2 >> 4 & 4) | (td3 >> 3 & 8) | (td0 >> 6 & 1) | (td1 >> 5 & 2));
			ta[2] = (byte)(pal | (td2 >> 3 & 4) | (td3 >> 2 & 8) | (td0 >> 5 & 1) | (td1 >> 4 & 2));
			ta[3] = (byte)(pal | (td2 >> 2 & 4) | (td3 >> 1 & 8) | (td0 >> 4 & 1) | (td1 >> 3 & 2));
			ta[4] = (byte)(pal | (td2 >> 1 & 4) | (td3 >> 0 & 8) | (td0 >> 3 & 1) | (td1 >> 2 & 2));
			ta[5] = (byte)(pal | (td2 >> 0 & 4) | (td3 << 1 & 8) | (td0 >> 2 & 1) | (td1 >> 1 & 2));
			ta[6] = (byte)(pal | (td2 << 1 & 4) | (td3 << 2 & 8) | (td0 >> 1 & 1) | (td1 >> 0 & 2));
			ta[7] = (byte)(pal | (td2 << 2 & 4) | (td3 << 3 & 8) | (td0 >> 0 & 1) | (td1 << 1 & 2));
		}
		return ta;
	}

	/**
	 * returns the background attribute byte for a specified 
	 * pixel
	 */
	byte getBackgroundAttr(int px, int py) {
		return getAttr((px + scx) >> 3 & 0x1F, (py + scy) >> 3 & 0x1F, bg_mode);
	}
	
	int getTile(int tx, int ty, boolean map_mode) {
		if(chr_mode) {
			return vram[(map_mode ? 0x1C00 : 0x1800) + (ty * 32) + tx] & 0xFF;
		} else {
			return vram[(map_mode ? 0x1C00 : 0x1800) + (ty * 32) + tx] + 256;
		}

	}
	/**
	 * returns the background attribute byte for a specified 
	 * map at the specified map position
	 */
	byte getAttr(int tx, int ty, boolean map_mode) {
		return vram[(map_mode ? 0x3C00 : 0x3800) + (ty * 32) + tx];
	}
	
	byte[] getTileLineArray(int tx, int ty, int tileline, boolean map_mode) {
		int tilenum = getTile(tx, ty, map_mode);
		if(PgbSettings.system == PgbSettings.SYS_GBC) {
			int attribute = getAttr(tx, ty, map_mode);
			return getTileLineArray((attribute & 0x08) >> 3, tilenum, (attribute & 0x40) == 0x40 ? (7 - tileline) : tileline, (attribute & 0x20) == 0x20, (byte)((attribute & 0x07) << 3));
		} else {
			return getTileLineArray(0, tilenum, tileline);
		}
	}
	byte[] getTileLineArray(int bank, int tilenum, int tileline) {
		byte[] tla = new byte[8];
		copyTileLineArray((bank * 0x2000) + (tilenum * 16) + (tileline * 2), tla, 0);
		return tla;
	}
	byte[] getTileLineArray(int bank, int tilenum, int tileline, boolean hflip, byte orwith) {
		byte[] tla = new byte[8];
		copyTileLineArray((bank * 0x2000) + (tilenum * 16) + (tileline * 2), hflip, orwith, tla, 0);
		return tla;
	}
	
	void copyTileLineArray(int tx, int ty, int tileline, boolean map_mode, byte[] dest, int dest_pos) {
		int tilenum = getTile(tx, ty, map_mode);
		if(PgbSettings.system == PgbSettings.SYS_GBC) {
			int attribute = getAttr(tx, ty, map_mode);
			copyTileLineArray(((attribute & 0x08) >> 3) * 0x2000 + tilenum * 16 + ((attribute & 0x40) == 0x40 ? (7 - tileline) : tileline) * 2, (attribute & 0x20) == 0x20, (byte)((attribute & 0x07) << 3), dest, dest_pos);
			return;
		} else {
			copyTileLineArray((tilenum * 16) + (tileline * 2), dest, dest_pos);
			return;
		}
	}

	
	void copyTileLineArray(int vram_pos, byte[] dest, int dest_pos) {
		byte td0 = vram[vram_pos];
		byte td1 = vram[vram_pos + 1];
		dest[dest_pos++] = (byte)((td0 >> 7 & 1) | (td1 >> 6 & 2));
		dest[dest_pos++] = (byte)((td0 >> 6 & 1) | (td1 >> 5 & 2));
		dest[dest_pos++] = (byte)((td0 >> 5 & 1) | (td1 >> 4 & 2));
		dest[dest_pos++] = (byte)((td0 >> 4 & 1) | (td1 >> 3 & 2));
		dest[dest_pos++] = (byte)((td0 >> 3 & 1) | (td1 >> 2 & 2));
		dest[dest_pos++] = (byte)((td0 >> 2 & 1) | (td1 >> 1 & 2));
		dest[dest_pos++] = (byte)((td0 >> 1 & 1) | (td1 >> 0 & 2));
		dest[dest_pos++] = (byte)((td0 >> 0 & 1) | (td1 << 1 & 2));
	}
	void copyTileLineArray(int vram_pos, boolean hflip, byte orwith, byte[] dest, int dest_pos) {
		byte td0 = vram[vram_pos];
		byte td1 = vram[vram_pos + 1];
		if(hflip) {
			dest_pos += 7;
			dest[dest_pos--] = (byte)(orwith | (td0 >> 7 & 1) | (td1 >> 6 & 2));
			dest[dest_pos--] = (byte)(orwith | (td0 >> 6 & 1) | (td1 >> 5 & 2));
			dest[dest_pos--] = (byte)(orwith | (td0 >> 5 & 1) | (td1 >> 4 & 2));
			dest[dest_pos--] = (byte)(orwith | (td0 >> 4 & 1) | (td1 >> 3 & 2));
			dest[dest_pos--] = (byte)(orwith | (td0 >> 3 & 1) | (td1 >> 2 & 2));
			dest[dest_pos--] = (byte)(orwith | (td0 >> 2 & 1) | (td1 >> 1 & 2));
			dest[dest_pos--] = (byte)(orwith | (td0 >> 1 & 1) | (td1 >> 0 & 2));
			dest[dest_pos--] = (byte)(orwith | (td0 >> 0 & 1) | (td1 << 1 & 2));
		} else {
			dest[dest_pos++] = (byte)(orwith | (td0 >> 7 & 1) | (td1 >> 6 & 2));
			dest[dest_pos++] = (byte)(orwith | (td0 >> 6 & 1) | (td1 >> 5 & 2));
			dest[dest_pos++] = (byte)(orwith | (td0 >> 5 & 1) | (td1 >> 4 & 2));
			dest[dest_pos++] = (byte)(orwith | (td0 >> 4 & 1) | (td1 >> 3 & 2));
			dest[dest_pos++] = (byte)(orwith | (td0 >> 3 & 1) | (td1 >> 2 & 2));
			dest[dest_pos++] = (byte)(orwith | (td0 >> 2 & 1) | (td1 >> 1 & 2));
			dest[dest_pos++] = (byte)(orwith | (td0 >> 1 & 1) | (td1 >> 0 & 2));
			dest[dest_pos++] = (byte)(orwith | (td0 >> 0 & 1) | (td1 << 1 & 2));
		}
	}

	
	public void doSgbPalette(int line) {
		byte b;
		int i, ls;
		for(i = 0; i < 5; i++) {
			b = sgbPaletteOverlay[(line / 8) * 5 + i];
			ls = line * 160 + i * 32;
			tileLineOr(screenMemory, ls + 0, (byte)((b & 0xC0) >> 2));
			tileLineOr(screenMemory, ls + 8, (byte)((b & 0x30) >> 0));
			tileLineOr(screenMemory, ls + 16, (byte)((b & 0x0C) << 2));
			tileLineOr(screenMemory, ls + 24, (byte)((b & 0x03) << 4));
		}
	}
	void tileLineOr(byte[] src, int position, byte orwith) {
		src[position++] |= orwith;
		src[position++] |= orwith;
		src[position++] |= orwith;
		src[position++] |= orwith;
		src[position++] |= orwith;
		src[position++] |= orwith;
		src[position++] |= orwith;
		src[position++] |= orwith;
	}
	
	public void setBgPal(int pval) {
		super.setBgPal(pval);
		int i, j;
		// super gameboy
		if(PgbSettings.system == PgbSettings.SYS_SGB) {
			for(j = 0; j < 4; j++) {
				for(i = 0; i < 4; i++) {
					setScreenPalette(false, 0, j, i, sgbPalette[j * 8 + (pval >> (i * 2) & 3) * 2 + 1], sgbPalette[j * 8 + (pval >> (i * 2) & 3) * 2 + 0]);
				}
			}
			return;
		}
		// mono gameboy
		if(PgbSettings.system == PgbSettings.SYS_GB || PgbSettings.system == PgbSettings.SYS_GBP) {
			for(i = 0; i < 4; i++) {
				setScreenPalette(0x00 | i, PgbSettings.bgcolors[pval >> (i * 2) & 3]);
			}
		}
	}
	
	public void setObjPal0(int pval) {
		super.setObjPal0(pval);
		int i, j;
		objpal0 = pval;
		// super gameboy
		if(PgbSettings.system == PgbSettings.SYS_SGB) {
			for(j = 0; j < 4; j++) {
				for(i = 0; i < 4; i++) {
					setScreenPalette(true, 0, j, i, sgbPalette[j * 8 + (pval >> (i * 2) & 3) * 2 + 1], sgbPalette[j * 8 + (pval >> (i * 2) & 3) * 2 + 0]);
				}
			}
			return;
		}
		// mono gameboy
		if(PgbSettings.system == PgbSettings.SYS_GB || PgbSettings.system == PgbSettings.SYS_GBP) {
			for(i = 0; i < 4; i++) {
				setScreenPalette(0x04 | i, PgbSettings.obj0colors[pval >> (i * 2) & 3]);
			}
		}
	}
	
	public void setObjPal1(int pval) {
		super.setObjPal1(pval);
		int i, j;
		// super gameboy
		if(PgbSettings.system == PgbSettings.SYS_SGB) {
			for(j = 0; j < 4; j++) {
				for(i = 0; i < 4; i++) {
					setScreenPalette(true, 1, j, i, sgbPalette[j * 8 + (pval >> (i * 2) & 3) * 2 + 1], sgbPalette[j * 8 + (pval >> (i * 2) & 3) * 2 + 0]);
				}
			}
			return;
		}
		// mono gameboy
		if(PgbSettings.system == PgbSettings.SYS_GB || PgbSettings.system == PgbSettings.SYS_GBP) {
			for(i = 0; i < 4; i++) {
				setScreenPalette(0x0D | i, PgbSettings.obj1colors[pval >> (i * 2) & 3]);
			}
		}
	}
	
	public void gbcSetBgpd(byte data) {
		int p = bgpi >> 3 & 0x07, c = bgpi >> 1 & 0x03;
		super.gbcSetBgpd(data);
		setScreenPalette(false, p, 0, c, gbcPalette[0x00 + p * 8 + c * 2 + 1], gbcPalette[0x00 + p * 8 + c * 2 + 0]);
	}
	public void gbcSetObpd(byte data) {
		int p = obpi >> 3 & 0x07, c = obpi >> 1 & 0x03;
		super.gbcSetObpd(data);
		setScreenPalette(true, p, 0, c, gbcPalette[0x40 + p * 8 + c * 2 + 1], gbcPalette[0x40 + p * 8 + c * 2 + 0]);
	}
	
	/**
	 * sets a color based off flags and two bytes
	 */
	void setScreenPalette(boolean obj, int pal, int sgbPal, int color, byte hi, byte low) {
		int cindex;
		cindex = ((sgbPal & 0x03) << 4) | (obj ? 0x04 : 0x00) | ((pal & 0x07) << 3) | (color & 0x03);
		setScreenPalette(cindex, getColor32(hi, low));
	}
	
	/**
	 * sets a color based off index and 32-bit AARRGGBB value
	 */
	void setScreenPalette(int cindex, int color) {
		int acolor = colorAdjust(color);
		screenPalette[cindex] = acolor;
		screenRPal[cindex] = (byte)(acolor >> 16);
		screenGPal[cindex] = (byte)(acolor >> 8);
		screenBPal[cindex] = (byte)(acolor >> 0);
	}
	
	/**
	 * returns a 32-bit color, given the hi and low bytes
	 * of a 15-bit one
	 */
	int getColor32(byte hi, byte low) {
		int color15, color32;
		color15 = (hi << 8) | (low & 0xFF);
		color32 = 0xFF000000 | ((color15 & 0x001F) << 19) | ((color15 & 0x03E0) << 6) | ((color15 & 0x7C00) >> 7);
		return color32;
	}
	
	int colorAdjust(int color) {
		int red, green , blue;
		if(PgbSettings.colormute) {
			red = color >> 16 & 0xFF;
			green = color >> 8 & 0xFF;
			blue = color >> 0 & 0xFF;
			red = (int)((red - 128) * .75 + 144);
			green = (int)((green - 128) * .75 + 144);
			blue = (int)((blue - 128) * .75 + 144);
			return 0xFF000000 | (red << 16) | (green << 8) | blue;
		} else {
			return color;
		}
	}
	
	public void sgbSetPalette(int pal, int color, byte hi, byte low) {
		super.sgbSetPalette(pal, color, hi, low);
		setBgPal(bgpal);
		setObjPal0(objpal0);
		setObjPal1(objpal1);
	}
	public void sgbSetPaletteIndirect(int pal0, int pal1, int pal2, int pal3, int atf) {
		super.sgbSetPaletteIndirect(pal0, pal1, pal2, pal3, atf);
		setBgPal(bgpal);
		setObjPal0(objpal0);
		setObjPal1(objpal1);
	}
	public void sgbPictureTransfer() {
		int i;
		super.sgbPictureTransfer();
		// duhhh... set the border screenPalette
		for(i = 0; i < 64; i++) {
			setBorderPalette(i + 64, getColor32(sgbPicture[2048 + i * 2 + 1], sgbPicture[2048 + i * 2 + 0]));
		}
		doSgbBorder();
		/*
		borderColorModel = new IndexColorModel(8, 256, borderRPal, borderGPal, borderBPal, 0);
		borderMISrc.newPixels(borderPixels, borderColorModel, 0, 256);
		*/
	}
	void setBorderPalette(int cindex, int color) {
		borderPalette[cindex] = color;
		/*
		borderRPal[cindex] = (byte)(colorAdjust(color) >> 16);
		borderGPal[cindex] = (byte)(colorAdjust(color) >> 8);
		borderBPal[cindex] = (byte)(colorAdjust(color) >> 0);
		*/
	}


	
	
}
