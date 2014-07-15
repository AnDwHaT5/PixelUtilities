package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AmethystBlock extends Block {

    public AmethystBlock(Material material) {
        super(material);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeMetal);
        setBlockName("AmethystBlock");
        setBlockTextureName("pixelmonblocks" + ":" + "AmethystBlock");
    }
}