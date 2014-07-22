package com.pixelutilitys.gui;

import com.pixelutilitys.arcade.interfaces.IArcadeGame;
import com.pixelutilitys.arcade.system.OS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.DynamicTexture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;

import java.util.ArrayList;
import java.util.List;

import com.pixelutilitys.arcade.interfaces.IArcadeGame.KEY;
import net.minecraft.client.settings.GameSettings;

public class PixelBoyGui extends GuiScreen {

    public PixelBoyGui()
    {

    }

    private int offsetX;
    private int offsetY;
    private IArcadeGame game;
    private final List<KEY> keysPressedDown = new ArrayList<>(12);
    DynamicTexture texture;

    @Override
    public void initGui()
    {
        offsetX = (width - 256) / 2;
        offsetY = (height - 256) / 2;
    }

    @Override
    public void onGuiClosed()
    {
        if(game != null)
            game.unload();
    }


    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        if(game == null)
            initMainMenu();

        keysPressedDown.clear();

        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        if (GameSettings.isKeyDown(settings.keyBindRight)) {
            keysPressedDown.add(KEY.RIGHT);
        }
        if (GameSettings.isKeyDown(settings.keyBindLeft)) {
            keysPressedDown.add(KEY.LEFT);
        }
        if (GameSettings.isKeyDown(settings.keyBindBack)) {
            keysPressedDown.add(KEY.DOWN);
        }
        if (GameSettings.isKeyDown(settings.keyBindForward)) {
            keysPressedDown.add(KEY.UP);
        }
        if (GameSettings.isKeyDown(settings.keyBindJump)) {
            keysPressedDown.add(KEY.A);
        }


        // let the game tick
        game.doGameTick(keysPressedDown);

        if(game.renderGraphics() == null)
            return;

        texture = new DynamicTexture(game.renderGraphics());
        glBindTexture(GL_TEXTURE_2D, texture.getGlTextureId());

        glGenerateMipmapEXT(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        float scaler = 1.00f;
        glScalef(scaler,scaler,scaler);
        drawTexturedModalRect(offsetX, offsetY, 0, 0, texture.width, texture.height);

        texture.deleteGlTexture();
        texture = null;


    }

    @Override
    public void updateScreen()
    {
    }

    public void initMainMenu()
    {
        game = new OS();
        game.initialize();
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

}
