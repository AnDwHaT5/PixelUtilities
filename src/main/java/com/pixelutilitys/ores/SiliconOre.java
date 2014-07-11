package com.pixelutilitys.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SiliconOre extends Block 
{

	public SiliconOre (Material material) 
	{
		super(material);
		setHardness(4.0F); // 33% harder than diamond
		setStepSound(Block.soundTypeStone);
		setBlockName("SiliconOre");
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}


}