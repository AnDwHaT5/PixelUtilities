package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RubyBlock extends Block {

    public RubyBlock(Material material) {
        super(material);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeStone);
        setBlockName("RubyBlock");


        setBlockTextureName("pixelmonblocks" + ":" + "RubyBlock");
    }


}