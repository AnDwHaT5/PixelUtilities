package com.pixelutilitys.radioplayer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.pixelutilitys.gui.GuiRadio;
import com.pixelutilitys.tileentitys.TileEntityRadio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class VLCPlayer implements Runnable {
	
	EmbeddedMediaPlayerComponent mediaPlayerComponent;

    String streamURL;
    boolean killed = false;

	public VLCPlayer(final String streamURL) {
        this.streamURL = streamURL;
        this.run();
	}

	public void setVolume(float v2) {//TODO fix volume level generation
        float musicLevel = Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC);
        float volume = (v2*musicLevel)*150;

		if(isPlaying())
		mediaPlayerComponent.getMediaPlayer().setVolume((int)volume);
		
	}

	public boolean isPlaying() {
		if(!killed && mediaPlayerComponent != null)
			return mediaPlayerComponent.getMediaPlayer().isPlaying();
		else
			return false;
	}

	public void stop() {
        if(mediaPlayerComponent != null)
        mediaPlayerComponent.release();
        killed = true;
	}

    @Override
    public void run() {
        JFrame frame = new JFrame("vlcj Tutorial");

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        frame.setContentPane(mediaPlayerComponent);
        frame.setLocation(10000, 10000);
        frame.setSize(0, 0);
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

    }
}
