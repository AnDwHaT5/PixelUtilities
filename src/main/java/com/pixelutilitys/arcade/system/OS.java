package com.pixelutilitys.arcade.system;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pixelutilitys.arcade.interfaces.IArcadeGame;
import com.pixelutilitys.arcade.interfaces.IArcadeMachine;

import org.lwjgl.input.Keyboard;

import com.pixelutilitys.arcade.system.programs.*;
import com.pixelutilitys.arcade.system.programs.gb.*;


public class OS implements IArcadeGame {

	public final int resX = 256;
	public final int resY = 224;

	private static OSSettings settings = OSSettings.load();
	public static OSLogger logger = new OSLogger("blazeit OS");

	public IArcadeMachine machine;
	public BufferedImage gameIcon;
	public String currentPlayer = null;
	public boolean imageDirty = false;
	public HashMap<Integer, Boolean> pressedKeys = new HashMap<>();
	private IProgram currentProgram;
	private int currentSelection = 0;
	public List<IProgram> programs = new ArrayList<>();
	public Map<String, List<Integer>> keys = new HashMap<>();

	@Override
	public void initialize() {
		unload();
        programs.add(new ProgramGBDirectory(this));
		programs.add(new ProgramReloadSettings(this));
		programs.add(new ProgramNotepad(this));
		Integer[] keyList = new Integer[] { Keyboard.KEY_DOWN, Keyboard.KEY_UP, Keyboard.KEY_RETURN };
		keys.put("os", Arrays.asList(keyList));
	}

	@Override
	public String getTitle() {
		return "Menu";
	}

	public void unloadProgram() {
		currentProgram = null;
	}

	private Color osBackground = null;
	private Color osForeground = null;

	public void reloadSettings() {
		osBackground = null;
		osForeground = null;
		settings = OSSettings.load();
		imageDirty = true;
	}

	public Color getBackground() {
		if (osBackground == null) {
			logger.log("Remapping colour!");
			osBackground = new Color(settings.colourBackground[0], settings.colourBackground[1], settings.colourBackground[2]);
		}
		return osBackground;
	}

	public Color getForeground() {
		if (osForeground == null) {
			logger.log("Remapping colour!");
			osForeground = new Color(settings.colourForeground[0], settings.colourForeground[1], settings.colourForeground[2]);
		}
		return osForeground;
	}

	public BufferedImage getImage() {
		if (currentProgram != null) {
			return currentProgram.getImage();
		}
		//	if (gameIcon == null || imageDirty)
		{
			gameIcon = new BufferedImage(resX, 224, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D)gameIcon.getGraphics();
			g.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
			g.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
			g.setColor(getBackground());
			g.fillRect(0, 0, resX, 224);
			g.setColor(getForeground());
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
			g.drawString(getTitle(), 10, 20);
			int markerOffset = 0;
			if (currentSelection > 11) {
				markerOffset = 11 - currentSelection;
			}
			g.drawString(">", 10, 36 + ((currentSelection + markerOffset) * 16));

			ArrayList<String> output = new ArrayList<>();
			for (IProgram p : programs) {
				output.add(p.getTitle());
			}

			if (output.size() == 0) {
				output.add("NO PROGRAMS FOUND!");
			}
			if (output.size() == 0) {
				g.setColor(Color.RED);
			}
			int offset = 0;
			if (currentSelection > 11) {
				offset = currentSelection - 11;
			}
			for (int i = offset; i < output.size(); i++) {
				if (i < 12 + offset)
				{
					g.drawString(output.get(i), 30, 36 + ((i - offset) * 16));
				}
			}
			imageDirty = false;
		}
		return gameIcon;
	}

	@Override
	public BufferedImage getGameIcon() {
		return getImage();
	}

	@Override
	public void unload() {
		if (currentProgram != null) {
			currentProgram.unload();
		}
	}

	public void onKeyDown(int key) {
		if (currentProgram != null && keys.get(currentProgram.getTitle()) != null && keys.get(currentProgram.getTitle()).contains(key)) {
			currentProgram.onKeyDown(key);
		}
		else {
			if (key == Keyboard.KEY_DOWN) {
				if (currentSelection < programs.size() - 1) {
					currentSelection++;
				}
				else {
					currentSelection = 0;
				}
				imageDirty = true;
			}
			if (key == Keyboard.KEY_UP) {
				if (currentSelection > 0) {
					currentSelection--;
				}
				else {
					currentSelection = programs.size() - 1;
				}
				imageDirty = true;

			}
			if (key == Keyboard.KEY_RETURN) {
				loadProgram(programs.get(currentSelection));
			}
		}

//        System.out.println(key);
	}

	public void onKeyUp(int key) {
		if (currentProgram != null) {
			currentProgram.onKeyUp(key);
		}
	}

	@Override
	public void doGameTick(List<KEY> input) {
			List<List<Integer>> keysets = new ArrayList<>(keys.values());
        for (List<Integer> keyset : keysets) {
            for (Integer aKeyset : keyset) {
                int key = aKeyset;
                if (Keyboard.isKeyDown(key)) {
                    if (pressedKeys.containsKey(key)) {
                        if (pressedKeys.get(key)) {
                            continue;
                        }
                    }
                    onKeyDown(key);
                    pressedKeys.put(key, true);
                }
            }
        }
        java.util.Set<Integer> var = pressedKeys.keySet();
        Integer[] downkeys = var.toArray(new Integer[var.size()]);

        for (Integer downkey : downkeys) {
            if (!Keyboard.isKeyDown(downkey)) {
                pressedKeys.put(downkey, false);
                onKeyUp(downkey);
            }
        }

	}


	@Override
	public BufferedImage renderGraphics() {
		return getImage();
	}

	public void loadProgram(IProgram p) {
		currentProgram = p;
		currentProgram.load();
		currentProgram.initialize();
	}

	public void registerKeys(IProgram toProgram, int... keys) {
		for (int i : keys) {
			registerKey(toProgram, i);
		}
	}

	public void registerKey(IProgram toProgram, int key) {
		if (keys.containsKey(toProgram.getTitle()) && keys.get(toProgram.getTitle()) != null) {
			keys.get(toProgram.getTitle()).add(key);
			logger.log("Registered key! " + toProgram.getTitle() + " " + key);
		}
		else {
			ArrayList<Integer> keyz = new ArrayList<>();
			keyz.add(key);
			keys.put(toProgram.getTitle(), keyz);
			logger.log("Registered key, created new keyset! " + toProgram.getTitle() + " " + key);
		}
	}

}