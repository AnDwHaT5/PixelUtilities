package com.pixelutilitys.tools;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.pixelutilitys.Basemod;
import com.pixelutilitys.config.PixelUtilitysTools;
import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;
import com.pixelmonmod.pixelmon.config.PixelmonBlocks;
import com.pixelmonmod.pixelmon.items.ItemHammer;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PixelHammerTool extends ItemHammer {

	public PixelHammerTool(ToolMaterial par3EnumToolMaterial, String itemString, String itemName) {
		super(par3EnumToolMaterial, itemString, itemName);
		
		setTextureName(itemString);
		setUnlocalizedName(itemName);
		setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools);
	}
	
	
	//Because pixelmon devs are idiots we can't use this normally.
	String localTex = "";
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(localTex);
	}
	
	@Override
	public Item setTextureName(String tex)
	{
		localTex = tex;
		return this;
	}
	//////
	
	@Override
	public Item setCreativeTab(CreativeTabs tabs)
	{
		super.setCreativeTab(tabs);
		if(!PixelUtilitysTools.toolList.contains(this))
		PixelUtilitysTools.toolList.add(this);
		return null;
	}
	
	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		if (par2Block == PixelmonBlocks.anvil) {
			if (toolMaterial == toolMaterial.WOOD)
				return 1;
			else if (toolMaterial == toolMaterial.STONE)
				return 2;
			else if (toolMaterial == toolMaterial.IRON)
				return 3;
			else if (toolMaterial == toolMaterial.GOLD)
				return 4;
			else if (toolMaterial == toolMaterial.EMERALD)
				return 5;
			else if (toolMaterial == Basemod.RUBY)
				return 4;
			else if (toolMaterial == Basemod.SAPHIRE)
				return 4;
			else if (toolMaterial == Basemod.FIRESTONE)
				return 6;
			else if (toolMaterial == Basemod.WATERSTONE)
				return 6;
			else if (toolMaterial == Basemod.LEAFSTONE)
				return 4;
		}
		return 1;
	}
	
}
