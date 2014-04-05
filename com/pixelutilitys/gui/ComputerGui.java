package com.pixelutilitys.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class ComputerGui extends GuiScreen
{
	public ComputerGui(EntityPlayer player)
	{

	}

	public final int xSizeOfTexture = 176;
	public final int ySizeOfTexture = 88;
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		drawDefaultBackground();

		//int var4 = this.mc.renderEngine.getTexture("/Block/changeColorGUI.png");

		ResourceLocation var4 = new ResourceLocation("pixelutilitys:gui/computer");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

		super.drawScreen(x, y, f);
		super.drawScreen(x, y, f);
	}

	public void initGui()
	{
		this.buttonList.clear();

		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		this.buttonList.add(new GuiButton(0, posX+ 40, posY + 40, 100, 20, "no use"));
	}


	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
		case 0: 
			break;
		default:
		}
	}
}