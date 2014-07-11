package com.pixelutilitys.achievements;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import com.pixelutilitys.armor.RubyHelmet;
import com.pixelutilitys.armor.SaphireBoots;
import com.pixelutilitys.armor.SaphireHelmet;
import com.pixelutilitys.armor.SaphireLegs;
import com.pixelutilitys.armor.SaphirePlate;
import com.pixelutilitys.config.PixelUtilitysArmor;
import com.pixelutilitys.config.PixelUtilitysItems;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PixelUtilitysAchievements
{
	// Call Once
	private static boolean called = false;

	private final static String INDEX = "wtf";
	private int magArmor = 0;
	private int aquaArmor = 0;

	public static Achievement teammagma = new Achievement(INDEX, "teammagma", 0, 0, PixelUtilitysArmor.rubyHelm, null);
	public static Achievement teamaqua = new Achievement(INDEX + 1, "teamaqua", 2, 0, PixelUtilitysArmor.saphireHelm, null);
	public static Achievement teammagma1 = new Achievement(INDEX + 2, "teammagma1", 0, 2, PixelUtilitysArmor.rubyPlate, teammagma);
	public static Achievement teamaqua1 = new Achievement(INDEX + 3, "teamaqua1", 2, 2, PixelUtilitysArmor.saphirePlate, teamaqua);
	public static Achievement getSilicon = new Achievement(INDEX + 4, "getSilicon", 1, -1, PixelUtilitysItems.SiliconItem, null);

	public static AchievementPage PixelUtilitysPage = new AchievementPage("PixelUtilitys", new Achievement[]{teammagma, teamaqua, teammagma1, teamaqua1, getSilicon});

	public static void setupAchievements()
	{

		if(called) 
		{
			return;
		}

		//addAllLanguageStrings();

		AchievementPage.registerAchievementPage(PixelUtilitysPage);

		called = true;
	}

	private static void addAllLanguageStrings(){

		addAchievementName("teammagma", "Team Magma Grunt!");
		addAchievementDesc("teammagma", "Join Team Magma!");

		addAchievementName("teamaqua", "Team Aqua Grunt!");
		addAchievementDesc("teamaqua", "Join Team Aqua!");

		addAchievementName("teammagma1", "Team Magma Executive!");
		addAchievementDesc("teammagma1", "Become a Team Magma Executive");

		addAchievementName("teamaqua1", "Team Aqua Executive!");
		addAchievementDesc("teamaqua1", "Become a Team Aqua Executive");

		addAchievementName("getSilicon", "Get Silicon!");
		addAchievementDesc("getSilicon", "Smelt some silicon");

	}

	@EventHandler
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {

		if(item.getItem() instanceof RubyHelmet) {
			player.addStat(teammagma, 1);
		}
		if(item.getItem() instanceof SaphirePlate || item.getItem() instanceof SaphireHelmet || item.getItem() instanceof SaphireBoots || item.getItem() instanceof SaphireLegs) {
			player.addStat(teamaqua, 1);
		}


	}

	@EventHandler
	public void onSmelting(EntityPlayer player, ItemStack item) {

		//if(item.itemID == PixelUtilitysItems.SiliconItem.itemID) {
		//	player.addStat(getSilicon, 1);
		//}

	}

	@Deprecated
	private static void addAchievementName(String ach, String name)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
	}

	@Deprecated
	private static void addAchievementDesc(String ach, String desc)
	{
		LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}	
}