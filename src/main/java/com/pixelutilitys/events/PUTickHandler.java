package com.pixelutilitys.events;

import com.pixelmonmod.pixelmon.api.events.*;
import com.pixelutilitys.Basemod;
import com.pixelutilitys.config.PixelUtilitysConfig;
import com.pixelutilitys.radioplayer.VLCPlayer;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

@SideOnly(Side.CLIENT)
@Optional.Interface(iface = "com.pixelmonmod.pixelmon.api.events.IPixelmonEventHandler", modid = "pixelmon")
public class PUTickHandler implements IPixelmonEventHandler {
    public static VLCPlayer playerRadio = new VLCPlayer(PixelUtilitysConfig.getInstance().BattleMusicURL, 50);
    public boolean inBattle = false;

    //http://www.youtube.com/watch?v=mTSpMl5jpPw&index=5&list=RDLqqjTHqYmiM
    //https://www.youtube.com/watch?v=eDfbtYOtNAU&list=RDLqqjTHqYmiM&index=3
    //https://www.youtube.com/watch?v=JuPx-3_8ssQ&index=4&list=RDLqqjTHqYmiM

    @Override
    public void eventFired(EventType eventType, EntityPlayer player, Object... objects) {
        if (!PixelUtilitysConfig.getInstance().battleMusicEnabled)
            return;

        switch(eventType)
        {
            case EventType.PlayerBattleStarted:
                playerRadio.start();
                break;

            case PlayerBattleEnded:
            case PlayerBattleEndedAbnormal:
                playerRadio.stop();
                break;
        }
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println("world join ");
        if (!Basemod.vlcLoaded)//Display message in chat with link to vlc for arch
        {
            ChatStyle style = new ChatStyle().setUnderlined(true).setColor(EnumChatFormatting.GOLD);
            IChatComponent text = new ChatComponentText("You need to download VLC here to hear the radio ").setChatStyle(style);
            event.player.addChatComponentMessage(text);

            if (Basemod.is64bit) {//TODO detect platform (mac/linux/windogs)
                text = new ChatComponentText("http://download.videolan.org/pub/videolan/vlc/last/win64/vlc-2.1.3-win64.exe").setChatStyle(style);
            } else {
                text = new ChatComponentText("http://download.videolan.org/pub/videolan/vlc/last/win32/vlc-2.1.3-win32.exe").setChatStyle(style);
            }
            event.player.addChatComponentMessage(text);
        }
    }

}
