package com.pixelutilitys.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class InvisibleRenderer extends Render {



	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return null;
	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {
		// TODO Auto-generated method stub
		
	}

	

}
