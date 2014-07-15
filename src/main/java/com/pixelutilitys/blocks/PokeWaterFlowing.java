package com.pixelutilitys.blocks;


import com.pixelutilitys.GrassSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class PokeWaterFlowing extends BlockFluidClassic {
    public static Boolean isActive = false;

    public PokeWaterFlowing(Fluid par1, Material blockMaterial) {
        super(par1, blockMaterial);

        this.blockHardness = 100.0F;
        this.setLightOpacity(3);
        //this.setCreativeTab(CreativeTabs.tabBlocks);
        //Uncomment this if you wish to test and need it in creative mode!
    }
        
    /*@Deprecated
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.theIcon = new Icon[] {
                (Icon) iconRegister.registerIcon("pixelutilitys:PokeWater"),
                (Icon) iconRegister.registerIcon("pixelutilitys:PokeWater") };
    }*/


    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        isActive = true;
        PokeWaterStill.isActive = false;
        PixelmonGrassBlock.isActive = false;

        GrassSpawner.getInstance().spawnInGrass(world, x, y, z, entity);

    }


}