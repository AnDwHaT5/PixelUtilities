package com.pixelutilitys.armor;

import com.pixelutilitys.config.PixelUtilitysArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class RocketLegs extends ItemArmor {

    public RocketLegs(ArmorMaterial par2EnumArmorMaterial,
                      int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(CreativeTabs.tabCombat);
        setTextureName("pixelUtilities:RocketLegs");
        setUnlocalizedName("Rocket Leggings");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (stack.getItem() == PixelUtilitysArmor.saphireLegs) {
            return "pixelutilitys:textures/armor/RocketArmor_2.png";
        } else {
            return "pixelutilitys:textures/armor/RocketArmor_1.png";
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("pixelutilitys:RocketLeggings");
    }
}
