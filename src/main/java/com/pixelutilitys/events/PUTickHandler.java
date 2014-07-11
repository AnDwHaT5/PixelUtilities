package com.pixelutilitys.events;

import com.pixelmonmod.pixelmon.client.ClientProxy;
import com.pixelutilitys.Basemod;
import com.pixelutilitys.radioplayer.VLCPlayer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;

import com.pixelutilitys.config.PixelUtilitysConfig;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.RenderPlayerEvent;

@SideOnly(Side.CLIENT)
public class PUTickHandler{
	public static VLCPlayer playerRadio = new VLCPlayer(PixelUtilitysConfig.BattleMusicURL, 50);

	//http://www.youtube.com/watch?v=mTSpMl5jpPw&index=5&list=RDLqqjTHqYmiM
	//https://www.youtube.com/watch?v=eDfbtYOtNAU&list=RDLqqjTHqYmiM&index=3
	//https://www.youtube.com/watch?v=JuPx-3_8ssQ&index=4&list=RDLqqjTHqYmiM

	@SubscribeEvent
	public void playerTickStart(TickEvent.PlayerTickEvent event) {
        if (PixelUtilitysConfig.battleMusicEnabled != true)
            return;

            EntityPlayer player = event.player;
            boolean inBattle = !ClientProxy.battleManager.battleEnded;

            FMLLog.fine("" + inBattle);
            if (inBattle && player.getEntityData().getInteger("Battle") != 1) {
                playerRadio.start();
                player.getEntityData().setInteger("Battle", 1);
            } else if (!inBattle && player.getEntityData().getInteger("Battle") == 1) {
                playerRadio.stop();
                player.getEntityData().setInteger("Battle", 0);
            }
	}


    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        System.out.println("world join ");
        if(!Basemod.vlcLoaded)//Display message in chat with link to vlc for arch
        {
            ChatStyle style = new ChatStyle().setUnderlined(true).setColor(EnumChatFormatting.GOLD);
            IChatComponent text = new ChatComponentText("You need to download VLC here to hear the radio ").setChatStyle(style);
            event.player.addChatComponentMessage(text);

            if(Basemod.is64bit)
            {
                text = new ChatComponentText("http://download.videolan.org/pub/videolan/vlc/last/win64/vlc-2.1.3-win64.exe").setChatStyle(style);
            }
            else
            {
                text = new ChatComponentText("http://download.videolan.org/pub/videolan/vlc/last/win32/vlc-2.1.3-win32.exe").setChatStyle(style);
            }
            event.player.addChatComponentMessage(text);
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