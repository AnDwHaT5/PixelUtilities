package com.pixelutilitys;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.pixelmonmod.pixelmon.enums.EnumApricorns;
import com.pixelmonmod.pixelmon.enums.EnumPokeballs;
import com.pixelutilitys.achievements.PixelUtilitysAchievements;
import com.pixelutilitys.commands.PokeRanCommand;
import com.pixelutilitys.commands.PokecheckmeCommand;
import com.pixelutilitys.commands.Rickroll;
import com.pixelutilitys.config.PixelUtilitysBlocks;
import com.pixelutilitys.config.PixelUtilitysConfig;
import com.pixelutilitys.config.PixelUtilitysItems;
import com.pixelutilitys.config.PixelUtilitysRecipes;
import com.pixelutilitys.entitys.BolderEntity;
import com.pixelutilitys.entitys.BoxEntity;
import com.pixelutilitys.entitys.ClothedTableEntity;
import com.pixelutilitys.entitys.PokeballEntity;
import com.pixelutilitys.entitys.RedCusionChairEntity;
import com.pixelutilitys.entitys.TileEntityRadio;
import com.pixelutilitys.entitys.TotodilePokedollEntity;
import com.pixelutilitys.entitys.TrashcanEntity;
import com.pixelutilitys.entitys.TreeEntity;
import com.pixelutilitys.entitys.YellowCusionChairEntity;
import com.pixelutilitys.events.ModRadioEvents;
import com.pixelutilitys.pokeballs.ShinyBall;
import com.pixelutilitys.radioplayer.BattleMusicPlayer;
import com.pixelutilitys.radioplayer.VLCPlayer;
import com.pixelutilitys.worldgen.AmethystGenerator;
import com.pixelutilitys.worldgen.CrystalGenerator;
import com.pixelutilitys.worldgen.RubyGenerator;
import com.pixelutilitys.worldgen.SaphireGenerator;
import com.pixelutilitys.worldgen.SiliconGenerator;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
//import PixelUtilitys.commands.FrontierBattleCommand;

