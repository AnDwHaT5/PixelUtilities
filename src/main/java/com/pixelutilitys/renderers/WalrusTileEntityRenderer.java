package com.pixelutilitys.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.pixelutilitys.BlockRotation;

public class WalrusTileEntityRenderer extends TileEntitySpecialRenderer {


	public WalrusTileEntityRenderer() {
		
	}
	public static ResourceLocation Walrus = new ResourceLocation("pixelutilitys:textures/blocks/Walrus.png");
	public static ResourceLocation Walrus2 = new ResourceLocation("/assets/pixelutilitys/models/WalrusStatue.obj");
	IModelCustom WalrusMod = AdvancedModelLoader.loadModel(Walrus2);

	@Override
	public void renderTileEntityAt(TileEntity tile, double d, double d1, double d2, float f) {
		int meta = Minecraft.getMinecraft().theWorld.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
//		if (meta > 4)
//			return;
		BlockRotation rot = BlockRotation.getRotationFromMetadata(meta);

		if (rot == BlockRotation.Normal) {
			d++;
			d2++;
		} else if (rot == BlockRotation.CW) {
			d++;
			d2--;
		} else if (rot == BlockRotation.Rotate180) {
			d--;
			d2--;
		} else {
			d--;
			d2++;
		}
		
		GL11.glPushMatrix(); // start
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.04f, (float) d2 + 0.9F); // size
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
		bindTexture(Walrus);
	  	WalrusMod.renderAll();
		GL11.glPopMatrix(); // end

	}
}
