package com.pixelutilitys.config;

import com.pixelutilitys.Basemod;
import com.pixelutilitys.liquids.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockLiquid;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class PixelUtilitysLiquids {

    public static Fluid sewage = new Fluid("sewage");

    public static void registerLiquids()
    {
        FluidRegistry.registerFluid(sewage);
        LiquidSewage sewageBlock = new LiquidSewage(sewage);
        GameRegistry.registerBlock(sewageBlock, Basemod.MODID+"_"+sewageBlock.getUnlocalizedName().substring(5));
        sewage.setUnlocalizedName(sewageBlock.getUnlocalizedName());
    }

}
