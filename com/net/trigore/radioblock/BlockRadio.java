package com.net.trigore.radioblock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRadio extends Block implements ITileEntityProvider {

	public TileEntityRadio radio;

	public BlockRadio(int par1, Material par2Material) {
		super(par2Material);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeStone);
		setBlockName("Radio");
		setCreativeTab(CreativeTabs.tabDecorations);
		setBlockTextureName("pixelutilitys:radio");
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par5EntityPlayer.isSneaking()) {

		} else {
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (side == Side.CLIENT) {
				openGUI(par1World, par2, par3, par4);
			}
		}

		return true;

	}

	@SideOnly(Side.CLIENT)
	private void openGUI(World par1World, int par2, int par3, int par4) {
		TileEntityRadio ter = (TileEntityRadio) par1World.getTileEntity(par2,
				par3, par4);
		Minecraft.getMinecraft().displayGuiScreen(new GuiRadio(ter));
	}

	

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;

	}

	@Override
	public int getRenderType() {
		return 189;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super
				.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		switch (par1IBlockAccess.getTileEntity(par2, par3, par4)
				.getBlockMetadata()) {
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

	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		// ModRadioBlock.killAllStreams();
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int l = MathHelper
				.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int i1 = par1World.getBlockMetadata(par2, par3, par4) >> 2;
		++l;
		l %= 4;

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 | i1 << 2,
					2);
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3 | i1 << 2,
					2);
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 | i1 << 2,
					2);
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1 | i1 << 2,
					2);
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World par1World, int x, int y,
			int z, Random par5Random) {

		TileEntityRadio radio = (TileEntityRadio) Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
		if(radio.isPlaying())
		for (int l = 0; l < 4; ++l) {
			double d0 = (double) ((float) x + par5Random.nextFloat());
			double d1 = (double) ((float) y + par5Random.nextFloat());
			double d2 = (double) ((float) z + par5Random.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = par5Random.nextInt(2) * 2 - 1;
			d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;

			if (par1World.getBlock(x - 1, y, z) != this
					&& par1World.getBlock(x + 1, y, z) != this) {
				d0 = (double) x + 0.5D + 0.25D * (double) i1;
				d3 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
			} else {
				d2 = (double) z + 0.5D + 0.25D * (double) i1;
				d5 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
			}

			par1World.spawnParticle("note", d0, d1, d2, d3, d4, d5);
		}

	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityRadio();
	}
}
