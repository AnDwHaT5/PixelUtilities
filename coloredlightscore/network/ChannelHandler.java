package coloredlightscore.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.util.EnumMap;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import coloredlightscore.server.ChunkStorageRGB;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChannelHandler extends FMLIndexedMessageToMessageCodec<IPacket> {

	public static ChannelHandler INSTANCE = null;
	
	public EnumMap<Side, FMLEmbeddedChannel> channels;
	public final String CHANNEL_NAME = "ColoredLightsCore";
	
	public ChannelHandler()
	{
		registerNetwork();
	}
	
	public void registerNetwork()
	{
		this.channels = NetworkRegistry.INSTANCE.newChannel(CHANNEL_NAME, this);
		
		this.addDiscriminator(ChunkColorDataPacket.PACKET_ID, ChunkColorDataPacket.class);
	}
		
	@Override
	public void encodeInto(ChannelHandlerContext ctx, IPacket msg, ByteBuf target) throws Exception {
		
		msg.writeBytes(target);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, IPacket msg) {
		
		msg.readBytes(source);
	
		ProcessColorDataPacket(msg);
	}

	public void SendChunkColorData(Chunk chunk, EntityPlayerMP player)
	{
		try
		{
			ChunkColorDataPacket packet = new ChunkColorDataPacket();
			NibbleArray[] redColorArray = ChunkStorageRGB.getRedColorArrays(chunk);
			NibbleArray[] greenColorArray = ChunkStorageRGB.getGreenColorArrays(chunk);
			NibbleArray[] blueColorArray = ChunkStorageRGB.getBlueColorArrays(chunk);
			
			if (redColorArray == null || greenColorArray == null || blueColorArray == null)
			{
				return;
			}
			
			packet.chunkXPosition = chunk.xPosition;
			packet.chunkZPosition = chunk.zPosition;
			packet.arraySize = redColorArray.length;
			packet.yLocation = ChunkStorageRGB.getYLocationArray(chunk);
			packet.RedColorArray = redColorArray;
			packet.GreenColorArray = greenColorArray;
			packet.BlueColorArray = blueColorArray;
						
			this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
			this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
			this.channels.get(Side.SERVER).writeOutbound(packet);		
	
			//FMLLog.info("SendChunkColorData()  Sent for %s, %s", chunk.xPosition, chunk.zPosition);
		}
		catch (Exception e)
		{
			FMLLog.getLogger().warn("SendChunkColorData()  ", e);
		}
	
	}
	
	@SideOnly(Side.CLIENT)
	private void ProcessColorDataPacket(IPacket packet)
	{
		ChunkColorDataPacket ccdPacket = (ChunkColorDataPacket)packet;
		Chunk targetChunk = null;
		
		targetChunk = Minecraft.getMinecraft().theWorld.getChunkFromChunkCoords(ccdPacket.chunkXPosition, ccdPacket.chunkZPosition);
				
		if (targetChunk != null)
		{
			ChunkStorageRGB.loadColorData(targetChunk, ccdPacket.arraySize, ccdPacket.yLocation, ccdPacket.RedColorArray, ccdPacket.GreenColorArray, ccdPacket.BlueColorArray);
			//FMLLog.info("ProcessColorDataPacket() loaded RGB for (%s,%s)", ccdPacket.chunkXPosition, ccdPacket.chunkZPosition);			
		}
		else
			FMLLog.warning("ProcessColorDataPacket()  Chunk located at (%s, %s) could not be found in the local world!", ccdPacket.chunkXPosition, ccdPacket.chunkZPosition);
	}
}
