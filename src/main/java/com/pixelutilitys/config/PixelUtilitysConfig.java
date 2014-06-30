package com.pixelutilitys.config;

import net.minecraftforge.common.config.Configuration;

import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.pixelutilitys.GrassSpawner;

public class PixelUtilitysConfig {

	public static Configuration config;

	// Call Once
	private static boolean called = false;

	public static boolean coinDrops = false;
	public static boolean grassBattles = false;
	public static boolean onlyGrassSpawns = false;
	public static int grassSpawnRate;
	public static int waterSpawnRate;
	public static boolean waterBattles = false;
	public static int coinDropRate;

	private static String[] forestWildEncounters = new String[10];
	public static EnumPokemon[] forestEncounterList;
	private static String[] extremeHillsWildEncounters = new String[10];
	public static EnumPokemon[] extremeHillsEncounterList;
	private static String[] plainsWildEncounters = new String[10];
	public static EnumPokemon[] plainsEncounterList;
	private static String[] taigaWildEncounters = new String[10];
	public static EnumPokemon[] taigaEncounterList;
	private static String[] jungleWildEncounters = new String[10];
	public static EnumPokemon[] jungleEncounterList;
	private static String[] icePlainsWildEncounters = new String[10];
	public static EnumPokemon[] icePlainsEncounterList;
	private static String[] iceMountainsWildEncounters = new String[10];
	public static EnumPokemon[] iceMountainsEncounterList;
	private static String[] beachWildEncounters = new String[10];
	public static EnumPokemon[] beachEncounterList;
	private static String[] desertWildEncounters = new String[10];
	public static EnumPokemon[] desertEncounterList;

	private static String[] oceanWildEncounters = new String[10];
	public static EnumPokemon[] oceanEncounterList;

	public static int forestSpecialRate;
	public static int extremeHillsSpecialRate;
	public static int plainsSpecialRate;
	public static int jungleSpecialRate;
	public static int taigaSpecialRate;
	public static int beachSpecialRate;
	public static int icePlainsSpecialRate;
	public static int iceMountainsSpecialRate;
	public static int desertSpecialRate;

	public static int oceanSpecialRate;

	public static void loadConfig(Configuration configuration) {

		config = configuration;
		PixelUtilitysTools.load(config);
		PixelUtilitysItems.load(config);
		PixelUtilitysBlocks.load(config);
		PixelUtilitysArmor.load(config);
		//PixelUtilitysPokeKit.load(config);
		PixelUtilitysBlocks.addNames();
		PixelUtilitysItems.addNames();
		PixelUtilitysTools.addNames();
		PixelUtilitysArmor.addNames();

		coinDrops = config.get("general", "Make Pixelmon drop PokeCoins", false).getBoolean(false);
		grassBattles = config.get("general", "Allow pixelmon spawning blocks to spawn pixelmon", true).getBoolean(true);
		onlyGrassSpawns = config.get("general", "Only spawn Pixelmon in grass", false).getBoolean(false);
		grassSpawnRate = config.get("general", "Pixelmon in grass spawn rate", 200).getInt();
		coinDropRate = config.get("general", "Pixelmon coin drop rate", 4).getInt();
		waterSpawnRate = config.get("general", "Pixelmon in water spawn rate", 400).getInt();

		writeEncounterListsToConfig();

		//encounter rates for extra pokes
		forestSpecialRate = config.get("Biome Encounters", "Forest Special Rate", 10).getInt();
		extremeHillsSpecialRate = config.get("Biome Encounters", "Extreme Hills Special Rate", 10).getInt();
		taigaSpecialRate = config.get("Biome Encounters", "Taiga Special Rate", 10).getInt();
		plainsSpecialRate = config.get("Biome Encounters", "Plains Special Rate", 10).getInt();
		jungleSpecialRate = config.get("Biome Encounters", "Jungle Special Rate", 10).getInt();
		iceMountainsSpecialRate = config.get("Biome Encounters", "Ice Mountains Special Rate", 10).getInt();
		icePlainsSpecialRate = config.get("Biome Encounters", "Ice Plains Special Rate", 10).getInt();
		beachSpecialRate = config.get("Biome Encounters", "Beach Special Rate", 10).getInt();
		desertSpecialRate = config.get("Biome Encounters", "Desert Special Rate", 10).getInt();

		config.save();
		fillEncounterLists();
	}
	
	/**
	 * @author MoeBoy76
	 * 
	 * @return writes each encounter pokemon into the config
	 */

