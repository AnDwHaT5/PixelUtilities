package com.pixelutilitys.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Window2Block extends Block 
{
	public Window2Block (Material material) 
	{
		super(material);
		setHardness(2.0F); // 33% harder than diamond
		setStepSound(Block.soundTypeGlass);
		setBlockName("Window2");
		setCreativeTab(CreativeTabs.tabBlock);

		setBlockTextureName("pixelmonblocks" +":"+ "Window2");
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass()
	{
		return 0;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
	 */
	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}               
}