package com.pixelutilitys.arcade.emulators.AEPgb;

/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information.
 * Copyright (C) 2000-2001 Ben Mazur
 * modified by retroK 2004 http://aep-emu.de/
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * PgbVideo is responsible for the GameBoy video hardware.
 * 
 * Additionally, some SGB memory and functions are contained 
 * here.
 * 
 * It is abstract because it only makes sure that the VRAM,
 * etc., contain the proper values.  PgbVideoOutput classes
 * depend on the getScreenPixels, etc methods
 */
public abstract class PgbVideo {
	static final int	VBLANK_CYCLES	= 109;
	static final int	HBLANK_CYCLES	= 49;
	static final int	OAM_CYCLES		= 20;
	static final int	TRANSFER_CYCLES	= 40;
	
	static final byte	STAT_HBLANK		= 0;
	static final byte	STAT_VBLANK		= 1;
	static final byte	STAT_OAM		= 2;
	static final byte	STAT_TRANSFER	= 3;
	
	static final byte	INT_LYC			= 0x40;
	static final byte	INT_OAM			= 0x20;
	static final byte	INT_HBLANK		= 0x10;
	static final byte	INT_VBLANK		= 0x08;
	static final byte	INT_LYCMODE		= 0x04;
	
	// output
	PgbVideoOutput		vidout;

	// registers
	public int			scx;
	public int			scy;
	public int			ly;
	public int			lyc;
	public int			wx;
	public int			wy;
	
	// lcdc
	public boolean		lcd_on;
	public boolean		win_on;
	public boolean		win_mode;
	public boolean		chr_mode;
	public boolean		bg_mode;
	public boolean		obj_mode;
	public boolean		obj_on;
	public boolean		bg_on;
	public int			win_src;
	public int			bg_src;
	public int			obj_siz;
	public int			chr_src;
	
	// stat
	public boolean		int_lyc;
	public boolean		int_oam;
	public boolean		int_hblank;
	public boolean		int_vblank;
	public byte			stat_mode;
	
	// counters
	int					cycles;
	int					curframe;
	public long			framecount;
	
	// memory
	byte[]				vram;
	byte[]				oam;
	
	// regular gb palettes
	public int			bgpal;
	public int			objpal0;
	public int			objpal1;
	
	// SGB memory
	boolean				sgbvramon;
	byte				sgbPicture[];
	byte				sgbCharset[];
	byte				sgbPalette[];
	byte				sgbSystemPalette[];
	byte				sgbAtfData[];
	byte				sgbPaletteOverlay[];
	
	// color gb
	byte[]				gbcPalette;
	byte				gbcVram;
	int					vramOffset;
	byte				bgpi;
	byte				obpi;
	
	public PgbVideo() {
		// setup ram
		vram = new byte[0x4000];
		oam = new byte[0xA0];
		// sgb
		sgbPicture = new byte[0x1000];
		sgbCharset = new byte[0x4000];
		sgbPalette = new byte[0x20];
		sgbSystemPalette = new byte[0x1000];
		sgbAtfData = new byte[0x1800];
		sgbPaletteOverlay = new byte[90];
		// gbc
		gbcPalette = new byte[0x80];
	}
	
	public void reset() {
		int i;
		
		// blank ram
		for(i = 0; i < 0x4000; i++) {
			vram[i] = 0;
		}
		for(i = 0; i < 0xA0; i++) {
			oam[i] = 0;
		}
		
		// registers
		setLcdc((byte)0x91);
		setStat((byte)0x80);
		scy = 0;
		scx = 0;
		wx = 0;
		wy = 0;
		ly = 0;
		lyc = 0;		
		
		cycles = 0;
		curframe = 0;
		framecount = 0;
		
		// bgpal
		setBgPal(0xFC);
		setObjPal0(0xFF);
		setObjPal1(0xFF);
		
		// super GB
		sgbvramon = false;
		
		// gbc
		vramOffset = 0x8000;
		gbcVram = (byte)0x00;
		
		bgpi = (byte)0x00;
		obpi = (byte)0x00;
	}
	