	private static void writeEncounterListsToConfig() {
		
		for(int i = 0; i < forestWildEncounters.length; i++){
			if(i >= 8){
				forestWildEncounters[i] = config.get("Biome Encounters", "Special Forest Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListF[i] != null){
				forestWildEncounters[i] = config.get("Biome Encounters", "Forest Pokemon " + (i + 1), GrassSpawner.encounterListF[i].name).getString();
			}
			else{
				forestWildEncounters[i] = config.get("Biome Encounters", "Forest Pokemon " + (i + 1), "null").getString();
			}
		}
		for(int i = 0; i < extremeHillsWildEncounters.length; i++){
			if(i >= 8){
				extremeHillsWildEncounters[i] = config.get("Biome Encounters", "Special Extreme Hills Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListEH[i] != null){
				extremeHillsWildEncounters[i] = config.get("Biome Encounters", "Extreme Hills Pokemon " + (i + 1), GrassSpawner.encounterListEH[i].name).getString();
			}
			else{
				extremeHillsWildEncounters[i] = config.get("Biome Encounters", "Extreme Hills Pokemon " + (i + 1), "null").getString();
			}
		}
		for(int i = 0; i < plainsWildEncounters.length; i++){
			if(i >= 8){
				plainsWildEncounters[i] = config.get("Biome Encounters", "Special Plains Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListP[i] != null){
				plainsWildEncounters[i] = config.get("Biome Encounters", "Plains Pokemon " + (i + 1), GrassSpawner.encounterListP[i].name).getString();
			}
			else{
				plainsWildEncounters[i] = config.get("Biome Encounters", "Plains Pokemon " + (i + 1), "null").getString();
			}
		}
		for(int i = 0; i < taigaWildEncounters.length; i++){
			if(i >= 8){
				taigaWildEncounters[i] = config.get("Biome Encounters", "Special Taiga Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListT[i] != null){
				taigaWildEncounters[i] = config.get("Biome Encounters", "Taiga Pokemon " + (i + 1), GrassSpawner.encounterListT[i].name).getString();
			}
			else{
				taigaWildEncounters[i] = config.get("Biome Encounters", "Taiga Pokemon " + (i + 1), "null").getString();
			}
		}
		for(int i = 0; i < jungleWildEncounters.length; i++){
			if(i >= 8){
				jungleWildEncounters[i] = config.get("Biome Encounters", "Special Jungle Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListJ[i] != null){
				jungleWildEncounters[i] = config.get("Biome Encounters", "Jungle Pokemon " + (i + 1), GrassSpawner.encounterListJ[i].name).getString();
			}
			else{
				jungleWildEncounters[i] = config.get("Biome Encounters", "Jungle Pokemon " + (i + 1), "null").getString();
			}
		}
		for(int i = 0; i < icePlainsWildEncounters.length; i++){
			if(i >= 8){
				icePlainsWildEncounters[i] = config.get("Biome Encounters", "Special Ice Plains Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListIP[i] != null){
				icePlainsWildEncounters[i] = config.get("Biome Encounters", "Ice Plains Pokemon " + (i + 1), GrassSpawner.encounterListIP[i].name).getString();
			}
			else{
				icePlainsWildEncounters[i] = config.get("Biome Encounters", "Ice Plains Pokemon " + (i + 1), "null").getString();
			}
		}
		for(int i = 0; i < iceMountainsWildEncounters.length; i++){
			if(i >= 8){
				iceMountainsWildEncounters[i] = config.get("Biome Encounters", "Special Ice Mountains Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListIM[i] != null){
				iceMountainsWildEncounters[i] = config.get("Biome Encounters", "Ice Mountains Pokemon " + (i + 1), GrassSpawner.encounterListIM[i].name).getString();
			}
			else{
				iceMountainsWildEncounters[i] = config.get("Biome Encounters", "Ice Mountains Pokemon " + (i + 1), "null").getString();
			}
		}
		for(int i = 0; i < beachWildEncounters.length; i++){
			if(i >= 8){
				beachWildEncounters[i] = config.get("Biome Encounters", "Special Beach Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListB[i] != null){
				beachWildEncounters[i] = config.get("Biome Encounters", "Beach Pokemon " + (i + 1), GrassSpawner.encounterListB[i].name).getString();
			}
			else{
				beachWildEncounters[i] = config.get("Biome Encounters", "Beach Pokemon " + (i + 1), "null").getString();
			}
		}
		for(int i = 0; i < desertWildEncounters.length; i++){
			if(i >= 8){
				desertWildEncounters[i] = config.get("Biome Encounters", "Special Desert Pokemon " + (i - 7), "null").getString();
			}
			else if(GrassSpawner.encounterListD[i] != null){
				desertWildEncounters[i] = config.get("Biome Encounters", "Desert Pokemon " + (i + 1), GrassSpawner.encounterListD[i].name).getString();
			}
			else{
				desertWildEncounters[i] = config.get("Biome Encounters", "Desert Pokemon " + (i + 1), "null").getString();
			}

		}



		for(int i = 0; i < oceanWildEncounters.length; i++){
			if(i >= 8){
				oceanWildEncounters[i] = config.get("Biome Encounters", "Special Ocean Pokemon " + (i - 7), "null").getString();
			}

			else if(GrassSpawner.encounterListW[i] != null){
				oceanWildEncounters[i] = config.get("Biome Encounters", "Ocean Pokemon " + (i + 1), GrassSpawner.encounterListW[i].name).getString();
			}
			else{
				oceanWildEncounters[i] = config.get("Biome Encounters", "Ocean Pokemon " + (i + 1), "null").getString();
			}

		}
	}
	


	/**
	 * @author MoeBoy76
	 * 
	 * @return fills the encounter lists with nulls for unspecified pokemon, needs changing to an ArrayList
	 * 
	 */
	private static void fillEncounterLists() {

		forestEncounterList = new EnumPokemon[forestWildEncounters.length];
		for(int i=0; i<forestWildEncounters.length; i++) {
			if(!forestWildEncounters[i].equalsIgnoreCase("NULL")) {
				forestEncounterList[i] = EnumPokemon.get(forestWildEncounters[i]);
			} else {
				forestEncounterList[i] = null;
			}
		}
		extremeHillsEncounterList = new EnumPokemon[extremeHillsWildEncounters.length];
		for(int i=0; i<extremeHillsWildEncounters.length; i++) {
			if(!extremeHillsWildEncounters[i].equalsIgnoreCase("NULL")) {
				extremeHillsEncounterList[i] = EnumPokemon.get(extremeHillsWildEncounters[i]);
			} else {
				extremeHillsEncounterList[i] = null;
			}
		}
		plainsEncounterList = new EnumPokemon[plainsWildEncounters.length];
		for(int i=0; i<plainsWildEncounters.length; i++) {
			if(!plainsWildEncounters[i].equalsIgnoreCase("NULL")) {
				plainsEncounterList[i] = EnumPokemon.get(plainsWildEncounters[i]);
			} else {
				plainsEncounterList[i] = null;
			}
		}
		jungleEncounterList = new EnumPokemon[jungleWildEncounters.length];
		for(int i=0; i<jungleWildEncounters.length; i++) {
			if(!jungleWildEncounters[i].equalsIgnoreCase("NULL")) {
				jungleEncounterList[i] = EnumPokemon.get(jungleWildEncounters[i]);
			} else {
				jungleEncounterList[i] = null;
			}
		}
		taigaEncounterList = new EnumPokemon[taigaWildEncounters.length];
		for(int i=0; i<taigaWildEncounters.length; i++) {
			if(!taigaWildEncounters[i].equalsIgnoreCase("NULL")) {
				taigaEncounterList[i] = EnumPokemon.get(taigaWildEncounters[i]);
			} else {
				taigaEncounterList[i] = null;
			}
		}
		icePlainsEncounterList = new EnumPokemon[icePlainsWildEncounters.length];
		for(int i=0; i<icePlainsWildEncounters.length; i++) {
			if(!icePlainsWildEncounters[i].equalsIgnoreCase("NULL")) {
				icePlainsEncounterList[i] = EnumPokemon.get(icePlainsWildEncounters[i]);
			} else {
				icePlainsEncounterList[i] = null;
			}
		}
		iceMountainsEncounterList = new EnumPokemon[iceMountainsWildEncounters.length];
		for(int i=0; i<iceMountainsWildEncounters.length; i++) {
			if(!iceMountainsWildEncounters[i].equalsIgnoreCase("NULL")) {
				iceMountainsEncounterList[i] = EnumPokemon.get(iceMountainsWildEncounters[i]);
			} else {
				iceMountainsEncounterList[i] = null;
			}
		}
		beachEncounterList = new EnumPokemon[beachWildEncounters.length];
		for(int i=0; i<beachWildEncounters.length; i++) {
			if(!beachWildEncounters[i].equalsIgnoreCase("NULL")) {
				beachEncounterList[i] = EnumPokemon.get(beachWildEncounters[i]);
			} else {
				beachEncounterList[i] = null;
			}
		}
		desertEncounterList = new EnumPokemon[desertWildEncounters.length];
		for(int i=0; i<desertWildEncounters.length; i++) {
			if(!desertWildEncounters[i].equalsIgnoreCase("NULL")) {
				desertEncounterList[i] = EnumPokemon.get(desertWildEncounters[i]);
			} else {
				desertEncounterList[i] = null;
			}
		}

		oceanEncounterList = new EnumPokemon[oceanWildEncounters.length];
		for(int i=0; i<oceanWildEncounters.length; i++) {
			if(!oceanWildEncounters[i].equalsIgnoreCase("NULL")) {
				oceanEncounterList[i] = EnumPokemon.get(oceanWildEncounters[i]);
			} else {
				oceanEncounterList[i] = null;
			}
		}


	}
	
	/**
	 * @author MoeBoy76
	 * 
	 * loads the encounters into the spawner class
	 */
	public static void reloadEncounters() {
		GrassSpawner.setEncounterList(forestEncounterList, extremeHillsEncounterList, plainsEncounterList, taigaEncounterList, jungleEncounterList, 
				icePlainsEncounterList, iceMountainsEncounterList, beachEncounterList, desertEncounterList, oceanEncounterList);
	}
}