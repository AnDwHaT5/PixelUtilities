package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PUBlock extends Block{

	public PUBlock(Material p_i45394_1_, String blockName, float hardness, CreativeTabs tab, Block.SoundType soundType) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
		
		setHardness(hardness); // 33% harder than diamond
        setStepSound(soundType);
        setBlockName(blockName);
        setCreativeTab(tab);
        setBlockTextureName("pixelutilitys" +":"+ blockName);
	}

}
