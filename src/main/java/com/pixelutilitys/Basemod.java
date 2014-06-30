package com.pixelutilitys;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.item.Item;
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

import com.pixelutilitys.achievements.PixelUtilitysAchievements;
import com.pixelutilitys.blocks.PokeballStatueBlock;
import com.pixelutilitys.commands.PokecheckmeCommand;
import com.pixelutilitys.config.PixelUtilitysBlocks;
import com.pixelutilitys.config.PixelUtilitysConfig;
import com.pixelutilitys.config.PixelUtilitysItems;
import com.pixelutilitys.config.PixelUtilitysRecipes;
import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;
import com.pixelutilitys.entitys.SeatEntity;
import com.pixelutilitys.events.ModRadioEvents;
import com.pixelutilitys.radioplayer.BattleMusicPlayer;
import com.pixelutilitys.radioplayer.HandHeldRadioPlayer;
import com.pixelutilitys.radioplayer.VLCPlayer;
import com.pixelutilitys.tileentitys.BolderEntity;
import com.pixelutilitys.tileentitys.BoxEntity;
import com.pixelutilitys.tileentitys.ClothedTableEntity;
import com.pixelutilitys.tileentitys.PokeballEntity;
import com.pixelutilitys.tileentitys.RedCusionChairEntity;
import com.pixelutilitys.tileentitys.TileEntityConveyor;
import com.pixelutilitys.tileentitys.TileEntityRadio;
import com.pixelutilitys.tileentitys.TotodilePokedollEntity;
import com.pixelutilitys.tileentitys.TrashcanEntity;
import com.pixelutilitys.tileentitys.TreeEntity;
import com.pixelutilitys.tileentitys.YellowCusionChairEntity;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
//import PixelUtilitys.commands.FrontierBattleCommand;

@Mod(modid = Basemod.MODID, name = Basemod.NAME, version = Basemod.VERSION)//, dependencies = "required-after:pixelmon")

public class Basemod 
{
	public final static String MODID = "pixelutilities";
	public final static String NAME = "PixelUtilities";
	public final static String VERSION = "3.0";

	public static ToolMaterial FIRESTONE = EnumHelper.addToolMaterial("FIRESTONE", 3, 1561, 8.0F, 3.0F, 10);
	public static ToolMaterial WATERSTONE = EnumHelper.addToolMaterial("WATERSTONE", 3, 1561, 8.0F, 3.0F, 10);
	public static ToolMaterial LEAFSTONE = EnumHelper.addToolMaterial("LEAFSTONE", 2, 250, 6.0F, 2.0F, 14);

	public static ToolMaterial RUBY = EnumHelper.addToolMaterial("RUBY", 2, 300, 6.5F, 2, 14);
	public static ToolMaterial SAPHIRE = EnumHelper.addToolMaterial("SAPHIRE", 2, 300, 6.5F, 2, 14);
	public static ToolMaterial AMETHYST = EnumHelper.addToolMaterial("AMETHYST", 2, 300, 6.5F, 2, 14);
	public static ToolMaterial CRYSTAL = EnumHelper.addToolMaterial("CRYSTAL", 2, 300, 6.5F, 2, 14);

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
	public static HandHeldRadioPlayer localMusicPlayer = null;

	//In development biome //pokebiome
	//Biomes
	public static BiomeGenBase PokeBiome;

	private static boolean preInit = false, init = false, postInit = false;

	@Instance(MODID)
	public static Basemod instance;
	@SidedProxy(clientSide="com.pixelutilitys.ClientProxy",	serverSide="com.pixelutilitys.CommonProxy")
	public static CommonProxy proxy;

	Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		AddMeta(event, VERSION);
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		PixelUtilitysConfig.loadConfig(config);
		config.save();
		PixelUtilitysAchievements.setupAchievements();
		//GameRegistry.registerCraftingHandler(new PixelUtilitysAchievements());
		//GameRegistry.registerPickupHandler(new PixelUtilitysPickupHandler());
		//FMLCommonHandler.instance().bus().register(new ModRadioEvents());

