package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrystalSwordTool extends ItemSword{

	String textureName;
	
	public CrystalSwordTool(ToolMaterial par2EnumToolMaterial, String textureName) {
		super(par2EnumToolMaterial);
		this.textureName = "pixelutilitys:CrystalSword";
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
	
}