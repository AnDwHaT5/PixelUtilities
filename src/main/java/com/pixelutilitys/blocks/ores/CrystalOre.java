package com.pixelutilitys.blocks.ores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import java.util.Random;

public class CrystalOre extends Block {

    public CrystalOre() {
        super(Material.rock);
        setHardness(4.0F); // 33% harder than diamond
        setStepSound(Block.soundTypeStone);
        setBlockName("CrystalOre");
        setCreativeTab(CreativeTabs.tabBlock);

        setBlockTextureName("pixelmonblocks" + ":" + "CrystalOre");
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }


}