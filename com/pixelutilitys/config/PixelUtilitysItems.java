package com.pixelutilitys.config;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import com.pixelutilitys.badges.*;
import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;
import com.pixelutilitys.economy.*;
import com.pixelutilitys.items.*;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelUtilitysItems {
	/*
	//IDs
	public static int PokeCoin1ItemID;
	public static int PokeCoin5ItemID;
	public static int PokeCoin10ItemID;
	public static int PokeCoin50ItemID;
	public static int PokeCoin100ItemID;
	public static int BugBadgeItemID;
	public static int FightingBadgeItemID;
	public static int FlyingBadgeItemID;
	public static int GroundBadgeItemID;
	public static int NormalBadgeItemID;
	public static int PoisonBadgeItemID;
	public static int RockBadgeItemID;
	public static int GhostBadgeItemID;
	public static int SteelBadgeItemID;
	public static int FireBadgeItemID;
	public static int WaterBadgeItemID;
	public static int GrassBadgeItemID;
	public static int ElectricBadgeItemID;
	public static int DarkBadgeItemID;
	public static int IceBadgeItemID;
	public static int PsychicBadgeItemID;
	public static int DragonBadgeItemID;
	public static int TreeItemID;
	public static int BolderItemID;
	public static int SaphireItemID;
	public static int RubyItemID;
	public static int BoxItemID;
	public static int AmethystItemID;
	public static int SiliconItemID;
	public static int ClothedTableItemID;
	public static int PokeballItemID;
	public static int RedCusionChairItemID;
	public static int TrashcanItemID;
	public static int YellowCusionChairItemID;
	public static int TotodilePokedollItemID;
	public static int AronPokedollItemID;
	public static int BisharpPokedollItemID;
	*/
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
	public static Item TreeItem;
	public static Item BolderItem;
	public static Item SaphireItem;
	public static Item RubyItem;
	public static Item BoxItem;
	public static Item AmethystItem;
	public static Item SiliconItem;
	public static Item ClothedTableItem;
	public static Item PokeballItem;
	public static Item RedCusionChairItem;
	public static Item TrashcanItem;
	public static Item YellowCusionChairItem;
	public static Item TotodilePokedollItem;
	public static Item AronPokedollItem;
	public static Item BisharpPokedollItem;
	public static Item CrystalItem;
	public static Item CDItem;
	
	public static void load(Configuration cfg) {
		/*
		PokeCoin1ItemID = cfg.get("item", "PokeCoin1ID", 6000).getInt();
		PokeCoin5ItemID = cfg.get("item", "PokeCoin5ID", 6001).getInt();
		PokeCoin10ItemID = cfg.get("item", "PokeCoin10ID", 6002).getInt();
		PokeCoin50ItemID = cfg.get("item", "PokeCoin50ID", 6003).getInt();
		PokeCoin100ItemID = cfg.get("item", "PokeCoin100ID", 6004).getInt();
		BugBadgeItemID = cfg.get("item", "BugBadgeID", 6005).getInt();
		FightingBadgeItemID = cfg.get("item", "FightBadgeID", 6006).getInt();
		FlyingBadgeItemID = cfg.get("item", "FlyBadgeID", 6007).getInt();
		GroundBadgeItemID = cfg.get("item", "GroundBadgeID", 6008).getInt();
		NormalBadgeItemID = cfg.get("item", "NormalBadgeID", 6009).getInt();
		PoisonBadgeItemID = cfg.get("item", "PoisonBadgeID", 6010).getInt();
		RockBadgeItemID = cfg.get("item", "RockBadgeID", 6011).getInt();
		GhostBadgeItemID = cfg.get("item", "GhostBadgeID", 6012).getInt();
		SteelBadgeItemID = cfg.get("item", "SteelBadgeID", 6013).getInt();
		FireBadgeItemID = cfg.get("item", "FireBadgeID", 6014).getInt();
		WaterBadgeItemID = cfg.get("item", "WaterBadgeID", 6015).getInt();
		GrassBadgeItemID = cfg.get("item", "GrassBadgeID", 6016).getInt();
		ElectricBadgeItemID = cfg.get("item", "ElectricBadgeID", 6017).getInt();
		DarkBadgeItemID = cfg.get("item", "DarkBadgeID", 6018).getInt();
		IceBadgeItemID = cfg.get("item", "IceBadgeID", 6019).getInt();
		PsychicBadgeItemID = cfg.get("item", "PsychicBadgeID", 6020).getInt();
		DragonBadgeItemID = cfg.get("item", "DragonBadgeID", 6021).getInt();
		TreeItemID = cfg.get("item", "TreeID", 6022).getInt();
		BolderItemID = cfg.get("item", "BolderID", 6023).getInt();
		SaphireItemID = cfg.get("item", "SaphireID", 7003).getInt();
		RubyItemID = cfg.get("item", "RubyID", 7002).getInt();
		BoxItemID = cfg.get("item", "BoxID", 6026).getInt();
		AmethystItemID = cfg.get("item", "AmethystID", 7001).getInt();
		SiliconItemID = cfg.get("item", "SiliconID", 7000).getInt();
		ClothedTableItemID = cfg.get("PokeFurniture", "ClothedTableItem", 6027).getInt();
		PokeballItemID = cfg.get("PokeFurniture", "PokeballItem", 6028).getInt();
		RedCusionChairItemID = cfg.get("PokeFurniture", "RedCushionItem", 6029).getInt();
		TrashcanItemID = cfg.get("PokeFurniture", "TrashcanItem", 6030).getInt();
		YellowCusionChairItemID = cfg.get("PokeFurniture", "YellowCusionItem", 6031).getInt();
		TotodilePokedollItemID = cfg.get("PokeFurniture", "TotodileDoll", 6032).getInt();
		AronPokedollItemID = cfg.get("PokeFurniture", "AronDoll", 6033).getInt();
		BisharpPokedollItemID = cfg.get("PokeFurniture", "BisharpDoll", 6034).getInt();
		*/
	    PokeCoin1Item = new PokeCoin1Item().setCreativeTab(CreativeTabs.tabMisc).setTextureName("PixelUtilitys:coin1");
		PokeCoin5Item = new PokeCoin5Item().setCreativeTab(CreativeTabs.tabMisc).setTextureName("PixelUtilitys:coin5");
		PokeCoin10Item = new PokeCoin10Item().setCreativeTab(CreativeTabs.tabMisc).setTextureName("PixelUtilitys:coin10");
		PokeCoin50Item = new PokeCoin50Item().setCreativeTab(CreativeTabs.tabMisc).setTextureName("PixelUtilitys:coin50");
		PokeCoin100Item = new PokeCoin100Item().setCreativeTab(CreativeTabs.tabMisc).setTextureName("PixelUtilitys:coin100");
		BugBadgeItem = new BugBadgeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges).setTextureName("PixelUtilitys:BugBadge");
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
		TreeItem = new TreeItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBlocks).setTextureName("PixelUtilitys:TreeIcon");
		BolderItem = new BolderItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBlocks).setTextureName("PixelUtilitys:BolderIcon");
		SaphireItem = new SaphireItem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Saphire");
		RubyItem = new RubyItem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Ruby");
		BoxItem = new BoxItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:Box");
		AmethystItem = new AmethystItem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Amethyst");
		SiliconItem  = new Siliconitem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Silicon");
		ClothedTableItem = new ClothedTableItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:ClothedTable");
		PokeballItem = new PokeballItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:Pokeball");
		RedCusionChairItem = new RedCusionChairItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:RedCusionChair");
		TrashcanItem = new TrashcanItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:Trashcan");
		YellowCusionChairItem = new YellowCusionChairItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:YellowCusionChair");
		TotodilePokedollItem = new TotodilePokedollItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:TotodileDoll");
		AronPokedollItem = new AronPokedollItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:AronDoll");
		BisharpPokedollItem = new BisharpPokedollItem().setCreativeTab(PixelUtilitysCreativeTabs.tabPokefurniture).setTextureName("PixelUtilitys:BisharpDoll");
		CrystalItem = new CrystalItem().setCreativeTab(CreativeTabs.tabMaterials).setTextureName("PixelUtilitys:Crystal");
		CDItem = new CDItem().setCreativeTab(CreativeTabs.tabMisc).setTextureName("PixelUtilitys:CD");
}

	public static void addNames() {
		/*
		//Items
		LanguageRegistry.addName(PokeCoin1Item, "PokeCoin $1");
		LanguageRegistry.addName(PokeCoin5Item, "PokeCoin $5");
		LanguageRegistry.addName(PokeCoin10Item, "PokeCoin $10");
		LanguageRegistry.addName(PokeCoin50Item, "PokeCoin $50");
		LanguageRegistry.addName(PokeCoin100Item, "PokeCoin $100");
		LanguageRegistry.addName(BolderItem, "Bolder");
		LanguageRegistry.addName(TreeItem, "Tree");
		LanguageRegistry.addName(SaphireItem, "Saphire");
		LanguageRegistry.addName(RubyItem, "Ruby");
		LanguageRegistry.addName(BoxItem, "Box");
		LanguageRegistry.addName(AmethystItem, "Amethyst");
		
		//Badges
		LanguageRegistry.addName(BugBadgeItem, "Bagu Badge");
		LanguageRegistry.addName(FightingBadgeItem, "Sento Badge");
		LanguageRegistry.addName(FlyingBadgeItem, "Tobu Badge");
		LanguageRegistry.addName(GhostBadgeItem, "Gosuto Badge");
		LanguageRegistry.addName(GroundBadgeItem, "Jimen Badge");
		LanguageRegistry.addName(NormalBadgeItem, "Nomaru Badge");
		LanguageRegistry.addName(PoisonBadgeItem, "Doku Badge");
		LanguageRegistry.addName(RockBadgeItem, "Iwa Badge");
		LanguageRegistry.addName(SteelBadgeItem, "Suchiru Badge");
		LanguageRegistry.addName(FireBadgeItem, "Kasai Badge");
		LanguageRegistry.addName(WaterBadgeItem, "Mizu Badge");
		LanguageRegistry.addName(GrassBadgeItem, "Kusa Badge");
		LanguageRegistry.addName(ElectricBadgeItem, "Dendo Badge");
		LanguageRegistry.addName(PsychicBadgeItem, "Seishin Badge");
		LanguageRegistry.addName(DarkBadgeItem, "Kurai Badge");
		LanguageRegistry.addName(IceBadgeItem, "Aisu Badge");
		LanguageRegistry.addName(DragonBadgeItem, "Doragon Badge");
		LanguageRegistry.addName(SiliconItem, "Silicon");*/
		
		//Items
		/*GameRegistry.registerItem(PokeCoin1Item, PokeCoin1Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeCoin5Item, PokeCoin5Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeCoin10Item, PokeCoin10Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeCoin50Item, PokeCoin50Item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeCoin100Item, PokeCoin100Item.getUnlocalizedName().substring(5));
		//GameRegistry.registerItem(BolderItem, BolderItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(TreeItem, TreeItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(SaphireItem, SaphireItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(RubyItem, RubyItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(BoxItem, BoxItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(AmethystItem, AmethystItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(TotodilePokedollItem, TotodilePokedollItem.getUnlocalizedName().substring(5));*/
		
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
		GameRegistry.registerItem(SiliconItem, SiliconItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(CDItem, CDItem.getUnlocalizedName().substring(5));
		/*
		LanguageRegistry.addName(TotodilePokedollItem, "Totodile Doll");
		LanguageRegistry.addName(BisharpPokedollItem, "Bisharp Doll");
		LanguageRegistry.addName(AronPokedollItem, "Aron Doll");
		LanguageRegistry.addName(ClothedTableItem, "Clothed Table");
		LanguageRegistry.addName(RedCusionChairItem, "Red Cushion Chair");
		LanguageRegistry.addName(TrashcanItem, "Trashcan");
		LanguageRegistry.addName(YellowCusionChairItem, "Yellow Cushion Chair");
		LanguageRegistry.addName(PokeballItem, "Placable Pokeball");*/
		/*GameRegistry.registerItem(ClothedTableItem, ClothedTableItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(RedCusionChairItem, RedCusionChairItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(TrashcanItem, TrashcanItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(YellowCusionChairItem, YellowCusionChairItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(PokeballItem, PokeballItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(TotodilePokedollItem, TotodilePokedollItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(AronPokedollItem, AronPokedollItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(BisharpPokedollItem, BisharpPokedollItem.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(CrystalItem, CrystalItem.getUnlocalizedName().substring(5));*/
		
	}
}