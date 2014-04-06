package com.pixelutilitys.config;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import com.pixelutilitys.Basemod;
import com.pixelutilitys.armor.CrystalBoots;
import com.pixelutilitys.armor.CrystalHelmet;
import com.pixelutilitys.armor.CrystalLegs;
import com.pixelutilitys.armor.CrystalPlate;
import com.pixelutilitys.armor.GalacticBoots;
import com.pixelutilitys.armor.GalacticHelmet;
import com.pixelutilitys.armor.GalacticLegs;
import com.pixelutilitys.armor.GalacticPlate;
import com.pixelutilitys.armor.RubyBoots;
import com.pixelutilitys.armor.RubyHelmet;
import com.pixelutilitys.armor.RubyLegs;
import com.pixelutilitys.armor.RubyPlate;
import com.pixelutilitys.armor.SaphireBoots;
import com.pixelutilitys.armor.SaphireHelmet;
import com.pixelutilitys.armor.SaphireLegs;
import com.pixelutilitys.armor.SaphirePlate;

import cpw.mods.fml.common.registry.GameRegistry;

public class PixelUtilitysArmor {
	/*
	public static int rubyHelmID;
	public static int rubyPlateID;
	public static int rubyLegsID;
	public static int rubyBootsID;
	
	public static int saphireHelmID;
	public static int saphirePlateID;
	public static int saphireLegsID;
	public static int saphireBootsID;
	
	public static int firestoneHelmID;
	public static int firestonePlateID;
	public static int firestoneLegsID;
	public static int firestoneBootsID;
	
	public static int waterstoneHelmID;
	public static int waterstonePlateID;
	public static int waterstoneLegsID;
	public static int waterstoneBootsID;
	
	public static int leafstoneHelmID;
	public static int leafstonePlateID;
	public static int leafstoneLegsID;
	public static int leafstoneBootsID;
	*/
	public static Item rubyHelm;
	public static Item rubyPlate;
	public static Item rubyLegs;
	public static Item rubyBoots;
	
	public static Item saphireHelm;
	public static Item saphirePlate;
	public static Item saphireLegs;
	public static Item saphireBoots;
	
	public static Item galacticHelm;
	public static Item galacticBoots;
	public static Item galacticPlate;
	public static Item galacticLegs;
	
	public static Item crystalHelm;
	public static Item crystalBoots;
	public static Item crystalPlate;
	public static Item crystalLegs;
	
