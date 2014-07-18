package com.pixelutilitys.achievements;

import com.pixelutilitys.items.armor.*;
import com.pixelutilitys.config.PixelUtilitysArmor;
import com.pixelutilitys.config.PixelUtilitysItems;
import cpw.mods.fml.common.Mod.EventHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class PixelUtilitysAchievements {
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

    public static AchievementPage PixelUtilitysPage = new AchievementPage("PixelUtilitys", teammagma, teamaqua, teammagma1, teamaqua1, getSilicon);

    public static void setupAchievements() {
        if (called) {
            return;
        }

        AchievementPage.registerAchievementPage(PixelUtilitysPage);

        called = true;
    }

    @EventHandler
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
        if (item.getItem() instanceof RubyHelmet) {
            player.addStat(teammagma, 1);
        }
        if (item.getItem() instanceof SaphirePlate || item.getItem() instanceof SaphireHelmet || item.getItem() instanceof SaphireBoots || item.getItem() instanceof SaphireLegs) {
            player.addStat(teamaqua, 1);
        }
    }

    @EventHandler
    public void onSmelting(EntityPlayer player, ItemStack item) {

        //if(item.itemID == PixelUtilitysItems.SiliconItem.itemID) {
        //	player.addStat(getSilicon, 1);
        //}

    }

}