	public byte read(int address) {
		// VRAM
		if(address >= 0x8000 && address < 0xA000) {
			return vram[address - vramOffset];
		}
		// OAM
		if(address >= 0xFE00 && address < 0xFEA0) {
			return oam[address - 0xFE00];
		}
		System.out.println("Read from unmapped video memory:" + Integer.toHexString(address));
		return 0;
	}
	
	public void write(int address, byte towrite) {
		/*
		// SGB
		if(sgbvramon) {
			System.out.print(Integer.toHexString(address & 0xFFFF) + ":" + Integer.toHexString(towrite & 0xFF) + " ");
		}
		*/
		// VRAM
		if(address >= 0x8000 && address < 0xA000) {
			vram[address - vramOffset] = towrite;
			return;
		}
		// OAM
		if(address >= 0xFE00 && address < 0xFEA0) {
			oam[address - 0xFE00] = towrite;
			return;
		}
		System.out.println("Write to unmapped video memory:" + Integer.toHexString(address) + ", " + Integer.toHexString(towrite));
	}
	
	/**
	 * how many cycles until the next possible interrupt?
	 */
	public int cyclesLeft() {
		return cycles;
	}
	
	/**
	 * this is called to update the LCD cycles
	 */
	public int cycle(int cy, PgbMemory pgbmemory) {
		byte interrupt = 0;
		cycles -= cy;
		if(cycles <= 0) {
			if(stat_mode == STAT_HBLANK || stat_mode == STAT_VBLANK) {
				if(++ly > 0x100) {
					ly = 0;	
				}
				if(ly < 0x90) {
					hblank();
					stat_mode = STAT_OAM;
					cycles = (int)(OAM_CYCLES * PgbSettings.clockspeed);
					interrupt |= int_hblank ? PgbMemory.INT_LCD : 0;
				} else {
					stat_mode = STAT_VBLANK;
					cycles = (int)(VBLANK_CYCLES * PgbSettings.clockspeed);
					if(ly == 0x90) {
						vblank();
						// by retroK:
						pgbmemory.soundPlay();
						interrupt |= int_vblank ? (PgbMemory.INT_LCD | PgbMemory.INT_VBLANK) : PgbMemory.INT_VBLANK;
					}
				}
				// coincidence interrupt
				if(ly == lyc) {
					interrupt |= int_lyc ? PgbMemory.INT_LCD : 0;
				}
				return interrupt;
			}
			if(stat_mode == STAT_OAM) {
				stat_mode = STAT_TRANSFER;
				cycles = (int)(TRANSFER_CYCLES * PgbSettings.clockspeed);
				interrupt |= int_oam ? PgbMemory.INT_LCD : 0;
				return interrupt;
			}
			if(stat_mode == STAT_TRANSFER) {
				stat_mode = STAT_HBLANK;
				cycles = (int)(HBLANK_CYCLES * PgbSettings.clockspeed);
				return interrupt;
			}
		}
		return 0;
	}
	
	public void hblank() {
		//if(lcd_on && curframe == 0 && (ly & 1) == (framecount & 1)) {
		if(lcd_on && curframe == 0) {
			vidout.hblank(ly);
		}
	}
	
	public void vblank() {
		framecount++;
		curframe--;
		if(curframe < 0) {
			curframe = PgbSettings.frameskip;
			vidout.vblank();
		}
	}
	
	public void setVideoOutput(PgbVideoOutput vidout) {
		this.vidout = vidout;
	}
	
	public abstract byte[]	getScreenMemory();
	public abstract byte	getScreenMemory(int index);
	public abstract byte	getScreenRed(byte index);
	public abstract byte	getScreenGreen(byte index);
	public abstract byte	getScreenBlue(byte index);
	public abstract int		getScreenColor(byte index);
	
