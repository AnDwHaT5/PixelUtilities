package com.pixelutilitys.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.pixelutilitys.gui.GuiHandHeldRadio;
import com.pixelutilitys.radioplayer.HandHeldRadioPlayer;
import com.pixelutilitys.radioplayer.VLCPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HandHeldRadioItem extends Item{
	private String URL;
	
	public  HandHeldRadioItem(){
		setUnlocalizedName("HandHeldRadioItem");
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiHandHeldRadio(player));
		return itemStack;
		
	}
	
	
}
