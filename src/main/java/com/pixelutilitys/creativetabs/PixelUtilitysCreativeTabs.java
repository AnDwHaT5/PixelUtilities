package com.pixelutilitys.creativetabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pixelutilitys.config.PixelUtilitysBlocks;
import com.pixelutilitys.config.PixelUtilitysItems;
import com.pixelutilitys.config.PixelUtilitysTools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
			for(Item item : PixelUtilitysTools.toolList)
				itemList.add(new ItemStack(item,1,0));
			
		}
		
		@Override
		public Item getTabIconItem() {
			return PixelUtilitysTools.rubyHammer;
		}
	};
	
	public static CreativeTabs tabPixelUtilitysLights = new CreativeTabs("tabPixelUtilitysLights") {
		
		@Override
		@SideOnly(Side.CLIENT)
		public void displayAllReleventItems(List itemList)//Allows us to not deal with vanillas stupid sorting
		{
			for(Block block : PixelUtilitysBlocks.LightBlockList)
				itemList.add(new ItemStack(block,1,0));
			
		}
		
		@Override
		public Item getTabIconItem() {
			return new ItemStack(PixelUtilitysBlocks.blueLightBlock).getItem();
		}
	};
}
