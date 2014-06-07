package com.net.trigore.radioblock;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.FMLCommonHandler;

public class MessageTileEntityRadio implements IMessage, IMessageHandler<MessageTileEntityRadio, IMessage> {

	public int x,y,z;
	public String url = "http://haxyshideout.co.uk/songs/desu.mp3";
	
	public MessageTileEntityRadio()
	{
	}
	
	public MessageTileEntityRadio(int x, int y, int z, String url)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.url = url;
	}
	
	public MessageTileEntityRadio(TileEntityRadio radio)
	{
		this.x = radio.xCoord;
		this.y = radio.yCoord;
		this.z = radio.zCoord;
		this.url = radio.streamURL;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		System.out.println("fromBytes");
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		int streamLength = buf.readInt();
		if(streamLength > 0)
			this.url = new String(buf.readBytes(streamLength).array());
		//System.out.println("read "+this.x+" "+this.y+" "+this.z+" "+streamLength);
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		System.out.println("toBytes");
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		
		int urlLength = this.url.length();
		buf.writeInt(urlLength);
		if(urlLength > 0)
			buf.writeBytes(this.url.getBytes());
		
		//System.out.println("wrote "+this.x+" "+this.y+" "+this.z+" "+urlLength+" "+this.url);
	}
	
	@Override
	public IMessage onMessage(MessageTileEntityRadio message, MessageContext context)
	{
		
		System.out.println("onMessage packet side "+context.side.toString());
		System.out.println("onMessage "+message.x+" "+message.y+" "+message.z);
		
		if (context.side == Side.SERVER){
			System.out.println("Resending to everyone");
			PacketHandlerNew.INSTANCE.sendToAll(message);
		}
		System.out.println("sent to everyone");
		
			TileEntity te = null;
			if(context.side == Side.SERVER){
				EntityPlayerMP p = context.getServerHandler().playerEntity;
				te = MinecraftServer.getServer().worldServerForDimension(p.dimension).getTileEntity(message.x, message.y, message.z);
			}
			if(context.side == Side.CLIENT) {
				te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
			}
			
			
			if(te == null)
			{
				System.out.println("te is null D:");
				System.out.println("te should be at "+message.x+" "+message.y+" "+message.z);
			}
			
			
			if(te instanceof TileEntityRadio){
				System.out.println("we got a radio :O");
				TileEntityRadio r = (TileEntityRadio)te;
				r.streamURL = message.url;
				
				if(context.side == Side.CLIENT)
				if(!r.isPlaying()){
					r.startStream();
				}else{
					r.stopStream();
				}
			}
			return null;
	
	
	}
	
	@Override
	public String toString()
	{
		return String.format("MessageTileEntityRadio - x:%s, y:%s, z:%s, url:%s", this.x, this.y, this.z, this.url);
	}

	
	
}
