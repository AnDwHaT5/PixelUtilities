package com.pixelutilitys.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.input.Keyboard;

import com.pixelutilitys.radioplayer.HandHeldRadioPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiHandHeldRadio extends GuiScreen{
	private double posX;
	private double posY;
	private double posZ;
	public String status;
	public static EntityPlayer player;
	private GuiTextField streamTextBox;
	String url = "http://www.youtube.com/watch?v=ktBGLpAYnCY";
	HandHeldRadioPlayer radio;
	public GuiHandHeldRadio(EntityPlayer r){
		posX = r.posX;
		posY = r.posY;
		posZ = r.posZ;
		player = r;
	}
	
	@Override
	public void initGui(){
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, height / 2 + 10, 200, 20, "Play/Stop"));
		streamTextBox = new GuiTextField(fontRendererObj, width / 2 - 100, height / 2 + 35 , 200, 20);
		streamTextBox.setMaxStringLength(1000);
		streamTextBox.setText(" Insert URL Here");
		url = streamTextBox.getText();
	}
	
	@Override
	public void onGuiClosed(){
		Keyboard.enableRepeatEvents(false);
		
	}
	
	
	
	@Override
	public void drawScreen(int par1, int par2, float par3){
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "Song URL", this.width / 2, 40, 16777215);
		streamTextBox.drawTextBox();
		super.drawScreen(par1, par2, par3);
		
		
	}
	
	@Override
	public void updateScreen(){
		
		
		
	}
	
	@Override
	protected void keyTyped(char par1, int par2){
		streamTextBox.textboxKeyTyped(par1, par2);
		if (par1 == 13){//enter pressed
			//actionPerformed((GuiButton)this.buttonList.get(1));
		}
		super.keyTyped(par1, par2);
	}
	
	@Override
	protected void mouseClicked(int par1, int par2, int par3){
		streamTextBox.mouseClicked(par1, par2, par3);
		super.mouseClicked(par1, par2, par3);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	 protected void actionPerformed(GuiButton par1GuiButton){
			if(par1GuiButton.id == 1)
			{
				HandHeldRadioPlayer rad = new HandHeldRadioPlayer(streamTextBox.getText(), false);
				radio = rad;
				if(radio.isPlaying())
				{
					radio.stop();
				}
				else
				{
					radio.start();
				}
				
				 
				
			}
	}
	
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

}
