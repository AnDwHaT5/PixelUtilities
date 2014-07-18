package com.pixelutilitys.arcade.emulators.AEPgb;

/**
 * this source file released under the GNU Public Licence.
 * see the accompanying copyright.txt for more information.
 * Copyright (C) 2000-2001 Ben Mazur
 * modified by retroK 2004 http://aep-emu.de/
 */

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;

/**
 * PgbSettings is a static class containing all the settings 
 * that the various components need to operate.
 * 
 * Remember to recompile all dependent classes if you change
 * DEBUG for it to take effect.
 */
public class PgbSettings {
	public static final boolean		DEBUG = false;
	
	public static final String		emulator = "Pgb";
	public static final String		version = "0.89.2";

	public static final int			SYS_GB	= 00; /* gameboy classic	*/
	public static final int			SYS_GBP	= 01; /* gameboy pocket		*/
	public static final int			SYS_SGB	= 10; /* super gameboy		*/
	public static final int			SYS_GBC	= 11; /* gameboy color		*/
	
	public static final int			VIDOUT_DEFAULT	= 00;
	public static final int			VIDOUT_DIRECTX	= 01;
	public static final int			VIDOUT_INDEXED	= 02;
	public static final int			VIDOUT_DIRECT	= 03;
	
	public volatile static boolean	paused = false;
	public volatile static boolean	active = false;
	
	public static int				priority = Thread.NORM_PRIORITY;
	
	public static boolean			usedirectx = false;
	
	public static int				debuglevel = 0;
	public static int				debugstop = -1;
	
	public static int				videooutput = VIDOUT_DIRECT;
	
	public static int				desiredsystem = SYS_GBC;
	public static int				system;
	
	public static int				lcdsize = 2;
	public static boolean			autowait = true;
	public static int				frameskip = 0;
	public static double			clockspeed = 4.194304;
	
	public static int				key_up		= KeyEvent.VK_UP;
	public static int				key_down	= KeyEvent.VK_DOWN;
	public static int				key_left	= KeyEvent.VK_LEFT;
	public static int				key_right	= KeyEvent.VK_RIGHT;
	public static int				key_a		= KeyEvent.VK_A;
	public static int				key_b		= KeyEvent.VK_S;
	public static int				key_select	= KeyEvent.VK_SPACE;
	public static int				key_start	= KeyEvent.VK_ENTER;
	public static int				key_screen  = KeyEvent.VK_F5;
	
	public static int[]				bgcolors = {0xFFFFFFFF, 0xFFDAA5AE, 0xFFA24051, 0xFF000000};
	public static int[]				obj0colors = {0xFFFFFFFF, 0xFF95D5A4, 0xFF579629, 0xFF000000};
	public static int[]				obj1colors = {0xFFFFFFFF, 0xFF92C0E7, 0xFF2F7ED0, 0xFF000000};
	
	public static boolean			sgbborder = false;
	public static boolean			colormute = false;
	// by retroK
	public static boolean			usesound = true;

	
	public static String			gamestring = "NONE            ";
	public static String			lastnetaddress = "";
	public static int				netlistentimeout = 10000;
	public static int				netport = 2907;
	
	public static File				savepath = new File("sav");
	
	public static Point				winloc = new Point();
	
	public static void popKeysDialog(Frame frame) {
		PgbKeyDialog kd;
		
		kd = new PgbKeyDialog(frame);
		kd.setVisible(true);
	}
	
	public static void popSavePathDialog(Frame frame) {
		FileDialog fd;
		
		fd = new FileDialog(frame, "Please choose a save directory.", FileDialog.SAVE);
		fd.setVisible(true);
		
		savepath = new File(fd.getDirectory());
	}
	
