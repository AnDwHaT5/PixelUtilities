package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RubyPickaxeTool extends ItemPickaxe{

	String textureName;
	
	public RubyPickaxeTool(float par2, ToolMaterial par3EnumToolMaterial, String textureName) {
		super(par3EnumToolMaterial);
		this.textureName = "pixelutilitys:RubyPickaxe";
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
	
}
