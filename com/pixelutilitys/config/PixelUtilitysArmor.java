package com.pixelutilitys.config;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
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
import com.pixelutilitys.armor.RocketBoots;
import com.pixelutilitys.armor.RocketHelmet;
import com.pixelutilitys.armor.RocketLegs;
import com.pixelutilitys.armor.RocketPlate;
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
	
	
	public static Item rocketHelm;
	public static Item rocketBoots;
	public static Item rocketPlate;
	public static Item rocketLegs;
	
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
//TODO, textures for armour are referenced but not present?
		rubyHelm = new RubyHelmet(Basemod.RUBYA, 0, 0);
		rubyPlate = new RubyPlate(Basemod.RUBYA, 0, 1);
		rubyLegs = new RubyLegs(Basemod.RUBYA, 0, 2);
		rubyBoots = new RubyBoots(Basemod.RUBYA, 0, 3);
		
		saphireHelm = new SaphireHelmet(Basemod.SAPHIREA, 0, 0);
		saphirePlate = new SaphirePlate(Basemod.SAPHIREA, 0, 1);
		saphireLegs = new SaphireLegs(Basemod.SAPHIREA, 0, 2);
		saphireBoots = new SaphireBoots(Basemod.SAPHIREA, 0, 3);
		
		galacticHelm = new GalacticHelmet(Basemod.SILICONA, 0, 0);
		galacticPlate = new GalacticPlate(Basemod.SILICONA, 0, 1);
		galacticLegs = new GalacticLegs(Basemod.SILICONA, 0, 2);
		galacticBoots = new GalacticBoots(Basemod.SILICONA, 0, 3);
		
		crystalHelm = new CrystalHelmet(Basemod.CRYSTALA, 0, 0);
		crystalPlate = new CrystalPlate(Basemod.CRYSTALA, 0, 1);
		crystalLegs = new CrystalLegs(Basemod.CRYSTALA, 0, 2);
		crystalBoots = new CrystalBoots(Basemod.CRYSTALA, 0, 3);
		
		rocketHelm = new RocketHelmet(Basemod.CRYSTALA, 0, 0);
		rocketPlate = new RocketPlate(Basemod.CRYSTALA,0, 1);
		rocketLegs = new RocketLegs( Basemod.CRYSTALA,0, 2);
		rocketBoots = new RocketBoots(Basemod.CRYSTALA, 0, 3);
		
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
		
		GameRegistry.registerItem(rubyHelm, rubyHelm.getUnlocalizedName().substring(5));
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
		
		GameRegistry.registerItem(rocketHelm, rocketHelm.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(rocketPlate, rocketPlate.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(rocketLegs, rocketLegs.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(rocketBoots, rocketBoots.getUnlocalizedName().substring(5));
		
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