package com.pixelutilitys.tools;

import com.pixelutilitys.config.PixelUtilitysTools;
import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseHammerTool extends Item {
    //lolhax.
    public BaseHammerTool(ToolMaterial par3EnumToolMaterial, String itemString, String itemName) {
        super();

        setTextureName(itemString);
        setUnlocalizedName(itemName);
        setCreativeTab(PixelUtilitysCreativeTabs.tabPixelUtilitysTools);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());
    }

    @Override
    public Item setCreativeTab(CreativeTabs tabs) {
        super.setCreativeTab(tabs);
        if (!PixelUtilitysTools.toolList.contains(this))
            PixelUtilitysTools.toolList.add(this);
        return null;
    }


}
