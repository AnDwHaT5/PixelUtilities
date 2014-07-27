package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PokeSandBlock extends Block {

    public PokeSandBlock() {
        super(Material.sand);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeSand);
        setBlockName("PokeSand");
        setCreativeTab(CreativeTabs.tabBlock);

    }

}