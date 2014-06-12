package com.pixelutilitys.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class CrystalOre extends Block 
{

	public CrystalOre (Material material) 
	{
		super(material);
		setHardness(4.0F); // 33% harder than diamond
		setStepSound(Block.soundTypeStone);
		setBlockName("CrystalOre");
		setCreativeTab(CreativeTabs.tabBlock);

		setBlockTextureName("pixelmonblocks" +":"+ "CrystalOre");
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}


}