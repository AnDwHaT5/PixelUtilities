package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PokeSandSide3Block extends Block {

    public PokeSandSide3Block() {
        super(Material.sand);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeSand);
        setBlockName("PokeSandSide3");
        setCreativeTab(CreativeTabs.tabBlock);

        setBlockTextureName("pixelmonblocks" + ":" + "PokeSandSide3");
    }


}