package com.pixelutilitys.blocks;

import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;
import com.pixelutilitys.tileentitys.TileEntityConveyor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

public class BlockConveyor extends BlockContainer {
    @SideOnly(Side.CLIENT)
    private IIcon base, overlay, overlayFast, overlayStopped;
    private int renderPass;

    public BlockConveyor() {
        super(Material.circuits);
        setHardness(0.5F);
        setBlockBounds(0.0F, 0.0F, 0.0F, 0.1F, 0.01F, 0.1F);
        setBlockName("conveyor");
        setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBlocks);
    }

    @Override
    public boolean canRenderInPass(int pass) {
        renderPass = pass;
        return true;
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        base = ir.registerIcon("pixelutilitys:" + getUnlocalizedName() + ".base");
        overlay = ir.registerIcon("pixelutilitys:" + getUnlocalizedName() + ".overlay");
        overlayFast = ir.registerIcon("pixelutilitys:" + getUnlocalizedName() + ".overlay.fast");
        overlayStopped = ir.registerIcon("pixelutilitys:" + getUnlocalizedName() + ".overlay.stopped");
    }

    @Override
    public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int colour) {
        TileEntity tile = world.getTileEntity(x, y, z);

        if (tile instanceof TileEntityConveyor) {
            int dye = ((TileEntityConveyor) tile).getDyeColor();
            ((TileEntityConveyor) tile).setDyeColor(colour);
            return dye != ((TileEntityConveyor) tile).getDyeColor();
        }
        return false;
    }

    @Override
    public int getRenderColor(int meta) {
        return 0xFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (renderPass == 1)
            switch (meta) {
                case 0:
                    return overlayStopped;
                case 1:
                    return overlay;
                case 2:
                    return overlayFast;
            }
        return base;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = 0;
        if (renderPass == 1) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof TileEntityConveyor) {
                TileEntityConveyor tec = (TileEntityConveyor) tile;
                meta = tec.isFast() ? 2 : 1;
            }
        }
        return getIcon(side, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (entity == null) {
            return;
        }
        int facing = MathHelper.floor_double((entity.rotationYaw * 4F) / 360F + 0.5D) & 3;
        if (facing == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }
        if (facing == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (facing == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (facing == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }

        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityConveyor) {
            ((TileEntityConveyor) te).setDyeColor(stack.getItemDamage() == 16 ? -1 : stack.getItemDamage());
        }
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int i, int j, int k, Vec3 vec3d, Vec3 vec3d1) {
        setBlockBoundsBasedOnState(world, i, j, k);
        return super.collisionRayTrace(world, i, j, k, vec3d, vec3d1);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k) {
        int l = iblockaccess.getBlockMetadata(i, j, k);
        if (l >= 4 && l <= 11) {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        } else {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        }
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 500;//TODO BAD BAD BAD BAD BAD
        //return MineFactoryReloadedCore.renderIdConveyor;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return canBlockStay(world, x, y, z);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return world.isSideSolid(x, y - 1, z, ForgeDirection.UP);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset) {
        ItemStack item = player.getHeldItem();

        if (item != null && item.getItem().equals(Items.glowstone_dust)) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileEntityConveyor && !((TileEntityConveyor) te).isFast()) {
                ((TileEntityConveyor) te).setFast(true);
                world.markBlockForUpdate(x, y, z);
                if (!player.capabilities.isCreativeMode)
                    item.stackSize--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborId) {
        if (!canBlockStay(world, x, y, z)) {
            world.setBlockToAir(x, y, z);
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            return;
        }

        TileEntity tec = world.getTileEntity(x, y, z);
        if (tec instanceof TileEntityConveyor) {
            ((TileEntityConveyor) tec).updateConveyorActive();
        }
    }


    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        double playerx = entity.posX;
        double playerz = entity.posZ;
        if (!((playerz > z + 0.40) && (playerz < z + 0.60)))
            if (!((playerx > x + 0.40) && (playerx < x + 0.60)))
                return;

        boolean isItem = entity instanceof EntityItem || entity instanceof EntityXPOrb;

        if (!(isItem || entity instanceof EntityLivingBase || entity instanceof EntityTNTPrimed))
            return;

        TileEntity conveyor = world.getTileEntity(x, y, z);
        if (!(conveyor instanceof TileEntityConveyor))
            return;

        if (entity instanceof EntityLivingBase)
            l:{
                ItemStack item = ((EntityLivingBase) entity).getEquipmentInSlot(1);
                if (item == null) break l;
            }
        double mult = ((TileEntityConveyor) conveyor).isFast() ? 500.1 : 10.15;

        double xVelocity = 0;
        double yVelocity = 0;
        double zVelocity = 0;

        int md = world.getBlockMetadata(x, y, z);

        int horizDirection = md & 0x03;
        boolean isUphill = (md & 0x04) != 0;
        boolean isDownhill = (md & 0x08) != 0;

        if (isUphill) {
            yVelocity = 0.17D * mult;
        } else if (entity.posY - y < 0.1 & entity.posY - y > 0) {
            entity.posY = y + 0.1;
        } else if (isDownhill) {
            yVelocity = -0.07 * mult;
        }

        if (isUphill | isDownhill) {
            entity.onGround = false;
        }

        switch (horizDirection) {
            case 0:
                zVelocity = 0;
                xVelocity = 0.1D * mult;
                break;
            case 1:
                xVelocity = 0;
                zVelocity = 0.1D * mult;
                break;
            case 2:
                zVelocity = 0;
                xVelocity = -0.1D * mult;
                break;
            case 3:
                xVelocity = 0;
                zVelocity = -0.1D * mult;
                break;
        }
        entity.setPosition(x + 0.5, entity.posY + 0.1, z + 0.5);

        setEntityVelocity(entity, xVelocity, yVelocity, zVelocity);

        entity.fallDistance *= 0.9;
        if (entity instanceof EntityItem) {
            ((EntityItem) entity).delayBeforeCanPickup = 40;
        }
    }

    private void setEntityVelocity(Entity e, double x, double y, double z) {
        e.motionX = x;
        e.motionY = y;
        e.motionZ = z;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityConveyor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        int dyeColor = 16;
        if (te instanceof TileEntityConveyor) {
            dyeColor = ((TileEntityConveyor) te).getDyeColor();
            if (dyeColor == -1) dyeColor = 16;
        }
        return getRenderColor(dyeColor);
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        int dyeColor = 16;
        if (te instanceof TileEntityConveyor) {
            dyeColor = ((TileEntityConveyor) te).getDyeColor();
            if (dyeColor == -1) dyeColor = 16;
        }
        return dyeColor;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<>();
        if (world.getBlock(x, y, z).equals(this)) {
            ret.add(new ItemStack(this, 1, getDamageValue(world, x, y, z)));
            if (((TileEntityConveyor) world.getTileEntity(x, y, z)).isFast())
                ret.add(new ItemStack(Items.glowstone_dust, 1));
        }
        return ret;
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) { // HACK: called before block is destroyed by the player prior to the player getting the drops. destroy block here.
        if (!player.capabilities.isCreativeMode) {
            world.func_147480_a(x, y, z, true);
        }
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int meta) {
        return false;
    }

    @Override
    public boolean canProvidePower() {
        return false;
    }

}
