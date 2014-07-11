package com.pixelutilitys.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class RubyOre extends Block 
{

        public RubyOre (Material material) 
        {
                super(material);
                setHardness(4.0F); // 33% harder than diamond
                setStepSound(Block.soundTypeStone);
                setBlockName("RubyOre");
                setCreativeTab(CreativeTabs.tabBlock);
                
                setBlockTextureName("pixelmonblocks" +":"+ "RubyOre");
        }
        /*
        @Deprecated
    	public int idDropped(int par1, Random par2Random, int par3) {
    		return PixelUtilitysItems.RubyItemID;
    	}

    	@SideOnly(Side.CLIENT)
    	// only called by clickMiddleMouseButton , and passed to
    	// inventory.setCurrentItem (along with isCreative)
    	public int idPicked(World par1World, int par2, int par3, int par4) {
    		return PixelUtilitysBlocks.RubyOreID;
    	}*/

      
    	@Override
    	public int quantityDropped(Random random) {
    		return 1;
    	}
              
    
}