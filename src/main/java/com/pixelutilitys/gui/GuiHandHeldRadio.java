package com.pixelutilitys.gui;

import com.pixelutilitys.radioplayer.VLCPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiHandHeldRadio extends GuiScreen {
    ResourceLocation background = new ResourceLocation("pixelutilitys:textures/guis/pokegear/Background2.png");

    public String status;
    public static EntityPlayer player;
    private GuiTextField streamTextBox;
    String url = "http://www.youtube.com/watch?v=ktBGLpAYnCY";
    VLCPlayer radio;

    public GuiHandHeldRadio(EntityPlayer r) {

        player = r;
    }

    @Override
    public void initGui() {
        this.buttonList.add(new PokegearExit(1, this.width / 2 - 40, height / 2 - 60, 30, 30, ""));
        streamTextBox = new GuiTextField(fontRendererObj, width / 2 - 100, height / 2 + 35, 200, 20);
        streamTextBox.setMaxStringLength(1000);
        streamTextBox.setText(" Insert URL Here");
        url = streamTextBox.getText();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);

    }


    @Override
    public void drawScreen(int par1, int par2, float par3) {
        //drawDefaultBackground();

        GL11.glColor4f(1f, 1f, 1f, 1f);
        int posX = (this.width / 2) - 70;
        int posY = (this.height / 2) - 100;
        this.mc.renderEngine.bindTexture(background);
        //                         x                  y          width    height               DONT TOUCH TEH 1
        GuiHelper.drawImageQuad(width / 2 - posX / 2, height / 2 - 130, posX, height / 2 + 110, 0, 0, 1, 146f / 470f, zLevel);//(posX, posY, 0, 0, 150, 200);


        //this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Song URL", this.width / 2, 40, 16777215);
        streamTextBox.drawTextBox();
        super.drawScreen(par1, par2, par3);


    }

    @Override
    public void updateScreen() {


    }

    @Override
    protected void keyTyped(char par1, int par2) {
        streamTextBox.textboxKeyTyped(par1, par2);
        if (par1 == 13) {//enter pressed
            //actionPerformed((GuiButton)this.buttonList.get(1));
        }
        super.keyTyped(par1, par2);
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
        streamTextBox.mouseClicked(par1, par2, par3);
        super.mouseClicked(par1, par2, par3);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.id == 1) {
                /*HandHeldRadioPlayer rad = new HandHeldRadioPlayer(streamTextBox.getText(), false);
				radio = rad;
				if(radio.isPlaying())
				{
					radio.stop();
				}
				else
				{
					radio.start();
				}
				*/

            this.mc.thePlayer.closeScreen();


        }
    }


    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
