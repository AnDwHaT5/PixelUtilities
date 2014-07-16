package com.pixelutilitys.models.renderers;

import com.pixelutilitys.models.ModelTV;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TVRenderer extends TileEntitySpecialRenderer {
    ResourceLocation texture = new ResourceLocation("pixelutilitys:textures/specialmodels/TV.png");

    //The model of your block
    private final ModelTV model;

    public TVRenderer() {
        this.model = new ModelTV();
    }

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        //The PushMatrix tells the renderer to "start" doing something.
        GL11.glPushMatrix();
        //This is setting the initial location.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.9F, (float) z + 0.5F); //1.7 edited here
        //This is the texture of your block. It's pathed to be the same place as your other blocks here.
        //Outdated bindTextureByName("/mods/roads/textures/blocks/TrafficLightPoleRed.png");
        //Use in 1.6.2  this

        //the ':' is very important
        //binding the textures
        this.bindTexture(texture);

        //This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        //A reference to your Model file. Again, very important.


        switch (te.getBlockMetadata()) {
            default:
                GL11.glRotatef(360f, 0f, 1f, 0f);
                break;
            case 1:
                GL11.glRotatef(90f, 0f, 1f, 0f);
                break;
            case 2:
                GL11.glRotatef(180f, 0f, 1f, 0f);
                break;
            case 3:
                GL11.glRotatef(270f, 0f, 1f, 0f);
                break;
        }


        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0700F);
        //Tell it to stop rendering for both the PushMatrix's
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    //Set the lighting stuff, so it changes it's brightness properly.       
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.instance;
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
        this.bindTexture(texture);
    }
}
