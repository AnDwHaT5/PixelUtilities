package com.pixelutilitys.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.pixelutilitys.GrassSpawner;

public class PixelUtilitysConfig {

	private Configuration configuration;

    private static PixelUtilitysConfig instance;

	// Call Once
	private  boolean called = false;

	public  boolean coinDrops = false;
	public  boolean grassBattles = false;
	public  boolean onlyGrassSpawns = false;
	public  int grassSpawnRate;
	public  int waterSpawnRate;
	public  boolean waterBattles = false;
	public  int coinDropRate;

	private  String[] forestWildEncounters = new String[10];
	public  EnumPokemon[] forestEncounterList;
	private  String[] extremeHillsWildEncounters = new String[10];
	public  EnumPokemon[] extremeHillsEncounterList;
	private  String[] plainsWildEncounters = new String[10];
	public  EnumPokemon[] plainsEncounterList;
	private  String[] taigaWildEncounters = new String[10];
	public  EnumPokemon[] taigaEncounterList;
	private  String[] jungleWildEncounters = new String[10];
	public  EnumPokemon[] jungleEncounterList;
	private  String[] icePlainsWildEncounters = new String[10];
	public  EnumPokemon[] icePlainsEncounterList;
	private  String[] iceMountainsWildEncounters = new String[10];
	public  EnumPokemon[] iceMountainsEncounterList;
	private  String[] beachWildEncounters = new String[10];
	public  EnumPokemon[] beachEncounterList;
	private  String[] desertWildEncounters = new String[10];
	public  EnumPokemon[] desertEncounterList;

	private  String[] oceanWildEncounters = new String[10];
	public  EnumPokemon[] oceanEncounterList;

	public  int forestSpecialRate;
	public  int extremeHillsSpecialRate;
	public  int plainsSpecialRate;
	public  int jungleSpecialRate;
	public  int taigaSpecialRate;
	public  int beachSpecialRate;
	public  int icePlainsSpecialRate;
	public  int iceMountainsSpecialRate;
	public  int desertSpecialRate;

	public  int oceanSpecialRate;

	public  String BattleMusicURL;
	public  boolean battleMusicEnabled;

    static {
        if(instance == null)
        {
            instance = new PixelUtilitysConfig();
        }
    }

    public static PixelUtilitysConfig getInstance()
    {
        return instance;
    }

    private PixelUtilitysConfig()
    {

    }

    public void setEvent(FMLPreInitializationEvent event)
    {
        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();
        this.loadConfig();
        configuration.save();
    }

	private void loadConfig() {

		PixelUtilitysTools.load(this.configuration);
		PixelUtilitysItems.load(this.configuration);
		PixelUtilitysBlocks.load(this.configuration);
		PixelUtilitysArmor.load(this.configuration);
		//PixelUtilitysPokeKit.load(configuration);
		PixelUtilitysBlocks.addNames();
		PixelUtilitysItems.addNames();
		PixelUtilitysTools.addNames();
		PixelUtilitysArmor.addNames();

		coinDrops = this.configuration.get("general", "Make Pixelmon drop PokeCoins", false).getBoolean(false);
		grassBattles = this.configuration.get("general", "Allow pixelmon spawning blocks to spawn pixelmon", true).getBoolean(true);
		onlyGrassSpawns = this.configuration.get("general", "Only spawn Pixelmon in grass", false).getBoolean(false);
		grassSpawnRate = this.configuration.get("general", "Pixelmon in grass spawn rate", 200).getInt();
		coinDropRate = this.configuration.get("general", "Pixelmon coin drop rate", 4).getInt();
		waterSpawnRate = this.configuration.get("general", "Pixelmon in water spawn rate", 400).getInt();

		//encounter rates for extra pokes
		forestSpecialRate = this.configuration.get("Biome Encounters", "Forest Special Rate", 10).getInt();
		extremeHillsSpecialRate = this.configuration.get("Biome Encounters", "Extreme Hills Special Rate", 10).getInt();
		taigaSpecialRate = this.configuration.get("Biome Encounters", "Taiga Special Rate", 10).getInt();
		plainsSpecialRate = this.configuration.get("Biome Encounters", "Plains Special Rate", 10).getInt();
		jungleSpecialRate = this.configuration.get("Biome Encounters", "Jungle Special Rate", 10).getInt();
		iceMountainsSpecialRate = this.configuration.get("Biome Encounters", "Ice Mountains Special Rate", 10).getInt();
		icePlainsSpecialRate = this.configuration.get("Biome Encounters", "Ice Plains Special Rate", 10).getInt();
		beachSpecialRate = this.configuration.get("Biome Encounters", "Beach Special Rate", 10).getInt();
		desertSpecialRate = this.configuration.get("Biome Encounters", "Desert Special Rate", 10).getInt();
		BattleMusicURL = this.configuration.get("General", "BattleMusic Music Song URL (If youtube make sure http not https)", "http://www.youtube.com/watch?v=WnkhVPmapc8").getString();
		battleMusicEnabled = this.configuration.get("General", "Battle Music Enabled", true).getBoolean(true);
		this.configuration.save();
	}
	

}