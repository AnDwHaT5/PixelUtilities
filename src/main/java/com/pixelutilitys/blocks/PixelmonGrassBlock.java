package com.pixelutilitys.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.pixelutilitys.GrassSpawner;

public class PixelmonGrassBlock extends Block {

    private GrassSpawner grassSpawner = GrassSpawner.getInstance();
	public static Boolean isActive=false;
	
    public PixelmonGrassBlock (Material material) 
    {
		super(material);
		setHardness(4.0F); // 33% harder than diamond
		setStepSound(Block.soundTypeGrass);
		setBlockName("PixelmonGrass");
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockTextureName("pixelmonblocks" +":"+ "PixelmonGrass");
	}
    
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return 2;
	}
	
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return null;
	}
    
    @Override
    public void onEntityCollidedWithBlock( World world, int x, int y, int z, Entity entity ) {
    	isActive = true;
    	PokeWaterFlowing.isActive = false;
    	PokeWaterStill.isActive = false;
    	grassSpawner.spawnInGrass(world, x, y, z, entity);
    }
    
}