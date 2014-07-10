package com.pixelutilitys.radioplayer;

import javax.swing.JFrame;

import com.pixelutilitys.Basemod;
import com.pixelutilitys.gui.GuiRadio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class VLCPlayer implements Runnable {
	
	EmbeddedMediaPlayerComponent mediaPlayerComponent;

    String streamURL;
    boolean killed = false;
    int baseVolume = 150;

	public VLCPlayer(final String streamURL, Integer... newBaseVolume) {
        this.streamURL = streamURL;

        if(!Basemod.vlcLoaded)
            return;

        if(newBaseVolume.length > 0) {
            this.baseVolume = newBaseVolume[0];
            setVolume(50);
        }
        else {
            this.run();
        }
	}

    public void start()
    {
        this.run();
    }

	public void setVolume(float v2) {
        float musicLevel = Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC);
        float volume = (v2*musicLevel)*baseVolume;

		if(isPlaying())
		mediaPlayerComponent.getMediaPlayer().setVolume((int)volume);

        System.out.println(volume);
		
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


        if(GuiRadio.radio == null || GuiRadio.radio.isLooping())
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
