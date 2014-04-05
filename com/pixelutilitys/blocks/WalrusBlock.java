package com.pixelutilitys.blocks;

import java.util.Random;

import javax.vecmath.Vector3f;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.pixelutilitys.BlockRotation;
import com.pixelutilitys.entitys.WalrusTileEntity;
import com.pixelmonmod.pixelmon.blocks.MultiBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WalrusBlock extends MultiBlock {



	public WalrusBlock(int par1, Material par2Material) {
		super(par2Material, 3, 3, 3);
	}

	@Deprecated
	public int idDropped(int par1, Random par2Random, int par3) {
		return -1;
	}

	@SideOnly(Side.CLIENT)
	// only called by clickMiddleMouseButton , and passed to
	// inventory.setCurrentItem (along with isCreative)
	public int idPicked(World par1World, int par2, int par3, int par4) {

			return 3099;
		
	}

	@Override
	public void setBlockBounds(World world, int x, int y, int z, int meta) {
		if (meta < 4) {
			BlockRotation rot = BlockRotation.getRotationFromMetadata(meta);
			if (rot == BlockRotation.Normal)
				this.setBlockBounds(0f, 0, 0f, width, 2.2f, length);
			else if (rot == BlockRotation.Rotate180)
				this.setBlockBounds(-width + 1, 0, 0f, 1, 2.2f, length);
			else if (rot == BlockRotation.CCW)
				this.setBlockBounds(0f, 0, 0f, length, 2.2f, width);
			else
				this.setBlockBounds(0f, 0, 1f, length, 2.2f, -1 * width);
		} else {
			com.pixelmonmod.pixelmon.tools.Vector3f vec = findBaseBlock(world, x, y, z, meta);
			if (y-vec.y >= 2)
				this.setBlockBounds(0, 0, 0, 1f, 0.2f, 1f);
			else
				this.setBlockBounds(0, 0, 0, 1f, 1f, 1f);
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
			blockIcon = par1IconRegister.registerIcon("pixelutilitys:Walrus");
		
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
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new WalrusTileEntity();
	}

	@Deprecated
	public int getDroppedItem() {
		return 3099;
	}

	@Override
	public Item getDroppedItem(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

}
