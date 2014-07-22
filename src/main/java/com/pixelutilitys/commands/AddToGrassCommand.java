package com.pixelutilitys.commands;

import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.pixelutilitys.GrassSpawner;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.biome.BiomeGenBase;

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

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if(args.length != 2)
        {
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            return;
        }

        String pokemonName = args[0];
        String biomeName = args[1].replace("_"," ");//biome names don't have spaces

        GrassSpawner.getInstance().addBiomeEncounter(sender, pokemonName, biomeName);
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            ArrayList<String> pokemon = new ArrayList<>();
            for (EnumPokemon p : EnumPokemon.values())
                pokemon.add(p.name);
            return getListOfStringsFromIterableMatchingLastWord(args, pokemon);
        }
        if (args.length == 2) {
            ArrayList<String> biomeNames = new ArrayList<>();
            BiomeGenBase[] biomes = BiomeGenBase.getBiomeGenArray();
            for(BiomeGenBase biome : biomes)
                if(biome != null)
                    biomeNames.add(biome.biomeName.replace(" ","_"));
            return getListOfStringsFromIterableMatchingLastWord(args, biomeNames);
        }
        return null;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

}