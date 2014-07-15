package com.pixelutilitys.economy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PokeCoin5Item extends Item {

    public PokeCoin5Item() {
        super();

        // Constructor Configuration
        maxStackSize = 64;
        setCreativeTab(CreativeTabs.tabMisc);
        setTextureName("PixelUtilitys:coin5");
        setUnlocalizedName("pokecoin5");
    }
}