package com.pixelutilitys.arcade.system.programs.gb;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.pixelutilitys.arcade.emulators.AEPgb.*;
import com.pixelutilitys.arcade.system.OS;
import com.pixelutilitys.arcade.system.programs.IProgram;

import org.lwjgl.input.Keyboard;


public class ProgramGBEmulator implements IProgram {

	public BufferedImage gameIcon;
	public BufferedImage gameboyOutput = null;
	public String gameboyRom;
	public AEPgb gameboy;
	public boolean gameboyStarted = false;
	public HashMap<Integer, Boolean> pressedKeys = new HashMap<>();
	public int loadDelay = 0;//20
	public String romTitle;
	public OS os;
	public int[] keyCodes = { Keyboard.KEY_UP, Keyboard.KEY_DOWN, Keyboard.KEY_LEFT, Keyboard.KEY_RIGHT, Keyboard.KEY_Z, Keyboard.KEY_X, Keyboard.KEY_RETURN, Keyboard.KEY_RSHIFT};


	public ProgramGBEmulator(OS o, String romPath, String romName) {
		os = o;
		gameboyRom = romPath;
		romTitle = romName;
	}

	@Override
	public void load() {
		getOS().registerKey(this, Keyboard.KEY_UP);
		getOS().registerKey(this, Keyboard.KEY_DOWN);
		getOS().registerKey(this, Keyboard.KEY_LEFT);
		getOS().registerKey(this, Keyboard.KEY_RIGHT);
		getOS().registerKey(this, Keyboard.KEY_Z);
		getOS().registerKey(this, Keyboard.KEY_X);
		getOS().registerKey(this, Keyboard.KEY_RETURN);
		getOS().registerKey(this, Keyboard.KEY_RSHIFT);

		getOS().registerKey(this, Keyboard.KEY_BACK);
		getOS().registerKey(this, Keyboard.KEY_W);
	}

	@Override
	public String getTitle() {
		return "Gameboy Emulator: " + romTitle;
	}

	@Override
	public BufferedImage getImage() {

		if (gameboy != null && gameboyStarted) {
            return gameboy.vidout.tempGraphics;
        }
		if (gameIcon == null || getOS().imageDirty)
		{

			gameIcon = new BufferedImage(getOS().resX, getOS().resY, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g = (Graphics2D)gameIcon.getGraphics();
			g.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
			g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
			g.setColor(getOS().getBackground());
			g.fillRect(0, 0, getOS().resX, getOS().resY);
			g.setColor(getOS().getForeground());
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
			String[] output = {
					"GAMEBOY EMULATOR",
					"Rom: " + romTitle,
					"",
					"Keys:",
					"Arrows -  D-Pad",
					"X      -  A",
					"Z      -  B",
					"Enter  -  Start",
					"Shift  -  Select",
					"Back   -  Quit Rom",
					"",
					"Press 'ENTER' to load",
			"Press  'BACK' to quit" };
			for (int i = 0; i < output.length; i++) {
				g.drawString(output[i], 10, 20 + (i * 16));
			}
		}
		return gameIcon;
	}

	@Override
	public void initialize() {

	}

	public void loadRom() {
        System.out.println("loadRom()");

		gameboy = new AEPgb();
        gameboy.curfile = gameboyRom;
        gameboy.go();
        gameboy.frame.setVisible(false);

        gameboyStarted = true;

	}

	@Override
	public void unload() {
        System.out.println("unload");
        if(gameboy != null) {
            gameboy.shutdown();
            gameboy = null;
        }
        gameboyStarted = false;
	}

	@Override
	public OS getOS() {
		return os;
	}


	@Override
	public void onKeyUp(int key) {
		if(gameboyStarted){
			if (key == keyCodes[0]) {
                gameboy.joy.c_up = false;
			}
			else if (key == keyCodes[1]) {
                gameboy.joy.c_down = false;
			}
			else if (key == keyCodes[2]) {
                gameboy.joy.c_left = false;
			}
			else if (key == keyCodes[3]) {
                gameboy.joy.c_right = false;
			}
			else if (key == keyCodes[4]) {
                gameboy.joy.b_a = false;
			}
			else if (key == keyCodes[5]) {
                gameboy.joy.b_b = false;
			}
			else if (key == keyCodes[6]) {
                gameboy.joy.b_start = false;
			}
			else if (key == keyCodes[7]) {
                gameboy.joy.b_select = false;
			}
		}
	}

	@Override
	public void onKeyDown(int key) {
		if (!gameboyStarted) {
			if (key == Keyboard.KEY_RETURN && loadDelay < 1) {
				loadRom();
				loadDelay = 20;
			}
			if (key == Keyboard.KEY_BACK) {
				getOS().loadProgram(new ProgramGBDirectory(getOS()));
			}
		}
		else {
			if(key == Keyboard.KEY_W){
				System.out.println("W");
//                gameboy.mainWindow.setVisible(!gameboy.mainWindow.isVisible());
//				System.out.println(gameboy.mainWindow.isVisible());
			}
			if (key == Keyboard.KEY_BACK) {
				unload();
			}

			if (key == keyCodes[0]) {
                gameboy.joy.c_up = true;
			}
			else if (key == keyCodes[1]) {
                gameboy.joy.c_down = true;
			}
			else if (key == keyCodes[2]) {
                gameboy.joy.c_left = true;
			}
			else if (key == keyCodes[3]) {
                gameboy.joy.c_right = true;
			}
			else if (key == keyCodes[4]) {
                gameboy.joy.b_a = true;
			}
			else if (key == keyCodes[5]) {
                gameboy.joy.b_b = true;
			}
			else if (key == keyCodes[6]) {
                gameboy.joy.b_start = true;
			}
			else if (key == keyCodes[7]) {
                gameboy.joy.b_select = true;
			}

			switch (key) {

				case KeyEvent.VK_F1:
//					if (gameboy.dmgcpu.graphicsChip.frameSkip != 1) {
//						gameboy.dmgcpu.graphicsChip.frameSkip--;
//					}
					break;
				case KeyEvent.VK_F2:
//					if (gameboy.dmgcpu.graphicsChip.frameSkip != 10) {
//						gameboy.dmgcpu.graphicsChip.frameSkip++;
//					}
					break;
			}
		}
	}

}