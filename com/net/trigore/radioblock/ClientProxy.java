package com.net.trigore.radioblock;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		//MinecraftForgeClient.preloadTexture(RADIO_PNG);
		//MinecraftForgeClient.registerItemRenderer(ModRadioBlock.blockRadio, (IItemRenderer)new ItemRenderHandRadio());
	}
	
	@Override
	public void initTileEntities(){
		//System.out.println("registering renderer");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadio.class, new RenderRadioBlock());
	}
}