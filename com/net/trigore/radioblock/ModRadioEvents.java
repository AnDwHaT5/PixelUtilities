package com.net.trigore.radioblock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ModRadioEvents {
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event)
	{
		System.out.println("world join ");
		if(!ModRadioBlock.vlcLoaded)//Display message in chat with link to vlc for arch
		{
			ChatStyle style = new ChatStyle().setUnderlined(true).setColor(EnumChatFormatting.GOLD);
			IChatComponent text = new ChatComponentText("You need to download VLC here to hear the radio ").setChatStyle(style);
			event.player.addChatComponentMessage(text);
			
			if(ModRadioBlock.is64bit)
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

}
