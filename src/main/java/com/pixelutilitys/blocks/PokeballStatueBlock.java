package com.pixelutilitys.blocks;

import com.pixelmonmod.pixelmon.blocks.MultiBlock;
import com.pixelutilitys.BlockRotation;
import com.pixelutilitys.tileentitys.PokeballStatueTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class PokeballStatueBlock extends MultiBlock {


    public PokeballStatueBlock(Material par2Material) {
        super(par2Material, 1, 3, 1);
    }

    @Override
    public void setBlockBounds(World world, int x, int y, int z, int meta) {
        if (meta < 4) {
            BlockRotation rot = BlockRotation.getRotationFromMetadata(meta);
            if (rot == BlockRotation.Normal)
                this.setBlockBounds(0f, 0, 0f, 1, 3.0f, 1);
            else if (rot == BlockRotation.Rotate180)
                this.setBlockBounds(0f, 0, 0f, 1, 3.0f, 1);
            else if (rot == BlockRotation.CCW)
                this.setBlockBounds(0f, 0, 0f, 1, 3.0f, 1);
            else
                this.setBlockBounds(0f, 0, 0f, 1, 3.0f, 1);
        } else {
            com.pixelmonmod.pixelmon.tools.Vector3f vec = findBaseBlock(world, x, y, z, meta);
            if (y - vec.y >= 2)
                this.setBlockBounds(0f, 0, 0f, 1, 3.0f, 1);
            else
                this.setBlockBounds(0f, 0, 0f, 1, 3.0f, 1);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBounds(par1World.getBlockMetadata(par2, par3, par4), blockHardness, blockHardness, blockHardness, blockHardness, blockHardness);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBounds(par1World.getBlockMetadata(par2, par3, par4), blockHardness, blockHardness, blockHardness, blockHardness, blockHardness);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        if ((l & 4) != 0) {
            this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcon = par1IconRegister.registerIcon("pixelutilitys:PokeballStatue");
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
        return new PokeballStatueTileEntity();
    }

    @Override
    public Item getDroppedItem(World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack par6ItemStack) {

        if (!world.isRemote) {

            // Player ID
            String playerID = ((EntityPlayerMP) player).getDisplayName();

            // Set Owner
            PokeballStatueTileEntity tile = ((PokeballStatueTileEntity) world.getTileEntity(x, y, z));
            tile.setOwner(playerID);

            // Set Front Face
            int face = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
            world.setBlockMetadataWithNotify(x, y, z, face + 1, 2); // +1 to not
            // get 0
            // (inv
            // block)

        }

    }
}
