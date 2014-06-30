package com.pixelutilitys.tools;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

import com.pixelutilitys.Basemod;
import com.pixelmonmod.pixelmon.config.PixelmonBlocks;
import com.pixelmonmod.pixelmon.items.ItemHammer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LeafstoneHammer extends ItemHammer{

	String iconString;


	public LeafstoneHammer(ToolMaterial par3EnumToolMaterial, String iconString,
			String itemName) {
		super(par3EnumToolMaterial, iconString, itemName);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("pixelutilitys:" + "LeafstoneHammer");
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