	public void setBgPal(int pval) {
		//System.out.println("Change background palette: " + Integer.toHexString(pval));
		bgpal = pval;
	}
	public void setObjPal0(int pval) {
		//System.out.println("Change obj 0 palette: " + Integer.toHexString(pval));
		objpal0 = pval;
	}
	public void setObjPal1(int pval) {
		//System.out.println("Change obj 1 palette: " + Integer.toHexString(pval));
		objpal1 = pval;
	}
	
	public void setLcdc(byte lval) {
		boolean old_lcd = lcd_on;
		lcd_on		= (lval & 0x80) == 0x80;
		win_mode	= (lval & 0x40) == 0x40;
		win_on		= (lval & 0x20) == 0x20;
		chr_mode	= (lval & 0x10) == 0x10;
		bg_mode		= (lval & 0x08) == 0x08;
		obj_mode	= (lval & 0x04) == 0x04;
		obj_on		= (lval & 0x02) == 0x02;
		bg_on		= (lval & 0x01) == 0x01;

		win_src		= win_mode ? 0x9C00 : 0x9800;
		chr_src		= chr_mode ? 0x8000 : 0x8800;
		bg_src		= bg_mode ? 0x9C00 : 0x9800;
		obj_siz		= obj_mode ? 16 : 8;
		
		// reset ly when LCD goes off to on?
		// docs are not very clear on this subject
		if(!old_lcd && lcd_on) {
			ly = 0;
			stat_mode = STAT_OAM;
			cycles = OAM_CYCLES;
		}
		if(!lcd_on) {
			ly = 0;
			stat_mode = STAT_HBLANK;
			cycles = HBLANK_CYCLES;
		}
	}
	public byte getLcdc() {
		byte lcdc = 0;
		lcdc |= lcd_on		? 0x80 : 0x00;
		lcdc |= win_mode	? 0x40 : 0x00;
		lcdc |= win_on		? 0x20 : 0x00;
		lcdc |= chr_mode	? 0x10 : 0x00;
		lcdc |= bg_mode		? 0x08 : 0x00;
		lcdc |= obj_mode	? 0x04 : 0x00;
		lcdc |= obj_on		? 0x02 : 0x00;
		lcdc |= bg_on		? 0x01 : 0x00;
		return lcdc;
	}
	
	public void setStat(byte sval) {
		int_lyc		= (sval & 0x40) == 0x40;
		int_oam		= (sval & 0x20) == 0x20;
		int_vblank	= (sval & 0x10) == 0x10;
		int_hblank	= (sval & 0x08) == 0x08;
	}
	public byte getStat() {
		byte stat = 0;
		stat |= int_lyc		? 0x40 : 0x00;
		stat |= int_oam		? 0x20 : 0x00;
		stat |= int_hblank	? 0x10 : 0x00;
		stat |= int_vblank	? 0x08 : 0x00;
		stat |= (ly == lyc)	? 0x04 : 0x00;
		stat |= stat_mode;
		return stat;
	}
	
	public void gbcSetVram(byte setting) {
		gbcVram = setting;
		vramOffset = ((gbcVram & 0x01) != 0) ? 0x6000 : 0x8000;
	}
	public byte gbcGetVram() {
		return gbcVram;
	}
	
	public void gbcSetBgpi(byte index) {
		//System.out.println("write to Color BG Palette Index:" + Integer.toHexString(index & 0xFF));
		bgpi = index;
	}
	public void gbcSetBgpd(byte data) {
		//System.out.println("write to Color BG Palette Data:" + Integer.toHexString(data & 0xFF));
		gbcPalette[bgpi & 0x3F] = data;
		if((bgpi & 0x80) == 0x80) {
			bgpi++;
		}
	}
	public void gbcSetObpi(byte index) {
		//System.out.println("write to Color OBJ Palette Index:" + Integer.toHexString(index & 0xFF));
		obpi = index;
	}
	public void gbcSetObpd(byte data) {
		//System.out.println("write to Color OBJ Palette Data:" + Integer.toHexString(data & 0xFF));
		gbcPalette[(obpi & 0x3F) + 0x40] = data;
		if((obpi & 0x80) == 0x80) {
			obpi++;
		}
	}
	public byte gbcGetBgpi() {
		//System.out.println("read Color BG Palette Index:" + Integer.toHexString(bgpi));
		return bgpi;
	}
	public byte gbcGetBgpd() {
		//System.out.println("read Color BG Palette Data:" + Integer.toHexString(gbcPalette[bgpi & 0x3F]));
		return gbcPalette[bgpi & 0x3F];
	}
	public byte gbcGetObpi() {
		//System.out.println("read Color OBJ Palette Index:" + Integer.toHexString(obpi));
		return obpi;
	}
	public byte gbcGetObpd() {
		//System.out.println("read Color OBJ Palette Data:" + Integer.toHexString(gbcPalette[(obpi & 0x3F) + 0x40]));
		return gbcPalette[(obpi & 0x3F) + 0x40];
	}
	
