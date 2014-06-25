package com.pixelutilitys.config;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import com.pixelmonmod.pixelmon.config.PixelmonBlocks;
import com.pixelmonmod.pixelmon.items.ItemBlock;
import com.pixelutilitys.badges.*;
import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;
import com.pixelutilitys.economy.*;
import com.pixelutilitys.items.*;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelUtilitysItems {
	
	//Items
    public static Item PokeCoin1Item;
	public static Item PokeCoin5Item;
	public static Item PokeCoin10Item;
	public static Item PokeCoin50Item;
	public static Item PokeCoin100Item;
	public static Item BugBadgeItem;
	public static Item FightingBadgeItem;
	public static Item FlyingBadgeItem;
	public static Item GroundBadgeItem;
	public static Item NormalBadgeItem;
	public static Item PoisonBadgeItem;
	public static Item RockBadgeItem;
	public static Item GhostBadgeItem;
	public static Item SteelBadgeItem;
	public static Item FireBadgeItem;
	public static Item WaterBadgeItem;
	public static Item GrassBadgeItem;
	public static Item ElectricBadgeItem;
	public static Item DarkBadgeItem;
	public static Item IceBadgeItem;
	public static Item PsychicBadgeItem;
	public static Item DragonBadgeItem;
	public static Item SaphireItem;
	public static Item RubyItem;
	public static Item SiliconItem;
	public static Item CrystalItem;
	public static Item CDItem;
	public static Item HandHeldRadio;
	
	public static void load(Configuration cfg) {
	    PokeCoin1Item = new PokeCoin1Item();
		PokeCoin5Item = new PokeCoin5Item();
		PokeCoin10Item = new PokeCoin10Item();
		PokeCoin50Item = new PokeCoin50Item();
		PokeCoin100Item = new PokeCoin100Item();
		BugBadgeItem = new BugBadgeItem();
		FightingBadgeItem = new FightingBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:FightingBadge");
		FlyingBadgeItem = new FlyingBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:FlyingBadge");
		GroundBadgeItem = new GroundBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:GroundBadge");
		NormalBadgeItem = new NormalBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:NormalBadge");
		PoisonBadgeItem = new PoisonBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:PoisonBadge");
		RockBadgeItem = new RockBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:RockBadge");
		GhostBadgeItem = new GhostBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:GhostBadge");
		SteelBadgeItem = new SteelBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:SteelBadge");
		FireBadgeItem = new FireBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:FireBadge");
		WaterBadgeItem = new WaterBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:WaterBadge");
		GrassBadgeItem = new GrassBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:GrassBadge");
		ElectricBadgeItem = new ElectricBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:ElectricBadge");
		DarkBadgeItem = new DarkBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:DarkBadge");
		IceBadgeItem = new IceBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:IceBadge");
		PsychicBadgeItem = new PsychicBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:PsychicBadge");
		DragonBadgeItem = new DragonBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:DragonBadge");
		SaphireItem = new SaphireItem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Saphire");
		RubyItem = new RubyItem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Ruby");
		SiliconItem  = new Siliconitem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Silicon");
		CrystalItem = new CrystalItem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Crystal");
		CDItem = new CDItem().setCreativeTab(CreativeTabs.tabMisc).setTextureName("PixelUtilitys:CD");
		HandHeldRadio = new HandHeldRadioItem().setCreativeTab(CreativeTabs.tabMisc).setTextureName("PixelUtilitys:handheldradio");
	}

	public static void addNames() {
		
		//Items
		GameRegistry.registerItem(PokeCoin1Item, PokeCoin1Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeCoin5Item, PokeCoin5Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeCoin10Item, PokeCoin10Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeCoin50Item, PokeCoin50Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeCoin100Item, PokeCoin100Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(SaphireItem, SaphireItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(RubyItem, RubyItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(SiliconItem, SiliconItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(CDItem, CDItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(HandHeldRadio, HandHeldRadio.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(CrystalItem, CrystalItem.getUnlocalizedName().substring(5));
		
		//Badges
		GameRegistry.registerItem(BugBadgeItem, BugBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(FightingBadgeItem, FightingBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(FlyingBadgeItem, FlyingBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(GhostBadgeItem, GhostBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(GroundBadgeItem, GroundBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(NormalBadgeItem, NormalBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PoisonBadgeItem, PoisonBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(RockBadgeItem, RockBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(SteelBadgeItem, SteelBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(FireBadgeItem, FireBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(WaterBadgeItem, WaterBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(GrassBadgeItem, GrassBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ElectricBadgeItem, ElectricBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PsychicBadgeItem, PsychicBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(DarkBadgeItem, DarkBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(IceBadgeItem, IceBadgeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(DragonBadgeItem, DragonBadgeItem.getUnlocalizedName().substring(5));
		
	}
}