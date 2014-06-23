/*package PixelUtilitys.commands;
 
import java.util.ArrayList;
import java.util.List;
 
import PixelUtilitys.config.PixelUtilitysPokeKit;
import pixelmon.Pixelmon;
import pixelmon.achievement.PixelmonAchievements;
import pixelmon.config.PixelmonEntityList;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumBossMode;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumPokeballs;
import pixelmon.enums.EnumPokemon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
 
public class PokeKitCommand extends CommandBase {
 
        @Override
        public String getCommandName() {
                return "pokekit";
        }
 
        @Override
        public int getRequiredPermissionLevel() {
                return 2;
        }
 
        @Override
        public String getCommandUsage(ICommandSender icommandsender) {
                return "/pokekit kitname player";
        }
 
        @Override
        public void processCommand(ICommandSender sender, String[] args) {
                EntityPlayer player = getPlayer(sender, args[1]);
       
                if(args.length != 2)
                {
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Incorrect usage: /pokekit kitname player"));
                }
               
                if (args.length == 2) {
               
                if (player == null) {
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText(args[1] + " does not exist."));
                        return;
                }
                }
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit1"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit11, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit12, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit13, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit14, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit15, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit16, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 1 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 1");
                       
                }
                
                
                
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit2"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit21, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit22, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit23, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit24, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit25, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit26, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 2 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 2");
                       
                }
                
                
                
                
                
                
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit3"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit31, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit32, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit33, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit34, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit35, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit36, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 3 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 3");
                       
                }
                
                
                
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit4"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit41, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit42, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit43, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit44, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit45, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit46, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 4 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 4");
                       
                }
                
                
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit5"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit51, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit52, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit53, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit54, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit55, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit56, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 5 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 5");
                       
                }
                
                
                
                
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit6"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit61, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit62, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit63, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit64, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit65, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit66, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 6 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 6");
                       
                }
                
                
                
                
                
                
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit7"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit71, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit72, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit73, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit74, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit75, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit76, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 7 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 7");
                       
                }
                
                
                
                
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit8"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit81, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit82, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit83, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit84, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit85, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit86, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 8 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 8");
                       
                }
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit9"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit91, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit92, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit93, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit94, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit95, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit96, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 9 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 9");
                       
                }
                
                
                
                
                
                
                
                
                
                if(args.length == 2 && args[0].equalsIgnoreCase("kit10"))
                {
                       
                        EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit101, player.worldObj);
                        EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit102, player.worldObj);
                        EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit103, player.worldObj);
                        EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit104, player.worldObj);
                        EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit105, player.worldObj);
                        EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(PixelUtilitysPokeKit.pkit106, player.worldObj);
                       
                        try {
                                pokemon1.caughtBall = EnumPokeballs.PokeBall;
                                pokemon2.caughtBall = EnumPokeballs.PokeBall;
                                pokemon3.caughtBall = EnumPokeballs.PokeBall;
                                pokemon4.caughtBall = EnumPokeballs.PokeBall;
                                pokemon5.caughtBall = EnumPokeballs.PokeBall;
                                pokemon6.caughtBall = EnumPokeballs.PokeBall;
                               
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon2);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon3);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon4);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon5);
                                PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon6);
                        }
                        catch (PlayerNotLoadedException e) {
                                System.out.println("Player error " + e.toString());
                                e.printStackTrace();
                        }
                        catch(Exception e){
                                System.out.println("random error " + e.toString());
                        }
                        sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " PokeKit 10 "));
                        notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " PokeKit 10");
                       
                }
        }
       
        @Override
        public List addTabCompletionOptions(ICommandSender sender, String[] args) {
                ArrayList<String> pokemon = new ArrayList<String>();
                if (args.length == 1)
                        return getListOfStringsMatchingLastWord(args, "kit1", "kit2","kit3", "kit4", "kit5","kit6","kit7","kit8","kit9","kit10");
                else if (args.length == 2) {
                        for (EnumPokemon p : EnumPokemon.values())
                                pokemon.add(p.name);
                        return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
                }
                return null;
        }
 
}*/