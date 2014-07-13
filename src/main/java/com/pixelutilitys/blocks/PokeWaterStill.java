package com.pixelutilitys.blocks;

import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import com.pixelutilitys.GrassSpawner;

public class PokeWaterStill extends BlockStaticLiquid{
	public static Boolean isActive=false;
	public PokeWaterStill(int par1, Material blockMaterial) {
		super(blockMaterial);

		this.blockHardness = 100.0F;
		this.setLightOpacity(3);
		this.disableStats();
	}
/*
	@Deprecated
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.theIcon = new Icon[] {
				(Icon) iconRegister.registerIcon("pixelutilitys:PokeWater"),
				(Icon) iconRegister.registerIcon("pixelutilitys:PokeWater") };
	}*/

	@Override
	public void onEntityCollidedWithBlock( World world, int x, int y, int z, Entity entity ) {
		isActive = true;
		PokeWaterFlowing.isActive = false;
		PixelmonGrassBlock.isActive = false;
		GrassSpawner.getInstance().spawnInGrass(world, x, y, z, entity);
	}



}