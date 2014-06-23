package com.pixelutilitys.badges;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class NormalBadgeItem extends Item {

        public NormalBadgeItem() {
                super();
                
                // Constructor Configuration
                maxStackSize = 64;
                
                setUnlocalizedName("normalbadge");
        }
}