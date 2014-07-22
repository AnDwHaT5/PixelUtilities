package com.pixelutilitys.creativetabs;

import com.pixelutilitys.config.PixelUtilitysBlocks;
import com.pixelutilitys.config.PixelUtilitysItems;
import com.pixelutilitys.config.PixelUtilitysTools;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class PixelUtilitysCreativeTabs {

    public static CreativeTabs tabPixelmonBlocks = new CreativeTabs("tabPixelmonBlocks") {

        @Override
        public Item getTabIconItem() {
            return new ItemStack(PixelUtilitysBlocks.TreeBlock).getItem();
        }
    };

    public static CreativeTabs tabPokefurniture = new CreativeTabs("tabPokefurniture") {

        @Override
        public Item getTabIconItem() {
            return new ItemStack(PixelUtilitysBlocks.TrashcanBlock).getItem();
        }
    };

    public static CreativeTabs tabPixelmonBadges = new CreativeTabs("tabPixelmonBadges") {

        @Override
        public Item getTabIconItem() {
            return PixelUtilitysItems.BugBadgeItem;
        }
    };

    public static CreativeTabs tabPixelUtilitysTools = new CreativeTabs("tabPixelUtilitysTools") {

        @Override
        @SideOnly(Side.CLIENT)
        public void displayAllReleventItems(List itemList)//Allows us to not deal with vanillas stupid sorting
        {
            itemList.clear();
            for (Item item : PixelUtilitysTools.getInstance().getToolList())
                itemList.add(new ItemStack(item, 1, 0));

        }

        @Override
        public Item getTabIconItem() {
            return PixelUtilitysTools.getInstance().rubyAxe;
        }
    };

    public static CreativeTabs tabPixelUtilitysLights = new CreativeTabs("tabPixelUtilitysLights") {

        @Override
        @SideOnly(Side.CLIENT)
        public void displayAllReleventItems(List itemList)//Allows us to not deal with vanillas stupid sorting
        {
            for (Block block : PixelUtilitysBlocks.LightBlockList)
                itemList.add(new ItemStack(block, 1, 0));
        }

        @Override
        public Item getTabIconItem() {
            return new ItemStack(PixelUtilitysBlocks.blueLightBlock).getItem();
        }
    };
}
