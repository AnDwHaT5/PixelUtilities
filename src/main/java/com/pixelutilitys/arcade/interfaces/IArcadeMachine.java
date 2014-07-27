package com.pixelutilitys.arcade.interfaces;

import java.awt.Color;
import java.io.File;

public interface IArcadeMachine {

    /**
     * Plays the sound contained in the file
     *
     * @param soundFile soundfile to play
     */
    public void playSound(File soundFile);

    /**
     * If a game produces an error it could call this method.
     *
     * @param hcf
     *            If set to true, shit will go wrong... :D
     */
    public void fail(boolean hcf);

    /**
     * Returns the resolution the game should use to render.<br>
     * That's just a resolution though, and the game does not necessarily need to use it.
     * int[0] = width<b>
     * int[1] = height<b>
     *
     * @return [0] = width, [1] = height
     */
    public int[] getSuggestedScreenSize();

    /**
     * Gets the background color of the arcades screen, which will be used to fill areas of the screen, that are not
     * used by the game.
     *
     * @return Color of background
     */
    public Color getScreenBackgroundColor();
}
