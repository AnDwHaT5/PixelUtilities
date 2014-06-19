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
import com.pixelutilitys.radioplayer.BattleMusicPlayer;
import com.pixelutilitys.tileentitys.TileEntityRadio;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class PUTickHandler{
	public static BattleMusicPlayer playerRadio = new BattleMusicPlayer("http://download1696.mediafire.com/8c0zd9upqaeg/n2cl170q0732i49/PixelMon+Theme+A.mp3", false);

	//http://www.youtube.com/watch?v=mTSpMl5jpPw&index=5&list=RDLqqjTHqYmiM
	//https://www.youtube.com/watch?v=eDfbtYOtNAU&list=RDLqqjTHqYmiM&index=3
	//https://www.youtube.com/watch?v=JuPx-3_8ssQ&index=4&list=RDLqqjTHqYmiM
	@SubscribeEvent
	public void playerTickStart(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		boolean inBattle = (BattleRegistry.getBattle(player) != null ? true:false);
		if(inBattle && player.getEntityData().getInteger("Battle") != 1)
		{
			playerRadio.start();
			//playerRadio.setVolume();
			player.getEntityData().setInteger("Battle", 1);
		}
		else if(!inBattle && player.getEntityData().getInteger("Battle") == 1){
			playerRadio.stop();
			player.getEntityData().setInteger("Battle", 0);
		}
	}
}