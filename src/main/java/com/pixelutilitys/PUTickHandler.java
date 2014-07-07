package com.pixelutilitys;

import com.pixelutilitys.radioplayer.VLCPlayer;
import net.minecraft.entity.player.EntityPlayer;

import com.pixelmonmod.pixelmon.battles.BattleRegistry;
import com.pixelutilitys.config.PixelUtilitysConfig;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class PUTickHandler{
	public static VLCPlayer playerRadio = new VLCPlayer(PixelUtilitysConfig.BattleMusicURL, 50);

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