package com.pixelutilitys.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import com.pixelutilitys.Basemod;
import com.pixelutilitys.radioplayer.VLCPlayer;

public class Rickroll extends CommandBase{

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "rickroll";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		// TODO Auto-generated method stub
		VLCPlayer playerRadio = null;
		playerRadio = new VLCPlayer("http://www.youtube.com/watch?v=dQw4w9WgXcQ");
		Basemod.playerList.add(playerRadio);
		
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
