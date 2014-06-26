package com.pixelutilitys.blocks;

import java.awt.Color;
import java.util.Random;

import com.pixelutilitys.config.PixelUtilitysBlocks;
import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import coloredlightscore.src.api.CLApi;
import coloredlightscore.src.api.CLBlock;

public class LightBlock extends Block {
	
	int red,green,blue;

	public LightBlock(String name, Color rgb) {
		super(Material.redstoneLight);
		setHardness(0.5F);
		
		this.red = (rgb.getRed()/255)*15;
		this.green = (rgb.getGreen()/255)*15;
		this.blue = (rgb.getBlue()/255)*15;
		
		CLApi.setBlockColorRGB(this, red, green, blue, 65535);
		setBlockName(name+"Light");
		setBlockTextureName("pixelutilitys:"+name+"Light");
		
		//make sure this is last
		setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysLights);
	}
	
	@Override
	public Block setCreativeTab(CreativeTabs tabs)
	{
		super.setCreativeTab(tabs);
		PixelUtilitysBlocks.LightBlockList.add(this);
		return null;
	}
	

}
