package com.pixelutilitys;

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
import cpw.mods.fml.common.FMLLog;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GrassSpawner {

    private static GrassSpawner instance;
    private Configuration spawnerConfig = null;
    String ssrates = "Special Spawn Rates";
    String pokeLists = "Encounter Lists";
    String[] blankArray = {"notConfigured"};

    static {
        if (instance == null) {
            instance = new GrassSpawner();
        }
    }


    private GrassSpawner() {

        spawnerConfig = new Configuration(new File("config/"+Basemod.MODID+"-spawner.cfg"));
        spawnerConfig.load();

        BiomeGenBase[] biomes = BiomeGenBase.getBiomeGenArray();
        for(BiomeGenBase biome : biomes)
        {
            if(biome == null)
                break;

            spawnerConfig.get(ssrates, biome.biomeName, 10).getInt();

            switch(biome.biomeName)
            {
                case "Plains":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListPlains).getStringList();
                    break;

                case "Jungle":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListJungle).getStringList();
                    break;

                case "Forest":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListForest).getStringList();
                    break;

                case "Extreme Hills":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListExtremeHills).getStringList();
                    break;

                case "Taiga":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListTaiga).getStringList();
                    break;

                case "Ice Plains":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListIcePlains).getStringList();
                    break;

                case "Beach":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListBeach).getStringList();
                    break;

                case "Desert":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListDesert).getStringList();
                    break;

                case "Ice Mountains":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListIceMountains).getStringList();
                    break;

                case "Ocean":
                    spawnerConfig.get(pokeLists, biome.biomeName, defaultEncounterListOcean).getStringList();
                    break;

                default:
                    spawnerConfig.get(pokeLists, biome.biomeName, blankArray).getStringList();
                    break;
            }
        }

        spawnerConfig.save();

    }

    public static GrassSpawner getInstance() {
        return instance;
    }

    private String[] defaultEncounterListPlains = {"Pikachu", "Pidgey", "Rattata", "Ponyta"};
    private String[] defaultEncounterListJungle = {"Oddish", "Paras", "Bellsprout", "Scyther"};
    private String[] defaultEncounterListForest = {"Pikachu", "Rattata", "Caterpie", "Weedle"};
    private String[] defaultEncounterListExtremeHills = {"Pidgey", "Pidgeotto", "Nidorino", "Geodude", "Machop"};
    private String[] defaultEncounterListTaiga = {"Swinub", "Slowpoke", "Magnemite", "Jynx"};
    private String[] defaultEncounterListIcePlains = {"Swinub", "Slowpoke", "Magnemite", "Jynx"};
    private String[] defaultEncounterListBeach = {"Slowpoke", "Staryu", "Shellder", "Psyduck"};
    private String[] defaultEncounterListDesert = {"Trapinch", "Sandshrew", "Sandile", "Geodude"};
    private String[] defaultEncounterListIceMountains = {"Swinub", "Slowpoke", "Magnemite", "Jynx"};
    private String[] defaultEncounterListOcean = {"Magikarp", "Staryu", "Goldeen", "Shellder"};

    private double xCoOrd;
    private double lastXCoOrd = 0;//moe if i catch you naming like this again i will hurt you.
    private double zCoOrd;
    private double lastZCoOrd = 0;
    BattleControllerBase bc;

    public void spawnInGrass(World world, int x, int y, int z, Entity entity) {
        if (!PixelUtilitysConfig.getInstance().grassBattles)
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

            if (isGrassBattle <= PixelUtilitysConfig.getInstance().grassSpawnRate) {
                if (PixelmonGrassBlock.isActive) {
                    processGrassBattle(world, x, y, z, player);
                }
            }
        }
        lastXCoOrd = xCoOrd;
        lastZCoOrd = zCoOrd;
    }

    private void processGrassBattle(World world, int x, int y, int z, EntityPlayerMP player) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        int specialRate = spawnerConfig.get(ssrates, biome.biomeName, 10).getInt();
        String[] encounters = spawnerConfig.get(pokeLists, biome.biomeName, blankArray).getStringList();

        spawnBattle(world, x, y, z, player, specialRate, encounters);

  }

    private void spawnBattle(World world, int x, int y, int z, EntityPlayerMP player, int spawnRate, String[] encounterList) {
        if(encounterList[0].equals("notConfigured"))
            return;

        int ranPoke = (int) (Math.random() * 10);
        int specRate = (int) (Math.random() * spawnRate);//TODO check this out..
        if (encounterList[ranPoke] != null) {
            try {
                EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(encounterList[ranPoke], world);
                spawnAndInitiate(world, x, y, z, player, pokemon);
            } catch (PlayerNotLoadedException e) {
                System.out.println("Error loading Player. " + e.toString());
            } catch (Exception e) {
                System.out.println("Error in Battling Pixelmon found in grass. " + e.toString());
            }
        }
    }

    private int getPlayerAvailablePokemonCount(EntityPlayerMP player) {
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
    private void spawnAndInitiate(final World world, final int x, final int y, final int z, final EntityPlayerMP player, final EntityPixelmon pokemon) throws Exception {
        pokemon.setPosition(x, y + 1, z);
        if (!world.isRemote)
            world.spawnEntityInWorld(pokemon);
        EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getFirstAblePokemon(world);
        WildPixelmonParticipant wildPixelmon = new WildPixelmonParticipant(pokemon);
        bc = new BattleController2Participant(new PlayerParticipant(player, player1firstPokemon), wildPixelmon);
    }

    public void addBiomeEncounter(ICommandSender sender, String pokemonName, String biomeName) {
        List<String> biomeEncounters = Arrays.asList(spawnerConfig.get(pokeLists, biomeName, blankArray).getStringList());

        if(biomeEncounters.contains(blankArray[0]))//not configured
        {
            biomeEncounters = new ArrayList<>();
        }
        if(EnumPokemon.get(pokemonName) != null)//check pokemon exists
        {
            biomeEncounters.add(pokemonName);
            spawnerConfig.get(pokeLists, biomeName, blankArray).set(biomeEncounters.toArray(new String[biomeEncounters.size()]));
            spawnerConfig.save();
            sender.addChatMessage(new ChatComponentText("Successfully added "+pokemonName+" to "+biomeName+" spawn list"));
        }
        else
        {
            sender.addChatMessage(new ChatComponentText(pokemonName+" is not a valid pokemon name"));
        }

    }


}