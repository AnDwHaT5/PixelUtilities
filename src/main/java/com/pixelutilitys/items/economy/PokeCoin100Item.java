package com.pixelutilitys.items.economy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PokeCoin100Item extends Item {

    public PokeCoin100Item() {
        super();

        // Constructor Configuration
        maxStackSize = 64;
        setCreativeTab(CreativeTabs.tabMisc);
        setTextureName("PixelUtilitys:coin100");
        setUnlocalizedName("pokecoin100");
    }
}