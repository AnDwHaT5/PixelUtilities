package com.net.trigore.radioblock;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

import com.net.trigore.radioblock.player.VLCPlayer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRadio extends TileEntity{
	
	private VLCPlayer player = null;
	private boolean isPlaying = false;
	public String streamURL = "";
	
	public Block getBlockType(){
		return ModRadioBlock.blockRadio;
	}
	
	@SideOnly(Side.CLIENT)
	public void startStream(){
		System.out.println("start Stream "+streamURL);
		
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		
		System.out.println(side.name());
		
		if(!isPlaying){
			isPlaying = true;
			
			if(side == Side.CLIENT){
				System.out.println("making new player");
				player = new VLCPlayer(streamURL);
				System.out.println("player working? "+player.isPlaying());
				ModRadioBlock.playerList.add(player);
			}
		}else{
			System.err.println("Tried to play a stream twice out of one radio!");
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void stopStream(){
		System.out.println("stopping stream");
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(isPlaying){
			if(side == Side.CLIENT){
				System.out.println("is stoped player playing "+player.isPlaying());
				player.stop();
				ModRadioBlock.playerList.remove(player);
			}
			isPlaying = false;//player.isPlaying();
		}else{
			System.err.println("Tried to stop a nonplaying radio!");
		}
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void invalidate(){
		//TODO: RAdio packets
		//Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(ModRadioBlock.setPacket(xCoord, yCoord, zCoord, streamURL, !isPlaying()));
		super.invalidate();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void onChunkUnload(){
		if(isPlaying){
			stopStream();
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateEntity() {
		if(Minecraft.getMinecraft().thePlayer != null && player != null && !isInvalid()){
			float vol = (float)getDistanceFrom(Minecraft.getMinecraft().thePlayer.posX,Minecraft.getMinecraft().thePlayer.posY,Minecraft.getMinecraft().thePlayer.posZ);
			if(vol > 10000){
				player.setVolume(0);
			}else{
				float v2 = (10000f / vol) / 100f;
				if(v2 > 1){
					player.setVolume(1);
				}else{
					player.setVolume(v2);
				}
			}
			//System.out.println("streamurl: \""+streamURL+"\"");
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readFromNBT(par1NBTTagCompound);
		streamURL = par1NBTTagCompound.getString("streamurl");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setString("streamurl", streamURL);
	}
	
	@Override
	public Packet getDescriptionPacket(){
		//TODO: RAdio packets
		//return ModRadioBlock.setPacket(xCoord, yCoord, zCoord, streamURL, isPlaying);
		return null;
	}
}