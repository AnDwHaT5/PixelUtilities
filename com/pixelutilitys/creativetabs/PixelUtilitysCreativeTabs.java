package com.pixelutilitys.creativetabs;

import com.pixelutilitys.config.PixelUtilitysBlocks;
import com.pixelutilitys.config.PixelUtilitysItems;
import com.pixelutilitys.config.PixelUtilitysTools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PixelUtilitysCreativeTabs {

	public static CreativeTabs tabPixelmonBlocks = new CreativeTabs("tabPixelmonBlocks") {

		@Override
		public Item getTabIconItem() {
			return PixelUtilitysItems.TreeItem;
		}
	};

	public static CreativeTabs tabPokefurniture = new CreativeTabs("tabPokefurniture") {

		@Override
		public Item getTabIconItem() {
			return PixelUtilitysItems.TrashcanItem;
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
		public Item getTabIconItem() {
			return PixelUtilitysTools.rubyHammer;
		}
	};
}
