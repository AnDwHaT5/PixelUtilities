package com.pixelutilitys;

//import com.net.trigore.radioblock.RenderRadioBlock;
//import com.net.trigore.radioblock.TileEntityRadio;
import com.pixelutilitys.entitys.AronPokedollEntity;
import com.pixelutilitys.entitys.BisharpPokedollEntity;
import com.pixelutilitys.entitys.BlockEntity;
import com.pixelutilitys.entitys.BolderEntity;
import com.pixelutilitys.entitys.BoxEntity;
import com.pixelutilitys.entitys.ClothedTableEntity;
import com.pixelutilitys.entitys.PokeballEntity;
import com.pixelutilitys.entitys.RedCusionChairEntity;
import com.pixelutilitys.entitys.TileEntityRadio;
import com.pixelutilitys.entitys.TotodilePokedollEntity;
import com.pixelutilitys.entitys.TrashcanEntity;
import com.pixelutilitys.entitys.TreeEntity;
import com.pixelutilitys.entitys.YellowCusionChairEntity;
import com.pixelutilitys.renderers.AronPokedollRenderer;
import com.pixelutilitys.renderers.BisharpPokedollRenderer;
import com.pixelutilitys.renderers.BlockRenderer;
import com.pixelutilitys.renderers.BolderRenderer;
import com.pixelutilitys.renderers.BoxRenderer;
import com.pixelutilitys.renderers.ClothedTableRenderer;
import com.pixelutilitys.renderers.PokeballRenderer;
import com.pixelutilitys.renderers.RedCusionChairRenderer;
import com.pixelutilitys.renderers.RenderRadioBlock;
import com.pixelutilitys.renderers.TotodilePokedollRenderer;
import com.pixelutilitys.renderers.TrashcanRenderer;
import com.pixelutilitys.renderers.TreeRenderer;
import com.pixelutilitys.renderers.YellowCusionChairRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;
//import PixelUtilitys.entitys.BasicDeskEntity;
//import PixelUtilitys.renderers.BasicDeskRenderer;

public class ClientProxy extends CommonProxy {
	public void initSounds() {
		// TODO Auto-generated method stub
	}


		
		
		//MinecraftForgeClient.registerItemRenderer(BlockInfo.ARCADE_ID, new RenderArcadeTechne());
    @Override
	public void registerRenderThings() {
     //   ClientRegistry.bindTileEntitySpecialRenderer(BasicDeskEntity.class, new BasicDeskRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEntity.class, new BlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TreeEntity.class, new TreeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(BolderEntity.class, new BolderRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(BoxEntity.class, new BoxRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(ClothedTableEntity.class, new ClothedTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(PokeballEntity.class, new PokeballRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(RedCusionChairEntity.class, new RedCusionChairRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TrashcanEntity.class, new TrashcanRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(YellowCusionChairEntity.class, new YellowCusionChairRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TotodilePokedollEntity.class, new TotodilePokedollRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(AronPokedollEntity.class, new AronPokedollRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(BisharpPokedollEntity.class, new BisharpPokedollRenderer());
		//ClientRegistry.bindTileEntitySpecialRenderer(WalrusTileEntity.class, new WalrusTileEntityRenderer());		
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadio.class, new RenderRadioBlock());
}
    
    @Override
	public void registerRenderers(){
		//MinecraftForgeClient.preloadTexture(RADIO_PNG);
		//MinecraftForgeClient.registerItemRenderer(Basemod.blockRadio.blockID, (IItemRenderer)new ItemRenderHandRadio());
	}
	
	@Override
	public void initTileEntities(){
		//System.out.println("registering renderer");
		//ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadio.class, new RenderRadioBlock());
	}
    }
