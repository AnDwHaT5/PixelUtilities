package com.pixelutilitys.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

import com.pixelutilitys.config.PixelUtilitysBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SiliconOre extends Block 
{

        public SiliconOre (int id, Material material) 
        {
                super(material);
                setHardness(4.0F); // 33% harder than diamond
                setStepSound(Block.soundTypeStone);
                setBlockName("SiliconOre");
                setCreativeTab(CreativeTabs.tabBlock);
                
                setBlockTextureName("pixelmonblocks" +":"+ "SiliconOre");
        }
        @Deprecated
    	public int idDropped(int par1, Random par2Random, int par3) {
    		return PixelUtilitysBlocks.SiliconOreID;
    	}

    	@SideOnly(Side.CLIENT)
    	// only called by clickMiddleMouseButton , and passed to
    	// inventory.setCurrentItem (along with isCreative)
    	public int idPicked(World par1World, int par2, int par3, int par4) {
    		return PixelUtilitysBlocks.SiliconOreID;
    	}

      
    	@Override
    	public int quantityDropped(Random random) {
    		return 1;
    	}
              
    
}