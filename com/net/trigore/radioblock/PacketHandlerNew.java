package com.net.trigore.radioblock;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandlerNew {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("haxyradio");
	
	public static void init()
	{
		INSTANCE.registerMessage(MessageTileEntityRadio.class, MessageTileEntityRadio.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageTileEntityRadio.class, MessageTileEntityRadio.class, 0, Side.SERVER);
	}
}
