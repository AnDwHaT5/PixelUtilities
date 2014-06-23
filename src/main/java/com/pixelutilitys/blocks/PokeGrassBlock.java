package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PokeGrassBlock extends Block 
{

	public PokeGrassBlock (Material material) 
	{
		super(material);
		setHardness(4.0F); // 33% harder than diamond
		setStepSound(Block.soundTypeGrass);
		setBlockName("PokeGrass");
		setCreativeTab(CreativeTabs.tabBlock);
	}

}