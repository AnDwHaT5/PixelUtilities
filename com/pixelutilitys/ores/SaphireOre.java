package com.pixelutilitys.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

import com.pixelutilitys.config.PixelUtilitysBlocks;
import com.pixelutilitys.config.PixelUtilitysItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SaphireOre extends Block 
{

        public SaphireOre (int id, Material material) 
        {
                super(material);
                setHardness(4.0F); // 33% harder than diamond
                setStepSound(Block.soundTypeStone);
                setBlockName("SaphireOre");
                setCreativeTab(CreativeTabs.tabBlock);
                
                setBlockTextureName("pixelmonblocks" +":"+ "SaphireOre");
        }
        /*
        @Deprecated
    	public int idDropped(int par1, Random par2Random, int par3) {
        	return PixelUtilitysItems.SaphireItemID;
    	}

    	@SideOnly(Side.CLIENT)
    	// only called by clickMiddleMouseButton , and passed to
    	// inventory.setCurrentItem (along with isCreative)
    	public int idPicked(World par1World, int par2, int par3, int par4) {
    		return PixelUtilitysBlocks.SaphireOreID;
    	}*/


    	@Override
    	public int quantityDropped(Random random) {
    		return 1;
    	}
              
    
}