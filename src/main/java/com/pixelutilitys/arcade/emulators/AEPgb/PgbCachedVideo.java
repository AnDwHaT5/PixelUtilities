package com.pixelutilitys.arcade.emulators.AEPgb;
/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information
 * Copyright (C) 2000-2001 Ben Mazur
 */

/**
 * PgbCachedIndexedVideo is a subclass of PgbIndexedVideo
 * that keeps copies of the tiles, background maps and
 * sprites cached.
 * 
 * It should be faster in most circumstances, as it does not
 * go back to the memory every time a tile is accessed.
 * Particularly, it does not have to blend the two display 
 * bytes together for each line in the tile.
 */
public class PgbCachedVideo extends PgbBasicVideo {
	
	// here's the memory
	byte[][][][]	tiles;
	byte[][][]		map;
	byte[][][]		sprites;
	int				lastTile;
	
	public PgbCachedVideo() {
		tiles = new byte[2][384][8][8]; // [bank][num][y][x]
		map = new byte[4][256][256];  // map, y, x
		sprites = new byte[40][16][8];
	}
	
	/**
	 * the heart of the operation.  monitors VRAM writes
	 * and updates the appropriate caches
	 */
	public void write(int address, byte towrite) {
		super.write(address, towrite);
		// tile storage
		if(address >= 0x8000 && address < 0x9800) {
			// this updates tiles in almost all circumstances
			checkTile(address);
		}
		// tile map at 9800
		else if(address >= 0x9800 && address < 0x9C00) {
			updateMap(0, (address - 0x9800) & 0x1F, (address - 0x9800) >> 5, vram[address - 0x8000] + 256);
			updateMap(1, (address - 0x9800) & 0x1F, (address - 0x9800) >> 5, vram[address - 0x8000] & 0xFF);
		}
		// tile map at 9C00
		else if(address >= 0x9C00 && address < 0xA000) {
			updateMap(2, (address - 0x9C00) & 0x1F, (address - 0x9C00) >> 5, vram[address - 0x8000] + 256);
			updateMap(3, (address - 0x9C00) & 0x1F, (address - 0x9C00) >> 5, vram[address - 0x8000] & 0xFF);
	    }
		// OAM
		else if(address >= 0xFE00 && address < 0xFEA0) {
			if((address & 0x02) == 0x02) {
				updateObj((address - 0xFE00) >> 2);
			}
		}
	}
	
	/**
	 * override to draw from the cache
	 */
	void doBgLine(int line) {
		int bgline, middle;
		byte[] srcmap;
		
		bgline = (line + scy) & 0xFF;
		srcmap = map[(chr_mode ? 1 : 0) + (bg_mode ? 2 : 0)][bgline];

		if(scx < 97) {
			System.arraycopy(srcmap, scx, screenMemory, line * 160, 160);
		} else {
			middle = 256 - scx;
			System.arraycopy(srcmap, scx, screenMemory, line * 160, middle);
			System.arraycopy(srcmap, 0, screenMemory, line * 160 + middle, 160 - middle);
		}
	}
	
	/**
	 * override to draw from the cache
	 */
	void doWinLine(int line) {
		if(wy > line || wx > 166) {
			return;
		}
		byte[] srcmap = map[(chr_mode ? 1 : 0) + (win_mode ? 2 : 0)][line - wy];
		int ws, ss, wl;
		if(wx < 7) {
			ws = 7 - wx;
			ss = 0;
			wl = 160 - ws;
		} else {
			ws = 0;
			ss = wx - 7;
			wl = 160 - ss;
		}
		System.arraycopy(srcmap, ws, screenMemory, line * 160 + ss, wl);
	}
	
	/**
	 * override to draw from cache
	 */
	void doObjLine(int line) {
		int s, so, sx, sy;
		for(s = 39; s >= 0; s--) {
			so = s * 4;
			sx = oam[so + 1] & 0xFF;
			sy = oam[so] & 0xFF;
			if((sx > 0 && sy > 0) && sy <= line + 16 && sy > line + (16 - obj_siz)) {
				// draw
				boolean shidden = (oam[so + 3] & 0x80) == 0x80;
				byte[] ssrc = sprites[s][line - sy + 16];
				for(int p = 0; p < 8; p++) {
					// grrr... transparency...
					int spx = sx - 8 + p;
					byte sp = ssrc[p];
					if((sp & 0x03) > 0  && spx < 160 && spx >= 0 
					   && (!(shidden || (getBackgroundAttr(spx, line) & 0x80) == 0x80) 
						   || ((screenMemory[line * 160 + spx] & 0x03) == 0))) {
						screenMemory[line * 160 + spx] = sp;
					}
				}
			}
		}
	}
	
