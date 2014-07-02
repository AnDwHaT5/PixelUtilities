package com.pixelutilitys.tileentitys;

import net.minecraft.tileentity.TileEntity;

public class TrashcanEntity extends TileEntity {

	public TrashcanEntity() {
	}	
	
	@Override
	public boolean canUpdate()
	{
		return false;
	}

}
