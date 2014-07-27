package com.pixelutilitys.networking;

import com.pixelutilitys.Basemod;
import com.pixelutilitys.networking.networkMessages.MessageTileEntityRadio;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Basemod.MODID);

    public static void init() {
        INSTANCE.registerMessage(MessageTileEntityRadio.Handler.class, MessageTileEntityRadio.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityRadio.Handler.class, MessageTileEntityRadio.class, 0, Side.SERVER);
    }
}
