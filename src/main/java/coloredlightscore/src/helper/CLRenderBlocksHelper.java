package coloredlightscore.src.helper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;


public class CLRenderBlocksHelper {

    public CLRenderBlocksHelper() {
        //

    }

    /**
     * Gutted to work with colors
     * CptSpaceToaster
     * <p/>
     * Reset to vanilla and fixed by Glitchfinder
     *
     * @param par1Block Block Type (Sky or Normal Block)
     * @param par2      the X coordinate
     * @param par3      the Y coordinate
     * @param par4      the Z coordinate
     * @param par5      amount of red
     * @param par6      amount of green
     * @param par7      amount of blue
     */
    public static boolean renderStandardBlockWithAmbientOcclusion(RenderBlocks renderBlocks, Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7) {
        renderBlocks.enableAO = true;
        boolean var8 = false;
        float var9;
        float var10;
        float var11;
        float var12;
        boolean var13 = true;
        int var14 = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4);
        Tessellator var15 = Tessellator.instance;
        var15.setBrightness(983055);

        if (renderBlocks.getBlockIcon(par1Block).getIconName().equals("grass_top")) {
            var13 = false;
        } else if (renderBlocks.hasOverrideBlockTexture()) {
            var13 = false;
        }

        boolean var17;
        boolean var16;
        boolean var19;
        boolean var18;
        float var21;
        int var20;

