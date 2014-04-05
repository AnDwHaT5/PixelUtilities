/*package PixelUtilitys.commands;

import java.util.ArrayList;
import java.util.List;

import PixelUtilitys.config.PixelUtilitysConfig;
import PixelUtilitys.config.PixelUtilitysPokeKit;
import pixelmon.Pixelmon;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.config.PixelmonEntityList;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.enums.EnumBossMode;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumPokeballs;
import pixelmon.enums.EnumPokemon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class FrontierBattleCommand extends CommandBase {
PlayerStorage playerStorage1;
	@Override
	public String getCommandName() {
		return "frontierbattle";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/frontierbattle";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		EntityPlayer player = (EntityPlayer)sender;
		
		EntityPlayer oponent = getPlayer(sender, args[1]);
		EntityPlayer player1 = getPlayer(sender, args[0]);
		ArrayList<String> pokemon = new ArrayList<String>();
		
		for (EnumPokemon p : EnumPokemon.values())
			pokemon.add(p.name);
		getListOfStringsMatchingLastWord(pokemon.toArray(new String[] {}));
		
		int number = (int) (Math.random()*pokemon.size());
		int number2 = (int) (Math.random()*pokemon.size());
		int number3 = (int) (Math.random()*pokemon.size());
		int number4 = (int) (Math.random()*pokemon.size());
		int number5 = (int) (Math.random()*pokemon.size());
		int number6 = (int) (Math.random()*pokemon.size());
		EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemon.get(number), player.worldObj);
		EntityPixelmon pokemon2 = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemon.get(number2), player.worldObj);
		EntityPixelmon pokemon3 = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemon.get(number3), player.worldObj);
		EntityPixelmon pokemon4 = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemon.get(number4), player.worldObj);
		EntityPixelmon pokemon5 = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemon.get(number5), player.worldObj);
		EntityPixelmon pokemon6 = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemon.get(number6), player.worldObj);
		
		
		 try {
             pokemon1.caughtBall = EnumPokeballs.PokeBall;
             pokemon2.caughtBall = EnumPokeballs.PokeBall;
             pokemon3.caughtBall = EnumPokeballs.PokeBall;
             pokemon4.caughtBall = EnumPokeballs.PokeBall;
             pokemon5.caughtBall = EnumPokeballs.PokeBall;
             pokemon6.caughtBall = EnumPokeballs.PokeBall;
             pokemon1.getLvl().setLevel(50);
             pokemon2.getLvl().setLevel(50);
             pokemon3.getLvl().setLevel(50);
             pokemon4.getLvl().setLevel(50);
             pokemon5.getLvl().setLevel(50);
             pokemon6.getLvl().setLevel(50);
             PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player1).addToParty(pokemon1);
             PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player1).addToParty(pokemon2);
             PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player1).addToParty(pokemon3);
             PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player1).addToParty(pokemon4);
             PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player1).addToParty(pokemon5);
             PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player1).addToParty(pokemon6);
     }
     catch (PlayerNotLoadedException e) {
             System.out.println("Player error " + e.toString());
             e.printStackTrace();
     }
     catch(Exception e){
             System.out.println("random error " + e.toString());
     }
		 sender.sendChatToPlayer(ChatMessageComponent.createFromText("Pokemon has been given out. Good luck in the fight "+sender.getCommandSenderName()+"!"));
		//EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player1).getFirstAblePokemon(player1.worldObj);
		//EntityPixelmon Pokemon1 = PixelmonStorage.PokeballManager.getPlayerStorage(player1).getFirstAblePokemon(player1.worldObj);
	
		
	}
	
	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
	    ArrayList<String> pokemon = new ArrayList<String>();
	    if (args.length == 1)
	    {
	    return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
	 
	    }
	    if (args.length == 2)
	    {
	    return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
	 
	    }
	    return null;
	}

}
*/