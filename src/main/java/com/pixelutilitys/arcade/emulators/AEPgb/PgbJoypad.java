package com.pixelutilitys.arcade.emulators.AEPgb;

/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information.
 * Copyright (C) 2000-2001 Ben Mazur
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PgbJoypad implements KeyListener {
	protected static final int	C_RIGHT		= 0x01;
	protected static final int	C_LEFT		= 0x02;
	protected static final int	C_UP		= 0x04;
	protected static final int	C_DOWN		= 0x08;
	protected static final int	B_A			= 0x01;
	protected static final int	B_B			= 0x02;
	protected static final int	B_SELECT	= 0x04;
	protected static final int	B_START		= 0x08;
	
	
	public boolean				c_right;
	public boolean				c_left;
	public boolean				c_up;
	public boolean				c_down;
	public boolean				b_a;
	public boolean				b_b;
	public boolean				b_select;
	public boolean				b_start;
	
	public byte					lastpoll;
	public byte					selected;
	public byte					player;
	
	public byte					gbtype;
	
	public void reset() {
		c_right = false;
		c_left = false;
		c_up = false;
		c_down = false;
		b_a = false;
		b_b = false;
		b_select = false;
		b_start = false;
		
		if(PgbSettings.system == PgbSettings.SYS_SGB) {
			gbtype = (byte)0x00;
		} else {
			gbtype = (byte)0xC0;
		}
		
		player = 0;
	}
	
	public void write(byte towrite) {
		selected = (byte)(towrite & 0x30);
	}
	
	public byte read() {
		int low;
		low = 0;
		switch(selected) {
		case 0x20 :
			// cursor
			low |= c_right	? C_RIGHT	: 0;
			low |= c_left	? C_LEFT	: 0;
			low |= c_up		? C_UP		: 0;
			low |= c_down	? C_DOWN	: 0;
			break;
		case 0x10 :
			// buttons
			low |= b_a		? B_A		: 0;
			low |= b_b		? B_B		: 0;
			low |= b_select	? B_SELECT	: 0;
			low |= b_start	? B_START	: 0;
			break;
		case 0x30:
			// SGB kludge
			low = player == 0 ? 0x00 : 0x01;
			break;
		}
		low = ~low;
		low &= 0x0F;
		
		return (byte)(gbtype | selected | low);
	}
	
	public boolean changed() {
		byte	poll;
		boolean	res;
		
		poll = 0;
		if((selected & 0x20) == 0) {
			// cursor
			poll |= c_right	? C_RIGHT	: 0;
			poll |= c_left	? C_LEFT	: 0;
			poll |= c_up	? C_UP		: 0;
			poll |= c_down	? C_DOWN	: 0;
		}
		if((selected & 0x10) == 0) {
			// buttons
			poll |= b_a		? B_A		: 0;
			poll |= b_b		? B_B		: 0;
			poll |= b_select? B_SELECT	: 0;
			poll |= b_start	? B_START	: 0;
		}
		
		res = poll != lastpoll;
		lastpoll = poll;
		return res;
	}
	
	public void setSgbPlayer(byte player) {
		this.player = player;
	}
	
	
	/**
	 * KeyListener
	 */
	public void keyPressed(KeyEvent ev) {
		if(ev.getKeyCode() == PgbSettings.key_right) {
			c_right = true;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_left) {
			c_left = true;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_up) {
			c_up = true;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_down) {
			c_down = true;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_a) {
			b_a = true;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_b) {
			b_b = true;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_select) {
			b_select = true;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_start) {
			b_start = true;
			return;
		}
	}
	public void keyReleased(KeyEvent ev) {
		if(ev.getKeyCode() == PgbSettings.key_right) {
			c_right = false;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_left) {
			c_left = false;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_up) {
			c_up = false;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_down) {
			c_down = false;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_a) {
			b_a = false;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_b) {
			b_b = false;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_select) {
			b_select = false;
			return;
		}
		if(ev.getKeyCode() == PgbSettings.key_start) {
			b_start = false;
			return;
		}
	}
	public void keyTyped(KeyEvent ev) {
		;
	}
}
