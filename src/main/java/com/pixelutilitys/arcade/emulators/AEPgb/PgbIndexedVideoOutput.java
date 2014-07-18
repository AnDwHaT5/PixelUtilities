package com.pixelutilitys.arcade.emulators.AEPgb;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.*;

import java.util.*;
import java.io.*;

/**
 * PgbIndexedVideoOutput is a video output class that uses
 * a java.awt.image.MemoryImageSource with an IndexColorModel
 * to provide video output.
 * 
 * The indexed nature allows it to use the memory from 
 * PgbVideo directly, but it is less  accurate in some 
 * circumstances becasue it only changes palettes with 
 * vblank instead of every hblank.
 */
public final class PgbIndexedVideoOutput extends PgbVideoOutput {
	Graphics 				graph;
	MemoryImageSource		mISrc;
	Image					mImg;
	byte[]					packedScreenPalette;

	MemoryImageSource		borderMISrc;
	Image					borderMImg;
	ColorModel				borderColorModel;
	byte[]					packedBorderPalette;

	public void setGraphics()
	{
		graph = getGraphics();
	}
	
	public PgbIndexedVideoOutput(PgbVideo video) {
		super(video);
		// color model
		packedScreenPalette = new byte[64 * 3];

		mISrc = new MemoryImageSource(160, 144, new IndexColorModel(6, 64, packedScreenPalette, 0, false), video.getScreenMemory(), 0, 160);
		mISrc.setAnimated(true);

		mImg = createImage(mISrc);

		packedBorderPalette = new byte[0x300];
		borderColorModel = new IndexColorModel(8, 256, packedBorderPalette, 0, false);
		
		//borderMISrc = new MemoryImageSource(256, 224, borderColorModel, borderPixels, 0, 256);
		//borderMISrc.setAnimated(true);

		//borderMImg = createImage(borderMISrc);
	}

	public void reset() {
		;
	}
	
	public void hblank(int line) {
		;
	}
	
	public void vblank() {
		mISrc.newPixels(video.getScreenMemory(), getScreenColorModel(), 0, 160);
	}
	
	public void paint(Graphics g)
    {
		update(g);
	}
	
	public void makeScreenshot() {
		Date now = new Date();
	    long nowLong = now.getTime();
		int width = mImg.getWidth(this);
		int height = mImg.getHeight(this);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D biContext = bi.createGraphics();
		biContext.drawImage(mImg, 0, 0, null);
		try {
		ImageIO.write(bi, "png", new File("AEPgb-"+ nowLong+".png"));
		} catch(IOException ex) {}
	}
	
	public void update(Graphics g) {
		if(PgbSettings.sgbborder) {
			g.drawImage(mImg, 48 * PgbSettings.lcdsize, 40 * PgbSettings.lcdsize, 160 * PgbSettings.lcdsize, 144 * PgbSettings.lcdsize, this);
			//g.drawImage(borderMImg, 0, 0, 256 * PgbSettings.lcdsize, 224 * PgbSettings.lcdsize, this);
			// don't trust the border transparency
			g.drawImage(borderMImg, 0 * PgbSettings.lcdsize, 0 * PgbSettings.lcdsize, 256 * PgbSettings.lcdsize, 40 * PgbSettings.lcdsize, 0, 0, 256, 40, this);
			g.drawImage(borderMImg, 0 * PgbSettings.lcdsize, 40 * PgbSettings.lcdsize, 48 * PgbSettings.lcdsize, 184 * PgbSettings.lcdsize, 0, 40, 48, 184, this);
			g.drawImage(borderMImg, 208 * PgbSettings.lcdsize, 40 * PgbSettings.lcdsize, 256 * PgbSettings.lcdsize, 184 * PgbSettings.lcdsize, 208, 40, 256, 184, this);
			g.drawImage(borderMImg, 0 * PgbSettings.lcdsize, 184 * PgbSettings.lcdsize, 256 * PgbSettings.lcdsize, 224 * PgbSettings.lcdsize, 0, 184, 256, 224, this);
		} else {
			g.drawImage(mImg, 0, 0, 160 * PgbSettings.lcdsize, 144 * PgbSettings.lcdsize, this);
		}
	}
	
	public ColorModel getScreenColorModel() {
		if(PgbSettings.system == PgbSettings.SYS_GBC) {
			for(byte i = 0; i < 64; i++) {
				packedScreenPalette[i * 3 + 0] = video.getScreenRed(i);
				packedScreenPalette[i * 3 + 1] = video.getScreenGreen(i);
				packedScreenPalette[i * 3 + 2] = video.getScreenBlue(i);
			}
			return new IndexColorModel(6, 64, packedScreenPalette, 0, false);
		}
		// gb mono
		for(byte i = 0; i < 12; i++) {
			packedScreenPalette[i * 3 + 0] = video.getScreenRed(i);
			packedScreenPalette[i * 3 + 1] = video.getScreenGreen(i);
			packedScreenPalette[i * 3 + 2] = video.getScreenBlue(i);
		}
		return new IndexColorModel(4, 12, packedScreenPalette, 0, false);
	}
}
