package com.pixelutilitys.models.renderers;

import com.pixelutilitys.BlockRotation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class PokeballStatueTileEntityRenderer extends TileEntitySpecialRenderer {


    public PokeballStatueTileEntityRenderer() {

    }

    public static ResourceLocation Walrus = new ResourceLocation("pixelutilitys:textures/specialmodels/PokeballStatue.png");
    public static ResourceLocation Walrus2 = new ResourceLocation("pixelutilitys:textures/models/PokeballStatue.obj");
    IModelCustom WalrusMod = AdvancedModelLoader.loadModel(Walrus2);

    @Override
    public void renderTileEntityAt(TileEntity tile, double d, double d1, double d2, float f) {
        int meta = Minecraft.getMinecraft().theWorld.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
//		if (meta > 4)
//			return;
        BlockRotation rot = BlockRotation.getRotationFromMetadata(meta);

        int i = tile.getBlockMetadata(); // this is for rotation
        int j = 0;

        if (i == 0) {
            j = 0;
        }

        if (i == 1) {
            j = 90;
        }

        if (i == 2) {
            j = 180;
        }

        if (i == 3) {
            j = 270;
        }

        if (i < 0)
            return;

        GL11.glPushMatrix(); // start
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.04f, (float) d2 + 0.5F); // size
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F); // if you read this comment out this line
        bindTexture(Walrus);
        WalrusMod.renderAll();
        GL11.glPopMatrix(); // end

    }
}
