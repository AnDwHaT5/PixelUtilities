package com.pixelutilitys.gui;

import com.pixelutilitys.arcade.interfaces.IArcadeGame;
import com.pixelutilitys.arcade.system.OS;
import com.sun.prism.util.tess.Tess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import com.pixelutilitys.arcade.interfaces.IArcadeGame.KEY;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.opengl.GL13;

public class PixelBoyGui extends GuiScreen {

    public PixelBoyGui()
    {

    }

    private BufferedImage screen;
    private int offsetX;
    private int offsetY;
    private IArcadeGame game;
    private final List<KEY> keysPressedDown = new ArrayList<KEY>(12);

    @Override
    public void initGui()
    {

        offsetX = (width - 256) / 2;
        offsetY = 0;

        System.out.println(offsetX);


        screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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

        DynamicTexture texture = new DynamicTexture(game.renderGraphics());
        glBindTexture(GL_TEXTURE_2D, texture.getGlTextureId());

        glGenerateMipmapEXT(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        float scaler = 1.00f;
        glScalef(scaler,scaler,scaler);


        drawTexturedModalRect(offsetX, offsetY, 0, 0, texture.width, texture.height);

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
