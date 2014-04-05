package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PokeSandSide2Block extends Block 
{

    public PokeSandSide2Block (int id, Material material) 
    {
            super(material);
            setHardness(4.0F); // 33% harder than diamond
            setStepSound(Block.soundTypeSand);
            setBlockName("PokeSandSide2");
            setCreativeTab(CreativeTabs.tabBlock);
            
            setBlockTextureName("pixelmonblocks" +":"+ "PokeSandSide2");
    }
                
              
    
}