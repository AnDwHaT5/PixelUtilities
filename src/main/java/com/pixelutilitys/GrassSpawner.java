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

import java.util.Random;

public class GrassSpawner {

    public static EnumPokemon[] encounterListP = {EnumPokemon.get("Pikachu"), EnumPokemon.get("Pidgey"), EnumPokemon.get("Rattata"), EnumPokemon.get("Ponyta"), null, null, null, null, null, null};
    public static EnumPokemon[] encounterListJ = {EnumPokemon.get("Oddish"), EnumPokemon.get("Paras"), EnumPokemon.get("Bellsprout"), EnumPokemon.get("Scyther"), null, null, null, null, null, null};
    public static EnumPokemon[] encounterListF = {EnumPokemon.get("Pikachu"), EnumPokemon.get("Rattata"), EnumPokemon.get("Caterpie"), EnumPokemon.get("Weedle"), null, null, null, null, null, null};
    public static EnumPokemon[] encounterListEH = {EnumPokemon.get("Pidgey"), EnumPokemon.get("Pidgeotto"), EnumPokemon.get("Nidorino"), EnumPokemon.get("Geodude"), EnumPokemon.get("Machop"), null, null, null, null, null};
    public static EnumPokemon[] encounterListT = {EnumPokemon.get("Swinub"), EnumPokemon.get("Slowpoke"), EnumPokemon.get("Magnemite"), EnumPokemon.get("Jynx"), null, null, null, null, null, null};
    public static EnumPokemon[] encounterListIP = {EnumPokemon.get("Swinub"), EnumPokemon.get("Slowpoke"), EnumPokemon.get("Magnemite"), EnumPokemon.get("Jynx"), null, null, null, null, null, null};
    public static EnumPokemon[] encounterListB = {EnumPokemon.get("Slowpoke"), EnumPokemon.get("Staryu"), EnumPokemon.get("Shellder"), EnumPokemon.get("Psyduck"), null, null, null, null, null, null};
    public static EnumPokemon[] encounterListD = {EnumPokemon.get("Trapinch"), EnumPokemon.get("Sandshrew"), EnumPokemon.get("Sandile"), EnumPokemon.get("Geodude"), null, null, null, null, null, null};
    public static EnumPokemon[] encounterListIM = {EnumPokemon.get("Swinub"), EnumPokemon.get("Slowpoke"), EnumPokemon.get("Magnemite"), EnumPokemon.get("Jynx"), null, null, null, null, null, null};
    public static EnumPokemon[] encounterListW = {EnumPokemon.get("Magikarp"), EnumPokemon.get("Staryu"), EnumPokemon.get("Goldeen"), EnumPokemon.get("Shellder"), null, null, null, null, null, null};

    private static double xCoOrd;
    private static double lastXCoOrd = 0;
    private static double zCoOrd;
    private static double lastZCoOrd = 0;

    static BattleControllerBase bc;

    public static void spawnInGrass(World world, int x, int y, int z, Entity entity) {
        if (!PixelUtilitysConfig.grassBattles)
            return;


        if (entity instanceof EntityPlayerMP) {

            EntityPlayerMP player = (EntityPlayerMP) entity;
            int availablePokemon = getPlayerAvailablePokemonCount(player);
            //Is player already in a battle
            if (BattleRegistry.getBattle(player) != null || availablePokemon == 0) {
                return;
            }
            Random random = new Random(System.currentTimeMillis());
            int isGrassBattle = random.nextInt(100);
            int isWaterBattle = random.nextInt(100);

            xCoOrd = x;
            zCoOrd = z;

            if (xCoOrd == lastXCoOrd && zCoOrd == lastZCoOrd)
                return;

            if (isGrassBattle <= PixelUtilitysConfig.grassSpawnRate) {
                if (PixelmonGrassBlock.isActive)
                {
                    processGrassBattle(world, x, y, z, player);
                }
            }
            lastXCoOrd = xCoOrd;
            lastZCoOrd = zCoOrd;
            if (isWaterBattle <= PixelUtilitysConfig.waterSpawnRate) {
                if (PokeWaterFlowing.isActive || PokeWaterStill.isActive) {
                    processWaterBattle(world, x, y, z, player);
                }
            }
        }
        lastXCoOrd = xCoOrd;
        lastZCoOrd = zCoOrd;
    }

