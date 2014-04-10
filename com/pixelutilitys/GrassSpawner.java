package com.pixelutilitys;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import com.pixelmonmod.pixelmon.battles.BattleRegistry;
import com.pixelmonmod.pixelmon.battles.controller.BattleController2Participant;
import com.pixelmonmod.pixelmon.battles.controller.BattleControllerBase;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.WildPixelmonParticipant;
import com.pixelmonmod.pixelmon.config.PixelmonEntityList;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.pixelmonmod.pixelmon.storage.PixelmonStorage;
import com.pixelmonmod.pixelmon.storage.PlayerNotLoadedException;
import com.pixelutilitys.blocks.PixelmonGrassBlock;
import com.pixelutilitys.blocks.PokeWaterFlowing;
import com.pixelutilitys.blocks.PokeWaterStill;
import com.pixelutilitys.config.PixelUtilitysConfig;

public class GrassSpawner {

	public static EnumPokemon[] encounterListP = {EnumPokemon.Pikachu, EnumPokemon.Pidgey, EnumPokemon.Rattata, EnumPokemon.Ponyta, null, null, null, null, null, null};
	public static EnumPokemon[] encounterListJ = {EnumPokemon.Oddish, EnumPokemon.Paras, EnumPokemon.Bellsprout, EnumPokemon.Scyther, null, null, null, null, null, null};
	public static EnumPokemon[] encounterListF = {EnumPokemon.Pikachu, EnumPokemon.Rattata, EnumPokemon.Caterpie, EnumPokemon.Weedle, null, null, null, null, null, null};
	public static EnumPokemon[] encounterListEH = {EnumPokemon.Pidgey, EnumPokemon.Pidgeotto, EnumPokemon.Nidorino, EnumPokemon.Geodude, EnumPokemon.Machop, null, null, null, null, null};
	public static EnumPokemon[] encounterListT = {EnumPokemon.Swinub, EnumPokemon.Slowpoke, EnumPokemon.Magnemite, EnumPokemon.Jynx, null, null, null, null, null, null};
	public static EnumPokemon[] encounterListIP = {EnumPokemon.Swinub, EnumPokemon.Slowpoke, EnumPokemon.Magnemite, EnumPokemon.Jynx, null, null, null, null, null, null};
	public static EnumPokemon[] encounterListB = {EnumPokemon.Slowpoke, EnumPokemon.Staryu, EnumPokemon.Shellder, EnumPokemon.Psyduck, null, null, null, null, null, null};
	public static EnumPokemon[] encounterListD = {EnumPokemon.Trapinch, EnumPokemon.Sandshrew, EnumPokemon.Sandile, EnumPokemon.Geodude, null, null, null, null, null, null};
	public static EnumPokemon[] encounterListIM = {EnumPokemon.Swinub, EnumPokemon.Slowpoke, EnumPokemon.Magnemite, EnumPokemon.Jynx, null, null, null, null, null, null};
	public static EnumPokemon[] encounterListW = {EnumPokemon.Magikarp, EnumPokemon.Staryu, EnumPokemon.Goldeen, EnumPokemon.Shellder, null, null, null, null, null, null};

	private static double xCoOrd;
	private static double lastXCoOrd = 0;
	private static double zCoOrd;
	private static double lastZCoOrd = 0;

	static BattleControllerBase bc;

