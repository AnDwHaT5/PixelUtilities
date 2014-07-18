package com.pixelutilitys.arcade.emulators.AEPgb;

/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information.
 * Copyright (C) 2000-2001 Ben Mazur
 */

import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

public class PgbMenuBar extends MenuBar {

	public Menu filemenu, cpumenu, optionsmenu;
	public Menu dblevelmenu;
	public Menu frameskipmenu,
		prioritymenu,
		videomenu,
		soundmenu,
		serialmenu,
		systemmenu;
	public Menu vidoutmenu;
	public MenuItem file_load, file_exit, file_about;
	public MenuItem cpu_reset, cpu_runto;
	public MenuItem options_setkeys, options_setsavepath;
	public CheckboxMenuItem cpu_paused;
	public CheckboxMenuItem debug_0,
		debug_1,
		debug_2,
		debug_3,
		debug_4,
		debug_5;
	public CheckboxMenuItem auto_wait, fs_0, fs_1, fs_2, fs_3, fs_4, fs_5;

	// by retroK
	public CheckboxMenuItem soundChannel1Enable,
		soundChannel2Enable,
		soundChannel3Enable,
		soundChannel4Enable;
	public CheckboxMenuItem soundFreq11, soundFreq22, soundFreq44;
	public CheckboxMenuItem soundBuffer200, soundBuffer300, soundBuffer400;
	// end by retroK
	
	public CheckboxMenuItem priority_low,
		priority_normal,
		priority_high,
		priority_max;
	public CheckboxMenuItem size_1,
		size_2,
		size_3,
		size_4,
		color_mute,
		sgb_border;
	public CheckboxMenuItem sys_gb, sys_gbp, sys_sgb, sys_gbc, sound;
	public MenuItem serial_connect;
	public CheckboxMenuItem vidout_default,
		vidout_directx,
		vidout_indexed,
		vidout_direct;

