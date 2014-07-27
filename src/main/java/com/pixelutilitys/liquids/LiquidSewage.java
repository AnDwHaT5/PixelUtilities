package com.pixelutilitys.liquids;

import com.pixelutilitys.Basemod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class LiquidSewage extends BlockFluidClassic {

    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;

    public LiquidSewage(Fluid fluid) {
        super(fluid,Material.water);
        setCreativeTab(CreativeTabs.tabMisc);
        setBlockName("sewageFluid");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.stillIcon = iconRegister.registerIcon(Basemod.MODID+":sewageStill");
        this.flowingIcon = iconRegister.registerIcon(Basemod.MODID+":sewageFlowing");
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
        return super.displaceIfPossible(world, x, y, z);
    }
}
