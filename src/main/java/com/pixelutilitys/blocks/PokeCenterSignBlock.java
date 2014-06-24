package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class PokeCenterSignBlock extends Block 
{

    public PokeCenterSignBlock (Material material) 
    {
            super(material);
            setHardness(4.0F); // 33% harder than diamond
            setStepSound(Block.soundTypeStone);
            setBlockName("PokeCenterSign");
            setCreativeTab(CreativeTabs.tabBlock);
            
            setBlockTextureName("pixelmonblocks" +":"+ "Pokecenter");
    }
            
          

}