package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SandyGrassBlock extends Block {

    public SandyGrassBlock() {
        super(Material.grass);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeSand);
        setBlockName("SandyGrass");
        setCreativeTab(CreativeTabs.tabBlock);

        setBlockTextureName("pixelmonblocks" + ":" + "SandyGrass");
    }


}