package com.pixelutilitys;

import com.pixelutilitys.radioplayer.VLCPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;

import com.pixelmonmod.pixelmon.battles.BattleRegistry;
import com.pixelutilitys.config.PixelUtilitysConfig;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

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
			player.getEntityData().setInteger("Battle", 1);
		}
		else if(!inBattle && player.getEntityData().getInteger("Battle") == 1){
			playerRadio.stop();
			player.getEntityData().setInteger("Battle", 0);
		}
	}


    EntityChicken chicken = null;

    @SubscribeEvent
    public void makeAndwhatsDuck(RenderPlayerEvent.Pre event)
    {
        if(event.entityPlayer.getUniqueID().toString().equalsIgnoreCase("e978a5b23ea74f10acde1c220967c338")) {

            event.setCanceled(true);

            EntityPlayer player = event.entityPlayer;

            if (chicken == null) {
                chicken = new EntityChicken(event.entityPlayer.getEntityWorld());
                player.getEntityWorld().spawnEntityInWorld(chicken);
            }

            chicken.setPosition(player.posX, player.posY, player.posZ);
            chicken.setRotationYawHead(player.getRotationYawHead());
            RenderManager.instance.renderEntityWithPosYaw(chicken, player.posX, player.posY, player.posZ, 1f, 0f);

        }
    }
}