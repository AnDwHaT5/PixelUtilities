/*package com.net.trigore.radioblock;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

import com.javazoom.jl.player.Player;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
@Deprecated
public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		//System.out.println("Got packet250");
		if(packet.channel.equals("RadioBlock")){
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (side == Side.SERVER){
				PacketDispatcher.sendPacketToAllPlayers(packet);
			}
			DataInputStream is = new DataInputStream(new ByteArrayInputStream(packet.data));
			try{
				int type = is.readInt();
				int x = is.readInt();
				int y = is.readInt();
				int z = is.readInt();
				TileEntity te = null;
				if(side == Side.SERVER){
					EntityPlayerMP p = (EntityPlayerMP)player;
					te = MinecraftServer.getServer().worldServerForDimension(p.dimension).getTileEntity(x, y, z);
				}
				if(side == Side.CLIENT){
					te = Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
				}
				if(te instanceof TileEntityRadio){
					TileEntityRadio r = (TileEntityRadio)te;
					String surl = is.readUTF();
					r.streamURL = surl;
					boolean playing = is.readBoolean();
					if(playing && !r.isPlaying()){
						r.startStream();
					}else{
						r.stopStream();
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
*/