	public void sgbSetPalette(int pal, int color, byte hi, byte low) {
		sgbPalette[pal * 8 + color * 2 + 0] = low;
		sgbPalette[pal * 8 + color * 2 + 1] = hi;
	}
	public void sgbSetPaletteIndirect(int pal0, int pal1, int pal2, int pal3, int atf) {
		System.arraycopy(sgbSystemPalette, pal0 * 8, sgbPalette, 0, 8);
		System.arraycopy(sgbSystemPalette, pal1 * 8, sgbPalette, 8, 8);
		System.arraycopy(sgbSystemPalette, pal2 * 8, sgbPalette, 16, 8);
		System.arraycopy(sgbSystemPalette, pal3 * 8, sgbPalette, 24, 8);
		if(atf != 0) {
			sgbSetOverlayFromAtf(atf);
		}
	}
	public void sgbSetPaletteOverlay(int index, int palette) {
		int i = index / 4, o = (3 - (index & 3)) * 2;
		sgbPaletteOverlay[i] &= ~(byte)(0x03 << o);
		sgbPaletteOverlay[i] |= (byte)((palette & 0x03) << o);
	}

	public void sgbSetPaletteOverlayByte(int index, byte palette) {
		sgbPaletteOverlay[index] = palette;
	}

	public void sgbPictureTransfer() {
		System.arraycopy(vram, 0x800, sgbPicture, 0, 0x1000);
	}
	public void sgbCharsetTransfer(boolean type, boolean range) {
		System.arraycopy(vram, 0x0800, sgbCharset, range ? 0x1000 : 0, 0x1000);
	}
	public void sgbPaletteTransfer() {
		System.arraycopy(vram, 0x800, sgbSystemPalette, 0, 0x1000);
	}
	public void sgbAtfTransfer() {
		System.arraycopy(vram, 0x800, sgbAtfData, 0, 0x1800);
		sgbSetOverlayFromAtf(1);
	}
	
	public void sgbSetOverlayFromAtf(int atf) {
		System.arraycopy(sgbAtfData, (atf & 0x3F) * 90, sgbPaletteOverlay, 0, 90);
	}
	
