package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ShinglesCorner1Block extends Block {

    public ShinglesCorner1Block() {
        super(Material.rock);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeWood);
        setBlockName("Shingles Corner 1");
        setCreativeTab(CreativeTabs.tabBlock);

        setBlockTextureName("pixelmonblocks" + ":" + "ShinglesCorner1");
    }


}