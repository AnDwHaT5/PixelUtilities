package com.pixelutilitys.arcade.emulators.AEPgb;
/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information
 * Copyright (C) 2000-2001 Ben Mazur
 * modified by retroK, XTale and baka0815 2004 http://aepgb.aep-emu.de/
 */
import java.applet.Applet;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AEPgb extends Applet
	implements KeyListener, WindowListener, ActionListener, ItemListener, Runnable {

	public static boolean applet;
	public JFrame frame;
	public Container container;
	public Thread cpuThread;

	public PgbMemory mem;
	public PgbCpu cpu;
	public PgbCart cart;
	public PgbVideo video;
	public PgbJoypad joy;
	public PgbNetplay net;

	public PgbVideoOutput vidout;
	public PgbDirectVideoOutput directvidout;

	public String curfile;
	public String curpath;

	public PgbMenuBar menubar;

	public AEPgb(){
		frame = new JFrame("AEPgb " + PgbSettings.version);
		container = frame.getContentPane();

		// load settings, if possible
		PgbSettings.load();

		// initialize...
		curfile = "";
		curpath = "";

		// init menu bar
		menubar = new PgbMenuBar(this, this);

		// init emulation
		cart = new PgbCart();
		video = new PgbCachedVideo();
		joy = new PgbJoypad();
		net = new PgbNetplay();

		mem = new PgbMemory(cart, video, joy, net);
		cpu = new PgbCpu(mem);

		vidout = getPgbVideoOutput(video);

		// setup frame stuff
		frame.setMenuBar(menubar);
		frame.setResizable(false);

		frame.addWindowListener(this);
		frame.addKeyListener(this);
		frame.addKeyListener(joy);
		//addFocusListener(this);
	}

	public static void main(String[] args) {

		System.out.println("Pgb" + " (c) 2000-2001 Ben Mazur");
		System.out.println(
			"AEPgb Version " + PgbSettings.version + " (c) 2004 retroK, XTale, baka0815");

		AEPgb pgb = new AEPgb();
		pgb.parseCommandLine(args);
		pgb.go();
	}
	
	public void start() {
		applet = true;
		Thread p = new Thread(this);
		addKeyListener(this);
		//runningAsApplet = true;
		System.out.println(
			"AEPgb Version "
				+ PgbSettings.version
				+ " (c) 2004 retroK & XTale(applet)");
		this.requestFocus();
		vidout = getPgbVideoOutput(video);
		cpuThread = new Thread(this);
		//conformToSettings();
		
		//frame.pack();
		vidout.setGraphics();
		//frame.setVisible(true);
		PgbSettings.paused = true;
		PgbSettings.active = true;
		cart.loadApplet("file.gb",this);
		
		if (cart.loaded()) {
			reset();
			unpause();
		}
		System.out.println("starting cputhread");
		p.start();
		cpuThread.start();
	}

	public void go() {
		cpuThread = new Thread(this);
		conformToSettings(); // once

		frame.pack();
		vidout.setGraphics();
		frame.setVisible(true);

		PgbSettings.paused = true;
		PgbSettings.active = true;

		cart.load(curpath, curfile);
		if (cart.loaded()) {
			setSystem(PgbSettings.desiredsystem);
			PgbSettings.gamestring = cart.getName();
			reset();
			unpause();
		}

		cpuThread.start();
	}

	/**
	 * Shuts down the emulator, killing off all threads and
	 * saving the battery and settings, if needed.
	 */
	public void shutdown() {
		cpuThread = null;
		PgbSettings.save(frame);

		cart.saveBattery(PgbSettings.savepath, curfile);

//		System.exit(0);
	}

	/**
	 * Pauses the cpu emulation process.
	 */
	public synchronized void pause() {
		if (!PgbSettings.paused) {
			PgbSettings.paused = true;
		}
		menubar.cpu_paused.setState(PgbSettings.paused);
	}

	/**
	 * Unpauses the cpu emulation process.
	 */
	public synchronized void unpause() {
		if (PgbSettings.paused) {
			PgbSettings.paused = false;
			notifyAll();
		}
		menubar.cpu_paused.setState(PgbSettings.paused);
	}

	/**
	 * Resets all aspects of emulation.
	 */
	public synchronized void reset() {
		pause();

		video.reset();
		vidout.reset();
		cart.reset();
		joy.reset();
		mem.reset();
		cpu.reset();

		unpause();
	}

	public void parseCommandLine(String[] args) {
		if (args.length > 0) {

			// file is last argument
			curfile = args[args.length - 1];
		}
	}

	public PgbVideoOutput getPgbVideoOutput(PgbVideo video) {
		// check if they can use directx
		if (applet) {
			return new PgbDirectVideoOutput(video,this);
		}
		if (PgbSettings.videooutput == PgbSettings.VIDOUT_DIRECTX
			&& !PgbSettings.usedirectx) {
			PgbSettings.videooutput = PgbSettings.VIDOUT_DEFAULT;
		}
		switch (PgbSettings.videooutput) {
			case PgbSettings.VIDOUT_DEFAULT :
				return new PgbIndexedVideoOutput(video);
			case PgbSettings.VIDOUT_DIRECT :
				return new PgbDirectVideoOutput(video);
			case PgbSettings.VIDOUT_INDEXED :
			default :
				return new PgbIndexedVideoOutput(video);
		}
	}

	public void conformToSettings() {
		setSgbBorder(PgbSettings.sgbborder);
		setColorMute(PgbSettings.colormute);
		frame.setLocation(PgbSettings.winloc);
		setDebugLevel(PgbSettings.debuglevel);
		setFrameskip(PgbSettings.frameskip);
		setPriority(PgbSettings.priority);
		setVideoOutput(PgbSettings.videooutput);
		setSystem(PgbSettings.desiredsystem);

		setLcdSize(PgbSettings.lcdsize);
	}

	void setPriority(int priority) {
		PgbSettings.priority = priority;

		menubar.priority_low.setState(priority == Thread.MIN_PRIORITY);
		menubar.priority_normal.setState(priority == Thread.NORM_PRIORITY);
		menubar.priority_high.setState(
			priority == ((Thread.NORM_PRIORITY + Thread.MAX_PRIORITY) / 2));
		menubar.priority_max.setState(priority == Thread.MAX_PRIORITY);

		cpuThread.setPriority(priority);
	}

	// by retroK
	void setSound(boolean usesound) {
		PgbSettings.usesound = usesound;
		menubar.sound.setState(usesound);
		mem.soundChip.channel1Enable = menubar.sound.getState();
		menubar.soundChannel1Enable.setState(menubar.sound.getState());
		mem.soundChip.channel2Enable = menubar.sound.getState();
		menubar.soundChannel2Enable.setState(menubar.sound.getState());
		mem.soundChip.channel3Enable = menubar.sound.getState();
		menubar.soundChannel3Enable.setState(menubar.sound.getState());
		mem.soundChip.channel4Enable = menubar.sound.getState();
		menubar.soundChannel4Enable.setState(menubar.sound.getState());
	}

	void setChannelEnable() {
		if (menubar.soundChannel1Enable.getState()
			|| menubar.soundChannel2Enable.getState()
			|| menubar.soundChannel3Enable.getState()
			|| menubar.soundChannel4Enable.getState()) {
			menubar.sound.setState(true);
		}
		mem.soundChip.channel1Enable = menubar.soundChannel1Enable.getState();
		mem.soundChip.channel2Enable = menubar.soundChannel2Enable.getState();
		mem.soundChip.channel3Enable = menubar.soundChannel3Enable.getState();
		mem.soundChip.channel4Enable = menubar.soundChannel4Enable.getState();
	}

	void setSoundFreq(int frequency) {
		if (frequency == 11025) {
			menubar.soundFreq11.setState(true);
			menubar.soundFreq22.setState(false);
			menubar.soundFreq44.setState(false);
		}
		if (frequency == 22050) {
			menubar.soundFreq22.setState(true);
			menubar.soundFreq11.setState(false);
			menubar.soundFreq44.setState(false);
		}
		if (frequency == 44100) {
			menubar.soundFreq44.setState(true);
			menubar.soundFreq11.setState(false);
			menubar.soundFreq22.setState(false);
		}

		mem.soundChip.setSampleRate(frequency);
	}

	void setBufferLength(int bufferlength) {
		if (bufferlength == 200) {
			menubar.soundBuffer200.setState(true);
			menubar.soundBuffer300.setState(false);
			menubar.soundBuffer400.setState(false);
		}
		if (bufferlength == 300) {
			menubar.soundBuffer300.setState(true);
			menubar.soundBuffer200.setState(false);
			menubar.soundBuffer400.setState(false);
		}
		if (bufferlength == 400) {
			menubar.soundBuffer400.setState(true);
			menubar.soundBuffer200.setState(false);
			menubar.soundBuffer300.setState(false);

		}
		mem.soundChip.setBufferLength(bufferlength);
	}

	// end by retroK

	void setLcdSize(int size) {
		// grr, due to a bug, this will not work on a window
		// until it is visible and has been resized...

		Dimension lcdsize;
		PgbSettings.lcdsize = size;

		menubar.size_1.setState(size == 1);
		menubar.size_2.setState(size == 2);
		menubar.size_3.setState(size == 3);
		menubar.size_4.setState(size == 4);

		if (PgbSettings.sgbborder) {
			lcdsize = new Dimension(256 * size, 224 * size);
		} else {
			lcdsize = new Dimension(160 * size, 144 * size);
		}
		vidout.setSize(lcdsize);

		frame.setSize(
			frame.getInsets().right + lcdsize.width + frame.getInsets().left,
			frame.getInsets().top + lcdsize.height + frame.getInsets().bottom);
	}

	void setVideoOutput(int videooutput) {
		// check if they can use directx
		if (videooutput == PgbSettings.VIDOUT_DIRECTX
			&& !PgbSettings.usedirectx) {
			videooutput = PgbSettings.VIDOUT_DEFAULT;
		}
		// then set up the menus as normal
		PgbSettings.videooutput = videooutput;
		if (videooutput == PgbSettings.VIDOUT_DEFAULT) {
			menubar.vidout_default.setState(true);
			if (PgbSettings.usedirectx) {
				menubar.vidout_directx.setState(true);
				menubar.vidout_indexed.setState(false);
				menubar.vidout_direct.setState(false);
			} else {
				menubar.vidout_directx.setState(false);
				menubar.vidout_indexed.setState(true);
				menubar.vidout_direct.setState(false);
			}
		} else {
			menubar.vidout_default.setState(
				videooutput == PgbSettings.VIDOUT_DEFAULT);
			menubar.vidout_directx.setState(
				videooutput == PgbSettings.VIDOUT_DIRECTX);
			menubar.vidout_indexed.setState(
				videooutput == PgbSettings.VIDOUT_INDEXED);
			menubar.vidout_direct.setState(
				videooutput == PgbSettings.VIDOUT_DIRECT);
		}
		pause();

		PgbVideoOutput oldVidout = vidout;

		// add new video
		PgbVideoOutput newVidout = getPgbVideoOutput(video);
		newVidout.addKeyListener(this);
		newVidout.addKeyListener(joy);

		// remove old video, add new one
		// old vidout, please die peacefully...
		frame.remove(oldVidout);
		frame.getContentPane().add(newVidout);
		frame.validate();

		if (frame.isVisible()) {
			newVidout.reset();
			newVidout.requestFocus();
		}

		this.vidout = newVidout;

		unpause();
	}

	void setColorMute(boolean muted) {
		PgbSettings.colormute = muted;
		menubar.color_mute.setState(muted);
	}

	void setSgbBorder(boolean border) {
		PgbSettings.sgbborder = border;
		menubar.sgb_border.setState(border);
	}

	void setFrameskip(int skip) {
		PgbSettings.frameskip = skip;

		menubar.auto_wait.setState(PgbSettings.autowait);
		menubar.fs_0.setState(skip == 0);
		menubar.fs_1.setState(skip == 1);
		menubar.fs_2.setState(skip == 2);
		menubar.fs_3.setState(skip == 3);
		menubar.fs_4.setState(skip == 4);
		menubar.fs_5.setState(skip == 5);
	}

	void setDebugLevel(int level) {
		PgbSettings.debuglevel = level;

		menubar.debug_0.setState(level == 0);
		menubar.debug_1.setState(level == 1);
		menubar.debug_2.setState(level == 2);
		menubar.debug_3.setState(level == 3);
		menubar.debug_4.setState(level == 4);
		menubar.debug_5.setState(level == 5);
	}

	void setSystem(int sys) {
		PgbSettings.desiredsystem = sys;

		menubar.sys_gb.setState(sys == PgbSettings.SYS_GB);
		menubar.sys_gbp.setState(sys == PgbSettings.SYS_GBP);
		menubar.sys_sgb.setState(sys == PgbSettings.SYS_SGB);
		menubar.sys_gbc.setState(sys == PgbSettings.SYS_GBC);

		if (sys == PgbSettings.SYS_GBC && !cart.getGbc()) {
			sys = PgbSettings.SYS_SGB;
		}
		if (sys == PgbSettings.SYS_SGB && !cart.getSgb()) {
			sys = PgbSettings.SYS_GBP;
		}

		PgbSettings.system = sys;
	}

	synchronized void loadCart() {
		FileDialog fd;

		cart.saveBattery(PgbSettings.savepath, curfile);

		fd = new FileDialog(frame, curpath, FileDialog.LOAD);
		fd.setTitle("Load ROM...");
		fd.setFilenameFilter(cart);
		fd.setVisible(true);

		if (fd.getFile() == null) {
			return;
		}
		curfile = fd.getDirectory()+fd.getFile();
		curpath = fd.getDirectory();

		//fd.dispose();

		if (cart.load(curpath, curfile)) {
			setSystem(PgbSettings.desiredsystem);

			PgbSettings.gamestring = cart.getName();
			net.sendInfo();

			reset();

			unpause();
		}
	}

	/**
	 * WindowListener
	 */
	public void windowClosing(WindowEvent ev) {
		shutdown();
	}
	public void windowOpened(WindowEvent ev) {
	}
	public void windowClosed(WindowEvent ev) {
	}
	public synchronized void windowDeiconified(WindowEvent ev) {
		if (!PgbSettings.active) {
			PgbSettings.active = true;
			notifyAll();
		}
	}
	public synchronized void windowActivated(WindowEvent ev) {
		/*
		if(!PgbSettings.active) {
			PgbSettings.active = true;
			notify();
		}
		*/
	}
	public synchronized void windowIconified(WindowEvent ev) {
		if (PgbSettings.active) {
			PgbSettings.active = false;
		}
	}
	public synchronized void windowDeactivated(WindowEvent ev) {
		/*
		if(PgbSettings.active) {
			PgbSettings.active = false;
		}
		*/
	}

	/**
	 * KeyListener
	 */
	public void keyReleased(KeyEvent ev) {
	}
	public void keyPressed(KeyEvent ev) {
		if (ev.getKeyCode() == KeyEvent.VK_SPACE) {
			cpu.exec(1);
		}
		
		if (ev.getKeyCode() == PgbSettings.key_screen) {
			vidout.makeScreenshot();
		}
	}
	public void keyTyped(KeyEvent ev) {
	}

	/**
	 * ActionListener
	 */
	public synchronized void actionPerformed(ActionEvent ev) {
        switch (ev.getActionCommand())
        {
             case "file_about":
                 String message = "Pgb (c) 2000-2001 Ben Mazur\nAEPgb Version "+PgbSettings.version+" (c) 2004 retroK, XTale, baka0815\nhttp://aepgb.aep-emu.de/";
                 JOptionPane.showMessageDialog(frame, message, "About AEPgb", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "file_load":
                loadCart();
                break;

            case "file_exit":
                shutdown();
                break;

            case "cpu_reset":
                reset();
                break;

            case "cpu_paused":
                if (PgbSettings.paused) {
                    unpause();
                } else {
                    pause();
                }
                break;

            case "serial_connect":
                net.popNetDialog(frame);
                break;

            case "options_setkeys":
                PgbSettings.popKeysDialog(frame);
                break;

            case "options_sersavepath":
                PgbSettings.popSavePathDialog(frame);
                break;
        }
	}

	/**
	 * ItemListener
	 */
	public synchronized void itemStateChanged(ItemEvent ev) {
		if (ev.getItemSelectable().equals(menubar.cpu_paused)) {
			if (PgbSettings.paused) {
				unpause();
			} else {
				pause();
			}
			return;
		}
		if (ev.getItemSelectable().equals(menubar.vidout_default)) {
			setVideoOutput(PgbSettings.VIDOUT_DEFAULT);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.vidout_directx)) {
			setVideoOutput(PgbSettings.VIDOUT_DIRECTX);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.vidout_indexed)) {
			setVideoOutput(PgbSettings.VIDOUT_INDEXED);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.vidout_direct)) {
			setVideoOutput(PgbSettings.VIDOUT_DIRECT);
			return;
		}

		if (ev.getItemSelectable().equals(menubar.sound)) {
			setSound(menubar.sound.getState());
			return;
		}

		if (ev.getItemSelectable().equals(menubar.soundChannel1Enable)
			|| ev.getItemSelectable().equals(menubar.soundChannel2Enable)
			|| ev.getItemSelectable().equals(menubar.soundChannel3Enable)
			|| ev.getItemSelectable().equals(menubar.soundChannel4Enable)) {
			setChannelEnable();
			return;
		}

		if (ev.getItemSelectable().equals(menubar.soundBuffer200)) {
			setBufferLength(200);
			return;
		}

		if (ev.getItemSelectable().equals(menubar.soundBuffer300)) {
			setBufferLength(300);
			return;
		}

		if (ev.getItemSelectable().equals(menubar.soundBuffer400)) {
			setBufferLength(400);
			return;
		}

		if (ev.getItemSelectable().equals(menubar.soundFreq11)) {
			setSoundFreq(11025);
			return;
		}

		if (ev.getItemSelectable().equals(menubar.soundFreq22)) {
			setSoundFreq(22050);
			return;
		}

		if (ev.getItemSelectable().equals(menubar.soundFreq44)) {
			setSoundFreq(44100);
			return;
		}

		if (ev.getItemSelectable().equals(menubar.size_1)) {
			setLcdSize(1);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.size_2)) {
			setLcdSize(2);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.size_3)) {
			setLcdSize(3);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.size_4)) {
			setLcdSize(4);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.color_mute)) {
			setColorMute(menubar.color_mute.getState());
			return;
		}
		if (ev.getItemSelectable().equals(menubar.sgb_border)) {
			setSgbBorder(menubar.sgb_border.getState());
			setLcdSize(PgbSettings.lcdsize);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.auto_wait)) {
			PgbSettings.autowait = !PgbSettings.autowait;
			setFrameskip(PgbSettings.frameskip);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.fs_0)) {
			setFrameskip(0);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.fs_1)) {
			setFrameskip(1);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.fs_2)) {
			setFrameskip(2);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.fs_3)) {
			setFrameskip(3);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.fs_4)) {
			setFrameskip(4);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.fs_5)) {
			setFrameskip(5);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.priority_low)) {
			setPriority(Thread.MIN_PRIORITY);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.priority_normal)) {
			setPriority(Thread.NORM_PRIORITY);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.priority_high)) {
			setPriority((Thread.NORM_PRIORITY + Thread.MAX_PRIORITY) / 2);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.priority_max)) {
			setPriority(Thread.MAX_PRIORITY);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.debug_0)) {
			setDebugLevel(0);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.debug_1)) {
			setDebugLevel(1);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.debug_2)) {
			setDebugLevel(2);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.debug_3)) {
			setDebugLevel(3);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.debug_4)) {
			setDebugLevel(4);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.debug_5)) {
			setDebugLevel(5);
			return;
		}
		if (ev.getItemSelectable().equals(menubar.sys_gb)) {
			setSystem(PgbSettings.SYS_GB);
			reset();
		}
		if (ev.getItemSelectable().equals(menubar.sys_gbp)) {
			setSystem(PgbSettings.SYS_GBP);
			reset();
		}
		if (ev.getItemSelectable().equals(menubar.sys_sgb)) {
			setSystem(PgbSettings.SYS_SGB);
			reset();
		}
		if (ev.getItemSelectable().equals(menubar.sys_gbc)) {
			setSystem(PgbSettings.SYS_GBC);
			reset();
		}
	}

	/**
	 * Runnable
	 */
	public final void run() {
		int cyclecount = 0;
		long lasttime = System.currentTimeMillis(),
			lastframe = video.framecount;
		long interval = 1;
		long thistime, time, frames;
		int framereal, frametarget;

		Thread currentThread = Thread.currentThread();
		while (cpuThread == currentThread) {
			try {
				Thread.sleep(interval);
				synchronized (this) {
					while ((PgbSettings.paused || !PgbSettings.active)
						&& cpuThread == currentThread) {
						wait();
					}
				}
			} catch (InterruptedException ex) {
				// duh?
			}
			cpu.exec((int) (PgbSettings.clockspeed * 100000));
			if (cyclecount++ == 10) {
				// calculate
				thistime = System.currentTimeMillis();
				time = thistime - lasttime;
				frames = video.framecount - lastframe;
				framereal = (int) (frames * (1000.0 / time));
				frametarget = 60;
				// adjust
				if (PgbSettings.autowait) {
					// attempt to regulate speed...
					// it doesn't adjust fast, but it doesn't overadjust
					if (frametarget - framereal > 5) {
						interval = Math.max(interval - 4, 1);
					}
					if (framereal - frametarget > 5) {
						interval += 4;
					}
				} else {
					interval = 1;
				}
				// display
				frame.setTitle(
					"AEPgb : "
						+ framereal
						+ "/"
						+ frametarget
						+ " fps, wait="
						+ interval);
				// get ready for next time
				cyclecount = 0;
				lasttime = thistime;
				lastframe = video.framecount;
			}
		}
	}
}
