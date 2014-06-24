package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RubySwordTool extends ItemSword{

	String textureName;
	
	public RubySwordTool(ToolMaterial par2EnumToolMaterial, String textureName) {
		super(par2EnumToolMaterial);
		this.textureName = "pixelutilitys:RubySword";
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
	
}