		EntityRegistry.registerModEntity(SeatEntity.class, "Seat", 0, this, 3, 1, false);
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
		proxy.registerRenderThings();
		GameRegistry.registerTileEntity(TreeEntity.class, "Tree");
		GameRegistry.registerTileEntity(BolderEntity.class, "Bolder");
		GameRegistry.registerTileEntity(BoxEntity.class, "Box");
		GameRegistry.registerTileEntity(ClothedTableEntity.class, "ClothedTable");
		GameRegistry.registerTileEntity(PokeballEntity.class, "Pokeball");
		GameRegistry.registerTileEntity(RedCusionChairEntity.class, "RedCusionChair");
		GameRegistry.registerTileEntity(TrashcanEntity.class, "Trashcan");
		GameRegistry.registerTileEntity(YellowCusionChairEntity.class, "YellowCusionChair");
		GameRegistry.registerTileEntity(TotodilePokedollEntity.class, "TotodileDoll");

		GameRegistry.registerTileEntity(TileEntityRadio.class, "Radio");
		GameRegistry.registerTileEntity(TileEntityConveyor.class, "Conveyor");

		//Ore generation

		GameRegistry.registerWorldGenerator(new RubyGenerator(), 0);
		GameRegistry.registerWorldGenerator(new SaphireGenerator(), 0);
		GameRegistry.registerWorldGenerator(new AmethystGenerator(), 0);
		GameRegistry.registerWorldGenerator(new SiliconGenerator(), 0);
		GameRegistry.registerWorldGenerator(new CrystalGenerator(), 0);

		PixelUtilitysRecipes.addRecipes();		

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
			//((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new UtilitiesStaffCommand());
			//((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new PokeCheckCommand());
			//((ServerCommandManager) MinecraftServer.getServer().getCommandManager()).registerCommand(new FrontierBattleCommand());
		}

		//Here be dragons.
		Set blocks = GameData.getBlockRegistry().getKeys();
		Object[] debugblocks = blocks.toArray();
		System.out.println("blocklength " + debugblocks.length);


		for(int i = 0; i < debugblocks.length; i++)
		{
			try{
				String blockreg = (String)debugblocks[i];

				if(blockreg.startsWith("minecraft:"))
					continue;

				Block block = GameData.getBlockRegistry().getObject(blockreg);
				String localName = block.getLocalizedName();

				if(localName.contains("tile"))
				{
					System.out.println("Block "+blockreg+" Doesn't seem to have a name set!");
					System.out.println(block.getLocalizedName());
					System.out.println();
				}

				if(block.getCreativeTabToDisplayOn() == null)
				{
					System.out.println("Block "+blockreg+" Doesn't seem to have a creative tab set!");
					System.out.println();
				}


			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		/*
		Set items = GameData.getItemRegistry().getKeys();
		Object[] debugitems = blocks.toArray();
		System.out.println("itemlength "+debugitems.length);


		for(int i = 0; i < debugitems.length; i++)
		{
			try{
			String itemreg = (String)debugitems[i];

			if(itemreg.startsWith("minecraft:"))
				continue;

			Item item = GameData.getItemRegistry().getObject(itemreg);
			String localName = item.getUnlocalizedName();

			if(localName.contains("tile"))
			{
				System.out.println("Item "+itemreg+" Doesn't seem to have a name set!");
				System.out.println(item.getUnlocalizedName());
				System.out.println();
			}

			if(item.getCreativeTabToDisplayOn() == null)
			{
				System.out.println("Item "+itemreg+" Doesn't seem to have a creative tab set!");
				System.out.println();
			}


			}catch(Exception e) {
				e.printStackTrace();
			}
		}*/
		//////////////////////////////////////////////////////


	}

	@Mod.EventHandler
	public void serverStop(FMLServerStoppedEvent event) {
		//killAllStreams();
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
		m.modId = MODID;
		m.version = version;
		m.name = NAME;
		m.url = "http://www.pixelmonmod.com";
		m.updateUrl = "http://www.pixelmonmod.com";
		m.description = "A mod that adds Pokemon blocks and items into Pixelmon";
		m.authorList.add("AnDwHaT5");
		m.credits = "The PixelUtilities Team";
	}
}