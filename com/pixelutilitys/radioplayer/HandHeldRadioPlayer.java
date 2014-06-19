package com.pixelutilitys.radioplayer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.pixelutilitys.gui.GuiRadio;
import com.pixelutilitys.tileentitys.TileEntityRadio;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class HandHeldRadioPlayer {

private EmbeddedMediaPlayerComponent mediaPlayerComponent;
private String url = "";
private String URL = "";

public HandHeldRadioPlayer(final String streamURL, boolean playNow)
{
this.url = streamURL;
if(playNow)
{
start();
setVolume(50);
}

}

public HandHeldRadioPlayer(final String streamURL) {
        this.url = streamURL;
start();
}

public void start()
{
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                JFrame frame = new JFrame("vlcj Tutorial");

                mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

                //quickhax to get the stream to not stop because im tired..
                frame.setContentPane(mediaPlayerComponent);
                frame.setLocation(10000, 10000);
                frame.setSize(0, 0);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);//needed for some streams (youtube)
                
                /*if(GuiRadio.radio.isLooping())
{*/
                mediaPlayerComponent.getMediaPlayer().setRepeat(true);//enable loop ;D
                /*}
else
{
mediaPlayerComponent.getMediaPlayer().setRepeat(false);
}*/
                mediaPlayerComponent.getMediaPlayer().playMedia(url.toString());
                
                frame.setVisible(false);
                
                System.out.println("Playing "+url);
                
                
                System.out.println("length "+mediaPlayerComponent.getMediaPlayer().getLength());
            }
        });

}


public void setVolume(float v2) {//TODO fix volume level generation
v2 *=50;
if(isPlaying())
mediaPlayerComponent.getMediaPlayer().setVolume((int) v2);

}

public boolean isPlaying() {
if(mediaPlayerComponent != null)
return true;
else
return false;
}

public void stop() {
if(isPlaying())
mediaPlayerComponent.getMediaPlayer().stop();
}
public String getURL()
{
	return URL;
}
public void setURL(String url)
{
	URL = url;
}
}