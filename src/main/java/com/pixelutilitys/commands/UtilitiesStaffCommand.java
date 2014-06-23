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
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class UtilitiesStaffCommand extends CommandBase {
	
	public int getRequiredPermissionLevel()
    {
        return 0;
    }
	
	
	@Override
	public String getCommandName() {
		return "utilitiesstaff";
	}
	
	//@Override
	//public int getRequiredPermissionLevel() {
	//	return 1;
	//}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/utilitiesstaff";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
			EntityPlayer player = (EntityPlayer)sender;
			EnumGameType game;
			if (((EntityPlayer) player).getCommandSenderName().toLowerCase().equals("andwhat5") ||((EntityPlayer) player).getCommandSenderName().toLowerCase().equals("trigore") || ((EntityPlayer) player).getCommandSenderName().toLowerCase().equals("shadowowner"))
			{
			if(args.length != 2)
			{
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("Incorrect usage: /utilitiesstaff type args"));
			}
			// TODO Auto-generated method stub
			
			ArrayList<String> pokemon = new ArrayList<String>();
			
			for (EnumPokemon p : EnumPokemon.values())
				pokemon.add(p.name);
			getListOfStringsMatchingLastWord(pokemon.toArray(new String[] {}));
			if(args.length == 2 && args[0].equalsIgnoreCase("pokegive"))
            {
                   
                    EntityPixelmon pokemon1 = (EntityPixelmon) PixelmonEntityList.createEntityByName(args[1], player.worldObj);
                    
                    try {
                    	pokemon1.getLvl().setLevel(50);
                            pokemon1.caughtBall = EnumPokeballs.PremierBall;
                            PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(pokemon1);
                    }
                    catch (PlayerNotLoadedException e) {
                            System.out.println("Player error " + e.toString());
                            e.printStackTrace();
                    }
                    catch(Exception e){
                            System.out.println("random error " + e.toString());
                    }
			}
			
			if(args.length == 2 && args[0].equalsIgnoreCase("gm"))
			{
				if(args[1].equalsIgnoreCase("1"))
				{
					game = EnumGameType.CREATIVE;
					
					player.setGameType(game);
				}
				if(args[1].equalsIgnoreCase("0"))
				{
					game = EnumGameType.SURVIVAL;
					player.setGameType(game);
					
				}
			}
			
			
			
		}
	else
		{
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("You are not on the staff whitelist!"));
			return;
		}
		
		
	}
		//EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player1).getFirstAblePokemon(player1.worldObj);
		//EntityPixelmon Pokemon1 = PixelmonStorage.PokeballManager.getPlayerStorage(player1).getFirstAblePokemon(player1.worldObj);

}
*/