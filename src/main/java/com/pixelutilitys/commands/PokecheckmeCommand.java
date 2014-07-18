package com.pixelutilitys.commands;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.enums.EnumGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class PokecheckmeCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "pokecheckme";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "/pokecheckme";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] astring) {
        // TODO Auto-generated method stub
        EntityPlayer player = (EntityPlayer) sender;
        player.openGui(Pixelmon.instance, EnumGui.PC.getIndex(), null, 0, 0, 0);
        ChatComponentTranslation success = new ChatComponentTranslation("You have successfuly opened your pc files!");
        sender.addChatMessage(success);
    }

    @Override
    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
