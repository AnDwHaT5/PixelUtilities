package com.pixelutilitys.arcade.emulators.AEPgb;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

/**
 * PgbVideoOutput takes a PgbVideo implementation and renders
 * it to the screen, probably through its java.awt.Canvas
 * superclass.
 */
public abstract class PgbVideoOutput extends Canvas {
	PgbVideo	video;
    public BufferedImage tempGraphics;
	
	public PgbVideoOutput(PgbVideo video) {
		this.video = video;
		// notify the video system that we're attached.
		video.setVideoOutput(this);
	}
	
	public abstract void setGraphics();

	/**
	 * reset() is called by Pgb when the emulated machine 
	 * resets.  If any screen clearing is needed, this is a
	 * good time.  Also the canvas is visible, so if any info
	 * needs to be acquired on the display, this is a good 
	 * time.
	 */
	public abstract void reset();
	
	/**
	 * hblank(line) is called by the attached PgbVideo every 
	 * time an hblank occurs and is not skipped by frame 
	 * skipping.
	 */
	public abstract void hblank(int line);

	/**
	 * vblank() is called by the attached PgbVideo every 
	 * time a vblank occurs and is not skipped by frame 
	 * skipping.
	 */
	public abstract void vblank();
	
	/**
	 * create a screenshot
	 */
	public abstract void makeScreenshot();
}
