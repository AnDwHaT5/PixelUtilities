package com.net.trigore.radioblock;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.net.trigore.radioblock.player.VLCPlayer;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "TriRadio", name = "Triradio ", version = "0.1")
//@Network(channels = {"RadioBlock"}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class, connectionHandler = ConnectionHandler.class)
public class ModRadioBlock {
	
	@Instance
	public static ModRadioBlock instance;
	
	public static FMLEventChannel channel;
	
	
	@SidedProxy(clientSide = "com.net.trigore.radioblock.ClientProxy", serverSide="com.net.trigore.radioblock.CommonProxy")
	public static CommonProxy proxy;
	
	public final static BlockRadio blockRadio = new BlockRadio(189, Material.wood);
	
	public static List<VLCPlayer> playerList = new ArrayList<VLCPlayer>();
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		AddMeta(event, "0.1");
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent evt){
		System.out.println("radio init");
		
		PacketHandler.init();
		
		GameRegistry.registerBlock(blockRadio, "Radio");
		GameRegistry.registerTileEntity(TileEntityRadio.class, "Radio");
		GameRegistry.addRecipe(new ItemStack(blockRadio), "  y", "xyx", "xzx", 'x', new ItemStack(Blocks.planks), 'y', new ItemStack(Items.iron_ingot), 'z', new ItemStack(Items.diamond));
		proxy.initTileEntities();
		
        NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC"
            );
        try{
        	Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        }catch(UnsatisfiedLinkError error)
        {
        	System.out.println("You need to install VLC for this mod");
        }
		
	}
	
	public void serverStop(FMLServerStoppedEvent event) {
		killAllStreams();
	}
	
	public static void killAllStreams(){
		for(VLCPlayer p : playerList){
			p.stop();
		}
	}
	
	 @Mod.EventHandler
	  public void load(FMLInitializationEvent event)
	  {
		 LanguageRegistry.addName(blockRadio, "Radio");
	  }

	 
	/**
	 * Add Pack Meta Data
	 */
	
	private void AddMeta(FMLPreInitializationEvent event, String version) {

		ModMetadata m = event.getModMetadata(); // This is required or it will not work
		m.autogenerated = false; // This is required otherwise it will not work
		m.modId = "TriRadio";
		m.version = version;
		m.name = "Triradio";
		m.url = "";
		m.updateUrl = "";
		m.description = "PixelUtilitys radio";
		m.authorList.add("Trigore");

	}
	
}
