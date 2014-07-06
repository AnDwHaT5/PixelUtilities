package com.pixelutilitys.radioplayer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.pixelutilitys.gui.GuiRadio;
import com.pixelutilitys.tileentitys.TileEntityRadio;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class VLCPlayer implements Runnable {
	
	EmbeddedMediaPlayerComponent mediaPlayerComponent;

    String streamURL;

	public VLCPlayer(final String streamURL) {
        this.streamURL = streamURL;
        this.run();
	}

	public void setVolume(float v2) {//TODO fix volume level generation
		v2 *=200;
		if(isPlaying())
		mediaPlayerComponent.getMediaPlayer().setVolume((int) v2);
		
	}

	public boolean isPlaying() {
		if(mediaPlayerComponent != null)
			return mediaPlayerComponent.getMediaPlayer().isPlaying();
		else
			return false;
	}

	public void stop() {
        mediaPlayerComponent.release();
	}

    @Override
    public void run() {
        JFrame frame = new JFrame("vlcj Tutorial");

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        //quickhax to get the stream to not stop because im tired..
        frame.setContentPane(mediaPlayerComponent);
        frame.setLocation(10000, 10000);
        frame.setSize(0, 0);

        //Forge security manager is litterally hitler, get rid of this
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);//needed for some streams (youtube)

        if(GuiRadio.radio.isLooping())
        {
            mediaPlayerComponent.getMediaPlayer().setRepeat(true);//enable loop ;D
        }
        else
        {
            mediaPlayerComponent.getMediaPlayer().setRepeat(false);
        }
        mediaPlayerComponent.getMediaPlayer().playMedia(this.streamURL.toString());

        frame.setVisible(false);

        System.out.println("Playing "+this.streamURL);


        System.out.println("length "+mediaPlayerComponent.getMediaPlayer().getLength());


    }
}
