package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BridgeBlockBlock extends Block {

    public BridgeBlockBlock() {
        super(Material.wood);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeWood);
        setBlockName("BridgeBlock");
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockTextureName("pixelmonblocks" + ":" + "BridgeBlock");
    }
}