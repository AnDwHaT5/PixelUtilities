package com.pixelutilitys.config;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import com.pixelutilitys.Basemod;
import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;
import com.pixelutilitys.tools.*;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelUtilitysTools {
/*
	public static int rubyPickaxeID;
	public static int rubyHammerID;
	public static int rubyAxeID;
	public static int rubyShovelID;
	public static int rubyHoeID;
	public static int rubySwordID;

	public static int saphirePickaxeID;
	public static int saphireHammerID;
	public static int saphireAxeID;
	public static int saphireShovelID;
	public static int saphireHoeID;
	public static int saphireSwordID;

	public static int amethystPickaxeID;
	public static int amethystHammerID;
	public static int amethystAxeID;
	public static int amethystShovelID;
	public static int amethystHoeID;
	public static int amethystSwordID;

	public static int firestonePickaxeID;
	public static int firestoneHammerID;
	public static int firestoneAxeID;
	public static int firestoneShovelID;
	public static int firestoneHoeID;
	public static int firestoneSwordID;

	public static int waterstonePickaxeID;
	public static int waterstoneHammerID;
	public static int waterstoneAxeID;
	public static int waterstoneShovelID;
	public static int waterstoneHoeID;
	public static int waterstoneSwordID;

	public static int leafstonePickaxeID;
	public static int leafstoneHammerID;
	public static int leafstoneAxeID;
	public static int leafstoneShovelID;
	public static int leafstoneHoeID;
	public static int leafstoneSwordID;
*/
	//Ruby Tools
	public static Item rubyPickaxe;
	public static Item rubyHammer;
	public static Item rubyAxe;
	public static Item rubyShovel;
	public static Item rubyHoe; 
	public static Item rubySword;

	//Saphire Tools
	public static Item saphirePickaxe;
	public static Item saphireHammer;// new com.PixelUtilitys.tools.SaphireHammerTool(6052, Basemod.SAPHIRE, "saphireHammer", "Saphire Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireHammer");
	public static Item saphireAxe;// new com.PixelUtilitys.tools.SaphireAxeTool(6053, Basemod.SAPHIRE, "pixelutilitys:SaphireAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireAxe");
	public static Item saphireShovel;// new com.PixelUtilitys.tools.SaphireShovelTool(6054, Basemod.SAPHIRE, "pixelutilitys:SaphireShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireShovel");
	public static Item saphireHoe;// new com.PixelUtilitys.tools.SaphireHoeTool(6055, Basemod.SAPHIRE, "PixelUtilitys:SaphireHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireHoe");
	public static Item saphireSword;// new com.PixelUtilitys.tools.SaphireSwordTool(6056, Basemod.SAPHIRE, "PixelUtilitys:SaphireSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireSword");

	//Amethyst Tools
	public static Item amethystPickaxe;// new com.PixelUtilitys.tools.AmethystPickaxeTool(6057, 6.5F, Basemod.AMETHYST, "PixelUtilitys:AmethystPickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystPickaxe");
	public static Item amethystHammer;// new com.PixelUtilitys.tools.AmethystHammerTool(6058, Basemod.AMETHYST, "AmethystHammer", "Amethyst Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystHammer").setTextureName("PixelUtilitys:AmethystHammer");
	public static Item amethystShovel;// new com.PixelUtilitys.tools.AmethystShovelTool(6059, Basemod.AMETHYST, "PixelUtilitys:AmethystShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystShovel");
	public static Item amethystAxe;// new com.PixelUtilitys.tools.AmethystAxeTool(6060, Basemod.AMETHYST, "PixelUtilitys:AmethystAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystAxe");
	public static Item amethystHoe;// new com.PixelUtilitys.tools.AmethystHoeTool(6061, Basemod.AMETHYST, "PixelUtilitys:AmethystHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystHoe");
	public static Item amethystSword;// new com.PixelUtilitys.tools.AmethystSwordTool(6062, Basemod.AMETHYST, "PixelUtilitys:AmethystSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystSword");

	//EvoTools
	//Firestone tools
	public static Item FirestonePickaxe;// new com.PixelUtilitys.tools.FirestonePickaxe(6083, 6.5F, FIRESTONE, "pixelutilitys:FirestonePickaxe").setTextureName("PixelUtilitys:FirestonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestonePickaxe");
	public static Item FirestoneHammer;// new com.PixelUtilitys.tools.FirestoneHammer(6084, FIRESTONE, "FirestoneHammer", "Firestone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:FirestoneHammer").setUnlocalizedName("FirestoneHammer");
	public static Item FirestoneAxe;// new com.PixelUtilitys.tools.FirestoneAxe(6085, FIRESTONE, "pixelutilitys:FirestoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneAxe").setTextureName("PixelUtilitys:FirestoneAxe");
	public static Item FirestoneShovel;// new com.PixelUtilitys.tools.FirestoneShovel(6086, FIRESTONE, "pixelutilitys:FirestoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneShovel").setTextureName("PixelUtilitys:FirestoneShovel");
	public static Item FirestoneHoe;// new com.PixelUtilitys.tools.FirestoneHoe(6087, FIRESTONE, "pixelutilitys:FirestoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneHoe").setTextureName("pixelutilitys:FirestoneHoe");
	public static Item FirestoneSword;// new com.PixelUtilitys.tools.FirestoneSword(6088, FIRESTONE, "pixelutilities:FirestoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneSword").setTextureName("PixelUtilitys:FirestoneSword");

	//Waterstone tools
	public static Item WaterstonePickaxe;// new com.PixelUtilitys.tools.WaterstonePickaxe(6089, 6.5F, Basemod.WATERSTONE, "pixelutilitys:WaterstonePickaxe").setTextureName("PixelUtilitys:WaterstonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstonePickaxe");
	public static Item WaterstoneHammer;// new com.PixelUtilitys.tools.WaterstoneHammer(6090, Basemod.WATERSTONE, "WaterstoneHammer", "Waterstone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:WaterstoneHammer").setUnlocalizedName("WaterstoneHammer");
	public static Item WaterstoneAxe;// new com.PixelUtilitys.tools.WaterstoneAxe(6091, Basemod.WATERSTONE, "pixelutilitys:WaterstoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneAxe").setTextureName("PixelUtilitys:WaterstoneAxe");
	public static Item WaterstoneShovel;// new com.PixelUtilitys.tools.WaterstoneShovel(6092, Basemod.WATERSTONE, "pixelutilitys:WaterstoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneShovel").setTextureName("PixelUtilitys:WaterstoneShovel");
	public static Item WaterstoneHoe;// new com.PixelUtilitys.tools.WaterstoneHoe(6093, Basemod.WATERSTONE, "pixelutilitys:WaterstoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneHoe").setTextureName("pixelutilitys:WaterstoneHoe");
	public static Item WaterstoneSword;// new com.PixelUtilitys.tools.WaterstoneSword(6094, Basemod.WATERSTONE, "pixelutilitys:WaterstoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneSword").setTextureName("PixelUtilitys:WaterstoneSword");

	//Leafstone tools
	public static Item LeafstonePickaxe;// new com.PixelUtilitys.tools.LeafstonePickaxe(6095, 6.5F, Basemod.LEAFSTONE, "pixelutilitys:LeafstonePickaxe").setTextureName("PixelUtilitys:LeafstonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstonePickaxe");
	public static Item LeafstoneHammer;// new com.PixelUtilitys.tools.LeafstoneHammer(6096, Basemod.LEAFSTONE, "rubyHammer", "Leafstone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:LeafstoneHammer").setUnlocalizedName("LeafstoneHammer");
	public static Item LeafstoneAxe;// new com.PixelUtilitys.tools.LeafstoneAxe(6097, Basemod.LEAFSTONE, "pixelutilitys:LeafstoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneAxe").setTextureName("PixelUtilitys:LeafstoneAxe");
	public static Item LeafstoneShovel;// new com.PixelUtilitys.tools.LeafstoneShovel(6098, Basemod.LEAFSTONE, "pixelutilitys:LeafstoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneShovel").setTextureName("PixelUtilitys:LeafstoneShovel");
	public static Item LeafstoneHoe;// new com.PixelUtilitys.tools.LeafstoneHoe(6099, Basemod.LEAFSTONE, "pixelutilitys:LeafstoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneHoe").setTextureName("pixelutilitys:LeafstoneHoe");
	public static Item LeafstoneSword;// new com.PixelUtilitys.tools.LeafstoneSword(6100, Basemod.LEAFSTONE, "pixelutilitys:LeafstoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneSword").setTextureName("PixelUtilitys:LeafstoneSword");
	
	//Ruby Tools
	public static Item CrystalPickaxe;
	public static Item CrystalHammer;
	public static Item CrystalAxe;
	public static Item CrystalShovel;
	public static Item CrystalHoe; 
	public static Item CrystalSword;

	public static void load(Configuration cfg) {
/*
		rubyPickaxeID = cfg.get("tool", "RubyPickID", 6050).getInt();
		rubyHammerID = cfg.get("tool", "RubyHammerID", 6051).getInt();
		rubyAxeID = cfg.get("tool", "RubyAxeID", 6052).getInt();
		rubyShovelID = cfg.get("tool", "RubyShovelID", 6053).getInt();
		rubyHoeID = cfg.get("tool", "RubyHoeID", 6054).getInt();
		rubySwordID = cfg.get("tool", "RubySwordID", 6055).getInt();

		saphirePickaxeID = cfg.get("tool", "SaphirePickID", 656).getInt();
		saphireHammerID = cfg.get("tool", "SaphireHammerID", 6057).getInt();
		saphireAxeID = cfg.get("tool", "SaphireAxeID", 6058).getInt();
		saphireShovelID = cfg.get("tool", "SaphireShovelID", 6059).getInt();
		saphireHoeID = cfg.get("tool", "SaphireHoeID", 6060).getInt();
		saphireSwordID = cfg.get("tool", "SaphireSwordID", 6061).getInt();

		amethystPickaxeID = cfg.get("tool", "AmethystPickID", 6062).getInt();
		amethystHammerID = cfg.get("tool", "AmethystHammerID", 6063).getInt();
		amethystAxeID = cfg.get("tool", "AmethystAxeID", 6064).getInt();
		amethystShovelID = cfg.get("tool", "AmethystShovelID", 6065).getInt();
		amethystHoeID = cfg.get("tool", "AmethystHoeID", 6066).getInt();
		amethystSwordID = cfg.get("tool", "AmethystSwordID", 6067).getInt();

		firestonePickaxeID = cfg.get("tool", "FireStonePickID", 6068).getInt();
		firestoneHammerID = cfg.get("tool", "FireStoneHammerID", 6069).getInt();
		firestoneAxeID = cfg.get("tool", "FireStoneAxeID", 6070).getInt();
		firestoneShovelID = cfg.get("tool", "FireStoneShovelID", 6071).getInt();
		firestoneHoeID = cfg.get("tool", "FireStoneHoeID", 6072).getInt();
		firestoneSwordID = cfg.get("tool", "FireStoneSwordID", 6073).getInt();

		waterstonePickaxeID = cfg.get("tool", "WaterStonePickID", 6074).getInt();
		waterstoneHammerID = cfg.get("tool", "WaterStoneHammerID", 6075).getInt();
		waterstoneAxeID = cfg.get("tool", "WaterStoneAxeID", 6076).getInt();
		waterstoneShovelID = cfg.get("tool", "WaterStoneShovelID", 6077).getInt();
		waterstoneHoeID = cfg.get("tool", "WaterStoneHoeID", 6078).getInt();
		waterstoneSwordID = cfg.get("tool", "WaterStoneSwordID", 6079).getInt();

		leafstonePickaxeID = cfg.get("tool", "LeafStonePickID", 6080).getInt();
		leafstoneHammerID = cfg.get("tool", "LeafStoneHammerID", 6081).getInt();
		leafstoneAxeID = cfg.get("tool", "LeafStoneAxeID", 6082).getInt();
		leafstoneShovelID = cfg.get("tool", "LeafStoneShovelID", 6083).getInt();
		leafstoneHoeID = cfg.get("tool", "LeafStoneHoeID", 6084).getInt();
		leafstoneSwordID = cfg.get("tool", "LeafStoneSwordID", 6085).getInt();
*/
		//Ruby Tools
		rubyPickaxe = new RubyPickaxeTool(6.5F, Basemod.RUBY, "pixelutilitys:RubyPickaxe").setTextureName("PixelUtilitys:RubyPickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("rubyPickaxe");
		rubyHammer = new RubyHammerTool(Basemod.RUBY, "rubyHammer", "Ruby Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:RubyHammer").setUnlocalizedName("rubyHammer");
		rubyAxe = new RubyAxeTool(Basemod.RUBY, "pixelutilitys:RubyAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("rubyAxe").setTextureName("PixelUtilitys:RubyAxe");
		rubyShovel = new RubyShovelTool(Basemod.RUBY, "pixelutilitys:RubyShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("rubyShovel").setTextureName("PixelUtilitys:RubyShovel");
		rubyHoe = new RubyHoeTool(Basemod.RUBY, "pixelutilitys:RubyHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("rubyHoe").setTextureName("pixelutilitys:RubyHoe");
		rubySword = new RubySwordTool(Basemod.RUBY, "pixelutilities:RubySword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("rubySword").setTextureName("PixelUtilitys:RubySword");

		//Saphire Tools
		saphirePickaxe = new SaphirePickaxeTool(6.5F, Basemod.SAPHIRE, "PixelUtilitys:SaphirePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphirePickaxe");
		saphireHammer = new SaphireHammerTool(Basemod.SAPHIRE, "saphireHammer", "Saphire Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireHammer");
		saphireAxe = new SaphireAxeTool(Basemod.SAPHIRE, "pixelutilitys:SaphireAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireAxe");
		saphireShovel = new SaphireShovelTool(Basemod.SAPHIRE, "pixelutilitys:SaphireShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireShovel");
		saphireHoe = new SaphireHoeTool(Basemod.SAPHIRE, "PixelUtilitys:SaphireHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireHoe");
		saphireSword = new SaphireSwordTool(Basemod.SAPHIRE, "PixelUtilitys:SaphireSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("saphireSword");

		//Amethyst Tools
		amethystPickaxe = new AmethystPickaxeTool(6.5F, Basemod.AMETHYST, "PixelUtilitys:AmethystPickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystPickaxe");
		amethystHammer = new AmethystHammerTool(Basemod.AMETHYST, "AmethystHammer", "Amethyst Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystHammer").setTextureName("PixelUtilitys:AmethystHammer");
		amethystShovel = new AmethystShovelTool(Basemod.AMETHYST, "PixelUtilitys:AmethystShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystShovel");
		amethystAxe = new AmethystAxeTool(Basemod.AMETHYST, "PixelUtilitys:AmethystAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystAxe");
		amethystHoe = new AmethystHoeTool(Basemod.AMETHYST, "PixelUtilitys:AmethystHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystHoe");
		amethystSword = new AmethystSwordTool(Basemod.AMETHYST, "PixelUtilitys:AmethystSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("amethystSword");

		//EvoTools
		//Firestone tools
		FirestonePickaxe = new FirestonePickaxe(6.5F, Basemod.FIRESTONE, "pixelutilitys:FirestonePickaxe").setTextureName("PixelUtilitys:FirestonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestonePickaxe");
		FirestoneHammer = new FirestoneHammer(Basemod.FIRESTONE, "FirestoneHammer", "Firestone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:FirestoneHammer").setUnlocalizedName("FirestoneHammer");
		FirestoneAxe = new FirestoneAxe(Basemod.FIRESTONE, "pixelutilitys:FirestoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneAxe").setTextureName("PixelUtilitys:FirestoneAxe");
		FirestoneShovel = new FirestoneShovel(Basemod.FIRESTONE, "pixelutilitys:FirestoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneShovel").setTextureName("PixelUtilitys:FirestoneShovel");
		FirestoneHoe = new FirestoneHoe(Basemod.FIRESTONE, "pixelutilitys:FirestoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneHoe").setTextureName("pixelutilitys:FirestoneHoe");
		FirestoneSword = new FirestoneSword(Basemod.FIRESTONE, "pixelutilities:FirestoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("FirestoneSword").setTextureName("PixelUtilitys:FirestoneSword");

		//Waterstone tools
		WaterstonePickaxe = new WaterstonePickaxe(6.5F, Basemod.WATERSTONE, "pixelutilitys:WaterstonePickaxe").setTextureName("PixelUtilitys:WaterstonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstonePickaxe");
		WaterstoneHammer = new WaterstoneHammer(Basemod.WATERSTONE, "WaterstoneHammer", "Waterstone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:WaterstoneHammer").setUnlocalizedName("WaterstoneHammer");
		WaterstoneAxe = new WaterstoneAxe(Basemod.WATERSTONE, "pixelutilitys:WaterstoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneAxe").setTextureName("PixelUtilitys:WaterstoneAxe");
		WaterstoneShovel = new WaterstoneShovel(Basemod.WATERSTONE, "pixelutilitys:WaterstoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneShovel").setTextureName("PixelUtilitys:WaterstoneShovel");
		WaterstoneHoe = new WaterstoneHoe(Basemod.WATERSTONE, "pixelutilitys:WaterstoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneHoe").setTextureName("pixelutilitys:WaterstoneHoe");
		WaterstoneSword = new WaterstoneSword(Basemod.WATERSTONE, "pixelutilitys:WaterstoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("WaterstoneSword").setTextureName("PixelUtilitys:WaterstoneSword");

		//Leafstone tools
		LeafstonePickaxe = new LeafstonePickaxe(6.5F, Basemod.LEAFSTONE, "pixelutilitys:LeafstonePickaxe").setTextureName("PixelUtilitys:LeafstonePickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstonePickaxe");
		LeafstoneHammer = new LeafstoneHammer(Basemod.LEAFSTONE, "rubyHammer", "Leafstone Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:LeafstoneHammer").setUnlocalizedName("LeafstoneHammer");
		LeafstoneAxe = new LeafstoneAxe(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneAxe").setTextureName("PixelUtilitys:LeafstoneAxe");
		LeafstoneShovel = new LeafstoneShovel(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneShovel").setTextureName("PixelUtilitys:LeafstoneShovel");
		LeafstoneHoe = new LeafstoneHoe(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneHoe").setTextureName("pixelutilitys:LeafstoneHoe");
		LeafstoneSword = new LeafstoneSword(Basemod.LEAFSTONE, "pixelutilitys:LeafstoneSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("LeafstoneSword").setTextureName("PixelUtilitys:LeafstoneSword");
		
		CrystalPickaxe = new CrystalPickaxeTool(6.5F, Basemod.CRYSTAL, "pixelutilitys:CrystalPickaxe").setTextureName("PixelUtilitys:RubyPickaxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("CrystalPickaxe");
		CrystalHammer = new CrystalHammerTool(Basemod.CRYSTAL, "CrystalHammer", "Crystal Hammer").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setTextureName("PixelUtilitys:CrystalHammer").setUnlocalizedName("CrystalHammer");
		CrystalAxe = new CrystalAxeTool(Basemod.CRYSTAL, "pixelutilitys:CrystalAxe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("CrystalAxe").setTextureName("PixelUtilitys:CrystalAxe");
		CrystalShovel = new CrystalShovelTool(Basemod.CRYSTAL, "pixelutilitys:CrystalShovel").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("CrystalShovel").setTextureName("PixelUtilitys:CrystalShovel");
		CrystalHoe = new CrystalHoeTool(Basemod.CRYSTAL, "pixelutilitys:CrystalHoe").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("CrystalHoe").setTextureName("pixelutilitys:CrystalHoe");
		CrystalSword = new CrystalSwordTool(Basemod.CRYSTAL, "pixelutilities:CrystalSword").setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools).setUnlocalizedName("CrystalSword").setTextureName("PixelUtilitys:CrystalSword");

	}

	public static void addNames() {

		/*LanguageRegistry.addName(rubyPickaxe, "Ruby Pickaxe");
		LanguageRegistry.addName(rubyHammer, "Ruby Hammer");
		LanguageRegistry.addName(rubyAxe, "Ruby Axe");
		LanguageRegistry.addName(rubyShovel, "Ruby Shovel");
		LanguageRegistry.addName(rubyHoe, "Ruby Hoe");
		LanguageRegistry.addName(rubySword, "Ruby Sword");

		LanguageRegistry.addName(saphirePickaxe, "Saphire Pickaxe");
		LanguageRegistry.addName(saphireHammer, "Saphire Hammer");
		LanguageRegistry.addName(saphireAxe, "Saphire Axe");
		LanguageRegistry.addName(saphireShovel, "Saphire Shovel");
		LanguageRegistry.addName(saphireHoe, "Saphire Hoe");
		LanguageRegistry.addName(saphireSword, "Saphire Sword");

		LanguageRegistry.addName(amethystPickaxe, "Amethyst Pickaxe");
		LanguageRegistry.addName(amethystHammer, "Amethyst Hammer");
		LanguageRegistry.addName(amethystAxe, "Amethyst Axe");
		LanguageRegistry.addName(amethystShovel, "Amethyst Shovel");
		LanguageRegistry.addName(amethystHoe, "Amethyst Hoe");
		LanguageRegistry.addName(amethystSword, "Amethyst Sword");

		LanguageRegistry.addName(FirestonePickaxe, "Firestone Pickaxe");
		LanguageRegistry.addName(FirestoneAxe, "Firestone Axe");
		LanguageRegistry.addName(FirestoneHoe, "Firestone Hoe");
		LanguageRegistry.addName(FirestoneSword, "Firestone Sword");
		LanguageRegistry.addName(FirestoneShovel, "Firestone Shovel");
		LanguageRegistry.addName(FirestoneHammer, "Firestone Hammer");

		LanguageRegistry.addName(WaterstonePickaxe, "Waterstone Pickaxe");
		LanguageRegistry.addName(WaterstoneAxe, "Waterstone Axe");
		LanguageRegistry.addName(WaterstoneHoe, "Waterstone Hoe");
		LanguageRegistry.addName(WaterstoneSword, "Waterstone Sword");
		LanguageRegistry.addName(WaterstoneShovel, "Waterstone Shovel");
		LanguageRegistry.addName(WaterstoneHammer, "Waterstone Hammer");

		LanguageRegistry.addName(LeafstonePickaxe, "Leafstone Pickaxe");
		LanguageRegistry.addName(LeafstoneAxe, "Leafstone Axe");
		LanguageRegistry.addName(LeafstoneHoe, "Leafstone Hoe");
		LanguageRegistry.addName(LeafstoneSword, "Leafstone Sword");
		LanguageRegistry.addName(LeafstoneShovel, "Leafstone Shovel");
		LanguageRegistry.addName(LeafstoneHammer, "Leafstone Hammer");*/

		GameRegistry.registerItem(rubyPickaxe, "Ruby Pickaxe");
		GameRegistry.registerItem(rubyHammer, "Ruby Hammer");
		GameRegistry.registerItem(rubyAxe, "Ruby Axe");
		GameRegistry.registerItem(rubyShovel, "Ruby Shovel");
		GameRegistry.registerItem(rubyHoe, "Ruby Hoe");
		GameRegistry.registerItem(rubySword, "Ruby Sword");

		GameRegistry.registerItem(saphirePickaxe, "Saphire Pickaxe");
		GameRegistry.registerItem(saphireHammer, "Saphire Hammer");
		GameRegistry.registerItem(saphireAxe, "Saphire Axe");
		GameRegistry.registerItem(saphireShovel, "Saphire Shovel");
		GameRegistry.registerItem(saphireHoe, "Saphire Hoe");
		GameRegistry.registerItem(saphireSword, "Saphire Sword");

		GameRegistry.registerItem(amethystPickaxe, "Amethyst Pickaxe");
		GameRegistry.registerItem(amethystHammer, "Amethyst Hammer");
		GameRegistry.registerItem(amethystAxe, "Amethyst Axe");
		GameRegistry.registerItem(amethystShovel, "Amethyst Shovel");
		GameRegistry.registerItem(amethystHoe, "Amethyst Hoe");
		GameRegistry.registerItem(amethystSword, "Amethyst Sword");

		GameRegistry.registerItem(FirestonePickaxe, "Firestone Pickaxe");
		GameRegistry.registerItem(FirestoneAxe, "Firestone Axe");
		GameRegistry.registerItem(FirestoneHoe, "Firestone Hoe");
		GameRegistry.registerItem(FirestoneSword, "Firestone Sword");
		GameRegistry.registerItem(FirestoneShovel, "Firestone Shovel");
		GameRegistry.registerItem(FirestoneHammer, "Firestone Hammer");

		GameRegistry.registerItem(WaterstonePickaxe, "Waterstone Pickaxe");
		GameRegistry.registerItem(WaterstoneAxe, "Waterstone Axe");
		GameRegistry.registerItem(WaterstoneHoe, "Waterstone Hoe");
		GameRegistry.registerItem(WaterstoneSword, "Waterstone Sword");
		GameRegistry.registerItem(WaterstoneShovel, "Waterstone Shovel");
		GameRegistry.registerItem(WaterstoneHammer, "Waterstone Hammer");

		GameRegistry.registerItem(LeafstonePickaxe, "Leafstone Pickaxe");
		GameRegistry.registerItem(LeafstoneAxe, "Leafstone Axe");
		GameRegistry.registerItem(LeafstoneHoe, "Leafstone Hoe");
		GameRegistry.registerItem(LeafstoneSword, "Leafstone Sword");
		GameRegistry.registerItem(LeafstoneShovel, "Leafstone Shovel");
		GameRegistry.registerItem(LeafstoneHammer, "Leafstone Hammer");
		
		GameRegistry.registerItem(CrystalPickaxe, "Crystal Pickaxe");
		GameRegistry.registerItem(CrystalAxe, "Crystal Axe");
		GameRegistry.registerItem(CrystalHoe, "Crystal Hoe");
		GameRegistry.registerItem(CrystalSword, "Crystal Sword");
		GameRegistry.registerItem(CrystalShovel, "Crystal Shovel");
		GameRegistry.registerItem(CrystalHammer, "Crystal Hammer");

	}
}