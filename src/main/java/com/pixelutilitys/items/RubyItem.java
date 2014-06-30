package com.pixelutilitys.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.pixelutilitys.Basemod;
public class RubyItem extends Item {

        public RubyItem() {
                super();
                
                // Constructor Configuration
                maxStackSize = 64;
                setUnlocalizedName("RubyItem");
        }
}