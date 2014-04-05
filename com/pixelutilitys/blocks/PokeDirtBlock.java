package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PokeDirtBlock extends Block 
{

    public PokeDirtBlock (int id, Material material) 
    {
            super(material);
            setHardness(4.0F); // 33% harder than diamond
            setStepSound(Block.soundTypeGravel);
            setBlockName("PokeDirt");
            setCreativeTab(CreativeTabs.tabBlock);
            
            setBlockTextureName("pixelmonblocks" +":"+ "PokeDirt");
    }
            
          

}