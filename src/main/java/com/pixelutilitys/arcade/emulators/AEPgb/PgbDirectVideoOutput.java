package com.pixelutilitys.arcade.emulators.AEPgb;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.applet.Applet;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * PgbDirectVideoOutput uses a java.awt.image.MemoryImageSource
 * with the default RGBColorModel to provide video output.
 */
public class PgbDirectVideoOutput extends PgbVideoOutput {
	Graphics 				graph;
	MemoryImageSource		screenMISrc;
	Image					screenMImg;
	int[]					screenMemory;

	MemoryImageSource		borderMISrc;
	Image					borderMImg;
	int[]					borderMemory;

	public void setGraphics()
	{
		graph = getGraphics();
	}

	public PgbDirectVideoOutput(PgbVideo video, Applet a) {
		super(video);
		System.out.println("applet video!");
		screenMemory = new int[160 * 144];
		screenMISrc = new MemoryImageSource(160, 144, screenMemory, 0, 160);
		screenMISrc.setAnimated(true);
		screenMImg = a.createImage(screenMISrc);
	}
	public PgbDirectVideoOutput(PgbVideo video) {
		super(video);
		// color model
		screenMemory = new int[160 * 144];
		screenMISrc = new MemoryImageSource(160, 144, screenMemory, 0, 160);
		screenMISrc.setAnimated(true);
		screenMImg = createImage(screenMISrc);

		borderMemory = new int[256 * 224];
		borderMISrc = new MemoryImageSource(256, 224, borderMemory, 0, 256);
		borderMISrc.setAnimated(true);
		borderMImg = createImage(borderMISrc);
	}
	
	public void reset() {
		;
	}
	
	public void hblank(int line) {
		int i, offset;
		offset = line * 160;
		for(i = 0; i < 160; i++) {
			screenMemory[offset + i] = video.getScreenColor(video.getScreenMemory(line * 160 + i));
		}
	}

	public void vblank() {
		screenMISrc.newPixels();
	}
	
	public void paint(Graphics g) {
		update(g);
	}
	
	public void update(Graphics g) {

        //TODO - update stuff in ere
		if(PgbSettings.sgbborder) {
			g.drawImage(screenMImg, 48 * PgbSettings.lcdsize, 40 * PgbSettings.lcdsize, 160 * PgbSettings.lcdsize, 144 * PgbSettings.lcdsize, this);
			//g.drawImage(borderMImg, 0, 0, 256 * PgbSettings.lcdsize, 224 * PgbSettings.lcdsize, this);
			// don't trust the border transparency
			g.drawImage(borderMImg, 0 * PgbSettings.lcdsize, 0 * PgbSettings.lcdsize, 256 * PgbSettings.lcdsize, 40 * PgbSettings.lcdsize, 0, 0, 256, 40, this);
			g.drawImage(borderMImg, 0 * PgbSettings.lcdsize, 40 * PgbSettings.lcdsize, 48 * PgbSettings.lcdsize, 184 * PgbSettings.lcdsize, 0, 40, 48, 184, this);
			g.drawImage(borderMImg, 208 * PgbSettings.lcdsize, 40 * PgbSettings.lcdsize, 256 * PgbSettings.lcdsize, 184 * PgbSettings.lcdsize, 208, 40, 256, 184, this);
			g.drawImage(borderMImg, 0 * PgbSettings.lcdsize, 184 * PgbSettings.lcdsize, 256 * PgbSettings.lcdsize, 224 * PgbSettings.lcdsize, 0, 184, 256, 224, this);
		} else {
			g.drawImage(screenMImg, 0, 0, 160 * PgbSettings.lcdsize, 144 * PgbSettings.lcdsize, this);
		}

        if(super.tempGraphics == null)
            super.tempGraphics = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);

        super.tempGraphics.getGraphics().drawImage(screenMImg, 0, 0, 256, 256, this);
	}
	
	public void makeScreenshot() {
		Date now = new Date();
	    long nowLong = now.getTime();
		int width = screenMImg.getWidth(this);
		int height = screenMImg.getHeight(this);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D biContext = bi.createGraphics();
		biContext.drawImage(screenMImg, 0, 0, null);
		try {
		ImageIO.write(bi, "png",  new File("AEPgb-" + nowLong+".png"));
		} catch(IOException ex) {}
	}
}
