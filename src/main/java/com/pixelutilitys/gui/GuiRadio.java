package com.pixelutilitys.gui;

import com.pixelutilitys.PacketHandler;
import com.pixelutilitys.networkMessages.MessageTileEntityRadio;
import com.pixelutilitys.tileentitys.TileEntityRadio;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GuiRadio extends GuiScreen {
    private int posX;
    private int posY;
    private int posZ;
    public String status;
    public static TileEntityRadio radio;
    private GuiTextField streamTextBox;

    public GuiRadio(TileEntityRadio r) {
        posX = r.xCoord;
        posY = r.yCoord;
        posZ = r.zCoord;
        radio = r;
    }

    @Override
    public void initGui() {

        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, height / 2 + 10, 200, 20, "Play/Pause"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 17, height / 2 + 60, 50, 20, "Loop On"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 60, height / 2 + 60, 50, 20, "Loop Off"));
        streamTextBox = new GuiTextField(fontRendererObj, width / 2 - 100, height / 2 + 35, 200, 20);
        streamTextBox.setMaxStringLength(1000);
        streamTextBox.setText(radio.streamURL);

    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }


    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Radio", this.width / 2, 40, 16777215);
        streamTextBox.drawTextBox();
        super.drawScreen(par1, par2, par3);

        if (radio.isPlaying()) {
            this.drawCenteredString(this.fontRendererObj, "Playing", this.width / 2, 60, 16777215);
        } else {
            this.drawCenteredString(this.fontRendererObj, "Not Playing", this.width / 2, 60, 16777215);
        }

        if (this.radio.isLooping()) {
            this.drawCenteredString(this.fontRendererObj, "Looping", this.width / 2, 70, 16777215);
        } else {
            this.drawCenteredString(this.fontRendererObj, "Not Looping", this.width / 2, 70, 16777215);
        }


    }

    @Override
    public void updateScreen() {

        streamTextBox.updateCursorCounter();
        if (radio.isInvalid()) {//close GUI if radio is dead
            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();
        }

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
            if (radio.isPlaying()) {
                radio.stopStream();

            } else {
                if (streamTextBox.getText().toLowerCase().endsWith(".m3u")) {
                    radio.streamURL = takeFirstEntryFromM3U(streamTextBox.getText());
                } else if (streamTextBox.getText().toLowerCase().endsWith(".pls")) {
                    radio.streamURL = parsePls(streamTextBox.getText());
                } else {
                    radio.streamURL = streamTextBox.getText();
                }

                PacketHandler.INSTANCE.sendToServer(new MessageTileEntityRadio(radio.xCoord, radio.yCoord, radio.zCoord, radio.isPlaying(), radio.streamURL));

                PacketHandler.INSTANCE.sendToServer(new MessageTileEntityRadio(radio.xCoord, radio.yCoord, radio.zCoord, !radio.isPlaying(), radio.streamURL));

            }


        }

        if (par1GuiButton.id == 2) {
            radio.setIsLooping(true);

        }
        if (par1GuiButton.id == 3) {

            radio.setIsLooping(false);
        }

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public String takeFirstEntryFromM3U(String m3uurl) {
        String out = "AudioNotFoundMaybeIShouldPutaPunchafaceSongHere";
        try {
            URL m3u = new URL(m3uurl);
            URLConnection con = m3u.openConnection();
            BufferedReader i = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String mp3;
            while ((mp3 = i.readLine()) != null) {
                if (!mp3.startsWith("#")) {
                    break;//just take the first noncomment
                }
            }
            out = mp3;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public String parsePls(String plsurl) {//contrary to popular belief I am not asking you to parse, the file format is .pls
        String out = "AudioNotFoundMaybeIShouldPutaPunchafaceSongHere";
        try {
            URL pls = new URL(plsurl);
            URLConnection con = pls.openConnection();
            BufferedReader i = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String mp3;
            while ((mp3 = i.readLine()) != null) {
                String f = mp3.trim();
                if (f.contains("http://")) {//yes I am cheating here, go home
                    out = f.substring(f.indexOf("http://"));
                    break;
                } else if (f.contains("https://")) {//yes I am cheating here, go home
                    out = f.substring(f.indexOf("https://"));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
