package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemHoe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AmethystHoeTool extends ItemHoe{
String textureName;
	public AmethystHoeTool(ToolMaterial par2EnumToolMaterial, String texturename) {
		super(par2EnumToolMaterial);
		// TODO Auto-generated constructor stub
		this.textureName = "pixelutilitys:AmethystHoe";
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
}