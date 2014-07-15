package com.pixelutilitys.commands;

import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class AddToGrassCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "grassadd";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/grassadd <pokemon> <biome>";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        // TODO Auto-generated method stub

    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            ArrayList<String> pokemon = new ArrayList<String>();
            for (EnumPokemon p : EnumPokemon.values())
                pokemon.add(p.name);
            return getListOfStringsMatchingLastWord(args, pokemon.toArray(new String[]{}));
        }
        if (args.length == 2) {

        }
        return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

}