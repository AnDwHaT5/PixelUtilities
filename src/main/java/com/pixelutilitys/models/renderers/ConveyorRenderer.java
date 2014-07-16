package com.pixelutilitys.models.renderers;

import com.pixelutilitys.config.PixelUtilitysBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class ConveyorRenderer implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        PixelUtilitysBlocks.BlockConveyor.canRenderInPass(0);
        int color = block.getRenderColor(meta);
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable) {
            float anaglyphRed = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
            float anaglyphGreen = (red * 30.0F + green * 70.0F) / 100.0F;
            float anaglyphBlue = (red * 30.0F + blue * 70.0F) / 100.0F;
            red = anaglyphRed;
            green = anaglyphGreen;
            blue = anaglyphBlue;
        }
        IIcon iconBase, iconOverlay;

        iconBase = block.getIcon(0, meta);
        PixelUtilitysBlocks.BlockConveyor.canRenderInPass(1);
        iconOverlay = block.getIcon(0, 0);

        double minXBase = iconBase.getMinU();
        double maxXBase = iconBase.getMaxU();
        double minYBase = iconBase.getMinV();
        double maxYBase = iconBase.getMaxV();

        double minXOverlay = iconOverlay.getMinU();
        double maxXOverlay = iconOverlay.getMaxU();
        double minYOverlay = iconOverlay.getMinV();
        double maxYOverlay = iconOverlay.getMaxV();

        double xMin = 0, xMax = 1;
        double yMin = 0, yMax = 1;
        double zMid = 0.5;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_F(red, green, blue);
        tessellator.addVertexWithUV(xMin, yMax, zMid, minXBase, minYBase);
        tessellator.addVertexWithUV(xMin, yMin, zMid, minXBase, maxYBase);
        tessellator.addVertexWithUV(xMax, yMin, zMid, maxXBase, maxYBase);
        tessellator.addVertexWithUV(xMax, yMax, zMid, maxXBase, minYBase);

        tessellator.setColorOpaque_F(1, 1, 1);
        tessellator.addVertexWithUV(xMin, yMax, zMid, minXOverlay, minYOverlay);
        tessellator.addVertexWithUV(xMin, yMin, zMid, minXOverlay, maxYOverlay);
        tessellator.addVertexWithUV(xMax, yMin, zMid, maxXOverlay, maxYOverlay);
        tessellator.addVertexWithUV(xMax, yMax, zMid, maxXOverlay, minYOverlay);
        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (renderer.hasOverrideBlockTexture()) {
            Tessellator tessellator = Tessellator.instance;

            calculateVerts(world, x, y, z);
            tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            draw(tessellator, renderer.overrideBlockTexture);
            return true;
        }
        renderConveyorWorld(world, x, y, z, block);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return 500;
    }

    private float vert1x, vert2x, vert3x, vert4x;
    private float vert1z, vert2z, vert3z, vert4z;
    private float vert1y, vert2y, vert3y, vert4y;

    private void renderConveyorWorld(IBlockAccess world, int x, int y, int z, Block block) {
        Tessellator tessellator = Tessellator.instance;
        int color = block.colorMultiplier(world, x, y, z);
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable) {
            float anaglyphRed = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
            float anaglyphGreen = (red * 30.0F + green * 70.0F) / 100.0F;
            float anaglyphBlue = (red * 30.0F + blue * 70.0F) / 100.0F;
            red = anaglyphRed;
            green = anaglyphGreen;
            blue = anaglyphBlue;
        }

        calculateVerts(world, x, y, z);

        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(red, green, blue);
        draw(tessellator, block.getIcon(world, x, y, z, 0));
    }

    private void draw(Tessellator tessellator, IIcon texture) {
        double uStart = texture.getInterpolatedU(0);
        double uEnd = texture.getInterpolatedU(16);
        double vStart = texture.getInterpolatedV(0);
        double vEnd = texture.getInterpolatedV(16);

        tessellator.addVertexWithUV(vert1x, vert1y, vert1z, uEnd, vStart);
        tessellator.addVertexWithUV(vert2x, vert2y, vert2z, uEnd, vEnd);
        tessellator.addVertexWithUV(vert3x, vert3y, vert3z, uStart, vEnd);
        tessellator.addVertexWithUV(vert4x, vert4y, vert4z, uStart, vStart);
        // TODO: thickness
        tessellator.addVertexWithUV(vert4x, vert4y, vert4z, uStart, vStart);
        tessellator.addVertexWithUV(vert3x, vert3y, vert3z, uStart, vEnd);
        tessellator.addVertexWithUV(vert2x, vert2y, vert2z, uEnd, vEnd);
        tessellator.addVertexWithUV(vert1x, vert1y, vert1z, uEnd, vStart);
    }

    private void calculateVerts(IBlockAccess iblockaccess, int blockX, int blockY, int blockZ) {
        int conveyorMetadata = iblockaccess.getBlockMetadata(blockX, blockY, blockZ);

        float renderHeight = 0.00625F;

        vert1x = vert2x = blockX + 1;
        vert3x = vert4x = blockX;

        vert1z = vert4z = blockZ;
        vert2z = vert3z = blockZ + 1;

        vert1y = blockY + renderHeight;
        vert2y = blockY + renderHeight;
        vert3y = blockY + renderHeight;
        vert4y = blockY + renderHeight;

        if (conveyorMetadata == 0 || conveyorMetadata == 4 || conveyorMetadata == 8) {
            vert1x = vert4x = blockX;
            vert2x = vert3x = blockX + 1;
            vert1z = vert2z = blockZ;
            vert3z = vert4z = blockZ + 1;
            if (conveyorMetadata == 4) {
                vert2y++; // 1,0
                vert3y++; // 1,1
            }
            if (conveyorMetadata == 8) {
                vert1y++;
                vert4y++;
            }
        } else if (conveyorMetadata == 1 || conveyorMetadata == 5 || conveyorMetadata == 9) {
            vert1x = vert2x = blockX + 1;
            vert3x = vert4x = blockX;
            vert1z = vert4z = blockZ;
            vert2z = vert3z = blockZ + 1;
            if (conveyorMetadata == 5) {
                vert3y++; // 0,1
                vert2y++; // 1,1
            }
            if (conveyorMetadata == 9) {
                vert1y++;
                vert4y++;
            }
        } else if (conveyorMetadata == 2 || conveyorMetadata == 6 || conveyorMetadata == 10) {
            vert1x = vert4x = blockX + 1;
            vert2x = vert3x = blockX;
            vert1z = vert2z = blockZ + 1;
            vert3z = vert4z = blockZ;
            if (conveyorMetadata == 6) {
                vert2y++;
                vert3y++;
            }
            if (conveyorMetadata == 10) {
                vert4y++; // 1,0
                vert1y++; // 1,1
            }
        } else if (conveyorMetadata == 3 || conveyorMetadata == 7 || conveyorMetadata == 11) {
            vert1x = vert2x = blockX;
            vert3x = vert4x = blockX + 1;
            vert1z = vert4z = blockZ + 1;
            vert2z = vert3z = blockZ;
            if (conveyorMetadata == 7) {
                vert2y++;
                vert3y++;
            }
            if (conveyorMetadata == 11) {
                vert1y++; // 0,1
                vert4y++; // 1,1
            }
        }
    }
}
