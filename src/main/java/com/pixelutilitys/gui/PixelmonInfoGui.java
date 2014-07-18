package com.pixelutilitys.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import org.lwjgl.opengl.GL11;

public class PixelmonInfoGui extends Gui {
    public FontRenderer fontRenderer;

    public PixelmonInfoGui() {
        fontRenderer = Minecraft.getMinecraft().fontRenderer;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {

        if (event.type != ElementType.ALL)
            return;
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth,
                Minecraft.getMinecraft().displayHeight);
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        mc.entityRenderer.setupOverlayRendering();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(0.5F, 0.5F, 0.5F, 1.0F);

        bindOverlayTexture();
        zLevel = -90f;
        this.drawTexturedModalRect(0, var7 / 6, 0, 0, 147, 182);

        fontRenderer.setUnicodeFlag(true);
        int i = 0;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void bindOverlayTexture() {
        ResourceLocation texture = new ResourceLocation("pixelutilitys:gui/info");
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }
}