	public PgbMenuBar(ActionListener al, ItemListener il) {
		super();

		filemenu = new Menu("File");
		filemenu.addActionListener(al);

		file_load = new MenuItem("Open...", new MenuShortcut(KeyEvent.VK_O));
		file_load.setActionCommand("file_load");
		file_exit = new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_X));
		file_exit.setActionCommand("file_exit");

		file_about = new MenuItem("About");
		file_about.setActionCommand("file_about");

		cpumenu = new Menu("CPU");
		cpumenu.addActionListener(al);

		cpu_reset = new MenuItem("Reset");
		cpu_reset.setActionCommand("cpu_reset");
		cpu_paused = new CheckboxMenuItem("Pause", true);
		cpu_paused.setShortcut(new MenuShortcut(KeyEvent.VK_P));
		cpu_paused.setActionCommand("cpu_paused");
		cpu_paused.addItemListener(il);
		cpu_runto = new MenuItem("Run To...");
		cpu_runto.setActionCommand("cpu_runto");
		cpu_runto.setEnabled(false);

		dblevelmenu = new Menu("Debug Level");
		dblevelmenu.addActionListener(al);

		debug_0 = new CheckboxMenuItem("0 - Basic messages only", false);
		debug_0.addItemListener(il);
		debug_1 = new CheckboxMenuItem("1 - Count, Error messages", false);
		debug_1.addItemListener(il);
		debug_1.setEnabled(false);
		debug_2 = new CheckboxMenuItem("2 - Uncommon status messages", false);
		debug_2.addItemListener(il);
		debug_2.setEnabled(false);
		debug_3 = new CheckboxMenuItem("3 - Common status messages", false);
		debug_3.addItemListener(il);
		debug_3.setEnabled(false);
		debug_4 = new CheckboxMenuItem("4 - Interrupts", false);
		debug_4.addItemListener(il);
		debug_4.setEnabled(false);
		debug_5 = new CheckboxMenuItem("5 - Opcode display", false);
		debug_5.addItemListener(il);

		optionsmenu = new Menu("Options");
		optionsmenu.addActionListener(al);

		frameskipmenu = new Menu("Frameskip");
		frameskipmenu.addActionListener(al);

		auto_wait = new CheckboxMenuItem("Auto Wait", false);
		auto_wait.addItemListener(il);
		fs_0 = new CheckboxMenuItem("0", false);
		fs_0.addItemListener(il);
		fs_1 = new CheckboxMenuItem("1", false);
		fs_1.addItemListener(il);
		fs_2 = new CheckboxMenuItem("2", false);
		fs_2.addItemListener(il);
		fs_3 = new CheckboxMenuItem("3", false);
		fs_3.addItemListener(il);
		fs_4 = new CheckboxMenuItem("4", false);
		fs_4.addItemListener(il);
		fs_5 = new CheckboxMenuItem("5", false);
		fs_5.addItemListener(il);

		prioritymenu = new Menu("Priority");
		prioritymenu.addActionListener(al);

		priority_low =
			new CheckboxMenuItem("Low (" + Thread.MIN_PRIORITY + ")", false);
		priority_low.addItemListener(il);
		priority_normal =
			new CheckboxMenuItem(
				"Normal (" + Thread.NORM_PRIORITY + ")",
				false);
		priority_normal.addItemListener(il);
		priority_high =
			new CheckboxMenuItem(
				"High ("
					+ ((Thread.NORM_PRIORITY + Thread.MAX_PRIORITY) / 2)
					+ ")",
				false);
		priority_high.addItemListener(il);
		priority_max =
			new CheckboxMenuItem("Max (" + Thread.MAX_PRIORITY + ")", false);
		priority_max.addItemListener(il);

		videomenu = new Menu("Video");
		videomenu.addActionListener(al);

		vidoutmenu = new Menu("Output");
		vidoutmenu.addActionListener(al);

		vidout_default = new CheckboxMenuItem("Default", false);
		vidout_default.addItemListener(il);
		vidout_directx =
			new CheckboxMenuItem("DirectX (fast & accurate)", false);
		vidout_directx.addItemListener(il);
		vidout_directx.setEnabled(PgbSettings.usedirectx);
		vidout_indexed = new CheckboxMenuItem("Indexed Color (fast)", false);
		vidout_indexed.addItemListener(il);
		vidout_direct = new CheckboxMenuItem("Direct Color (accurate)", false);
		vidout_direct.addItemListener(il);

		size_1 = new CheckboxMenuItem("1x window", false);
		size_1.addItemListener(il);
		size_2 = new CheckboxMenuItem("2x", false);
		size_2.addItemListener(il);
		size_3 = new CheckboxMenuItem("3x", false);
		size_3.addItemListener(il);
		size_4 = new CheckboxMenuItem("4x", false);
		size_4.addItemListener(il);

		color_mute = new CheckboxMenuItem("Mute Colors", false);
		color_mute.addItemListener(il);

		sgb_border = new CheckboxMenuItem("SGB Border (slow)", false);
		sgb_border.addItemListener(il);

		soundmenu = new Menu("Sound");
		soundmenu.addActionListener(al);

		sound = new CheckboxMenuItem("Enabled", true);
		sound.addItemListener(il);
		soundChannel1Enable =
			new CheckboxMenuItem("Channel 1 (Square Wave)", true);
		soundChannel1Enable.addItemListener(il);
		soundChannel2Enable =
			new CheckboxMenuItem("Channel 2 (Square Wave)", true);
		soundChannel2Enable.addItemListener(il);
		soundChannel3Enable =
			new CheckboxMenuItem("Channel 3 (Voluntary Wave)", true);
		soundChannel3Enable.addItemListener(il);
		soundChannel4Enable = new CheckboxMenuItem("Channel 4 (Noise)", true);
		soundChannel4Enable.addItemListener(il);

		soundBuffer200 = new CheckboxMenuItem("Buffer length: 200ms", true);
		soundBuffer200.addItemListener(il);
		soundBuffer300 = new CheckboxMenuItem("Buffer length: 300ms");
		soundBuffer300.addItemListener(il);
		soundBuffer400 = new CheckboxMenuItem("Buffer length: 400ms");
		soundBuffer400.addItemListener(il);

		soundFreq11 = new CheckboxMenuItem("Sample rate: 11khz");
		soundFreq11.addItemListener(il);
		soundFreq22 = new CheckboxMenuItem("Sample rate: 22khz");
		soundFreq22.addItemListener(il);
		soundFreq44 = new CheckboxMenuItem("Sample rate: 44khz", true);
		soundFreq44.addItemListener(il);

		serialmenu = new Menu("Serial");
		serialmenu.addActionListener(al);

		serial_connect = new MenuItem("Connect...");
		serial_connect.setActionCommand("serial_connect");
		//serial_connect.setEnabled(false);

		systemmenu = new Menu("System");
		systemmenu.addActionListener(al);

		sys_gb = new CheckboxMenuItem("Gameboy", false);
		sys_gb.addItemListener(il);
		sys_gbp = new CheckboxMenuItem("Gameboy Pocket", false);
		sys_gbp.addItemListener(il);
		sys_sgb = new CheckboxMenuItem("Super Gameboy", false);
		sys_sgb.addItemListener(il);
		sys_gbc = new CheckboxMenuItem("Gameboy Color", false);
		sys_gbc.addItemListener(il);

		options_setkeys = new MenuItem("Set Keys...");
		options_setkeys.setActionCommand("options_setkeys");
		options_setsavepath = new MenuItem("Directories...");
		options_setsavepath.setActionCommand("options_setsavepath");
		options_setsavepath.setEnabled(false);

		// arrange things on it
		add(filemenu);
		filemenu.add(file_load);
		filemenu.addSeparator();
		filemenu.add(file_exit);
		filemenu.add(file_about);
		add(cpumenu);
		cpumenu.add(cpu_reset);
		cpumenu.add(cpu_paused);
		cpumenu.addSeparator();
		cpumenu.add(cpu_runto);
		cpumenu.addSeparator();
		cpumenu.add(dblevelmenu);
		dblevelmenu.add(debug_0);
		dblevelmenu.add(debug_1);
		dblevelmenu.add(debug_2);
		dblevelmenu.add(debug_3);
		dblevelmenu.add(debug_4);
		dblevelmenu.add(debug_5);
		add(optionsmenu);
		optionsmenu.add(frameskipmenu);
		frameskipmenu.add(auto_wait);
		frameskipmenu.addSeparator();
		frameskipmenu.add(fs_0);
		frameskipmenu.add(fs_1);
		frameskipmenu.add(fs_2);
		frameskipmenu.add(fs_3);
		frameskipmenu.add(fs_4);
		frameskipmenu.add(fs_5);
		optionsmenu.add(prioritymenu);
		prioritymenu.add(priority_low);
		prioritymenu.add(priority_normal);
		prioritymenu.add(priority_high);
		prioritymenu.addSeparator();
		prioritymenu.add(priority_max);
		optionsmenu.addSeparator();
		optionsmenu.add(videomenu);
		videomenu.add(vidoutmenu);
		vidoutmenu.add(vidout_default);
		vidoutmenu.addSeparator();
		vidoutmenu.add(vidout_directx);
		vidoutmenu.add(vidout_indexed);
		vidoutmenu.add(vidout_direct);
		videomenu.addSeparator();
		videomenu.add(size_1);
		videomenu.add(size_2);
		videomenu.add(size_3);
		videomenu.add(size_4);
		videomenu.addSeparator();
		videomenu.add(color_mute);
		videomenu.add(sgb_border);
		optionsmenu.add(soundmenu);
		soundmenu.add(sound);
		soundmenu.addSeparator();
		soundmenu.add(soundChannel1Enable);
		soundmenu.add(soundChannel2Enable);
		soundmenu.add(soundChannel3Enable);
		soundmenu.add(soundChannel4Enable);
		soundmenu.addSeparator();
		soundmenu.add(soundFreq11);
		soundmenu.add(soundFreq22);
		soundmenu.add(soundFreq44);
		soundmenu.addSeparator();
		soundmenu.add(soundBuffer200);
		soundmenu.add(soundBuffer300);
		soundmenu.add(soundBuffer400);

		optionsmenu.add(serialmenu);
		serialmenu.add(serial_connect);
		optionsmenu.addSeparator();
		optionsmenu.add(systemmenu);
		systemmenu.add(sys_gb);
		systemmenu.add(sys_gbp);
		systemmenu.add(sys_sgb);
		systemmenu.add(sys_gbc);
		optionsmenu.addSeparator();
		optionsmenu.add(options_setkeys);
		optionsmenu.add(options_setsavepath);
	}
}