	public static void spawnInGrass(World world, int x, int y, int z, Entity entity){
		if(!PixelUtilitysConfig.grassBattles){
			return;
		}

		if(entity instanceof EntityPlayerMP){

			EntityPlayerMP player = (EntityPlayerMP) entity;
			if (BattleRegistry.getBattle(player) != null) {
				return;
			}
			int isBattle = (int) (Math.random()*PixelUtilitysConfig.grassSpawnRate);
			int waterBattle = (int) (Math.random()*PixelUtilitysConfig.waterSpawnRate);

			xCoOrd = x;
			zCoOrd = z;
			if(xCoOrd == lastXCoOrd && zCoOrd == lastZCoOrd){
				return;
			}

			if(isBattle == 25)
			{
				BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
				if(PixelmonGrassBlock.isActive == true)
				{
					if(biome.biomeName.equalsIgnoreCase("taiga")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.taigaSpecialRate);
						if(encounterListT[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListT.length - 2) && specRate == 1){
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListT[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else{
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListT[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							} 
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
					else if(biome.biomeName.equalsIgnoreCase("jungle")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.jungleSpecialRate);
						if(encounterListJ[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListJ.length - 2) && specRate == 1){
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListJ[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else{
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListJ[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							} 
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
					else if(biome.biomeName.equalsIgnoreCase("forest")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.forestSpecialRate);
						if(encounterListF[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListF.length - 2) && specRate == 1){
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListF[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else{
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListF[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							} 
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
					else if(biome.biomeName.equalsIgnoreCase("plains")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.plainsSpecialRate);
						if(encounterListP[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListP.length - 2) && specRate == 1){
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListP[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else{
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListP[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							} 
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
					else if(biome.biomeName.equalsIgnoreCase("extreme hills")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.extremeHillsSpecialRate);
						if(encounterListEH[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListEH.length - 2) && specRate == 1){
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListEH[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else{
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListEH[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							} 
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
					else if(biome.biomeName.equalsIgnoreCase("Ice Plains")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.icePlainsSpecialRate);
						if(encounterListIP[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListIP.length - 2) && specRate == 1){
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListIP[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else{
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListIP[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							} 
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
					else if(biome.biomeName.equalsIgnoreCase("Ice Mountains")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.iceMountainsSpecialRate);
						if(encounterListIM[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListIM.length - 2) && specRate == 1){
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListIM[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else{
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListIM[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							} 
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
					else if(biome.biomeName.equalsIgnoreCase("beach")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.beachSpecialRate);
						if(encounterListB[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListB.length - 2) && specRate == 1){
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListB[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else{
									EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListB[ranPoke].name, world);
									pokemon.setPosition(x + 1, y + 1, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pokemon);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							} 
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
					else if(biome.biomeName.equalsIgnoreCase("desert")){
						int ranPoke = (int) (Math.random()*10);
						int specRate = (int) (Math.random()*PixelUtilitysConfig.desertSpecialRate);	            	
						if(encounterListD[ranPoke]!=null){
							try{
								if(ranPoke >= (encounterListD.length - 2) && specRate == 1){
									EntityPixelmon pixelmonEntity = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListD[ranPoke].name, world);
									pixelmonEntity.setPosition(x, y + 2, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pixelmonEntity);
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pixelmonEntity);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
								else {
									EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
									EntityPixelmon pixelmonEntity = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListD[ranPoke].name, world);
									pixelmonEntity.setPosition(x, y + 2, z);
									if(!world.isRemote)
										world.spawnEntityInWorld(pixelmonEntity);
									if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
										return;
									WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pixelmonEntity);
									bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
								}
							}
							catch(PlayerNotLoadedException e){
								System.out.println("Error loading Player. " + e.toString());
							}
							catch (Exception e) {
								System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
							}
						}
					}
				}
			}

			lastXCoOrd = xCoOrd;
			lastZCoOrd = zCoOrd;

			if(waterBattle == 25)
			{
				if (PokeWaterFlowing.isActive == true || PokeWaterStill.isActive == true)
				{
					int ranPoke = (int) (Math.random()*10);
					int specRate = (int) (Math.random()*PixelUtilitysConfig.oceanSpecialRate);
					if(encounterListB[ranPoke]!=null){
						try{
							if(ranPoke >= (encounterListW.length - 2) && specRate == 1){
								EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListW[ranPoke].name, world);
								pokemon.setPosition(x + 1, y + 1, z);
								if(!world.isRemote)
									world.spawnEntityInWorld(pokemon);
								EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
								if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
									return;
								WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
								bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
							}
							else{
								EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterListW[ranPoke].name, world);
								pokemon.setPosition(x + 1, y + 1, z);
								if(!world.isRemote)
									world.spawnEntityInWorld(pokemon);
								EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entity).getFirstAblePokemon(world);
								if (player1firstPokemon == null || player1firstPokemon.isFainted || player1firstPokemon.equals(null))
									return;
								WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
								bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
							}
						}
						catch(PlayerNotLoadedException e){
							System.out.println("Error loading Player. " + e.toString());
						} 
						catch (Exception e) {
							System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
						}
					}
				}
			}
		}
		lastXCoOrd = xCoOrd;
		lastZCoOrd = zCoOrd;
	}
	
	public static void setEncounterList(EnumPokemon[] forest, EnumPokemon[] extremeHillsEncounterList, EnumPokemon[] plainsEncounterList, EnumPokemon[] taigaEncounterList, EnumPokemon[] jungleEncounterList, EnumPokemon[] icePlainsEncounterList, EnumPokemon[] iceMountainsEncounterList, EnumPokemon[] beachEncounterList, EnumPokemon[] desertEncounterList, EnumPokemon[] oceanEncounterList){
		encounterListF = forest;
		encounterListB = beachEncounterList;
		encounterListEH = extremeHillsEncounterList;
		encounterListIM = iceMountainsEncounterList;
		encounterListIP = icePlainsEncounterList;
		encounterListJ = jungleEncounterList;
		encounterListP = plainsEncounterList;
		encounterListT = taigaEncounterList;
		encounterListD = desertEncounterList;
		encounterListW = oceanEncounterList;
	}
	public static void onlyGrassSpawns(World world){
		//world.getEntityByID(PixelmonConfig.idPixelmon).setDead();

	}
}