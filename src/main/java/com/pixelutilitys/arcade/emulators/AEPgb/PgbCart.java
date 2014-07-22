package com.pixelutilitys.arcade.emulators.AEPgb;
/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information
 * Copyright (C) 2000-2001 Ben Mazur
 */

import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * PgbCart is responsible for the cartridge are of the
 * memory map.  It also contains functions to read
 * header data and to load and save carts and batteries.
 */
public final class PgbCart implements FilenameFilter {
	private static final int	C_NONE		= 0x00;
	private static final int	C_MBC1		= 0x01;
	private static final int	C_MBC2		= 0x06;
	private static final int	C_MMM01		= 0x0B;
	private static final int	C_MBC3		= 0x0F;
	private static final int	C_MBC5		= 0x19;
	
	private static final int	CLOCK_REL	= 0x00;
	private static final int	CLOCK_LATCH	= 0x01;
	private static final int	CLOCK_SEC	= 0x08;
	private static final int	CLOCK_MIN	= 0x09;
	private static final int	CLOCK_HRS	= 0x0A;
	private static final int	CLOCK_DAYL	= 0x0B;
	private static final int	CLOCK_DAYH	= 0x0C;
	
	public byte[]				romdata;
	public byte[]				ramdata;
	
	//public int					currom;
	//public int					curram;
	public int					romoffset;
	public int					ramoffset;
	
	public boolean				mbc1mode;
	public boolean				mbc1ramenabled;
	
	public boolean				mbc3clockenabled;
	public long					mbc3time;
	public int					mbc3reg;
	
	public void reset() {
		setRomBank(1);
		setRamBank(0);
		
		mbc1mode = true;
		mbc1ramenabled = false;
		
		mbc3clockenabled = false;
	}
	
	public final byte read(int address) {
		// ROM bank 0
		if(address < 0x4000) {
			return romdata[address];
		}
		// ROM bank 1 and beyond
		if(address < 0x8000) {
			return romdata[address + romoffset];
		}
		// cart RAM
		if(address < 0xC000) {
			if(!mbc3clockenabled) {
				return ramdata[address + ramoffset];
			} else {
				Calendar cal;
				cal = Calendar.getInstance();
				//System.out.println("Read MBC3 clock"); 
				switch(mbc3reg) {
				case 0x08: // second
					return (byte)cal.get(Calendar.SECOND);
				case 0x09: // minute
					return (byte)cal.get(Calendar.MINUTE);
				case 0x0A: // hour
					return (byte)cal.get(Calendar.HOUR);
				case 0x0B: // day low
					return (byte)cal.get(Calendar.DAY_OF_YEAR);
				case 0x0C: // day high
					return (byte)(cal.get(Calendar.DAY_OF_YEAR) >> 8);
				default:
					return 0;
				}
			}
		}
		System.out.println("Read from unmapped cart memory:" + Integer.toHexString(address)); 
		return 0;
	}
	
	/**
	 * write a byte to the cart.  This is how the various MBC
	 * registers are set and this is responsible for emulating
	 * them.
	 */
	public final void write(int address, int towrite) {
		// ram enable
		if(address < 0x2000) {
			//System.out.println("ram enable:" + towrite);
			mbc1ramenabled = (towrite & 0x0F) == 0x0A;
			return;
		}
		// rom select (ls byte)
		if(address < 0x3000) {
			if(PgbSettings.DEBUG && (towrite & 0xFF) > getRomBanks()) {
				System.out.println("rom select (ls byte):" + (towrite & 0xFF) + " (" + ((towrite & 0xFF) < getRomBanks() ? "valid" : "BAD!!") + ")");
			}
			if(getType() != C_MBC5 && towrite == 0) {
				// can't set ROM0 here
				setRomBank(1);
			} else {
				setRomBank(towrite & 0xFF);
			}
			return;
		}
		// rom select (ms byte on mbc5, ls byte otherwise)
		if(address < 0x4000) {
			if(getType() == C_MBC5) {
				//System.out.println("rom select (ms byte):" + towrite);
			} else {
				setRomBank(towrite);
			}
			return;
		}
		// ram select
		if(address < 0x6000) {
			if(towrite < 0x04) {
				if(PgbSettings.DEBUG && (towrite & 0xFF) > getRamBanks()) {
					System.out.println("ram switch:" + towrite + " (" + ((towrite & 0xFF) < getRamBanks() ? "valid" : "BAD!!") + ")");
				}
				setRamBank(towrite & 0xFF);
				mbc3clockenabled = false;
				return;
			} else {
				// clock select
				//System.out.println("MBC3 clock select:" + Integer.toHexString(towrite));
				mbc3reg = towrite;
				mbc3clockenabled = true;
				return;
			}
		}
		// RAM/ROM select (MBC1 only)
		if(address < 0x8000 && getType() == C_MBC1) {
			//System.out.println("RAM/ROM select (MBC1 only):" + Integer.toHexString(towrite & 0xFF) + " (" + (towrite & 1) + ")");
			mbc1mode = (towrite & 1) == 0;
			return;
		}
		// MBC3 clock latch/release
		if(address < 0x8000) {
			//System.out.println("MBC3 clock latch:" + towrite);
			mbc3time = System.currentTimeMillis();
			return;
		}
		// cart RAM
		if(address < 0xC000 && address >= 0xA000) {
			if(mbc1ramenabled) {
				ramdata[address + ramoffset] = (byte)towrite;
			} else {
				//System.out.println("attempted to write to disabled RAM");
			}
			return;
		}
		System.out.println("Write to unmapped cart memory:" + Integer.toHexString(address) + ", " + Integer.toHexString(towrite)); 
	}
	
