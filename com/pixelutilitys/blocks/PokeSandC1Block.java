package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PokeSandC1Block extends Block 
{

    public PokeSandC1Block (int id, Material material) 
    {
            super(material);
            setHardness(4.0F); // 33% harder than diamond
            setStepSound(Block.soundTypeSand);
            setBlockName("PokesandCorner1");
            setCreativeTab(CreativeTabs.tabBlock);
            setBlockTextureName("pixelmonblocks" +":"+ "PokeSandC1");
    }
                
              
    
}