	public static void load() {
		File cfgfile;
		Reader cr;
		StreamTokenizer st;
		String key;
		
		// read, read, read!
		try {
			cfgfile = new File("Pgb.cfg");
			if(!cfgfile.exists()) {
				return;
			}
			cr = new FileReader(cfgfile);
			st = new StreamTokenizer(cr);

			st.lowerCaseMode(true);
			st.quoteChar('"');
			st.commentChar('#');

scan:
			while(true) {
				switch(st.nextToken()) {
				case StreamTokenizer.TT_EOF:
					break scan;
				case StreamTokenizer.TT_EOL:
					break scan;
				case StreamTokenizer.TT_WORD:
					// read in 
					key = st.sval;
					if(key.equals("winloc")) {
						winloc = new Point();
						st.nextToken();
						winloc.x = (int)st.nval;
						st.nextToken();
						winloc.y = (int)st.nval;
					}
					if(key.equals("lcdsize")) {
						st.nextToken();
						lcdsize = (int)st.nval;
					}
					if(key.equals("frameskip")) {
						st.nextToken();
						frameskip = (int)st.nval;
					}
					if(key.equals("colormute")) {
						st.nextToken();
						colormute = st.sval.equalsIgnoreCase("true");
					}
					if(key.equals("sgbborder")) {
						st.nextToken();
						sgbborder = st.sval.equalsIgnoreCase("true");
					}
					if(key.equals("debuglevel")) {
						st.nextToken();
						debuglevel = (int)st.nval;
					}
					if(key.equals("keys")) {
						st.nextToken();
						key_up = (int)st.nval;
						st.nextToken();
						key_down = (int)st.nval;
						st.nextToken();
						key_left = (int)st.nval;
						st.nextToken();
						key_right = (int)st.nval;
						st.nextToken();
						key_a = (int)st.nval;
						st.nextToken();
						key_b = (int)st.nval;
						st.nextToken();
						key_select = (int)st.nval;
						st.nextToken();
						key_start = (int)st.nval;
					}
					if(key.equals("savepath")) {
						st.nextToken();
						savepath = new File(st.sval);
					}
					if(key.equals("lastnetaddress")) {
						st.nextToken();
						lastnetaddress = st.sval;
					}
					if(key.equals("netlistentimeout")) {
						st.nextToken();
						netlistentimeout = (int)st.nval;
					}
					if(key.equals("netport")) {
						st.nextToken();
						netport = (int)st.nval;
					}
					if(key.equals("lastnetaddress")) {
						st.nextToken();
						lastnetaddress = st.sval;
					}
					if(key.equals("systemtype")) {
						st.nextToken();
						desiredsystem = (int)st.nval;
					}
					if(key.equals("cpupriority")) {
						st.nextToken();
						priority = (int)st.nval;
					}
					if(key.equals("videooutput")) {
						st.nextToken();
						videooutput = (int)st.nval;
					}
					if(key.equals("autowait")) {
						st.nextToken();
						autowait = st.sval.equalsIgnoreCase("true");
					}
					// by retroK
					if(key.equals("usesound")) {
						 st.nextToken();
						 usesound = st.sval.equals("true");
					} 
				}
			}
			
			cr.close();
		} catch(Exception e) {
			System.err.println("error reading settings file:");
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
	}
	
	public static void save(Frame tosave) {
		File cfgfile;
		Writer cw;

		winloc = tosave.getLocation();
		
		// yay! file stuff!
		try {
			cfgfile = new File("Pgb.cfg");
			cw = new FileWriter(cfgfile);
			
			cw.write("# Pgb config file" + "\r\n");
			cw.write("# Edit at your own risk" + "\r\n");
			cw.write("\r\n");
			cw.write("debuglevel " + debuglevel + "\r\n");
			cw.write("frameskip " + frameskip + "\r\n");
			cw.write("keys " + key_up + " " + key_down + " " + key_left + " " + key_right + " " + key_a + " " + key_b + " " + key_select + " " + key_start + "\r\n");
			cw.write("winloc " + winloc.x + " " + winloc.y + "\r\n");
			cw.write("lcdsize " + lcdsize + "\r\n");
			cw.write("colormute " + colormute + "\r\n");
			cw.write("sgbborder " + sgbborder + "\r\n");
			cw.write("savepath " + '"' + savepath.getPath() + '"' + "\r\n");
			cw.write("lastnetaddress " + '"' + lastnetaddress + '"' + "\r\n");
			cw.write("netlistentimeout " + netlistentimeout + "\r\n");
			cw.write("netport " + netport + "\r\n");
			cw.write("systemtype " + desiredsystem + "\r\n");
			cw.write("cpupriority " + priority + "\r\n");
			cw.write("videooutput " + videooutput + "\r\n");
			cw.write("autowait " + autowait + "\r\n");
			cw.write("usesound " + usesound + "\r\n");
			
			cw.close();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}

/**
 * KeyDialog is the box that pops up to set the keys
 */
class PgbKeyDialog extends Dialog implements ActionListener, KeyListener{
	public int key_up, key_down, key_left,
	key_right, key_a, key_b, key_select, key_start, key_screen;
		
	private Label upL, downL, leftL, rightL,
		aL, bL, selectL, startL, screenL;
	private TextField upF, downF, leftF, rightF,
		aF, bF, selectF, startF, screenF;
	private Button okayB, cancelB;
		
	public PgbKeyDialog(Frame frame) {
		super(frame, "Key Assignment", true); // modal
			
		LayoutManager layout;
			
		key_up		= PgbSettings.key_up;
		key_down	= PgbSettings.key_down;
		key_left	= PgbSettings.key_left;
		key_right	= PgbSettings.key_right;
		key_a		= PgbSettings.key_a;
		key_b		= PgbSettings.key_b;
		key_select	= PgbSettings.key_select;
		key_start	= PgbSettings.key_start;
		key_screen  = PgbSettings.key_screen;
			
		upL		= new Label("Up", Label.RIGHT);
		downL	= new Label("Down", Label.RIGHT);
		leftL	= new Label("Left", Label.RIGHT);
		rightL	= new Label("Right", Label.RIGHT);
		aL		= new Label("A", Label.RIGHT);
		bL		= new Label("B", Label.RIGHT);
		selectL	= new Label("Select", Label.RIGHT);
		startL	= new Label("Start", Label.RIGHT);
		screenL = new Label("Screenshot", Label.RIGHT);

		upF		= new TextField(KeyEvent.getKeyText(key_up), 5);
		upF.addKeyListener(this);
		upF.setEditable(false);
		downF	= new TextField(KeyEvent.getKeyText(key_down), 5);
		downF.addKeyListener(this);
		downF.setEditable(false);
		leftF	= new TextField(KeyEvent.getKeyText(key_left), 5);
		leftF.addKeyListener(this);
		leftF.setEditable(false);
		rightF	= new TextField(KeyEvent.getKeyText(key_right), 5);
		rightF.addKeyListener(this);
		rightF.setEditable(false);
		aF		= new TextField(KeyEvent.getKeyText(key_a), 5);
		aF.addKeyListener(this);
		aF.setEditable(false);
		bF		= new TextField(KeyEvent.getKeyText(key_b), 5);
		bF.addKeyListener(this);
		bF.setEditable(false);
		selectF	= new TextField(KeyEvent.getKeyText(key_select), 5);
		selectF.addKeyListener(this);
		selectF.setEditable(false);
		startF	= new TextField(KeyEvent.getKeyText(key_start), 5);
		startF.addKeyListener(this);
		startF.setEditable(false);
		screenF = new TextField(KeyEvent.getKeyText(key_screen), 5);
		screenF.addKeyListener(this);
		screenF.setEditable(false);
			
		okayB	= new Button("Okay");
		okayB.setActionCommand("okay");
		okayB.addActionListener(this);
		cancelB	= new Button("Cancel");
		cancelB.setActionCommand("cancel");
		cancelB.addActionListener(this);
			
		setSize(180, 250);
		setLocation(frame.getLocation().x + 25, frame.getLocation().y + 25);
			
		layout = new GridLayout(10,2,15,5); 
		setLayout(layout);
			
		add(upL);
		add(upF);
		add(downL);
		add(downF);
		add(leftL);
		add(leftF);
		add(rightL);
		add(rightF);
		add(aL);
		add(aF);
		add(bL);
		add(bF);
		add(selectL);
		add(selectF);
		add(startL);
		add(startF);
		add(screenL);
		add(screenF);
			
		add(okayB);
		add(cancelB);
	}
		
	// ActionListener
	public void actionPerformed(ActionEvent ev) {
		if(ev.getActionCommand().equals("okay")) {
			this.setVisible(false);
			PgbSettings.key_up		= key_up;
			PgbSettings.key_down	= key_down;
			PgbSettings.key_left	= key_left;
			PgbSettings.key_right	= key_right;
			PgbSettings.key_a		= key_a;
			PgbSettings.key_b		= key_b;
			PgbSettings.key_select	= key_select;
			PgbSettings.key_start	= key_start;
			PgbSettings.key_screen  = key_screen;
		}
		if(ev.getActionCommand().equals("cancel")) {
			this.setVisible(false);
		}
	}
	// KeyListener
	public void keyReleased(KeyEvent ev) {
	}
	public void keyPressed(KeyEvent ev) {
		if(ev.getComponent() == upF) {
			key_up = ev.getKeyCode();
			upF.setText(KeyEvent.getKeyText(key_up));
			downF.requestFocus();
			ev.consume();
		}
		if(ev.getComponent() == downF) {
			key_down = ev.getKeyCode();
			downF.setText(KeyEvent.getKeyText(key_down));
			leftF.requestFocus();
			ev.consume();
		}
		if(ev.getComponent() == leftF) {
			key_left = ev.getKeyCode();
			leftF.setText(KeyEvent.getKeyText(key_left));
			rightF.requestFocus();
			ev.consume();
		}
		if(ev.getComponent() == rightF) {
			key_right = ev.getKeyCode();
			rightF.setText(KeyEvent.getKeyText(key_right));
			aF.requestFocus();
			ev.consume();
		}
		if(ev.getComponent() == aF) {
			key_a = ev.getKeyCode();
			aF.setText(KeyEvent.getKeyText(key_a));
			bF.requestFocus();
			ev.consume();
		}
		if(ev.getComponent() == bF) {
			key_b = ev.getKeyCode();
			bF.setText(KeyEvent.getKeyText(key_b));
			selectF.requestFocus();
			ev.consume();
		}
		if(ev.getComponent() == selectF) {
			key_select = ev.getKeyCode();
			selectF.setText(KeyEvent.getKeyText(key_select));
			downF.requestFocus();
			startF.requestFocus();
			ev.consume();
		}
		if(ev.getComponent() == startF) {
			key_start = ev.getKeyCode();
			startF.setText(KeyEvent.getKeyText(key_start));
			okayB.requestFocus();
			ev.consume();
		}
		
		if(ev.getComponent() == screenF) {
			key_screen = ev.getKeyCode();
			screenF.setText(KeyEvent.getKeyText(key_screen));
			okayB.requestFocus();
			ev.consume();
		}
	}
	public void keyTyped(KeyEvent ev) {
	}
}
