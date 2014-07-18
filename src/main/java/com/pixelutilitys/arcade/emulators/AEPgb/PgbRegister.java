package com.pixelutilitys.arcade.emulators.AEPgb;

/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information.
 * Copyright (C) 2000-2001 Ben Mazur
 */

/**
 * Interface for CPU registers
 */
interface PgbRegister {
	
	/**
	 * Return the high byte of the register.
	 */
	public int getH();
	
	/**
	 * Return the low byte of the register.
	 */
	public int getL();
	
	/**
	 * Return the 16-bit register word.
	 */
	public int getR();
	
	/**
	 * Gets the value from memory at the address specified
	 * by this register word.
	 * 
	 * @param mem		the memory object to read from.
	 */
	public int getI(PgbMemory mem);
	
	/**
	 * Sets the high byte of the register.
	 */
	public void setH(byte val);
	
	/**
	 * Sets the low byte of the register.
	 */
	public void setL(byte val);
	
	/**
	 * Sets the high byte of the register.
	 */
	public void setH(int val);
	
	/**
	 * Sets the high byte of the register.
	 */
	public void setL(int val);
	
	/**
	 * Sets the 16-bit register word.
	 */
	public void setR(int val);
	
	/**
	 * Sets the value into memory, at the address specified
	 * by this register word.
	 * 
	 * @param mem		the memory object to write to.
	 */
	public void setI(int val, PgbMemory mem);
}

/**
 * Optimized for register byte access.  Methods are final
 * and should be inlined.
 */
final class PgbRegisterB implements PgbRegister{
	public int hi;
	public int lo;
	
	public PgbRegisterB() {
		hi = 0;
		lo = 0;
	}
	public PgbRegisterB(int data) {
		setR(data);
	}
	
	public final int getH() {
		return hi;
	}
	public final int getL() {
		return lo;
	}
	public final int getI(PgbMemory mem) {
		return mem.read(getR()) & 0xFF;
	}
	public final int getR() {
		return hi << 8 | lo;
	}
	
	public final void setH(byte val) {
		hi = val & 0xFF;
	}
	public final void setL(byte val) {
		lo = val & 0xFF;
	}
	public final void setH(int val) {
		if(PgbSettings.DEBUG) {
			if(val < 0 || val > 0xFF) {
				System.out.println("bad value set into high: " + Integer.toHexString(val));
				PgbSettings.paused = true;
			}
		}
		hi = val;
	}
	public final void setL(int val) {
		if(PgbSettings.DEBUG) {
			if(val < 0 || val > 0xFF) {
				System.out.println("bad value set into low: " + Integer.toHexString(val));
				PgbSettings.paused = true;
			}
		}
		lo = val;
	}
	public final void setI(int val, PgbMemory mem) {
		if(PgbSettings.DEBUG) {
			if(val < 0 || val > 0xFF) {
				System.out.println("bad value set into indirect: " + Integer.toHexString(val));
				PgbSettings.paused = true;
			}
		}
		mem.write(getR(), val);
	}
	public final void setR(int val) {
		if(PgbSettings.DEBUG) {
			if(val < 0 || val > 0xFFFF) {
				System.out.println("bad value set into register: " + Integer.toHexString(val));
				PgbSettings.paused = true;
			}
		}
		hi = val >> 8;
		lo = val & 0x00FF;
	}
}

/**
 * Register implementation that is optimized for register 
 * word access.
 */
final class PgbRegisterW implements PgbRegister{
	public int data;
	
	public PgbRegisterW() {
		data = 0;
	}
	public PgbRegisterW(int data) {
		this.data = data;
	}
	
	public final int getH() {
		return data >> 8;
	}
	public final int getL() {
		return data & 0xFF;
	}
	public final int getI(PgbMemory mem) {
		return mem.read(data) & 0xFF;
	}
	public int getR() {
		return data;
	}	
	
	public final void setH(byte val) {
		setH(val & 0xFF);
	}
	public final void setL(byte val) {
		setL(val & 0xFF);
	}
	public final void setH(int val) {
		if(PgbSettings.DEBUG) {
			if(val < 0 || val > 0xFF) {
				System.out.println("bad value set into high: " + Integer.toHexString(val));
				PgbSettings.paused = true;
			}
		}
		data = val << 8 | getL();
	}
	public final void setL(int val) {
		if(PgbSettings.DEBUG) {
			if(val < 0 || val > 0xFF) {
				System.out.println("bad value set into low: " + Integer.toHexString(val));
				PgbSettings.paused = true;
			}
		}
		data = (data & 0xFF00) | val & 0xFF;
	}
	public final void setI(int val, PgbMemory mem) {
		if(PgbSettings.DEBUG) {
			if(val < 0 || val > 0xFF) {
				System.out.println("bad value set into indirect: " + Integer.toHexString(val));
				PgbSettings.paused = true;
			}
		}
		mem.write(data, val);
	}
	public final void setR(int val) {
		if(PgbSettings.DEBUG) {
			if(val < 0 || val > 0xFFFF) {
				System.out.println("bad value set into register: " + Integer.toHexString(val));
				PgbSettings.paused = true;
			}
		}
		data = val;
	}
}

