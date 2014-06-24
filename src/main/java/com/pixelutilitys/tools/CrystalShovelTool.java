package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrystalShovelTool extends ItemSpade{

	String textureName;
	
	public CrystalShovelTool(ToolMaterial par2EnumToolMaterial, String textureName) {
		super(par2EnumToolMaterial);
		this.textureName = "pixelutilitys:CrystalShovel";
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
}