	/*
	public static Item firestoneHelm;
	public static Item firestonePlate;
	public static Item firestoneLegs;
	public static Item firestoneBoots;
	
	public static Item waterstoneHelm;
	public static Item waterstonePlate;
	public static Item waterstoneLegs;
	public static Item waterstoneBoots;
	
	public static Item leafstoneHelm;
	public static Item leafstonePlate;
	public static Item leafstoneLegs;
	public static Item leafstoneBoots;
	*/
	public static void load(Configuration cfg) {
		/*
		rubyHelmID = cfg.get("armor", "RubyHelmID", 6040).getInt();
		rubyPlateID = cfg.get("armor", "RubyPlateID", 6041).getInt();
		rubyLegsID = cfg.get("armor", "RubyLegsID", 6042).getInt();
		rubyBootsID = cfg.get("armor", "RubyBootsID", 6043).getInt();
		
		saphireHelmID = cfg.get("armor", "SaphireHelmID", 6044).getInt();
		saphirePlateID = cfg.get("armor", "SaphirePlateID", 6045).getInt();
		saphireLegsID = cfg.get("armor", "SaphireLegsID", 6046).getInt();
		saphireBootsID = cfg.get("armor", "SaphireBootsID", 6047).getInt();
		/*
		firestoneHelmID = cfg.get("armor", "RubyHelmID", 6048).getInt();
		firestonePlateID = cfg.get("armor", "RubyHelmID", 6049).getInt();
		firestoneLegsID = cfg.get("armor", "RubyHelmID", 6050).getInt();
		firestoneBootsID = cfg.get("armor", "RubyHelmID", 6051).getInt();
		
		waterstoneHelmID = cfg.get("armor", "RubyHelmID", 6052).getInt();
		waterstonePlateID = cfg.get("armor", "RubyHelmID", 6053).getInt();
		waterstoneLegsID = cfg.get("armor", "RubyHelmID", 6054).getInt();
		waterstoneBootsID = cfg.get("armor", "RubyHelmID", 6055).getInt();
		
		leafstoneHelmID = cfg.get("armor", "RubyHelmID", 6056).getInt();
		leafstonePlateID = cfg.get("armor", "RubyHelmID", 6057).getInt();
		leafstoneLegsID = cfg.get("armor", "RubyHelmID", 6058).getInt();
		leafstoneBootsID = cfg.get("armor", "RubyHelmID", 6059).getInt();
		*/
		rubyHelm = new RubyHelmet(Basemod.RUBYA, 0, 0).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("Ruby Helmet");
		rubyPlate = new RubyPlate(Basemod.RUBYA, 0, 1).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("Ruby Chestlate");
		rubyLegs = new RubyLegs(Basemod.RUBYA, 0, 2).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("Ruby Leggings");
		rubyBoots = new RubyBoots(Basemod.RUBYA, 0, 3).setCreativeTab(CreativeTabs.tabCombat).setUnlocalizedName("Ruby Boots");
		
		saphireHelm = new SaphireHelmet(Basemod.SAPHIREA, 0, 0).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:SaphireHelmet").setUnlocalizedName("Saphire Helmet");
		saphirePlate = new SaphirePlate(Basemod.SAPHIREA, 0, 1).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:SaphirePlate").setUnlocalizedName("Saphire ChestPlate");
		saphireLegs = new SaphireLegs(Basemod.SAPHIREA, 0, 2).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:SaphireLegs").setUnlocalizedName("Saphire Leggings");
		saphireBoots = new SaphireBoots(Basemod.SAPHIREA, 0, 3).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:SaphireBoots").setUnlocalizedName("Saphire Booties");
		
		galacticHelm = new GalacticHelmet(Basemod.SILICONA, 0, 0).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:GalacticHelmet").setUnlocalizedName("Galactic Helmet");
		galacticPlate = new GalacticPlate(Basemod.SILICONA, 0, 1).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:GalacticPlate").setUnlocalizedName("Galactic ChestPlate");
		galacticLegs = new GalacticLegs(Basemod.SILICONA, 0, 2).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:GalacticLegs").setUnlocalizedName("Galactic Leggings");
		galacticBoots = new GalacticBoots(Basemod.SILICONA, 0, 3).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:GalacticBoots").setUnlocalizedName("Galactic Boots");
		
		crystalHelm = new CrystalHelmet(Basemod.CRYSTALA, 0, 0).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:CrystalHelmet").setUnlocalizedName("Crystal Helmet");
		crystalPlate = new CrystalPlate(Basemod.CRYSTALA, 0, 1).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:CrystalPlate").setUnlocalizedName("Crystal ChestPlate");
		crystalLegs = new CrystalLegs(Basemod.CRYSTALA, 0, 2).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:CrystalLegs").setUnlocalizedName("Crystal Leggings");
		crystalBoots = new CrystalBoots(Basemod.CRYSTALA, 0, 3).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:CrystalBoots").setUnlocalizedName("Crystal Boots");
		
		/*
		firestoneHelm = new FireStoneHelmet(firestoneHelmID, Basemod.FIRESTONEA, 0, 0).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:FireStoneHelmet").setUnlocalizedName("Firestone Helmet");
		firestonePlate = new FireStonePlate(firestonePlateID, Basemod.FIRESTONEA, 0, 1).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:FireStonePlate").setUnlocalizedName("Firestone Chestlate");
		firestoneLegs = new FireStoneLegs(firestoneLegsID, Basemod.FIRESTONEA, 0, 2).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:FireStoneLegs").setUnlocalizedName("Firestone Leggings");
		firestoneBoots = new FireStoneBoots(firestoneBootsID, Basemod.FIRESTONEA, 0, 3).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:FireStoneBoots").setUnlocalizedName("Firestone Boots");
		
		waterstoneHelm = new WaterStoneHelmet(waterstoneHelmID, Basemod.WATERSTONEA, 0, 0).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:WaterStoneHelmet").setUnlocalizedName("Waterstone Helmet");
		waterstonePlate = new WaterStonePlate(waterstonePlateID, Basemod.WATERSTONEA, 0, 1).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:WaterStonePlate").setUnlocalizedName("Waterstone Chestlate");
		waterstoneLegs = new WaterStoneLegs(waterstoneLegsID, Basemod.WATERSTONEA, 0, 2).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:WaterStoneLegs").setUnlocalizedName("Waterstone Leggings");
		waterstoneBoots = new WaterStoneBoots(waterstoneBootsID, Basemod.WATERSTONEA, 0, 3).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:WaterStoneBoots").setUnlocalizedName("Waterstone Boots");
		
		leafstoneHelm = new LeafStoneHelmet(leafstoneHelmID, Basemod.LEAFSTONEA, 0, 0).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:LeafStoneHelmet").setUnlocalizedName("Leafstone Helmet");
		leafstonePlate = new LeafStonePlate(leafstonePlateID, Basemod.LEAFSTONEA, 0, 1).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:LeafStonePlate").setUnlocalizedName("Leafstone Chestlate");
		leafstoneLegs = new LeafStoneLegs(leafstoneLegsID, Basemod.LEAFSTONEA, 0, 2).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:LeafStoneLegs").setUnlocalizedName("Leafstone Leggings");
		leafstoneBoots = new LeafStoneBoots(leafstoneBootsID, Basemod.LEAFSTONEA, 0, 3).setCreativeTab(CreativeTabs.tabCombat).setTextureName("pixelUtilities:LeafStoneBoots").setUnlocalizedName("Leafstone Boots");
		*/
	}

