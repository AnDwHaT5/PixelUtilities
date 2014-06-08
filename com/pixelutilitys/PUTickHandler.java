package com.pixelutilitys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import com.pixelmonmod.pixelmon.battles.BattleRegistry;
import com.pixelutilitys.entitys.TileEntityRadio;
import com.pixelutilitys.radioplayer.BattleMusicPlayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class PUTickHandler{
	public static TileEntityRadio radio = new TileEntityRadio();

	
	@SubscribeEvent
	public void tickStart(TickEvent.ClientTickEvent event) {
		
		
		BattleMusicPlayer playerRadio = null;
		if(Minecraft.getMinecraft().thePlayer != null)
		{
		String usernames[] = MinecraftServer.getServer().getAllUsernames();
		
		for(int i = 0; i < usernames.length; i++)
		{
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(usernames[i]);
			
			if(BattleRegistry.getBattle(player) == null && player.getEntityData().getInteger("Battling") == 1)
			{
				
				 //playerRadio.stop();
				
				if(playerRadio.isPlaying())
					playerRadio.stop();
				Basemod.battleMusicList.remove(playerRadio);
				
				
				player.getEntityData().setInteger("Battling", 0);
			}
			else if(BattleRegistry.getBattle(player) != null && player.getEntityData().getInteger("Battling") != 1)
			{
				
				player.getEntityData().setInteger("Battling", 1);
				
				playerRadio = new BattleMusicPlayer("http://www.youtube.com/watch?v=mTSpMl5jpPw&index=5&list=RDLqqjTHqYmiM");
				Basemod.battleMusicList.add(playerRadio);
				playerRadio.setVolume(100);
			}
		}
		}
	}
	
	public String parsePls(String plsurl){//contrary to popular belief I am not asking you to parse, the file format is .pls
		String out = "AudioNotFoundMaybeIShouldPutaPunchafaceSongHere";
		try {
			URL pls = new URL(plsurl);
			URLConnection con = pls.openConnection();
			BufferedReader i = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String mp3;
			while((mp3 = i.readLine()) != null){
				String f = mp3.trim();
				if(f.contains("http://")){//yes I am cheating here, go home
					out = f.substring(f.indexOf("http://"));
					break;
				}
				else
					if(f.contains("https://")){//yes I am cheating here, go home
						out = f.substring(f.indexOf("https://"));
						break;
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	public String takeFirstEntryFromM3U(String m3uurl){
		String out = "AudioNotFoundMaybeIShouldPutaPunchafaceSongHere";
		try {
			URL m3u = new URL(m3uurl);
			URLConnection con = m3u.openConnection();
			BufferedReader i = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String mp3;
			while((mp3 = i.readLine()) != null){
				if(!mp3.startsWith("#")){
					break;//just take the first noncomment
				}
			}
			out = mp3;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
}