	public boolean loaded() {
		return romdata != null && romdata.length > 0;
	}
	
	public void setRomBank(int bank) {
		//currom = select;
		romoffset = (bank - 1) * 0x4000;
	}
	
	public void setRamBank(int bank) {
		//curram = select;
		ramoffset = -0xA000 + bank * 0x2000;
	}
	
	public String getName() {
		// this doesn't work right.
		return new String(romdata, 134, 11);
	}
	
	/**
	 * read the number of ROM banks from the cart header
	 */
	public int getRomBanks() {
		switch(romdata[0x0148]) {
		case 0 : return 2;
		case 1 : return 4;
		case 2 : return 8;
		case 3 : return 16;
		case 4 : return 32;
		case 5 : return 64;
		case 6 : return 128;
		case 7 : return 256;
		default : return 2;
		}
	}
	
	/**
	 * read the number of RAM banks from the cart header
	 */
	public int getRamBanks() {
		switch(romdata[0x0149]) {
		case 0 : return 0;
		case 1 : return 1;
		case 2 : return 1;
		case 3 : return 4;
		case 4 : return 16;
		case 5 : return 32;
		default : return 0;
		}
	}
	
	/**
	 * is this cart Super Gameboy compatible?
	 */
	public boolean getSgb() {
        return loaded() && romdata[0x0146] == 3;
	}

	/**
	 * is this cart Gameboy Color compatible?
	 */
	public boolean getGbc() {
        return loaded() && ((romdata[0x0143] & 0xFF) == 0x80 || (romdata[0x0143] & 0xFF) == 0xC0);
	}

	public int getType() {
		int type = romdata[0x0147] & 0xFF;
		if(type >= 0x01 && type <= 0x03) {
			return C_MBC1;
		}
		if(type == 0x06) {
			return C_MBC2;
		}
		if(type >= 0x0B && type <= 0x0D) {
			return C_MMM01;
		}
		if(type >= 0x0F && type <= 0x13) {
			return C_MBC3;
		}
		if(type >= 0x19 && type <= 0x1E) {
			return C_MBC5;
		}
		
		return C_NONE;
	}
	public String getTypeString() {
		switch(getType()) {
		case C_MBC1 :
			return "MBC1";
		case C_MBC2 :
			return "MBC2";
		case C_MMM01 :
			return "MMM01";
		case C_MBC3 :
			return "MBC3";
		case C_MBC5 :
			return "MBC5";
		default :
			return "NONE";
		}
	}
	
	/**
	 * loads a gb file, given a path and a filename
	 */
	public boolean load(String path, String filename) {
        return load(filename);
    }
	
	/**
	 * loads a file in .gb or .zip format, then tries to
	 * load a battery file, if necessary
	 */
	public boolean load(String filename) {
		if(filename.length() < 1) {
			return false;
		}
		if(accept(null, filename)) {
			loadGB(filename);
			loadBattery(filename.substring(0, filename.lastIndexOf('.')) + ".sav");
			System.out.println("");
			return true;
		}
		if(filename.endsWith(".zip")) {
			if(loadZip(filename)) {
				loadBattery(filename.substring(0, filename.lastIndexOf('.')) + ".sav");
				System.out.println("");
				return true;
			}
		}
		System.out.println("File format not recognized.");
		return false;
	}

	/**
	 * simplified loadingcode for applet usage 
	 */
	public void loadApplet(String filename, Applet a) {
		InputStream is = null;
		try {
			is = new URL(a.getDocumentBase(), filename).openStream();
		} catch (IOException e) {
			System.out.println("Error opening rom");
		}
		romdata = new byte[255000];
		loadCart(is,255000);
	}
	
