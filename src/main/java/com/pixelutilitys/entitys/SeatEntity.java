package com.pixelutilitys.entitys;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SeatEntity extends Entity
{
	public SeatEntity(World world)
	{
		super(world);
	}
	
	public SeatEntity(World world, double x, double y, double z) {
		super(world);
		setPosition(x,y,z);
	}

	
	@Override
	public void onEntityUpdate()
	{
		//remove seats that arn't in use
	 //TODO have a single entity for each chair tileentity, makes this easier
		if(super.riddenByEntity == null)
			setDead();
	}
	
	@Override
	public double getMountedYOffset(){
		return -0.1;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
	}
    
	
}