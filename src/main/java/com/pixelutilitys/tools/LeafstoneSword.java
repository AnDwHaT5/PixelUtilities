package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LeafstoneSword extends ItemSword{

	String textureName;
	
	public LeafstoneSword(ToolMaterial par2EnumToolMaterial, String textureName) {
		super(par2EnumToolMaterial);
		this.textureName = "pixelutilitys:LeafstoneSword";
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
	
}
