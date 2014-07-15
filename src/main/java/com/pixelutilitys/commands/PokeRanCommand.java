package com.pixelutilitys.commands;

import com.pixelmonmod.pixelmon.config.PixelmonEntityList;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;

import java.util.ArrayList;

public class PokeRanCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "pokerandom";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "/pokerandom";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] astring) {

        // TODO Auto-generated method stub
        EntityPlayer player = (EntityPlayer) sender;
        ArrayList<String> pokemon = new ArrayList<String>();

        for (EnumPokemon p : EnumPokemon.values())
            pokemon.add(p.name);
        getListOfStringsMatchingLastWord(pokemon.toArray(new String[]{}));

        int number = (int) (Math.random() * pokemon.size());
        EntityPixelmon p1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemon.get(number), player.worldObj);
        ChunkCoordinates cc = sender.getPlayerCoordinates();
        p1.setPosition(cc.posX, cc.posY + 1, cc.posZ);
        player.worldObj.spawnEntityInWorld(p1);
        p1.setAttackTarget((EntityPlayer) sender);
        //EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player1).getFirstAblePokemon(player1.worldObj);
        //EntityPixelmon Pokemon1 = PixelmonStorage.PokeballManager.getPlayerStorage(player1).getFirstAblePokemon(player1.worldObj);


    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