    private static void processWaterBattle(World world, int x, int y, int z, EntityPlayerMP player) {

        spawnBattle(world, x, y, z, player, PixelUtilitysConfig.oceanSpecialRate, encounterListW);
    }

    private static void processGrassBattle(World world, int x, int y, int z, EntityPlayerMP player) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);


        if (biome.biomeName.equalsIgnoreCase("taiga")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.taigaSpecialRate, encounterListT);
        } else if (biome.biomeName.equalsIgnoreCase("jungle")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.jungleSpecialRate, encounterListJ);
        } else if (biome.biomeName.equalsIgnoreCase("forest")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.forestSpecialRate, encounterListF);
        } else if (biome.biomeName.equalsIgnoreCase("plains")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.plainsSpecialRate, encounterListP);
        } else if (biome.biomeName.equalsIgnoreCase("extreme hills")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.extremeHillsSpecialRate, encounterListEH);
        } else if (biome.biomeName.equalsIgnoreCase("Ice Plains")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.icePlainsSpecialRate, encounterListIP);
        } else if (biome.biomeName.equalsIgnoreCase("Ice Mountains")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.iceMountainsSpecialRate, encounterListIM);
        } else if (biome.biomeName.equalsIgnoreCase("beach")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.beachSpecialRate, encounterListB);
        } else if (biome.biomeName.equalsIgnoreCase("desert")) {
            spawnBattle(world, x, y, z, player, PixelUtilitysConfig.desertSpecialRate, encounterListD);
        }
    }

    private static void spawnBattle(World world, int x, int y, int z, EntityPlayerMP player, int spawnRate, EnumPokemon[] encounterList) {
        int ranPoke = (int) (Math.random() * 10);
        int specRate = (int) (Math.random() * spawnRate);
        if (encounterList[ranPoke] != null) {
            try {
                EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterList[ranPoke].name, world);
                spawnAndInitiate(world, x, y, z, player, pokemon);
            } catch (PlayerNotLoadedException e) {
                System.out.println("Error loading Player. " + e.toString());
            } catch (Exception e) {
                System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
            }
        }
    }

    private static int getPlayerAvailablePokemonCount(EntityPlayerMP player) {
        int availablePokemon = 0;
        try {
            availablePokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player).countAblePokemon();
        } catch (PlayerNotLoadedException e) {
            e.printStackTrace();
        }
        return availablePokemon;
    }

    /**
     * Spawns a pokemon in the world and initiates a battle between the player and the pokemon
     *
     * @param world   The dimension to use
     * @param x       The X coordinate
     * @param y       The Y coordinate
     * @param z       The z coordinate
     * @param player  The player
     * @param pokemon - the pokemon to spawn
     * @throws Exception Bad Move should not throw generic Exception
     */
    private static void spawnAndInitiate(final World world, final int x, final int y, final int z, final EntityPlayerMP player, final EntityPixelmon pokemon) throws Exception {
        pokemon.setPosition(x, y + 1, z);
        if (!world.isRemote)
            world.spawnEntityInWorld(pokemon);
        EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getFirstAblePokemon(world);
        WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
        bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
    }

    public static void setEncounterList(EnumPokemon[] forest, EnumPokemon[] extremeHillsEncounterList, EnumPokemon[] plainsEncounterList, EnumPokemon[] taigaEncounterList, EnumPokemon[] jungleEncounterList, EnumPokemon[] icePlainsEncounterList, EnumPokemon[] iceMountainsEncounterList, EnumPokemon[] beachEncounterList, EnumPokemon[] desertEncounterList, EnumPokemon[] oceanEncounterList) {
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

}