package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TreeBottomBlock extends Block {

    public TreeBottomBlock() {
        super(Material.wood);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeWood);
        setBlockName("Tree Bottom");
        setCreativeTab(CreativeTabs.tabBlock);

        setBlockTextureName("pixelmonblocks" + ":" + "TreeBottom");
    }


}