	public void sgbBlockDesignate(byte control, byte palettes, byte startx, byte starty, byte endx, byte endy) {
		int x, y;
		byte in, on, out;
		boolean useIn, useOn, useOut;
		in		= (byte)((palettes & 0x03)     );
		on		= (byte)((palettes & 0x0C) >> 2);
		out		= (byte)((palettes & 0x30) >> 4);
		useIn	= (control & 0x01) == 0x01;
		useOn	= (control & 0x02) == 0x02;
		useOut	= (control & 0x04) == 0x04;
		if(PgbSettings.DEBUG) {
			System.out.println(" - sgb block[in:" + in + (useIn ? "+" : "-") + ", on:" + on + (useOn ? "+" : "-") + ", out:" + out + (useOut ? "+" : "-") + ", from(" + startx + ", " + starty + ") to(" + endx + ", " + endy + ")]");
		}
		// the ugliest code ever:
		for(y = 0; y < 18; y++) {
			for(x = 0; x < 20; x++) {
				if(y < starty || y > endy) {
					// y is outside the block
					if(useOut) {
						sgbSetPaletteOverlay(y * 20 + x, out);
					}
				}
				if(y == starty || y == endy) {
					// y is on the line
					if(x < startx || x > endx) {
						// but x is off the line
						if(useOut) {
							sgbSetPaletteOverlay(y * 20 + x, out);
						}
					} else {
						// and x is on the line
						if(useOn) {
							sgbSetPaletteOverlay(y * 20 + x, on);
						}
					}
				}
				if(y > starty && y < endy) {
					// y is inside the block
					if(x < startx || x > endx) {
						// but x is outside the block
						if(useOut) {
							sgbSetPaletteOverlay(y * 20 + x, out);
						}
					} else {
						if(x == startx || x == endx) {
							// and x is on the line
							if(useOn) {
								sgbSetPaletteOverlay(y * 20 + x, on);
							}
						} else {
							// and x is inside the block
							if(useIn) {
								sgbSetPaletteOverlay(y * 20 + x, in);
							}
						}
					}
				}
			}
		}
	}
	public void sgbLineDesignate(byte control) {
		int line, i;
		byte pal;
		boolean mode;
		line = (control & 0x1F);
		pal = (byte)((control & 0x60) >> 5);
		mode = (control & 0x80) == 0x80;
		if(mode) {
			// vertical line
			for(i = 0; i < 18; i++) {
				sgbSetPaletteOverlay(i * 20 + line, pal);
			}
		} else {
			// horizontal line
			for(i = 0; i < 20; i++) {
				sgbSetPaletteOverlay(line * 20 + i, pal);
			}
		}
	}

	public void sgbDivideDesignate(byte control, byte line) {
		int x, y;
		byte on, before, after;
		boolean mode;
		on = (byte)((control & 0x03)     );
		before = (byte)((control & 0x0C) >> 2);
		after = (byte)((control & 0x30) >> 4);
		mode = (control & 0x40) == 0x40;
		if(mode) {
			// divide vertical
			for(y = 0; y < 18; y++) {
				for(x = 0; x < 20; x++) {
					if(x < line) {
						sgbSetPaletteOverlay(y * 20 + x, before);
					}
					if(x == line) {
						sgbSetPaletteOverlay(y * 20 + x, on);
					}
					if(x > line) {
						sgbSetPaletteOverlay(y * 20 + x, after);
					}
				}
			}
		} else {
			// divide horizontal
			for(y = 0; y < 18; y++) {
				for(x = 0; x < 20; x++) {
					if(y < line) {
						sgbSetPaletteOverlay(y * 20 + x, before);
					}
					if(y == line) {
						sgbSetPaletteOverlay(y * 20 + x, on);
					}
					if(y > line) {
						sgbSetPaletteOverlay(y * 20 + x, after);
					}
				}
			}
		}
	}
	
	public void dumpGbcPalette() {
		OutputStream os;
		File dumpfile;
		
		dumpfile = new File("gbcPalette");
		
		try {
			os = new FileOutputStream(dumpfile);
			os.write(gbcPalette);
			
			os.close();
		} catch(Exception e) {
			System.out.println("error!");
			System.out.println(e.getMessage());
		}
	}
	public void dumpSgbPaletteOverlay() {
		OutputStream os;
		File dumpfile;
		
		dumpfile = new File("sgbPaletteOverlay");
		
		try {
			os = new FileOutputStream(dumpfile);
			os.write(sgbPaletteOverlay);
			
			os.close();
		} catch(Exception e) {
			System.out.println("error!");
			System.out.println(e.getMessage());
		}
	}
	public void dumpVram() {
		OutputStream os;
		File dumpfile;
		dumpfile = new File("vram");
		
		try {
			os = new FileOutputStream(dumpfile);
			os.write(vram);
			
			os.close();
		} catch(Exception e) {
			System.out.println("error!");
			System.out.println(e.getMessage());
		}
	}
}