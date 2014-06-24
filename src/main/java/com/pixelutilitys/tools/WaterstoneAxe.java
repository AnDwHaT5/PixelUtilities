package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemAxe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WaterstoneAxe extends ItemAxe{

	String textureName;
	
	public WaterstoneAxe(ToolMaterial par2EnumToolMaterial, String textureName)  {
		super(par2EnumToolMaterial);
		this.textureName = "pixelutilitys:WaterstoneAxe";
		
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
}