        //YYY-1
        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 - 1, par4, 0)) {
            if (renderBlocks.renderMinY <= 0.0D) {
                --par3;
            }

            renderBlocks.aoLightValueScratchXYNN = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchYZNN = renderBlocks.blockAccess.getBlock(par2, par3, par4 - 1).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchYZNP = renderBlocks.blockAccess.getBlock(par2, par3, par4 + 1).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXYPN = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoBrightnessXYNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessYZNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessYZNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4);
            var16 = renderBlocks.blockAccess.getBlock(par2 + 1, par3 - 1, par4).getCanBlockGrass();
            var17 = renderBlocks.blockAccess.getBlock(par2 - 1, par3 - 1, par4).getCanBlockGrass();
            var18 = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4 + 1).getCanBlockGrass();
            var19 = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4 - 1).getCanBlockGrass();

            if (!var19 && !var17) {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXYNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXYNN;
            } else {

                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4 - 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
            }

            if (!var18 && !var17) {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXYNN;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXYNN;
            } else {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4 + 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
            }

            if (!var19 && !var16) {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXYPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXYPN;
            } else {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4 - 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
            }

            if (!var18 && !var16) {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXYPN;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXYPN;
            } else {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4 + 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
            }

            if (renderBlocks.renderMinY <= 0.0D) {
                ++par3;
            }

            var20 = var14;

            if (renderBlocks.renderMinY <= 0.0D || !renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4).isOpaqueCube()) {
                var20 = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4);
            }

            var21 = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4).getAmbientOcclusionLightValue();
            var9 = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchYZNP + var21) / 4.0F;
            var12 = (renderBlocks.aoLightValueScratchYZNP + var21 + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXYPN) / 4.0F;
            var11 = (var21 + renderBlocks.aoLightValueScratchYZNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNN) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNN + var21 + renderBlocks.aoLightValueScratchYZNN) / 4.0F;

            // BEGIN - GlitchLights

            // Calculate the basic corner brightnesses
            renderBlocks.brightnessTopLeft = ((renderBlocks.aoBrightnessXYZNNP & 15728880) + (renderBlocks.aoBrightnessXYNN & 15728880) + (renderBlocks.aoBrightnessYZNP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomLeft = ((renderBlocks.aoBrightnessXYZNNN & 15728880) + (renderBlocks.aoBrightnessXYNN & 15728880) + (renderBlocks.aoBrightnessYZNN & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomRight = ((renderBlocks.aoBrightnessXYZPNN & 15728880) + (renderBlocks.aoBrightnessYZNN & 15728880) + (renderBlocks.aoBrightnessXYPN & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessTopRight = ((renderBlocks.aoBrightnessXYZPNP & 15728880) + (renderBlocks.aoBrightnessYZNP & 15728880) + (renderBlocks.aoBrightnessXYPN & 15728880) + (var20 & 15728880)) >> 2;

            // Calculate a basic corner color: x+ z+
            renderBlocks.colorRedTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 4) & 240) + ((renderBlocks.aoBrightnessXYNN >> 4) & 240) + ((renderBlocks.aoBrightnessYZNP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 8) & 240) + ((renderBlocks.aoBrightnessXYNN >> 8) & 240) + ((renderBlocks.aoBrightnessYZNP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 12) & 240) + ((renderBlocks.aoBrightnessXYNN >> 12) & 240) + ((renderBlocks.aoBrightnessYZNP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x+ z-
            renderBlocks.colorRedBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 4) & 240) + ((renderBlocks.aoBrightnessXYNN >> 4) & 240) + ((renderBlocks.aoBrightnessYZNN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 8) & 240) + ((renderBlocks.aoBrightnessXYNN >> 8) & 240) + ((renderBlocks.aoBrightnessYZNN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 12) & 240) + ((renderBlocks.aoBrightnessXYNN >> 12) & 240) + ((renderBlocks.aoBrightnessYZNN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z-
            renderBlocks.colorRedBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 4) & 240) + ((renderBlocks.aoBrightnessYZNN >> 4) & 240) + ((renderBlocks.aoBrightnessXYPN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 8) & 240) + ((renderBlocks.aoBrightnessYZNN >> 8) & 240) + ((renderBlocks.aoBrightnessXYPN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 12) & 240) + ((renderBlocks.aoBrightnessYZNN >> 12) & 240) + ((renderBlocks.aoBrightnessXYPN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z+
            renderBlocks.colorRedTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 4) & 240) + ((renderBlocks.aoBrightnessYZNP >> 4) & 240) + ((renderBlocks.aoBrightnessXYPN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 8) & 240) + ((renderBlocks.aoBrightnessYZNP >> 8) & 240) + ((renderBlocks.aoBrightnessXYPN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 12) & 240) + ((renderBlocks.aoBrightnessYZNP >> 12) & 240) + ((renderBlocks.aoBrightnessXYPN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);

            // If colors are applied to any corners
            if ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft + renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft + renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight + renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) > 0f) {
                // Calculate a rough brightness modifier for each corner color
                float brightnessTopLeft = (1f - ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopLeft & 240))) / 240f);
                float brightnessBottomLeft = (1f - ((renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomLeft & 240))) / 240f);
                float brightnessBottomRight = (1f - ((renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomRight & 240))) / 240f);
                float brightnessTopRight = (1f - ((renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopRight & 240))) / 240f);

                // Finalize the corner color: x+ z+
                renderBlocks.colorRedTopLeft = Math.min(1f, renderBlocks.colorRedTopLeft + brightnessTopLeft);
                renderBlocks.colorGreenTopLeft = Math.min(1f, renderBlocks.colorGreenTopLeft + brightnessTopLeft);
                renderBlocks.colorBlueTopLeft = Math.min(1f, renderBlocks.colorBlueTopLeft + brightnessTopLeft);
                // Finalize the corner color: x+ z-
                renderBlocks.colorRedBottomLeft = Math.min(1f, renderBlocks.colorRedBottomLeft + brightnessBottomLeft);
                renderBlocks.colorGreenBottomLeft = Math.min(1f, renderBlocks.colorGreenBottomLeft + brightnessBottomLeft);
                renderBlocks.colorBlueBottomLeft = Math.min(1f, renderBlocks.colorBlueBottomLeft + brightnessBottomLeft);
                // Finalize the corner color: x- z-
                renderBlocks.colorRedBottomRight = Math.min(1f, renderBlocks.colorRedBottomRight + brightnessBottomRight);
                renderBlocks.colorGreenBottomRight = Math.min(1f, renderBlocks.colorGreenBottomRight + brightnessBottomRight);
                renderBlocks.colorBlueBottomRight = Math.min(1f, renderBlocks.colorBlueBottomRight + brightnessBottomRight);
                // Finalize the corner color: x- z+
                renderBlocks.colorRedTopRight = Math.min(1f, renderBlocks.colorRedTopRight + brightnessTopRight);
                renderBlocks.colorGreenTopRight = Math.min(1f, renderBlocks.colorGreenTopRight + brightnessTopRight);
                renderBlocks.colorBlueTopRight = Math.min(1f, renderBlocks.colorBlueTopRight + brightnessTopRight);
            }
            // If no colors are applied to any of the corners
            else {
                // Reset the corners to white
                renderBlocks.colorRedTopLeft = renderBlocks.colorGreenTopLeft = renderBlocks.colorBlueTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorGreenBottomRight = renderBlocks.colorBlueBottomRight = renderBlocks.colorRedTopRight = renderBlocks.colorGreenTopRight = renderBlocks.colorBlueTopRight = 1f;
            }

            // If the block color is not being overridden
            if (var13) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopRight *= par7;
            }

            // Apply shadows next to walls and highlights next to edges
            renderBlocks.colorRedTopLeft *= var9 * 0.5f;
            renderBlocks.colorGreenTopLeft *= var9 * 0.5f;
            renderBlocks.colorBlueTopLeft *= var9 * 0.5f;
            renderBlocks.colorRedBottomLeft *= var10 * 0.5f;
            renderBlocks.colorGreenBottomLeft *= var10 * 0.5f;
            renderBlocks.colorBlueBottomLeft *= var10 * 0.5f;
            renderBlocks.colorRedBottomRight *= var11 * 0.5f;
            renderBlocks.colorGreenBottomRight *= var11 * 0.5f;
            renderBlocks.colorBlueBottomRight *= var11 * 0.5f;
            renderBlocks.colorRedTopRight *= var12 * 0.5f;
            renderBlocks.colorGreenTopRight *= var12 * 0.5f;
            renderBlocks.colorBlueTopRight *= var12 * 0.5f;

            // END - GlitchLights

            renderBlocks.renderFaceYNeg(par1Block, (double) par2, (double) par3, (double) par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 0));
            var8 = true;
        }
        //YYY+1
        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3 + 1, par4, 1)) {
            if (renderBlocks.renderMaxY >= 1.0D) {
                ++par3;
            }

            renderBlocks.aoLightValueScratchXYNP = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXYPP = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchYZPN = renderBlocks.blockAccess.getBlock(par2, par3, par4 - 1).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchYZPP = renderBlocks.blockAccess.getBlock(par2, par3, par4 + 1).getAmbientOcclusionLightValue();
            renderBlocks.aoBrightnessXYNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessXYPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoBrightnessYZPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessYZPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 + 1);
            var16 = renderBlocks.blockAccess.getBlock(par2 + 1, par3 + 1, par4).getCanBlockGrass();
            var17 = renderBlocks.blockAccess.getBlock(par2 - 1, par3 + 1, par4).getCanBlockGrass();
            var18 = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4 + 1).getCanBlockGrass();
            var19 = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4 - 1).getCanBlockGrass();

            if (!var19 && !var17) {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXYNP;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXYNP;
            } else {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4 - 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4 - 1);
            }

            if (!var19 && !var16) {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXYPP;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXYPP;
            } else {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4 - 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4 - 1);
            }

            if (!var18 && !var17) {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXYNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXYNP;
            } else {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4 + 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4 + 1);
            }

            if (!var18 && !var16) {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXYPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXYPP;
            } else {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4 + 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4 + 1);
            }

            if (renderBlocks.renderMaxY >= 1.0D) {
                --par3;
            }

            var20 = var14;

            if (renderBlocks.renderMaxY >= 1.0D || !renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4).isOpaqueCube()) {
                var20 = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4);
            }

            var21 = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4).getAmbientOcclusionLightValue();
            var12 = (renderBlocks.aoLightValueScratchXYZNPP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchYZPP + var21) / 4.0F;
            var9 = (renderBlocks.aoLightValueScratchYZPP + var21 + renderBlocks.aoLightValueScratchXYZPPP + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
            var10 = (var21 + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPN + var21 + renderBlocks.aoLightValueScratchYZPN) / 4.0F;

            // BEGIN - GlitchLights

            // Calculate the basic corner brightnesses
            renderBlocks.brightnessTopLeft = ((renderBlocks.aoBrightnessXYZPPP & 15728880) + (renderBlocks.aoBrightnessYZPP & 15728880) + (renderBlocks.aoBrightnessXYPP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomLeft = ((renderBlocks.aoBrightnessXYZPPN & 15728880) + (renderBlocks.aoBrightnessYZPN & 15728880) + (renderBlocks.aoBrightnessXYPP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomRight = ((renderBlocks.aoBrightnessXYZNPN & 15728880) + (renderBlocks.aoBrightnessXYNP & 15728880) + (renderBlocks.aoBrightnessYZPN & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessTopRight = ((renderBlocks.aoBrightnessXYZNPP & 15728880) + (renderBlocks.aoBrightnessXYNP & 15728880) + (renderBlocks.aoBrightnessYZPP & 15728880) + (var20 & 15728880)) >> 2;

            // Calculate a basic corner color: x+ z+
            renderBlocks.colorRedTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 4) & 240) + ((renderBlocks.aoBrightnessYZPP >> 4) & 240) + ((renderBlocks.aoBrightnessXYPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 8) & 240) + ((renderBlocks.aoBrightnessYZPP >> 8) & 240) + ((renderBlocks.aoBrightnessXYPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 12) & 240) + ((renderBlocks.aoBrightnessYZPP >> 12) & 240) + ((renderBlocks.aoBrightnessXYPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x+ z-
            renderBlocks.colorRedBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 4) & 240) + ((renderBlocks.aoBrightnessYZPN >> 4) & 240) + ((renderBlocks.aoBrightnessXYPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 8) & 240) + ((renderBlocks.aoBrightnessYZPN >> 8) & 240) + ((renderBlocks.aoBrightnessXYPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 12) & 240) + ((renderBlocks.aoBrightnessYZPN >> 12) & 240) + ((renderBlocks.aoBrightnessXYPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z-
            renderBlocks.colorRedBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 4) & 240) + ((renderBlocks.aoBrightnessXYNP >> 4) & 240) + ((renderBlocks.aoBrightnessYZPN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 8) & 240) + ((renderBlocks.aoBrightnessXYNP >> 8) & 240) + ((renderBlocks.aoBrightnessYZPN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 12) & 240) + ((renderBlocks.aoBrightnessXYNP >> 12) & 240) + ((renderBlocks.aoBrightnessYZPN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z+
            renderBlocks.colorRedTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 4) & 240) + ((renderBlocks.aoBrightnessXYNP >> 4) & 240) + ((renderBlocks.aoBrightnessYZPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 8) & 240) + ((renderBlocks.aoBrightnessXYNP >> 8) & 240) + ((renderBlocks.aoBrightnessYZPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 12) & 240) + ((renderBlocks.aoBrightnessXYNP >> 12) & 240) + ((renderBlocks.aoBrightnessYZPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);

            // If colors are applied to any corners
            if ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft + renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft + renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight + renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) > 0f) {
                // Calculate a rough brightness modifier for each corner color
                float brightnessTopLeft = (1f - ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopLeft & 240))) / 240f);
                float brightnessBottomLeft = (1f - ((renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomLeft & 240))) / 240f);
                float brightnessBottomRight = (1f - ((renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomRight & 240))) / 240f);
                float brightnessTopRight = (1f - ((renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopRight & 240))) / 240f);

                // Finalize the corner color: x+ z+
                renderBlocks.colorRedTopLeft = Math.min(1f, renderBlocks.colorRedTopLeft + brightnessTopLeft);
                renderBlocks.colorGreenTopLeft = Math.min(1f, renderBlocks.colorGreenTopLeft + brightnessTopLeft);
                renderBlocks.colorBlueTopLeft = Math.min(1f, renderBlocks.colorBlueTopLeft + brightnessTopLeft);
                // Finalize the corner color: x+ z-
                renderBlocks.colorRedBottomLeft = Math.min(1f, renderBlocks.colorRedBottomLeft + brightnessBottomLeft);
                renderBlocks.colorGreenBottomLeft = Math.min(1f, renderBlocks.colorGreenBottomLeft + brightnessBottomLeft);
                renderBlocks.colorBlueBottomLeft = Math.min(1f, renderBlocks.colorBlueBottomLeft + brightnessBottomLeft);
                // Finalize the corner color: x- z-
                renderBlocks.colorRedBottomRight = Math.min(1f, renderBlocks.colorRedBottomRight + brightnessBottomRight);
                renderBlocks.colorGreenBottomRight = Math.min(1f, renderBlocks.colorGreenBottomRight + brightnessBottomRight);
                renderBlocks.colorBlueBottomRight = Math.min(1f, renderBlocks.colorBlueBottomRight + brightnessBottomRight);
                // Finalize the corner color: x- z+
                renderBlocks.colorRedTopRight = Math.min(1f, renderBlocks.colorRedTopRight + brightnessTopRight);
                renderBlocks.colorGreenTopRight = Math.min(1f, renderBlocks.colorGreenTopRight + brightnessTopRight);
                renderBlocks.colorBlueTopRight = Math.min(1f, renderBlocks.colorBlueTopRight + brightnessTopRight);
            }
            // If no colors are applied to any of the corners
            else {
                // Reset the corners to white
                renderBlocks.colorRedTopLeft = renderBlocks.colorGreenTopLeft = renderBlocks.colorBlueTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorGreenBottomRight = renderBlocks.colorBlueBottomRight = renderBlocks.colorRedTopRight = renderBlocks.colorGreenTopRight = renderBlocks.colorBlueTopRight = 1f;
            }

            // Apply shadows next to walls, highlights next to edges, and the supplied argument colors
            renderBlocks.colorRedTopLeft *= var9 * par5;
            renderBlocks.colorGreenTopLeft *= var9 * par6;
            renderBlocks.colorBlueTopLeft *= var9 * par7;
            renderBlocks.colorRedBottomLeft *= var10 * par5;
            renderBlocks.colorGreenBottomLeft *= var10 * par6;
            renderBlocks.colorBlueBottomLeft *= var10 * par7;
            renderBlocks.colorRedBottomRight *= var11 * par5;
            renderBlocks.colorGreenBottomRight *= var11 * par6;
            renderBlocks.colorBlueBottomRight *= var11 * par7;
            renderBlocks.colorRedTopRight *= var12 * par5;
            renderBlocks.colorGreenTopRight *= var12 * par6;
            renderBlocks.colorBlueTopRight *= var12 * par7;

            // END - GlitchLights

            renderBlocks.renderFaceYPos(par1Block, (double) par2, (double) par3, (double) par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 1));
            var8 = true;
        }

        IIcon var22;

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 - 1, 2)) {
            if (renderBlocks.renderMinZ <= 0.0D) {
                --par4;
            }

            renderBlocks.aoLightValueScratchXZNN = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchYZNN = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchYZPN = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXZPN = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoBrightnessXZNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessYZNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessYZPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4);
            renderBlocks.aoBrightnessXZPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4);
            var16 = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4 - 1).getCanBlockGrass();
            var17 = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4 - 1).getCanBlockGrass();
            var18 = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4 - 1).getCanBlockGrass();
            var19 = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4 - 1).getCanBlockGrass();

            if (!var17 && !var19) {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXZNN;
            } else {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.blockAccess.getBlock(par2 - 1, par3 - 1, par4).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
            }

            if (!var17 && !var18) {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXZNN;
            } else {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.blockAccess.getBlock(par2 - 1, par3 + 1, par4).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
            }

            if (!var16 && !var19) {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXZPN;
            } else {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.blockAccess.getBlock(par2 + 1, par3 - 1, par4).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
            }

            if (!var16 && !var18) {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXZPN;
            } else {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.blockAccess.getBlock(par2 + 1, par3 + 1, par4).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
            }

            if (renderBlocks.renderMinZ <= 0.0D) {
                ++par4;
            }

            var20 = var14;

            if (renderBlocks.renderMinZ <= 0.0D || !renderBlocks.blockAccess.getBlock(par2, par3, par4 - 1).isOpaqueCube()) {
                var20 = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 - 1);
            }

            var21 = renderBlocks.blockAccess.getBlock(par2, par3, par4 - 1).getAmbientOcclusionLightValue();
            var9 = (renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchXYZNPN + var21 + renderBlocks.aoLightValueScratchYZPN) / 4.0F;
            var10 = (var21 + renderBlocks.aoLightValueScratchYZPN + renderBlocks.aoLightValueScratchXZPN + renderBlocks.aoLightValueScratchXYZPPN) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchYZNN + var21 + renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXZPN) / 4.0F;
            var12 = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXZNN + renderBlocks.aoLightValueScratchYZNN + var21) / 4.0F;

            // BEGIN - GlitchLights

            // Calculate the basic corner brightnesses
            renderBlocks.brightnessTopLeft = ((renderBlocks.aoBrightnessXYZNPN & 15728880) + (renderBlocks.aoBrightnessXZNN & 15728880) + (renderBlocks.aoBrightnessYZPN & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomLeft = ((renderBlocks.aoBrightnessXYZPPN & 15728880) + (renderBlocks.aoBrightnessYZPN & 15728880) + (renderBlocks.aoBrightnessXZPN & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomRight = ((renderBlocks.aoBrightnessXYZPNN & 15728880) + (renderBlocks.aoBrightnessYZNN & 15728880) + (renderBlocks.aoBrightnessXZPN & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessTopRight = ((renderBlocks.aoBrightnessXYZNNN & 15728880) + (renderBlocks.aoBrightnessXZNN & 15728880) + (renderBlocks.aoBrightnessYZNN & 15728880) + (var20 & 15728880)) >> 2;

            // Calculate a basic corner color: x+ z+
            renderBlocks.colorRedTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 4) & 240) + ((renderBlocks.aoBrightnessXZNN >> 4) & 240) + ((renderBlocks.aoBrightnessYZPN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 8) & 240) + ((renderBlocks.aoBrightnessXZNN >> 8) & 240) + ((renderBlocks.aoBrightnessYZPN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 12) & 240) + ((renderBlocks.aoBrightnessXZNN >> 12) & 240) + ((renderBlocks.aoBrightnessYZPN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x+ z-
            renderBlocks.colorRedBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 4) & 240) + ((renderBlocks.aoBrightnessYZPN >> 4) & 240) + ((renderBlocks.aoBrightnessXZPN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 8) & 240) + ((renderBlocks.aoBrightnessYZPN >> 8) & 240) + ((renderBlocks.aoBrightnessXZPN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 12) & 240) + ((renderBlocks.aoBrightnessYZPN >> 12) & 240) + ((renderBlocks.aoBrightnessXZPN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z-
            renderBlocks.colorRedBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 4) & 240) + ((renderBlocks.aoBrightnessYZNN >> 4) & 240) + ((renderBlocks.aoBrightnessXZPN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 8) & 240) + ((renderBlocks.aoBrightnessYZNN >> 8) & 240) + ((renderBlocks.aoBrightnessXZPN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 12) & 240) + ((renderBlocks.aoBrightnessYZNN >> 12) & 240) + ((renderBlocks.aoBrightnessXZPN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z+
            renderBlocks.colorRedTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 4) & 240) + ((renderBlocks.aoBrightnessXZNN >> 4) & 240) + ((renderBlocks.aoBrightnessYZNN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 8) & 240) + ((renderBlocks.aoBrightnessXZNN >> 8) & 240) + ((renderBlocks.aoBrightnessYZNN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 12) & 240) + ((renderBlocks.aoBrightnessXZNN >> 12) & 240) + ((renderBlocks.aoBrightnessYZNN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);

            // If colors are applied to any corners
            if ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft + renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft + renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight + renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) > 0f) {
                // Calculate a rough brightness modifier for each corner color
                float brightnessTopLeft = (1f - ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopLeft & 240))) / 240f);
                float brightnessBottomLeft = (1f - ((renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomLeft & 240))) / 240f);
                float brightnessBottomRight = (1f - ((renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomRight & 240))) / 240f);
                float brightnessTopRight = (1f - ((renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopRight & 240))) / 240f);

                // Finalize the corner color: x+ z+
                renderBlocks.colorRedTopLeft = Math.min(1f, renderBlocks.colorRedTopLeft + brightnessTopLeft);
                renderBlocks.colorGreenTopLeft = Math.min(1f, renderBlocks.colorGreenTopLeft + brightnessTopLeft);
                renderBlocks.colorBlueTopLeft = Math.min(1f, renderBlocks.colorBlueTopLeft + brightnessTopLeft);
                // Finalize the corner color: x+ z-
                renderBlocks.colorRedBottomLeft = Math.min(1f, renderBlocks.colorRedBottomLeft + brightnessBottomLeft);
                renderBlocks.colorGreenBottomLeft = Math.min(1f, renderBlocks.colorGreenBottomLeft + brightnessBottomLeft);
                renderBlocks.colorBlueBottomLeft = Math.min(1f, renderBlocks.colorBlueBottomLeft + brightnessBottomLeft);
                // Finalize the corner color: x- z-
                renderBlocks.colorRedBottomRight = Math.min(1f, renderBlocks.colorRedBottomRight + brightnessBottomRight);
                renderBlocks.colorGreenBottomRight = Math.min(1f, renderBlocks.colorGreenBottomRight + brightnessBottomRight);
                renderBlocks.colorBlueBottomRight = Math.min(1f, renderBlocks.colorBlueBottomRight + brightnessBottomRight);
                // Finalize the corner color: x- z+
                renderBlocks.colorRedTopRight = Math.min(1f, renderBlocks.colorRedTopRight + brightnessTopRight);
                renderBlocks.colorGreenTopRight = Math.min(1f, renderBlocks.colorGreenTopRight + brightnessTopRight);
                renderBlocks.colorBlueTopRight = Math.min(1f, renderBlocks.colorBlueTopRight + brightnessTopRight);
            }
            // If no colors are applied to any of the corners
            else {
                // Reset the corners to white
                renderBlocks.colorRedTopLeft = renderBlocks.colorGreenTopLeft = renderBlocks.colorBlueTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorGreenBottomRight = renderBlocks.colorBlueBottomRight = renderBlocks.colorRedTopRight = renderBlocks.colorGreenTopRight = renderBlocks.colorBlueTopRight = 1f;
            }

            // If the block color is not being overridden
            if (var13) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopRight *= par7;
            }

            // Apply shadows next to walls and highlights next to edges
            renderBlocks.colorRedTopLeft *= var9 * 0.8f;
            renderBlocks.colorGreenTopLeft *= var9 * 0.8f;
            renderBlocks.colorBlueTopLeft *= var9 * 0.8f;
            renderBlocks.colorRedBottomLeft *= var10 * 0.8f;
            renderBlocks.colorGreenBottomLeft *= var10 * 0.8f;
            renderBlocks.colorBlueBottomLeft *= var10 * 0.8f;
            renderBlocks.colorRedBottomRight *= var11 * 0.8f;
            renderBlocks.colorGreenBottomRight *= var11 * 0.8f;
            renderBlocks.colorBlueBottomRight *= var11 * 0.8f;
            renderBlocks.colorRedTopRight *= var12 * 0.8f;
            renderBlocks.colorGreenTopRight *= var12 * 0.8f;
            renderBlocks.colorBlueTopRight *= var12 * 0.8f;

            // END - GlitchLights

            var22 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 2);
            renderBlocks.renderFaceZNeg(par1Block, (double) par2, (double) par3, (double) par4, var22);

            if (RenderBlocks.fancyGrass && var22.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture()) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceZNeg(par1Block, (double) par2, (double) par3, (double) par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        //ZZZ+1
        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2, par3, par4 + 1, 3)) {
            if (renderBlocks.renderMaxZ >= 1.0D) {
                ++par4;
            }

            renderBlocks.aoLightValueScratchXZNP = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXZPP = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchYZNP = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchYZPP = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoBrightnessXZNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4);
            renderBlocks.aoBrightnessXZPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4);
            renderBlocks.aoBrightnessYZNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessYZPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4 + 1).getCanBlockGrass();
            var17 = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4 + 1).getCanBlockGrass();
            var18 = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4 + 1).getCanBlockGrass();
            var19 = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4 + 1).getCanBlockGrass();

            if (!var17 && !var19) {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXZNP;
            } else {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.blockAccess.getBlock(par2 - 1, par3 - 1, par4).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3 - 1, par4);
            }

            if (!var17 && !var18) {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXZNP;
            } else {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.blockAccess.getBlock(par2 - 1, par3 + 1, par4).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3 + 1, par4);
            }

            if (!var16 && !var19) {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXZPP;
            } else {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.blockAccess.getBlock(par2 + 1, par3 - 1, par4).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3 - 1, par4);
            }

            if (!var16 && !var18) {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXZPP;
            } else {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.blockAccess.getBlock(par2 + 1, par3 + 1, par4).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3 + 1, par4);
            }

            if (renderBlocks.renderMaxZ >= 1.0D) {
                --par4;
            }

            var20 = var14;

            if (renderBlocks.renderMaxZ >= 1.0D || !renderBlocks.blockAccess.getBlock(par2, par3, par4 + 1).isOpaqueCube()) {
                var20 = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 + 1);
            }

            var21 = renderBlocks.blockAccess.getBlock(par2, par3, par4 + 1).getAmbientOcclusionLightValue();
            var9 = (renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYZNPP + var21 + renderBlocks.aoLightValueScratchYZPP) / 4.0F;
            var12 = (var21 + renderBlocks.aoLightValueScratchYZPP + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchYZNP + var21 + renderBlocks.aoLightValueScratchXYZPNP + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXYZNNP + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchYZNP + var21) / 4.0F;

            // BEGIN - GlitchLights

            // Calculate the basic corner brightnesses
            renderBlocks.brightnessTopLeft = ((renderBlocks.aoBrightnessXYZNPP & 15728880) + (renderBlocks.aoBrightnessXZNP & 15728880) + (renderBlocks.aoBrightnessYZPP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomLeft = ((renderBlocks.aoBrightnessXYZNNP & 15728880) + (renderBlocks.aoBrightnessYZNP & 15728880) + (renderBlocks.aoBrightnessXZNP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomRight = ((renderBlocks.aoBrightnessXYZPNP & 15728880) + (renderBlocks.aoBrightnessYZNP & 15728880) + (renderBlocks.aoBrightnessXZPP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessTopRight = ((renderBlocks.aoBrightnessXYZPPP & 15728880) + (renderBlocks.aoBrightnessXZPP & 15728880) + (renderBlocks.aoBrightnessYZPP & 15728880) + (var20 & 15728880)) >> 2;

            // Calculate a basic corner color: x+ z+
            renderBlocks.colorRedTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 4) & 240) + ((renderBlocks.aoBrightnessXZNP >> 4) & 240) + ((renderBlocks.aoBrightnessYZPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 8) & 240) + ((renderBlocks.aoBrightnessXZNP >> 8) & 240) + ((renderBlocks.aoBrightnessYZPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 12) & 240) + ((renderBlocks.aoBrightnessXZNP >> 12) & 240) + ((renderBlocks.aoBrightnessYZPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x+ z-
            renderBlocks.colorRedBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 4) & 240) + ((renderBlocks.aoBrightnessYZNP >> 4) & 240) + ((renderBlocks.aoBrightnessXZNP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 8) & 240) + ((renderBlocks.aoBrightnessYZNP >> 8) & 240) + ((renderBlocks.aoBrightnessXZNP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 12) & 240) + ((renderBlocks.aoBrightnessYZNP >> 12) & 240) + ((renderBlocks.aoBrightnessXZNP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z-
            renderBlocks.colorRedBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 4) & 240) + ((renderBlocks.aoBrightnessYZNP >> 4) & 240) + ((renderBlocks.aoBrightnessXZPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 8) & 240) + ((renderBlocks.aoBrightnessYZNP >> 8) & 240) + ((renderBlocks.aoBrightnessXZPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 12) & 240) + ((renderBlocks.aoBrightnessYZNP >> 12) & 240) + ((renderBlocks.aoBrightnessXZPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z+
            renderBlocks.colorRedTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 4) & 240) + ((renderBlocks.aoBrightnessXZPP >> 4) & 240) + ((renderBlocks.aoBrightnessYZPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 8) & 240) + ((renderBlocks.aoBrightnessXZPP >> 8) & 240) + ((renderBlocks.aoBrightnessYZPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 12) & 240) + ((renderBlocks.aoBrightnessXZPP >> 12) & 240) + ((renderBlocks.aoBrightnessYZPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);

            // If colors are applied to any corners
            if ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft + renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft + renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight + renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) > 0f) {
                // Calculate a rough brightness modifier for each corner color
                float brightnessTopLeft = (1f - ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopLeft & 240))) / 240f);
                float brightnessBottomLeft = (1f - ((renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomLeft & 240))) / 240f);
                float brightnessBottomRight = (1f - ((renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomRight & 240))) / 240f);
                float brightnessTopRight = (1f - ((renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopRight & 240))) / 240f);

                // Finalize the corner color: x+ z+
                renderBlocks.colorRedTopLeft = Math.min(1f, renderBlocks.colorRedTopLeft + brightnessTopLeft);
                renderBlocks.colorGreenTopLeft = Math.min(1f, renderBlocks.colorGreenTopLeft + brightnessTopLeft);
                renderBlocks.colorBlueTopLeft = Math.min(1f, renderBlocks.colorBlueTopLeft + brightnessTopLeft);
                // Finalize the corner color: x+ z-
                renderBlocks.colorRedBottomLeft = Math.min(1f, renderBlocks.colorRedBottomLeft + brightnessBottomLeft);
                renderBlocks.colorGreenBottomLeft = Math.min(1f, renderBlocks.colorGreenBottomLeft + brightnessBottomLeft);
                renderBlocks.colorBlueBottomLeft = Math.min(1f, renderBlocks.colorBlueBottomLeft + brightnessBottomLeft);
                // Finalize the corner color: x- z-
                renderBlocks.colorRedBottomRight = Math.min(1f, renderBlocks.colorRedBottomRight + brightnessBottomRight);
                renderBlocks.colorGreenBottomRight = Math.min(1f, renderBlocks.colorGreenBottomRight + brightnessBottomRight);
                renderBlocks.colorBlueBottomRight = Math.min(1f, renderBlocks.colorBlueBottomRight + brightnessBottomRight);
                // Finalize the corner color: x- z+
                renderBlocks.colorRedTopRight = Math.min(1f, renderBlocks.colorRedTopRight + brightnessTopRight);
                renderBlocks.colorGreenTopRight = Math.min(1f, renderBlocks.colorGreenTopRight + brightnessTopRight);
                renderBlocks.colorBlueTopRight = Math.min(1f, renderBlocks.colorBlueTopRight + brightnessTopRight);
            }
            // If no colors are applied to any of the corners
            else {
                // Reset the corners to white
                renderBlocks.colorRedTopLeft = renderBlocks.colorGreenTopLeft = renderBlocks.colorBlueTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorGreenBottomRight = renderBlocks.colorBlueBottomRight = renderBlocks.colorRedTopRight = renderBlocks.colorGreenTopRight = renderBlocks.colorBlueTopRight = 1f;
            }

            // If the block color is not being overridden
            if (var13) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopRight *= par7;
            }

            // Apply shadows next to walls and highlights next to edges
            renderBlocks.colorRedTopLeft *= var9 * 0.8f;
            renderBlocks.colorGreenTopLeft *= var9 * 0.8f;
            renderBlocks.colorBlueTopLeft *= var9 * 0.8f;
            renderBlocks.colorRedBottomLeft *= var10 * 0.8f;
            renderBlocks.colorGreenBottomLeft *= var10 * 0.8f;
            renderBlocks.colorBlueBottomLeft *= var10 * 0.8f;
            renderBlocks.colorRedBottomRight *= var11 * 0.8f;
            renderBlocks.colorGreenBottomRight *= var11 * 0.8f;
            renderBlocks.colorBlueBottomRight *= var11 * 0.8f;
            renderBlocks.colorRedTopRight *= var12 * 0.8f;
            renderBlocks.colorGreenTopRight *= var12 * 0.8f;
            renderBlocks.colorBlueTopRight *= var12 * 0.8f;

            // END - GlitchLights

            var22 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3);
            renderBlocks.renderFaceZPos(par1Block, (double) par2, (double) par3, (double) par4, renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 3));

            if (RenderBlocks.fancyGrass && var22.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture()) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceZPos(par1Block, (double) par2, (double) par3, (double) par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 - 1, par3, par4, 4)) {
            if (renderBlocks.renderMinX <= 0.0D) {
                --par2;
            }

            renderBlocks.aoLightValueScratchXYNN = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXZNN = renderBlocks.blockAccess.getBlock(par2, par3, par4 - 1).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXZNP = renderBlocks.blockAccess.getBlock(par2, par3, par4 + 1).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXYNP = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoBrightnessXYNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessXZNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessXZNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = renderBlocks.blockAccess.getBlock(par2 - 1, par3 + 1, par4).getCanBlockGrass();
            var17 = renderBlocks.blockAccess.getBlock(par2 - 1, par3 - 1, par4).getCanBlockGrass();
            var18 = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4 - 1).getCanBlockGrass();
            var19 = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4 + 1).getCanBlockGrass();

            if (!var18 && !var17) {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNNN = renderBlocks.aoBrightnessXZNN;
            } else {
                renderBlocks.aoLightValueScratchXYZNNN = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4 - 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
            }

            if (!var19 && !var17) {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNNP = renderBlocks.aoBrightnessXZNP;
            } else {
                renderBlocks.aoLightValueScratchXYZNNP = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4 + 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
            }

            if (!var18 && !var16) {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.aoLightValueScratchXZNN;
                renderBlocks.aoBrightnessXYZNPN = renderBlocks.aoBrightnessXZNN;
            } else {
                renderBlocks.aoLightValueScratchXYZNPN = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4 - 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
            }

            if (!var19 && !var16) {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.aoLightValueScratchXZNP;
                renderBlocks.aoBrightnessXYZNPP = renderBlocks.aoBrightnessXZNP;
            } else {
                renderBlocks.aoLightValueScratchXYZNPP = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4 + 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZNPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
            }

            if (renderBlocks.renderMinX <= 0.0D) {
                ++par2;
            }

            var20 = var14;

            if (renderBlocks.renderMinX <= 0.0D || !renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4).isOpaqueCube()) {
                var20 = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 - 1, par3, par4);
            }

            var21 = renderBlocks.blockAccess.getBlock(par2 - 1, par3, par4).getAmbientOcclusionLightValue();
            var12 = (renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXYZNNP + var21 + renderBlocks.aoLightValueScratchXZNP) / 4.0F;
            var9 = (var21 + renderBlocks.aoLightValueScratchXZNP + renderBlocks.aoLightValueScratchXYNP + renderBlocks.aoLightValueScratchXYZNPP) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXZNN + var21 + renderBlocks.aoLightValueScratchXYZNPN + renderBlocks.aoLightValueScratchXYNP) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchXYZNNN + renderBlocks.aoLightValueScratchXYNN + renderBlocks.aoLightValueScratchXZNN + var21) / 4.0F;

            // BEGIN - GlitchLights

            // Calculate the basic corner brightnesses
            renderBlocks.brightnessTopLeft = ((renderBlocks.aoBrightnessXYZNPP & 15728880) + (renderBlocks.aoBrightnessXZNP & 15728880) + (renderBlocks.aoBrightnessXYNP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomLeft = ((renderBlocks.aoBrightnessXYZNPN & 15728880) + (renderBlocks.aoBrightnessXZNN & 15728880) + (renderBlocks.aoBrightnessXYNP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomRight = ((renderBlocks.aoBrightnessXYZNNN & 15728880) + (renderBlocks.aoBrightnessXYNN & 15728880) + (renderBlocks.aoBrightnessXZNN & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessTopRight = ((renderBlocks.aoBrightnessXYZNNP & 15728880) + (renderBlocks.aoBrightnessXYNN & 15728880) + (renderBlocks.aoBrightnessXZNP & 15728880) + (var20 & 15728880)) >> 2;

            // Calculate a basic corner color: x+ z+
            renderBlocks.colorRedTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 4) & 240) + ((renderBlocks.aoBrightnessXZNP >> 4) & 240) + ((renderBlocks.aoBrightnessXYNP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 8) & 240) + ((renderBlocks.aoBrightnessXZNP >> 8) & 240) + ((renderBlocks.aoBrightnessXYNP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPP >> 12) & 240) + ((renderBlocks.aoBrightnessXZNP >> 12) & 240) + ((renderBlocks.aoBrightnessXYNP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x+ z-
            renderBlocks.colorRedBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 4) & 240) + ((renderBlocks.aoBrightnessXZNN >> 4) & 240) + ((renderBlocks.aoBrightnessXYNP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 8) & 240) + ((renderBlocks.aoBrightnessXZNN >> 8) & 240) + ((renderBlocks.aoBrightnessXYNP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZNPN >> 12) & 240) + ((renderBlocks.aoBrightnessXZNN >> 12) & 240) + ((renderBlocks.aoBrightnessXYNP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z-
            renderBlocks.colorRedBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 4) & 240) + ((renderBlocks.aoBrightnessXYNN >> 4) & 240) + ((renderBlocks.aoBrightnessXZNN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 8) & 240) + ((renderBlocks.aoBrightnessXYNN >> 8) & 240) + ((renderBlocks.aoBrightnessXZNN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZNNN >> 12) & 240) + ((renderBlocks.aoBrightnessXYNN >> 12) & 240) + ((renderBlocks.aoBrightnessXZNN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z+
            renderBlocks.colorRedTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 4) & 240) + ((renderBlocks.aoBrightnessXYNN >> 4) & 240) + ((renderBlocks.aoBrightnessXZNP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 8) & 240) + ((renderBlocks.aoBrightnessXYNN >> 8) & 240) + ((renderBlocks.aoBrightnessXZNP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopRight = ((float) ((((renderBlocks.aoBrightnessXYZNNP >> 12) & 240) + ((renderBlocks.aoBrightnessXYNN >> 12) & 240) + ((renderBlocks.aoBrightnessXZNP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);

            // If colors are applied to any corners
            if ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft + renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft + renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight + renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) > 0f) {
                // Calculate a rough brightness modifier for each corner color
                float brightnessTopLeft = (1f - ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopLeft & 240))) / 240f);
                float brightnessBottomLeft = (1f - ((renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomLeft & 240))) / 240f);
                float brightnessBottomRight = (1f - ((renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomRight & 240))) / 240f);
                float brightnessTopRight = (1f - ((renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopRight & 240))) / 240f);

                // Finalize the corner color: x+ z+
                renderBlocks.colorRedTopLeft = Math.min(1f, renderBlocks.colorRedTopLeft + brightnessTopLeft);
                renderBlocks.colorGreenTopLeft = Math.min(1f, renderBlocks.colorGreenTopLeft + brightnessTopLeft);
                renderBlocks.colorBlueTopLeft = Math.min(1f, renderBlocks.colorBlueTopLeft + brightnessTopLeft);
                // Finalize the corner color: x+ z-
                renderBlocks.colorRedBottomLeft = Math.min(1f, renderBlocks.colorRedBottomLeft + brightnessBottomLeft);
                renderBlocks.colorGreenBottomLeft = Math.min(1f, renderBlocks.colorGreenBottomLeft + brightnessBottomLeft);
                renderBlocks.colorBlueBottomLeft = Math.min(1f, renderBlocks.colorBlueBottomLeft + brightnessBottomLeft);
                // Finalize the corner color: x- z-
                renderBlocks.colorRedBottomRight = Math.min(1f, renderBlocks.colorRedBottomRight + brightnessBottomRight);
                renderBlocks.colorGreenBottomRight = Math.min(1f, renderBlocks.colorGreenBottomRight + brightnessBottomRight);
                renderBlocks.colorBlueBottomRight = Math.min(1f, renderBlocks.colorBlueBottomRight + brightnessBottomRight);
                // Finalize the corner color: x- z+
                renderBlocks.colorRedTopRight = Math.min(1f, renderBlocks.colorRedTopRight + brightnessTopRight);
                renderBlocks.colorGreenTopRight = Math.min(1f, renderBlocks.colorGreenTopRight + brightnessTopRight);
                renderBlocks.colorBlueTopRight = Math.min(1f, renderBlocks.colorBlueTopRight + brightnessTopRight);
            }
            // If no colors are applied to any of the corners
            else {
                // Reset the corners to white
                renderBlocks.colorRedTopLeft = renderBlocks.colorGreenTopLeft = renderBlocks.colorBlueTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorGreenBottomRight = renderBlocks.colorBlueBottomRight = renderBlocks.colorRedTopRight = renderBlocks.colorGreenTopRight = renderBlocks.colorBlueTopRight = 1f;
            }

            // If the block color is not being overridden
            if (var13) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopRight *= par7;
            }

            // Apply shadows next to walls and highlights next to edges
            renderBlocks.colorRedTopLeft *= var9 * 0.6f;
            renderBlocks.colorGreenTopLeft *= var9 * 0.6f;
            renderBlocks.colorBlueTopLeft *= var9 * 0.6f;
            renderBlocks.colorRedBottomLeft *= var10 * 0.6f;
            renderBlocks.colorGreenBottomLeft *= var10 * 0.6f;
            renderBlocks.colorBlueBottomLeft *= var10 * 0.6f;
            renderBlocks.colorRedBottomRight *= var11 * 0.6f;
            renderBlocks.colorGreenBottomRight *= var11 * 0.6f;
            renderBlocks.colorBlueBottomRight *= var11 * 0.6f;
            renderBlocks.colorRedTopRight *= var12 * 0.6f;
            renderBlocks.colorGreenTopRight *= var12 * 0.6f;
            renderBlocks.colorBlueTopRight *= var12 * 0.6f;

            // END - GlitchLights

            var22 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 4);
            renderBlocks.renderFaceXNeg(par1Block, (double) par2, (double) par3, (double) par4, var22);

            if (RenderBlocks.fancyGrass && var22.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture()) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceXNeg(par1Block, (double) par2, (double) par3, (double) par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        //XXX+1
        if (renderBlocks.renderAllFaces || par1Block.shouldSideBeRendered(renderBlocks.blockAccess, par2 + 1, par3, par4, 5)) {
            if (renderBlocks.renderMaxX >= 1.0D) {
                ++par2;
            }

            renderBlocks.aoLightValueScratchXYPN = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXZPN = renderBlocks.blockAccess.getBlock(par2, par3, par4 - 1).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXZPP = renderBlocks.blockAccess.getBlock(par2, par3, par4 + 1).getAmbientOcclusionLightValue();
            renderBlocks.aoLightValueScratchXYPP = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4).getAmbientOcclusionLightValue();
            renderBlocks.aoBrightnessXYPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4);
            renderBlocks.aoBrightnessXZPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 - 1);
            renderBlocks.aoBrightnessXZPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3, par4 + 1);
            renderBlocks.aoBrightnessXYPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4);
            var16 = renderBlocks.blockAccess.getBlock(par2 + 1, par3 + 1, par4).getCanBlockGrass();
            var17 = renderBlocks.blockAccess.getBlock(par2 + 1, par3 - 1, par4).getCanBlockGrass();
            var18 = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4 + 1).getCanBlockGrass();
            var19 = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4 - 1).getCanBlockGrass();

            if (!var17 && !var19) {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPNN = renderBlocks.aoBrightnessXZPN;
            } else {
                renderBlocks.aoLightValueScratchXYZPNN = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4 - 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPNN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4 - 1);
            }

            if (!var17 && !var18) {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPNP = renderBlocks.aoBrightnessXZPP;
            } else {
                renderBlocks.aoLightValueScratchXYZPNP = renderBlocks.blockAccess.getBlock(par2, par3 - 1, par4 + 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPNP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 - 1, par4 + 1);
            }

            if (!var16 && !var19) {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.aoLightValueScratchXZPN;
                renderBlocks.aoBrightnessXYZPPN = renderBlocks.aoBrightnessXZPN;
            } else {
                renderBlocks.aoLightValueScratchXYZPPN = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4 - 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPPN = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4 - 1);
            }

            if (!var16 && !var18) {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.aoLightValueScratchXZPP;
                renderBlocks.aoBrightnessXYZPPP = renderBlocks.aoBrightnessXZPP;
            } else {
                renderBlocks.aoLightValueScratchXYZPPP = renderBlocks.blockAccess.getBlock(par2, par3 + 1, par4 + 1).getAmbientOcclusionLightValue();
                renderBlocks.aoBrightnessXYZPPP = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2, par3 + 1, par4 + 1);
            }

            if (renderBlocks.renderMaxX >= 1.0D) {
                --par2;
            }

            var20 = var14;

            if (renderBlocks.renderMaxX >= 1.0D || !renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4).isOpaqueCube()) {
                var20 = CLBlockHelper.getMixedBrightnessForBlockWithColor(renderBlocks.blockAccess, par2 + 1, par3, par4);
            }

            var21 = renderBlocks.blockAccess.getBlock(par2 + 1, par3, par4).getAmbientOcclusionLightValue();
            var9 = (renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXYZPNP + var21 + renderBlocks.aoLightValueScratchXZPP) / 4.0F;
            var10 = (renderBlocks.aoLightValueScratchXYZPNN + renderBlocks.aoLightValueScratchXYPN + renderBlocks.aoLightValueScratchXZPN + var21) / 4.0F;
            var11 = (renderBlocks.aoLightValueScratchXZPN + var21 + renderBlocks.aoLightValueScratchXYZPPN + renderBlocks.aoLightValueScratchXYPP) / 4.0F;
            var12 = (var21 + renderBlocks.aoLightValueScratchXZPP + renderBlocks.aoLightValueScratchXYPP + renderBlocks.aoLightValueScratchXYZPPP) / 4.0F;

            // BEGIN - GlitchLights

            // Calculate the basic corner brightnesses
            renderBlocks.brightnessTopLeft = ((renderBlocks.aoBrightnessXYZPNP & 15728880) + (renderBlocks.aoBrightnessXYPN & 15728880) + (renderBlocks.aoBrightnessXZPP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomLeft = ((renderBlocks.aoBrightnessXYZPNN & 15728880) + (renderBlocks.aoBrightnessXYPN & 15728880) + (renderBlocks.aoBrightnessXZPN & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessBottomRight = ((renderBlocks.aoBrightnessXYZPPN & 15728880) + (renderBlocks.aoBrightnessXZPN & 15728880) + (renderBlocks.aoBrightnessXYPP & 15728880) + (var20 & 15728880)) >> 2;
            renderBlocks.brightnessTopRight = ((renderBlocks.aoBrightnessXYZPPP & 15728880) + (renderBlocks.aoBrightnessXZPP & 15728880) + (renderBlocks.aoBrightnessXYPP & 15728880) + (var20 & 15728880)) >> 2;

            // Calculate a basic corner color: x+ z+
            renderBlocks.colorRedTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 4) & 240) + ((renderBlocks.aoBrightnessXYPN >> 4) & 240) + ((renderBlocks.aoBrightnessXZPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 8) & 240) + ((renderBlocks.aoBrightnessXYPN >> 8) & 240) + ((renderBlocks.aoBrightnessXZPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopLeft = ((float) ((((renderBlocks.aoBrightnessXYZPNP >> 12) & 240) + ((renderBlocks.aoBrightnessXYPN >> 12) & 240) + ((renderBlocks.aoBrightnessXZPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x+ z-
            renderBlocks.colorRedBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 4) & 240) + ((renderBlocks.aoBrightnessXYPN >> 4) & 240) + ((renderBlocks.aoBrightnessXZPN >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 8) & 240) + ((renderBlocks.aoBrightnessXYPN >> 8) & 240) + ((renderBlocks.aoBrightnessXZPN >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomLeft = ((float) ((((renderBlocks.aoBrightnessXYZPNN >> 12) & 240) + ((renderBlocks.aoBrightnessXYPN >> 12) & 240) + ((renderBlocks.aoBrightnessXZPN >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z-
            renderBlocks.colorRedBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 4) & 240) + ((renderBlocks.aoBrightnessXZPN >> 4) & 240) + ((renderBlocks.aoBrightnessXYPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 8) & 240) + ((renderBlocks.aoBrightnessXZPN >> 8) & 240) + ((renderBlocks.aoBrightnessXYPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueBottomRight = ((float) ((((renderBlocks.aoBrightnessXYZPPN >> 12) & 240) + ((renderBlocks.aoBrightnessXZPN >> 12) & 240) + ((renderBlocks.aoBrightnessXYPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);
            // Calculate a basic corner color: x- z+
            renderBlocks.colorRedTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 4) & 240) + ((renderBlocks.aoBrightnessXZPP >> 4) & 240) + ((renderBlocks.aoBrightnessXYPP >> 4) & 240) + ((var20 >> 4) & 240)) >> 2) / 240f);
            renderBlocks.colorGreenTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 8) & 240) + ((renderBlocks.aoBrightnessXZPP >> 8) & 240) + ((renderBlocks.aoBrightnessXYPP >> 8) & 240) + ((var20 >> 8) & 240)) >> 2) / 240f);
            renderBlocks.colorBlueTopRight = ((float) ((((renderBlocks.aoBrightnessXYZPPP >> 12) & 240) + ((renderBlocks.aoBrightnessXZPP >> 12) & 240) + ((renderBlocks.aoBrightnessXYPP >> 12) & 240) + ((var20 >> 12) & 240)) >> 2) / 240f);

            // If colors are applied to any corners
            if ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft + renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft + renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight + renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) > 0f) {
                // Calculate a rough brightness modifier for each corner color
                float brightnessTopLeft = (1f - ((renderBlocks.colorRedTopLeft + renderBlocks.colorGreenTopLeft + renderBlocks.colorBlueTopLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopLeft & 240))) / 240f);
                float brightnessBottomLeft = (1f - ((renderBlocks.colorRedBottomLeft + renderBlocks.colorGreenBottomLeft + renderBlocks.colorBlueBottomLeft) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomLeft & 240))) / 240f);
                float brightnessBottomRight = (1f - ((renderBlocks.colorRedBottomRight + renderBlocks.colorGreenBottomRight + renderBlocks.colorBlueBottomRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessBottomRight & 240))) / 240f);
                float brightnessTopRight = (1f - ((renderBlocks.colorRedTopRight + renderBlocks.colorGreenTopRight + renderBlocks.colorBlueTopRight) / 3f)) * (((float) (240 - (renderBlocks.brightnessTopRight & 240))) / 240f);

                // Finalize the corner color: x+ z+
                renderBlocks.colorRedTopLeft = Math.min(1f, renderBlocks.colorRedTopLeft + brightnessTopLeft);
                renderBlocks.colorGreenTopLeft = Math.min(1f, renderBlocks.colorGreenTopLeft + brightnessTopLeft);
                renderBlocks.colorBlueTopLeft = Math.min(1f, renderBlocks.colorBlueTopLeft + brightnessTopLeft);
                // Finalize the corner color: x+ z-
                renderBlocks.colorRedBottomLeft = Math.min(1f, renderBlocks.colorRedBottomLeft + brightnessBottomLeft);
                renderBlocks.colorGreenBottomLeft = Math.min(1f, renderBlocks.colorGreenBottomLeft + brightnessBottomLeft);
                renderBlocks.colorBlueBottomLeft = Math.min(1f, renderBlocks.colorBlueBottomLeft + brightnessBottomLeft);
                // Finalize the corner color: x- z-
                renderBlocks.colorRedBottomRight = Math.min(1f, renderBlocks.colorRedBottomRight + brightnessBottomRight);
                renderBlocks.colorGreenBottomRight = Math.min(1f, renderBlocks.colorGreenBottomRight + brightnessBottomRight);
                renderBlocks.colorBlueBottomRight = Math.min(1f, renderBlocks.colorBlueBottomRight + brightnessBottomRight);
                // Finalize the corner color: x- z+
                renderBlocks.colorRedTopRight = Math.min(1f, renderBlocks.colorRedTopRight + brightnessTopRight);
                renderBlocks.colorGreenTopRight = Math.min(1f, renderBlocks.colorGreenTopRight + brightnessTopRight);
                renderBlocks.colorBlueTopRight = Math.min(1f, renderBlocks.colorBlueTopRight + brightnessTopRight);
            }
            // If no colors are applied to any of the corners
            else {
                // Reset the corners to white
                renderBlocks.colorRedTopLeft = renderBlocks.colorGreenTopLeft = renderBlocks.colorBlueTopLeft = renderBlocks.colorRedBottomLeft = renderBlocks.colorGreenBottomLeft = renderBlocks.colorBlueBottomLeft = renderBlocks.colorRedBottomRight = renderBlocks.colorGreenBottomRight = renderBlocks.colorBlueBottomRight = renderBlocks.colorRedTopRight = renderBlocks.colorGreenTopRight = renderBlocks.colorBlueTopRight = 1f;
            }

            // If the block color is not being overridden
            if (var13) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopRight *= par7;
            }

            // Apply shadows next to walls and highlights next to edges
            renderBlocks.colorRedTopLeft *= var9 * 0.6f;
            renderBlocks.colorGreenTopLeft *= var9 * 0.6f;
            renderBlocks.colorBlueTopLeft *= var9 * 0.6f;
            renderBlocks.colorRedBottomLeft *= var10 * 0.6f;
            renderBlocks.colorGreenBottomLeft *= var10 * 0.6f;
            renderBlocks.colorBlueBottomLeft *= var10 * 0.6f;
            renderBlocks.colorRedBottomRight *= var11 * 0.6f;
            renderBlocks.colorGreenBottomRight *= var11 * 0.6f;
            renderBlocks.colorBlueBottomRight *= var11 * 0.6f;
            renderBlocks.colorRedTopRight *= var12 * 0.6f;
            renderBlocks.colorGreenTopRight *= var12 * 0.6f;
            renderBlocks.colorBlueTopRight *= var12 * 0.6f;

            // END - GlitchLights

            var22 = renderBlocks.getBlockIcon(par1Block, renderBlocks.blockAccess, par2, par3, par4, 5);
            renderBlocks.renderFaceXPos(par1Block, (double) par2, (double) par3, (double) par4, var22);

            if (RenderBlocks.fancyGrass && var22.getIconName().equals("grass_side") && !renderBlocks.hasOverrideBlockTexture()) {
                renderBlocks.colorRedTopLeft *= par5;
                renderBlocks.colorRedBottomLeft *= par5;
                renderBlocks.colorRedBottomRight *= par5;
                renderBlocks.colorRedTopRight *= par5;
                renderBlocks.colorGreenTopLeft *= par6;
                renderBlocks.colorGreenBottomLeft *= par6;
                renderBlocks.colorGreenBottomRight *= par6;
                renderBlocks.colorGreenTopRight *= par6;
                renderBlocks.colorBlueTopLeft *= par7;
                renderBlocks.colorBlueBottomLeft *= par7;
                renderBlocks.colorBlueBottomRight *= par7;
                renderBlocks.colorBlueTopRight *= par7;
                renderBlocks.renderFaceXPos(par1Block, (double) par2, (double) par3, (double) par4, BlockGrass.getIconSideOverlay());
            }

            var8 = true;
        }

        renderBlocks.enableAO = false;
        return var8;
    }

    /**
     * Renders a standard cube block at the given coordinates, with a given color ratio.  Args: block, x, y, z, r, g, b
     * <p/>
     * Accepts and tints blocks according to their colored light value
     * CptSpaceToaster
     * <p/>
     * 03-05-2014 heaton84 - Ported to helper method, refactored to match 1.7.2 architecture
     */
    public static boolean renderStandardBlockWithColorMultiplier(RenderBlocks instance, Block par1Block, int par2X, int par3Y, int par4Z, float par5R, float par6G, float par7B) {
        instance.enableAO = false;
        Tessellator tessellator = Tessellator.instance;
        boolean flag = false;
        float f3 = 0.5F;
        float f4 = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float f7 = f4 * par5R;
        float f8 = f4 * par6G;
        float f9 = f4 * par7B;
        float f10 = f3;
        float f11 = f5;
        float f12 = f6;
        float f13 = f3;
        float f14 = f5;
        float f15 = f6;
        float f16 = f3;
        float f17 = f5;
        float f18 = f6;
        IIcon blockIcon;

        if (par1Block != Blocks.grass) {
            f10 = f3 * par5R;
            f11 = f5 * par5R;
            f12 = f6 * par5R;
            f13 = f3 * par6G;
            f14 = f5 * par6G;
            f15 = f6 * par6G;
            f16 = f3 * par7B;
            f17 = f5 * par7B;
            f18 = f6 * par7B;
        }

        int l = CLBlockHelper.getMixedBrightnessForBlockWithColor(instance.blockAccess, par2X, par3Y, par4Z);

        if (instance.renderAllFaces || par1Block.shouldSideBeRendered(instance.blockAccess, par2X, par3Y - 1, par4Z, 0)) {
            int i = instance.renderMinY > 0.0D ? l : CLBlockHelper.getMixedBrightnessForBlockWithColor(instance.blockAccess, par2X, par3Y - 1, par4Z);
            float rc = 1;
            float gc = 1;
            float bc = 1;
            float lc;

            if ((i & 1048320) > 0) {
                lc = 1f - (i & 240) / 240f;
                rc = (i & 3840) / 3840f;
                gc = (i & 61440) / 61440f;
                bc = (i & 983040) / 983040f;

                rc = (rc + lc > 1) ? 1 : rc + lc;
                gc = (gc + lc > 1) ? 1 : gc + lc;
                bc = (bc + lc > 1) ? 1 : bc + lc;
            }

            tessellator.setBrightness(i);
            tessellator.setColorOpaque_F(f10 * rc, f13 * gc, f16 * bc);
            instance.renderFaceYNeg(par1Block, (double) par2X, (double) par3Y, (double) par4Z, instance.getBlockIcon(par1Block, instance.blockAccess, par2X, par3Y, par4Z, 0));
            flag = true;
        }

        if (instance.renderAllFaces || par1Block.shouldSideBeRendered(instance.blockAccess, par2X, par3Y + 1, par4Z, 1)) {
            int i = instance.renderMaxY < 1.0D ? l : CLBlockHelper.getMixedBrightnessForBlockWithColor(instance.blockAccess, par2X, par3Y + 1, par4Z);

            float rc = 1;
            float gc = 1;
            float bc = 1;
            float lc;

            if ((i & 1048320) > 0) {
                lc = 1f - (i & 240) / 240f;
                rc = (i & 3840) / 3840f;
                gc = (i & 61440) / 61440f;
                bc = (i & 983040) / 983040f;

                rc = (rc + lc > 1) ? 1 : rc + lc;
                gc = (gc + lc > 1) ? 1 : gc + lc;
                bc = (bc + lc > 1) ? 1 : bc + lc;
            }

            tessellator.setBrightness(i);
            tessellator.setColorOpaque_F(f7 * rc, f8 * gc, f9 * bc);
            instance.renderFaceYPos(par1Block, (double) par2X, (double) par3Y, (double) par4Z, instance.getBlockIcon(par1Block, instance.blockAccess, par2X, par3Y, par4Z, 1));
            flag = true;
        }

        if (instance.renderAllFaces || par1Block.shouldSideBeRendered(instance.blockAccess, par2X, par3Y, par4Z - 1, 2)) {
            int i = instance.renderMinZ > 0.0D ? l : CLBlockHelper.getMixedBrightnessForBlockWithColor(instance.blockAccess, par2X, par3Y, par4Z - 1);

            float rc = 1;
            float gc = 1;
            float bc = 1;
            float lc;

            if ((i & 1048320) > 0) {
                lc = 1f - (i & 240) / 240f;
                rc = (i & 3840) / 3840f;
                gc = (i & 61440) / 61440f;
                bc = (i & 983040) / 983040f;

                rc = (rc + lc > 1) ? 1 : rc + lc;
                gc = (gc + lc > 1) ? 1 : gc + lc;
                bc = (bc + lc > 1) ? 1 : bc + lc;
            }

            tessellator.setBrightness(i);
            tessellator.setColorOpaque_F(f11 * rc, f14 * gc, f17 * bc);
            blockIcon = instance.getBlockIcon(par1Block, instance.blockAccess, par2X, par3Y, par4Z, 2);
            instance.renderFaceZNeg(par1Block, (double) par2X, (double) par3Y, (double) par4Z, blockIcon);

            if (RenderBlocks.fancyGrass && blockIcon.getIconName().equals("grass_side") && !instance.hasOverrideBlockTexture()) {
                tessellator.setColorOpaque_F(f11 * par5R * rc, f14 * par6G * gc, f17 * par7B * bc);
                instance.renderFaceZNeg(par1Block, (double) par2X, (double) par3Y, (double) par4Z, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (instance.renderAllFaces || par1Block.shouldSideBeRendered(instance.blockAccess, par2X, par3Y, par4Z + 1, 3)) {
            int i = instance.renderMaxZ < 1.0D ? l : CLBlockHelper.getMixedBrightnessForBlockWithColor(instance.blockAccess, par2X, par3Y, par4Z + 1);


            float rc = 1;
            float gc = 1;
            float bc = 1;
            float lc;

            if ((i & 1048320) > 0) {
                lc = 1f - (i & 240) / 240f;
                rc = (i & 3840) / 3840f;
                gc = (i & 61440) / 61440f;
                bc = (i & 983040) / 983040f;

                rc = (rc + lc > 1) ? 1 : rc + lc;
                gc = (gc + lc > 1) ? 1 : gc + lc;
                bc = (bc + lc > 1) ? 1 : bc + lc;
            }

            tessellator.setBrightness(i);
            tessellator.setColorOpaque_F(f11 * rc, f14 * gc, f17 * bc);
            blockIcon = instance.getBlockIcon(par1Block, instance.blockAccess, par2X, par3Y, par4Z, 3);
            instance.renderFaceZPos(par1Block, (double) par2X, (double) par3Y, (double) par4Z, blockIcon);

            if (RenderBlocks.fancyGrass && blockIcon.getIconName().equals("grass_side") && !instance.hasOverrideBlockTexture()) {
                tessellator.setColorOpaque_F(f11 * par5R * rc, f14 * par6G * gc, f17 * par7B * bc);
                instance.renderFaceZPos(par1Block, (double) par2X, (double) par3Y, (double) par4Z, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (instance.renderAllFaces || par1Block.shouldSideBeRendered(instance.blockAccess, par2X - 1, par3Y, par4Z, 4)) {
            int i = instance.renderMinX > 0.0D ? l : CLBlockHelper.getMixedBrightnessForBlockWithColor(instance.blockAccess, par2X - 1, par3Y, par4Z);


            float rc = 1;
            float gc = 1;
            float bc = 1;
            float lc;

            if ((i & 1048320) > 0) {
                lc = 1f - (i & 240) / 240f;
                rc = (i & 3840) / 3840f;
                gc = (i & 61440) / 61440f;
                bc = (i & 983040) / 983040f;

                rc = (rc + lc > 1) ? 1 : rc + lc;
                gc = (gc + lc > 1) ? 1 : gc + lc;
                bc = (bc + lc > 1) ? 1 : bc + lc;
            }

            tessellator.setBrightness(i);
            tessellator.setColorOpaque_F(f12 * rc, f15 * gc, f18 * bc);
            blockIcon = instance.getBlockIcon(par1Block, instance.blockAccess, par2X, par3Y, par4Z, 4);
            instance.renderFaceXNeg(par1Block, (double) par2X, (double) par3Y, (double) par4Z, blockIcon);

            if (RenderBlocks.fancyGrass && blockIcon.getIconName().equals("grass_side") && !instance.hasOverrideBlockTexture()) {
                tessellator.setColorOpaque_F(f12 * par5R * rc, f15 * par6G * gc, f18 * par7B * bc);
                instance.renderFaceXNeg(par1Block, (double) par2X, (double) par3Y, (double) par4Z, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        if (instance.renderAllFaces || par1Block.shouldSideBeRendered(instance.blockAccess, par2X + 1, par3Y, par4Z, 5)) {
            int i = instance.renderMaxX < 1.0D ? l : CLBlockHelper.getMixedBrightnessForBlockWithColor(instance.blockAccess, par2X + 1, par3Y, par4Z);


            float rc = 1;
            float gc = 1;
            float bc = 1;
            float lc;

            if ((i & 1048320) > 0) {
                lc = 1f - (i & 240) / 240f;
                rc = (i & 3840) / 3840f;
                gc = (i & 61440) / 61440f;
                bc = (i & 983040) / 983040f;

                rc = (rc + lc > 1) ? 1 : rc + lc;
                gc = (gc + lc > 1) ? 1 : gc + lc;
                bc = (bc + lc > 1) ? 1 : bc + lc;
            }

            tessellator.setBrightness(i);
            tessellator.setColorOpaque_F(f12 * rc, f15 * gc, f18 * bc);
            blockIcon = instance.getBlockIcon(par1Block, instance.blockAccess, par2X, par3Y, par4Z, 5);
            instance.renderFaceXPos(par1Block, (double) par2X, (double) par3Y, (double) par4Z, blockIcon);

            if (RenderBlocks.fancyGrass && blockIcon.getIconName().equals("grass_side") && !instance.hasOverrideBlockTexture()) {
                tessellator.setColorOpaque_F(f12 * par5R * rc, f15 * par6G * gc, f18 * par7B * bc);
                instance.renderFaceXPos(par1Block, (double) par2X, (double) par3Y, (double) par4Z, BlockGrass.getIconSideOverlay());
            }

            flag = true;
        }

        return flag;
    }


}
