package com.pixelutilitys.badges;


import com.pixelutilitys.creativetabs.PixelUtilitysCreativeTabs;

import net.minecraft.item.Item;

public class BugBadgeItem extends Item {
	
        public BugBadgeItem() {
                super();
                
                // Constructor Configuration
                maxStackSize = 64;
                setCreativeTab(PixelUtilitysCreativeTabs.tabPixelmonBadges);
                setTextureName("PixelUtilitys:BugBadge");
                setUnlocalizedName("bugbadge");
        }
}