	public static void addNames() {
		
		/*LanguageRegistry.addName(rubyHelm, "Ruby Helmet");
		LanguageRegistry.addName(rubyPlate, "Ruby Chestplate");
		LanguageRegistry.addName(rubyLegs, "Ruby Leggings");
		LanguageRegistry.addName(rubyBoots, "Ruby Boots");
		
		LanguageRegistry.addName(saphireHelm, "Saphire Helmet");
		LanguageRegistry.addName(saphirePlate, "Saphire Chestplate");
		LanguageRegistry.addName(saphireLegs, "Saphire Leggings");
		LanguageRegistry.addName(saphireBoots, "Saphire Booties");*/
		
		/*LanguageRegistry.addName(firestoneHelm, "Firestone Helmet");
		LanguageRegistry.addName(firestonePlate, "Firestone Chestplate");
		LanguageRegistry.addName(firestoneLegs, "Firestone Leggings");
		LanguageRegistry.addName(firestoneBoots, "Firestone Boots");
		
		LanguageRegistry.addName(waterstoneHelm, "Waterstone Helmet");
		LanguageRegistry.addName(waterstonePlate, "Waterstone Chestplate");
		LanguageRegistry.addName(waterstoneLegs, "Waterstone Leggings");
		LanguageRegistry.addName(waterstoneBoots, "Waterstone Boots");
		
		LanguageRegistry.addName(leafstoneHelm, "Leafstone Helmet");
		LanguageRegistry.addName(leafstonePlate, "Leafstone Chestplate");
		LanguageRegistry.addName(leafstoneLegs, "Leafstone Leggings");
		LanguageRegistry.addName(leafstoneBoots, "Leafstone Boots");*/
		
		GameRegistry.registerItem(rubyHelm, "Ruby Helmet");
		GameRegistry.registerItem(rubyPlate, rubyPlate.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(rubyLegs, rubyLegs.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(rubyBoots, rubyBoots.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(saphireHelm, saphireHelm.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(saphirePlate, saphirePlate.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(saphireLegs, saphireLegs.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(saphireBoots, saphireBoots.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(galacticHelm, galacticHelm.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(galacticPlate, galacticPlate.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(galacticLegs, galacticLegs.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(galacticBoots, galacticBoots.getUnlocalizedName().substring(5));
		
		/*GameRegistry.registerItem(firestoneHelm, "Firestone Helmet");
		GameRegistry.registerItem(firestonePlate, "Firestone Chestplate");
		GameRegistry.registerItem(firestoneLegs, "Firestone Leggings");
		GameRegistry.registerItem(firestoneBoots, "Firestone Boots");
		
		GameRegistry.registerItem(waterstoneHelm, "Waterstone Helmet");
		GameRegistry.registerItem(waterstonePlate, "Waterstone Chestplate");
		GameRegistry.registerItem(waterstoneLegs, "Waterstone Leggings");
		GameRegistry.registerItem(waterstoneBoots, "Waterstone Boots");
		
		GameRegistry.registerItem(leafstoneHelm, "Leafstone Helmet");
		GameRegistry.registerItem(leafstonePlate, "Leafstone Chestplate");
		GameRegistry.registerItem(leafstoneLegs, "Leafstone Leggings");
		GameRegistry.registerItem(leafstoneBoots, "Leafstone Boots");*/
	}
}