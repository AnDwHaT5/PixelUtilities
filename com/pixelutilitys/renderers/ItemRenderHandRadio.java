package com.pixelutilitys.renderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.pixelutilitys.models.ModelRadioHand;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderHandRadio implements IItemRenderer {

	protected ModelRadioHand ModelRadioHand;
	private static final ResourceLocation field_110422_t = new ResourceLocation("pixelutilitys","textures/specialmodels/radioentity.png");
	
	
	public ItemRenderHandRadio()
	{
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case EQUIPPED: return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type)
		{
		case EQUIPPED:
		{
			float scale = 1.2F;
		      GL11.glScalef(scale, scale, scale);
			
			
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
	//		FMLClientHandler.instance().getClient().renderEngine.bindTexture(field_110422_t);
			Minecraft.getMinecraft().getTextureManager().bindTexture(field_110422_t);
			
			ModelRadioHand.render((Entity)data[0], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			
			GL11.glDisable(GL11.GL_BLEND);
			
	//		ForgeHooksClient.unbindTexture(); 

			scale = 0.2F;
		      GL11.glScalef(scale, scale, scale);
			
			
			GL11.glPopMatrix();
			
		}
		default:
			break;
		}

	}

}
