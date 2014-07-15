package com.pixelutilitys.tileentitys;

import net.minecraft.tileentity.TileEntity;

public class PokeballStatueTileEntity extends TileEntity {
    public PokeballStatueTileEntity() {
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    public void setOwner(String playerID) {
        // TODO Auto-generated method stub

    }
}
