package com.pixelutilitys.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.pixelutilitys.config.PixelUtilitysItems;
import com.pixelutilitys.entitys.AronPokedollEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class AronPokedollBlock extends BlockContainer {

	public AronPokedollBlock(int id, Material iron) {
		super(Material.iron);

		this.setBlockBounds(0.4F, 0.0F, 1.0F, 0.6F, 3.0F, 0.6F);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBounds(par1World.getBlockMetadata(par2, par3, par4));
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */

	public void func_82541_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

		if ((l & 4) != 0)
		{
			this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		}
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("AronDoll");
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 * 
	 * @param world
	 */
	public void setBlockBounds(int stage) {
		this.setBlockBounds(0.15f, 0, 0f, 0.8f, 0.6f, 0.6f);
	}
	/*
	@Deprecated
	public int idDropped(int par1, Random par2Random, int par3) {
		return PixelUtilitysItems.AronPokedollItem;
	}*/
	
	@SideOnly(Side.CLIENT)
	// only called by clickMiddleMouseButton , and passed to
	// inventory.setCurrentItem (along with isCreative)
	/*
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return PixelUtilitysItems.AronPokedollItem;
	}*/


	@Override
	public int quantityDropped(Random random) {
		return 1;
	}






	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public boolean isOpaqueCube(){
		return false;

	}

	@Override
	public int getRenderType()
	{
		return 189;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		switch(par1IBlockAccess.getTileEntity(par2, par3, par4).getBlockMetadata()){
		default:
			setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 0.5F, 0.7F);
			break;
		case 1:
			setBlockBounds(0.4F, 0.5F, 0.0F, 0.7F, 0.0F, 1.0F);
			break;
		case 2:
			setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 0.5F, 0.7F);
			break;
		case 3:
			setBlockBounds(0.4F, 0.5F, 0.0F, 0.7F, 0.0F, 1.0F);
			break;
		}

	}

	public void breakBlock (World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		//	ModRadioBlock.killAllStreams();
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int i1 = par1World.getBlockMetadata(par2, par3, par4) >> 2;
		++l;
		l %= 4;

		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 | i1 << 2, 2);
		}

		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3 | i1 << 2, 2);
		}

		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 | i1 << 2, 2);
		}

		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1 | i1 << 2, 2);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new AronPokedollEntity();
	}



	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}
	
	/**
	 * set a blocks direction
	 */
	private void setDefaultDirection(World world, int par2, int par3, int par4)
	{
		if (!world.isRemote)
		{
			Block l = world.getBlock(par2, par3, par4 - 1);
            Block i1 = world.getBlock(par2, par3, par4 + 1);
            Block j1 = world.getBlock(par2 - 1, par3, par4);
            Block k1 = world.getBlock(par2 + 1, par3, par4);
            byte b0 = 3;

            if (l.func_149730_j() && !i1.func_149730_j())
            {
                b0 = 3;
            }

            if (i1.func_149730_j() && !l.func_149730_j())
            {
                b0 = 2;
            }

            if (j1.func_149730_j() && !k1.func_149730_j())
            {
                b0 = 5;
            }

            if (k1.func_149730_j() && !j1.func_149730_j())
            {
                b0 = 4;
            }

			world.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

}