@Mod(modid = "pixelutilitys", name = "PixelUtilitys", version = "2.5")//, dependencies = "required-after:pixelmon")
//@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class Basemod {
	
	
	

	public static ToolMaterial FIRESTONE = EnumHelper.addToolMaterial("FIRESTONE", 3, 1561, 8.0F, 3.0F, 10);
	public static ToolMaterial WATERSTONE = EnumHelper.addToolMaterial("WATERSTONE", 3, 1561, 8.0F, 3.0F, 10);
	public static ToolMaterial LEAFSTONE = EnumHelper.addToolMaterial("LEAFSTONE", 2, 250, 6.0F, 2.0F, 14);

	public static ToolMaterial RUBY = EnumHelper.addToolMaterial("RUBY", 2, 300, 6.5F, 2, 14);
	public static ToolMaterial SAPHIRE = EnumHelper.addToolMaterial("SAPHIRE", 2, 300, 6.5F, 2, 14);
	public static ToolMaterial AMETHYST = EnumHelper.addToolMaterial("AMETHYST", 2, 300, 6.5F, 2, 14);
	public static ToolMaterial CRYSTAL = EnumHelper.addToolMaterial("CRYSTAL", 2, 300, 6.5F, 2, 14);

	//	public static final Block Walrus = new WalrusBlock(3099, Material.iron).setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setUnlocalizedName("WalrusStatue");
	public static ArmorMaterial FIRESTONEA = EnumHelper.addArmorMaterial("FIRESTONEA", 40, new int[]{4, 8, 6, 4}, 10);
	public static ArmorMaterial WATERSTONEA = EnumHelper.addArmorMaterial("WATERSTONEA", 40, new int[]{4, 8, 6, 4}, 10);
	public static ArmorMaterial LEAFSTONEA = EnumHelper.addArmorMaterial("LEAFSTONEA", 15, new int[]{2, 6, 5, 2}, 9);


	public static ArmorMaterial RUBYA = EnumHelper.addArmorMaterial("RUBYA", 200, new int[] {3, 7, 6, 3}, 10);
	public static ArmorMaterial SAPHIREA = EnumHelper.addArmorMaterial("SAPHIREA", 200, new int[] {3, 7, 6, 3}, 10);
	public static ArmorMaterial CRYSTALA = EnumHelper.addArmorMaterial("CRYSTALA", 200, new int[] {3, 7, 6, 3}, 10);
	public static ArmorMaterial SILICONA = EnumHelper.addArmorMaterial("SILICONA", 200, new int[] {3, 7, 6, 3}, 10);
	
	public static boolean vlcLoaded = false;
	public static boolean is64bit = false;
	public static FMLEventChannel channel;
	public static List<VLCPlayer> playerList = new ArrayList<VLCPlayer>();
	public static List<BattleMusicPlayer> battleMusicList = new ArrayList<BattleMusicPlayer>();

	//In development biome //pokebiome
	//Biomes
	public static BiomeGenBase PokeBiome;

	private static boolean preInit = false, init = false, postInit = false;

	@Instance("pixelutilitys")
	public static Basemod instance;
	@SidedProxy(clientSide="com.pixelutilitys.ClientProxy",
			serverSide="com.pixelutilitys.CommonProxy")
	public static CommonProxy proxy;

	Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		AddMeta(event, "3.0");
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		PixelUtilitysConfig.loadConfig(config);
		config.save();
		PixelUtilitysAchievements.setupAchievements();
		//GameRegistry.registerCraftingHandler(new PixelUtilitysAchievements());
		//GameRegistry.registerPickupHandler(new PixelUtilitysPickupHandler());
		FMLCommonHandler.instance().bus().register(new ModRadioEvents());
		preInit = true;
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(new PUTickHandler());
		//NetworkRegistry.instance().registerConnectionHandler(new PixelUtilitysConnectionHandler());
		//NetworkRegistry.instance().registerConnectionHandler(new OnEntityJoin());
		PacketHandler.init();
		initVLC();
		init = true;
	}

	private void initVLC() {
		
		is64bit = Integer.parseInt(System.getProperty("sun.arch.data.model")) == 64;
		
		if(is64bit)
        {
			NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC"
            );
        }
		else
		{
			NativeLibrary.addSearchPath(
				RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files (x86)/VideoLAN/VLC"
			);
		}
		
        try{
        	Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        	vlcLoaded = true;
        }catch(UnsatisfiedLinkError error)
        {
        	System.out.println("You need to install VLC for this mod");
        }
		
	}

	@EventHandler
	public void load(FMLInitializationEvent event) 
	{		
		EnumApricorns apricorns[] = {EnumApricorns.Yellow,  EnumApricorns.Yellow, EnumApricorns.Yellow};
		ShinyBall.registerPokeball("ShinyBall", 20, 2, "pixelutilitys:shinyball", "shinyball", apricorns, 1, 2);
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(TreeEntity.class, "Tree");
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(BolderEntity.class, "Bolder");
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(BoxEntity.class, "Box");
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(ClothedTableEntity.class, "ClothedTable");
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(PokeballEntity.class, "Pokeball");
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(RedCusionChairEntity.class, "RedCusionChair");
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(TrashcanEntity.class, "Trashcan");
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(YellowCusionChairEntity.class, "YellowCusionChair");
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(TotodilePokedollEntity.class, "TotodileDoll");
		
		GameRegistry.registerTileEntity(TileEntityRadio.class, "Radio");
		
		//Creative Tabs
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabPixelmonBlocks", "en_US", "PixelmonBlocks");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabPixelmonBadges", "en_US", "Extra Badges");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabPixelUtilitysTools", "en_US", "PixelUtilitys Tools");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabPokefurniture", "en_US", "PokeFurniture");

		//Ore generation

		GameRegistry.registerWorldGenerator(new RubyGenerator(), 0);
		GameRegistry.registerWorldGenerator(new SaphireGenerator(), 0);
		GameRegistry.registerWorldGenerator(new AmethystGenerator(), 0);
		GameRegistry.registerWorldGenerator(new SiliconGenerator(), 0);
		GameRegistry.registerWorldGenerator(new CrystalGenerator(), 0);

		PixelUtilitysRecipes.addRecipes();

		//	GameRegistry.registerBlock(Walrus, "WalrusStatue");
		//LanguageRegistry.addName(Walrus, "Walrus Statue");
		//Block crafting
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.RubyBlock, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), PixelUtilitysItems.RubyItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.SaphireBlock, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), PixelUtilitysItems.SaphireItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.AmethystBlock, 1), new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), PixelUtilitysBlocks.AmethystBlock});

		/*	GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.firestoneHelm, 1), new Object[] { "XXX", "X X", "   ", Character.valueOf('X'), PixelmonItems.fireStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.firestonePlate, 1), new Object[] { "X X", "XXX", "XXX", Character.valueOf('X'), PixelmonItems.fireStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.firestoneLegs, 1), new Object[] { "XXX", "X X", "X X", Character.valueOf('X'), PixelmonItems.fireStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.firestoneBoots, 1), new Object[] { "X X", "X X", "   ", Character.valueOf('X'), PixelmonItems.fireStone});

		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.waterstoneHelm, 1), new Object[] { "XXX", "X X", "   ", Character.valueOf('X'), PixelmonItems.waterStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.waterstonePlate, 1), new Object[] { "X X", "XXX", "XXX", Character.valueOf('X'), PixelmonItems.waterStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.waterstoneLegs, 1), new Object[] { "XXX", "X X", "X X", Character.valueOf('X'), PixelmonItems.waterStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.waterstoneBoots, 1), new Object[] { "X X", "X X", Character.valueOf('X'), PixelmonItems.waterStone});

		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.leafstoneHelm, 1), new Object[] { "XXX", "X X", "   ", Character.valueOf('X'), PixelmonItems.leafStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.leafstonePlate, 1), new Object[] { "X X", "XXX", "XXX", Character.valueOf('X'), PixelmonItems.leafStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.leafstoneLegs, 1), new Object[] { "XXX", "X X", "X X", Character.valueOf('X'), PixelmonItems.leafStone});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.leafstoneBoots, 1), new Object[] { "X X", "X X", Character.valueOf('X'), PixelmonItems.leafStone});
		 */

		//Furnace Smelting

		//GameRegistry.addSmelting(3086, new ItemStack(PixelUtilitysItems.SiliconItem), 5);

		PixelUtilitysBlocks.RubyOre.setHarvestLevel("pickaxe", 2);
		PixelUtilitysBlocks.SaphireOre.setHarvestLevel("pickaxe", 2);
		PixelUtilitysBlocks.AmethystOre.setHarvestLevel("pickaxe", 2);
		PixelUtilitysBlocks.SiliconOre.setHarvestLevel("pickaxe", 2);
		PixelUtilitysBlocks.CrystalOre.setHarvestLevel("pickaxe", 2);

		MinecraftForge.EVENT_BUS.register(new CustomDrops());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{

		postInit = true;
	}

	@Mod.EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		if (MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager) {
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new PokecheckmeCommand());
			//((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new PokeKitCommand());
			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new PokeRanCommand());

			((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new Rickroll());
//((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new UtilitiesStaffCommand());
			//((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new PokeCheckCommand());
			//((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new FrontierBattleCommand());
		}
	}
	
	@Mod.EventHandler
	public void serverStop(FMLServerStoppedEvent event) {
		killAllStreams();
	}
	
	public static void killAllStreams(){
		for(VLCPlayer p : playerList){
			p.stop();
		}
	}

	/**
	 * whether or not PixelUtilitys has finished the {@link #preInit(FMLPreInitializationEvent) preInit} phase.
	 */
	public static boolean preInitialized(){return preInit;}

	/**
	 * whether or not PixelUtilitys has finished the {@link #load(FMLInitializationEvent) load} phase.
	 */
	public static boolean initialized(){return init;}

	/**
	 * whether or not PixelUtilitys has finished the {@link #modsLoaded(FMLPostInitializationEvent) modsLoaded} phase.
	 */
	public static boolean postInitialized(){return postInit;}

	/**
	 * Add Pack Meta Data
	 */
	private void AddMeta(FMLPreInitializationEvent event, String version) {

		ModMetadata m = event.getModMetadata(); // This is required or it will not work
		m.autogenerated = false; // This is required otherwise it will not work
		m.modId = "pixelutilitys";
		m.version = version;
		m.name = "PixelUtilitys";
		m.url = "http://www.pixelmonmod.com";
		m.updateUrl = "http://www.pixelmonmod.com";
		m.description = "A mod that adds Pokemon blocks and items into Pixelmon";
		m.authorList.add("AnDwHaT5");
	}
}