	/**
	 * updates the screen maps at the specified coordinates
	 */
	void updateMap(int which, int x, int y, int tilenum) {
		// draw the tile on the map
		if(PgbSettings.system == PgbSettings.SYS_GBC) {
			// stupid GBC tile routine
			int i, mx, my, ty;
			int attribute = getAttr(x, y, (which & 2) == 2);//(which & 2) == 2 ? vram[0x3C00 + x + y * 32] : vram[0x3800 + x + y * 32];
			byte pal = (byte)((attribute & 0x07) << 3);
			int bank = (attribute & 0x08) == 0x08 ? 1 : 0;
			for(i = 0; i < 8; i++) {
				my = y * 8 + i;
				mx = x * 8;
				ty = ((attribute & 0x40) == 0x40) ? 7 - i : i;
				if((attribute & 0x20) == 0x20){
					// hflip
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][7]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][6]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][5]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][4]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][3]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][2]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][1]);
					map[which][my][mx] = (byte)(pal | tiles[bank][tilenum][ty][0]);
				} else {
					// !hflip
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][0]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][1]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][2]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][3]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][4]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][5]);
					map[which][my][mx++] = (byte)(pal | tiles[bank][tilenum][ty][6]);
					map[which][my][mx] = (byte)(pal | tiles[bank][tilenum][ty][7]);
				}
			}
		} else {
			// mono tile copy
			int mx = x * 8;
			int my = y * 8;
			System.arraycopy(tiles[0][tilenum][0], 0, map[which][my++],mx, 8);
			System.arraycopy(tiles[0][tilenum][1], 0, map[which][my++],mx, 8);
			System.arraycopy(tiles[0][tilenum][2], 0, map[which][my++],mx, 8);
			System.arraycopy(tiles[0][tilenum][3], 0, map[which][my++],mx, 8);
			System.arraycopy(tiles[0][tilenum][4], 0, map[which][my++],mx, 8);
			System.arraycopy(tiles[0][tilenum][5], 0, map[which][my++],mx, 8);
			System.arraycopy(tiles[0][tilenum][6], 0, map[which][my++],mx, 8);
			System.arraycopy(tiles[0][tilenum][7], 0, map[which][my],mx, 8);
		}
	}
	
	/**
	 * updates the specified sprite cache
	 */
	public void updateObj(int onum) {
		int tnum = oam[(onum << 2) + 2] & (obj_mode ? 0xFE : 0xFF);
		byte attr = oam[(onum << 2) + 3];
		int bank;
		byte pal;
		if(PgbSettings.system == PgbSettings.SYS_GBC) {
			bank = attr >> 3 & 0x01;
			pal = (byte)((attr << 3 & 0x38) | 0x04);
		} else {
			bank = 0;
			pal = (byte)((attr >> 1 & 0x08) | 0x04);
		}
		boolean vflip = (attr & 0x40) == 0x40;
		boolean hflip = (attr & 0x20) == 0x20;
		int ts = (tnum * 16) + (0x2000 * bank);
		for(int y = 0; y < obj_siz; y++) {
			int ty = vflip ? obj_siz - y - 1 : y;
			copyTileLineArray(ts + ty * 2, hflip, pal, sprites[onum][y], 0);
		}
		/*
		tnum += vflip ? 1 : 0;
		for(int y = 0; y < 8; y++) {
			int ty = vflip ? 7 - y : y;
			for(int x = 0; x < 8; x++) {
				int tx = hflip ? 7 - x : x;
				sprites[onum][y][x] = (byte)(pal | tiles[bank][tnum][ty][tx]);
			}
		}
		if(obj_mode) {
			tnum += vflip ? -1 : 1;
			for(int y = 8; y < 16; y++) {
				int ty = vflip ? 15 - y : y - 8;
				for(int x = 0; x < 8; x++) {
					int tx = hflip ? 7 - x : x;
					sprites[onum][y][x] = (byte)(pal | tiles[bank][tnum][ty][tx]);
				}
			}
		}
		*/
	}
	
	/**
	 * check if we need to redraw this tile
	 * this isn't exactly perfect, but it'll do.
	 */
	void checkTile(int address) {
		int tilenum = (address - 0x8000) / 16;
		if((address & 0x0F) == 0x0F) {
			// if it's the last byte of a tile
			doTile(tilenum, gbcVram & 0x01);
		} else {
			// if they've left one off in the middle
			if(tilenum != lastTile) {
				doTile(lastTile, gbcVram & 0x01);
			}
		}
		lastTile = tilenum;
	}
	
	/**
	 * updates the tile cache
	 */
	void doTile(int tilenum, int bank) {
		int ts = tilenum * 16 + 0x2000 * bank;
		copyTileLineArray(ts    , tiles[bank][tilenum][0], 0);
		copyTileLineArray(ts + 2, tiles[bank][tilenum][1], 0);
		copyTileLineArray(ts + 4, tiles[bank][tilenum][2], 0);
		copyTileLineArray(ts + 6, tiles[bank][tilenum][3], 0);
		copyTileLineArray(ts + 8, tiles[bank][tilenum][4], 0);
		copyTileLineArray(ts + 10, tiles[bank][tilenum][5], 0);
		copyTileLineArray(ts + 12, tiles[bank][tilenum][6], 0);
		copyTileLineArray(ts + 14, tiles[bank][tilenum][7], 0);

		scanUpdateTile(tilenum);
	}
	
	/**
	 * Scans the screen maps and updates a tile when tile
	 * data is changed.  
	 * 
	 * This is sort of the weakness of the tile/map caching 
	 * scheme -- whenever a tile is changed, you must then
	 * update all instances of the tile in the map.  This
	 * makes it pretty easy, tho.
	 */
	void scanUpdateTile(int tilenum) {
		byte tn = (byte)(tilenum);
		for(int i = 0x1800; i < 0x1C00; i++) {
			if(vram[i] == tn) {
				if(tilenum > 127) {
					updateMap(0, (i - 0x1800) & 0x1F, (i - 0x1800) >> 5, tilenum);
				}
				if(tilenum < 256) {
					updateMap(1, (i - 0x1800) & 0x1F, (i - 0x1800) >> 5, tilenum);
				}
			}
		}
		for(int i = 0x1C00; i < 0x2000; i++) {
			if(vram[i] == tn) {
				if(tilenum > 127) {
					updateMap(2, (i - 0x1C00) & 0x1F, (i - 0x1C00) >> 5, tilenum);
				}
				if(tilenum < 256) {
					updateMap(3, (i - 0x1C00) & 0x1F, (i - 0x1C00) >> 5, tilenum);
				}
			}
		}
	}
}
