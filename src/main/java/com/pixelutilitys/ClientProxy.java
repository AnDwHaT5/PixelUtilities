package com.pixelutilitys;

//import com.net.trigore.radioblock.RenderRadioBlock;
//import com.net.trigore.radioblock.TileEntityRadio;
import com.pixelutilitys.entitys.SeatEntity;
import com.pixelutilitys.renderers.AronPokedollRenderer;
import com.pixelutilitys.renderers.BisharpPokedollRenderer;
import com.pixelutilitys.renderers.BlockRenderer;
import com.pixelutilitys.renderers.BlueRugRenderer;
import com.pixelutilitys.renderers.BolderRenderer;
import com.pixelutilitys.renderers.BoxRenderer;
import com.pixelutilitys.renderers.ClothedTableRenderer;
import com.pixelutilitys.renderers.ConveyorRenderer;
import com.pixelutilitys.renderers.GreenRugRenderer;
import com.pixelutilitys.renderers.GymSignRenderer;
import com.pixelutilitys.renderers.InvisibleRenderer;
import com.pixelutilitys.renderers.PokeballRenderer;
import com.pixelutilitys.renderers.PokeballStatueTileEntityRenderer;
import com.pixelutilitys.renderers.RadioBlockRenderer;
import com.pixelutilitys.renderers.RedCusionChairRenderer;
import com.pixelutilitys.renderers.RedRugRenderer;
import com.pixelutilitys.renderers.TVRenderer;
import com.pixelutilitys.renderers.TotodilePokedollRenderer;
import com.pixelutilitys.renderers.TrashcanRenderer;
import com.pixelutilitys.renderers.TreeRenderer;
import com.pixelutilitys.renderers.YellowCusionChairRenderer;
import com.pixelutilitys.tileentitys.AronPokedollEntity;
import com.pixelutilitys.tileentitys.BisharpPokedollEntity;
import com.pixelutilitys.tileentitys.BlockEntity;
import com.pixelutilitys.tileentitys.BlueRugEntity;
import com.pixelutilitys.tileentitys.BolderEntity;
import com.pixelutilitys.tileentitys.BoxEntity;
import com.pixelutilitys.tileentitys.ClothedTableEntity;
import com.pixelutilitys.tileentitys.GreenRugEntity;
import com.pixelutilitys.tileentitys.GymSignEntity;
import com.pixelutilitys.tileentitys.PokeballEntity;
import com.pixelutilitys.tileentitys.PokeballStatueTileEntity;
import com.pixelutilitys.tileentitys.RedCusionChairEntity;
import com.pixelutilitys.tileentitys.RedRugEntity;
import com.pixelutilitys.tileentitys.TVEntity;
import com.pixelutilitys.tileentitys.TileEntityRadio;
import com.pixelutilitys.tileentitys.TotodilePokedollEntity;
import com.pixelutilitys.tileentitys.TrashcanEntity;
import com.pixelutilitys.tileentitys.TreeEntity;
import com.pixelutilitys.tileentitys.YellowCusionChairEntity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
//import PixelUtilitys.entitys.BasicDeskEntity;
//import PixelUtilitys.renderers.BasicDeskRenderer;

public class ClientProxy extends CommonProxy {
	public void initSounds() {

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
		ClientRegistry.bindTileEntitySpecialRenderer(PokeballStatueTileEntity.class, new PokeballStatueTileEntityRenderer());		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadio.class, new RadioBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(GymSignEntity.class, new GymSignRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TVEntity.class, new TVRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(BlueRugEntity.class, new BlueRugRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(RedRugEntity.class, new RedRugRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(GreenRugEntity.class, new GreenRugRenderer());

		RenderingRegistry.registerEntityRenderingHandler(SeatEntity.class, new InvisibleRenderer());
		RenderingRegistry.registerBlockHandler(500, new ConveyorRenderer());
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