	/**
	 * sends a GB formatted file (raw cart dump) to loadCart
	 */
	public void loadGB(String filename) {
		InputStream is;
		File romfile;
		
		romfile = new File(filename);
		romdata = new byte[(int)romfile.length()];
		
		System.out.println("\nLoading cart '" + romfile.getName() + "', size:" + romfile.length() + "...");
		try {
			is = new FileInputStream(romfile);
		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
			System.out.println(e.getMessage());
			romdata = new byte[0x8000];
			return;
		}
		loadCart(is, (int)romfile.length());
	}
	
	/**
	 * searches for the first acceptable file in a .zip
	 * archive and sends it to loadCart
	 */
	public boolean loadZip(String filename) {
		File			romfile;
		ZipEntry		ze;
		ZipInputStream	zis;
		
		romfile = new File(filename);
		System.out.println("\nLoading zip file '" + romfile.getName() + "'...");
		try {
			zis = new ZipInputStream(new FileInputStream(romfile));
			while((ze = zis.getNextEntry()) != null) {  // or until out of files
				if(accept(null, ze.getName())) {
					System.out.println("Loading entry '" + ze.getName() + "', size : " + ze.getSize() + "...");
					break;
				}
			}
			loadCart(zis, (int) ze.getSize());
			return true;
 		} catch(Exception ex) {
			System.out.println("File not found!");
			System.out.println(ex.getMessage());
			return false;
		}
		
	}
	
	/**
	 * loads a cartridge from the InputStream it is given
	 */
	public void loadCart(InputStream is, int length) {
		try {
			romdata = new byte[length];
 			int nRead, count = 0;
 			while((length > 0) && ((nRead = is.read(romdata, count, length)) != -1)) {
 				count += nRead;
 				length -= nRead;
 			}
			
 			is.close();
		} catch(Exception e) {
			System.out.println("File read error:");
			System.out.println(e.getMessage());
		}
		System.out.println("rom byte: " + Integer.toHexString(romdata[0x0148]) + ", banks: " + getRomBanks());
		System.out.println("ram byte: " + Integer.toHexString(romdata[0x0149]) + ", banks: " + getRamBanks());
		System.out.println("type byte: " + Integer.toHexString(romdata[0x0147]) + " (" + getTypeString() + ")" + ", SGB features: " + Integer.toHexString(romdata[0x146] & 0xFF) + " (" + getSgb() + "), GBC features: " + Integer.toHexString(romdata[0x143] & 0xFF) + " (" + getGbc() + ")");
	}
	
	/**
	 * if there are any RAM banks, this allocates memory
	 * for them and tries to load saved RAM from disk
	 */
	public void loadBattery(String filename) {
		InputStream is;
		File ramfile;
		String ramdesc;
		// create & load ram
		if(getRamBanks() > 0) {
			ramdata = new byte[0x2000 * getRamBanks()];
			ramfile = new File(filename);
			ramdesc = "Looking for battery save... ";

			try {
				if(ramfile.exists()) {
					ramdesc += "loading... ";
					is = new FileInputStream(ramfile);
					
					loadBattery(is);
					
					ramdesc += "done.";
				} else {
					ramdesc += "not found.";
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			// print some ram info
			// System.out.println("ramdesc: "+ramdesc);
		} else {
			ramdata = new byte[0x2000];
		}
	}
	
	/**
	 * reads battery-backed RAM from an InputStream
	 */
	public void loadBattery(InputStream is) {
		try {
			is.read(ramdata);
			is.close();
		} catch(Exception e) {
			System.out.println("File read error:");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * if there is any battery-backed save RAM allocated, this
	 * saves it to the disk, in the save directory specified
	 * in the settings
	 */
	public void saveBattery(String romfilename) {
		if(!loaded() || getRamBanks() == 0) {
			return;
		}
		OutputStream os;
		File ramfile;
		
		ramfile = new File(romfilename.substring(0, romfilename.lastIndexOf('.')) + ".sav");
		
		try {
			
			os = new FileOutputStream(ramfile);
			os.write(ramdata);
			
			os.close();
			
			System.out.println("Saving ram to file... done.");
		} catch(Exception e) {
			System.out.println("Saving ram to file... error!");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * tests if a file extension is in an acceptable format
	 */
	public boolean accept(File file, String filename) {
		return (filename.toLowerCase().endsWith(".gb") || filename.toLowerCase().endsWith(".gbc") || filename.toLowerCase().endsWith(".cgb") || filename.toLowerCase().endsWith(".sgb"));
	}
}
