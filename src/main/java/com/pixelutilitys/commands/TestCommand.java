package com.pixelutilitys.commands;

import net.minecraft.server.MinecraftServer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import com.pixelmonmod.pixelmon.storage.PlayerNotLoadedException;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.util.ChatComponentTranslation;
import com.pixelmonmod.pixelmon.achievement.PixelmonAchievements;
import net.minecraft.entity.player.EntityPlayerMP;
import com.pixelmonmod.pixelmon.storage.PixelmonStorage;
import com.pixelmonmod.pixelmon.enums.EnumPokeballs;
import com.pixelmonmod.pixelmon.config.PixelmonEntityList;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.pixelmonmod.pixelmon.comm.ChatHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;

public class TestCommand extends CommandBase
{
    public String getCommandName() {
        return "haxygive";
    }

    public String getCommandUsage(final ICommandSender icommandsender) {
        return "/pokegive <player> <pokemon>";
    }

    public void processCommand(final ICommandSender par1ICommandSender, final String[] args) {
        final EntityPlayerMP sender = getPlayer(par1ICommandSender, par1ICommandSender.getCommandSenderName());
        if (args.length < 2) {
            ChatHandler.sendChat(sender, "pixelmon.command.general.invalid");
            ChatHandler.sendChat(sender, this.getCommandUsage(par1ICommandSender));
            return;
        }
        try {
            final EntityPlayer player = getPlayer(par1ICommandSender, args[0]);
            final String name = args[1];
            if (EnumPokemon.hasPokemon(name)) {
                final EntityPixelmon pokemon = (EntityPixelmon)PixelmonEntityList.createEntityByName(name, player.worldObj);
                if (args.length > 2) {
                    for (int i = 2; i < args.length; ++i) {
                        final String s = args[i];
                        if (s.equalsIgnoreCase("s")) {
                            pokemon.setIsShiny(true);
                        }
                        else if (s.startsWith("lvl")) {
                            try {
                                final int lvl = Integer.parseInt(s.replaceAll("[^0-9]", ""));
                                if (lvl <= 0 || lvl > 100) {
                                    ChatHandler.sendChat(sender, "pixelmon.command.general.cheater");
                                    return;
                                }
                                pokemon.getLvl().setLevel(lvl);
                            }
                            catch (Exception e2) {
                                ChatHandler.sendChat(sender, "pixelmon.command.general.lvlerror");
                                return;
                            }
                        }
                    }
                }
                pokemon.caughtBall = EnumPokeballs.PokeBall;

                pokemon.getEntityData().setBoolean("golden", true);

                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon);
                PixelmonAchievements.pokedexChieves(player);
                ChatHandler.sendChat(sender, "pixelmon.command.give.givesuccess", player.getDisplayName(), new ChatComponentTranslation("pixelmon." + EnumPokemon.get(name).name.toLowerCase() + ".name"));
            }
            else {
                ChatHandler.sendChat(sender, "pixelmon.command.general.notingame", name);
            }
        }
        catch (PlayerNotFoundException e3) {
            ChatHandler.sendChat(sender, "pixelmon.command.general.invalidplayer");
        }
        catch (PlayerNotLoadedException e) {
            e.printStackTrace();
        }
    }

    public List addTabCompletionOptions(final ICommandSender sender, final String[] args) {
        final ArrayList<String> pokemon = new ArrayList<>();
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
        }
        if (args.length == 2) {
            for (final EnumPokemon p : EnumPokemon.values()) {
                pokemon.add(p.name);
            }
            return getListOfStringsMatchingLastWord(args, (String[]) pokemon.toArray(new String[pokemon.size()]));
        }
        return null;
    }

    public int compareTo(final Object o) {
        return 0;
    }
}
