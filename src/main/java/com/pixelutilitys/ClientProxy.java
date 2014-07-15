package com.pixelutilitys;

//import com.net.trigore.radioblock.RenderRadioBlock;
//import com.net.trigore.radioblock.TileEntityRadio;

import com.pixelutilitys.entitys.SeatEntity;
import com.pixelutilitys.renderers.*;
import com.pixelutilitys.tileentitys.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
//import PixelUtilitys.entitys.BasicDeskEntity;
//import PixelUtilitys.renderers.BasicDeskRenderer;

public class ClientProxy extends CommonProxy {

    public void initSounds() {
    }

    @Override
    public void registerRenderThings() {
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEntity.class, new BlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TreeEntity.class, new TreeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(BolderEntity.class, new BolderRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(BoxEntity.class, new BoxRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(ClothedTableEntity.class, new ClothedTableRenderer());
        if (Basemod.pixelmonPresent)
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
    public void registerRenderers() {
        //MinecraftForgeClient.preloadTexture(RADIO_PNG);
        //MinecraftForgeClient.registerItemRenderer(Basemod.blockRadio.blockID, (IItemRenderer)new ItemRenderHandRadio());
    }

    @Override
    public void initTileEntities() {
        //System.out.println("registering renderer");
        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadio.class, new RenderRadioBlock());
    }
}
