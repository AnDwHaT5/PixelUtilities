package com.pixelutilitys.items;

import com.pixelutilitys.gui.GuiPixelBoy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PixelBoyItem extends Item {

    public PixelBoyItem() {
        super();

        maxStackSize = 1;
        setUnlocalizedName("PixelBoyItem");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiPixelBoy());

        return itemStack;
    }

}
