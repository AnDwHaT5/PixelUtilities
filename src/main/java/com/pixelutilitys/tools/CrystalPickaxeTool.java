package com.pixelutilitys.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrystalPickaxeTool extends ItemPickaxe{

	String textureName;
	
	public CrystalPickaxeTool(float par2, ToolMaterial par3EnumToolMaterial, String textureName) {
		super(par3EnumToolMaterial);
		this.textureName = "pixelutilitys:CrystalPickaxe";
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
	
}
