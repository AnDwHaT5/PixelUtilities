package com.pixelutilitys.blocks.ores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.Random;

public class SiliconOre extends Block {

    public SiliconOre() {
        super(Material.rock);
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