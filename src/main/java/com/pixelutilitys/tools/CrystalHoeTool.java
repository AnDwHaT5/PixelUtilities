package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item.ToolMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrystalHoeTool extends ItemHoe{
String textureName;
	public CrystalHoeTool(ToolMaterial par2EnumToolMaterial, String texturename) {
		super(par2EnumToolMaterial);
		// TODO Auto-generated constructor stub
		this.textureName = "pixelutilitys:CrystalHoe";
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
}
