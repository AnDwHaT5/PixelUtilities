package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class AquaFloorBlock extends Block 
{

        public AquaFloorBlock (int id, Material material) 
        {
                super(material);
                setHardness(4.0F); // 33% harder than diamond
                setStepSound(Block.soundTypeMetal);
                setBlockName("AquaFloor");
                setCreativeTab(CreativeTabs.tabBlock);
                
                setBlockTextureName("pixelmonblocks" +":"+ "AquaFloor");
        }
                
              
    
}