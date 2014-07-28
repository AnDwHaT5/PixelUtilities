package com.pixelutilitys.events;

import com.pixelmonmod.pixelmon.api.events.EventType;
import com.pixelmonmod.pixelmon.api.events.IPixelmonEventHandler;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelutilitys.config.PixelUtilitysConfig;
import com.pixelutilitys.config.PixelUtilitysItems;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.Random;

@Optional.Interface(iface = "com.pixelmonmod.pixelmon.api.events.IPixelmonEventHandler", modid = "pixelmon")
public class CustomDrops implements IPixelmonEventHandler {

    public static Random random = new Random();
    private PixelUtilitysConfig pixelConfig = PixelUtilitysConfig.getInstance();

    @Optional.Method(modid = "pixelmon")
    @Override
    public void eventFired(EventType eventType, EntityPlayer entityPlayer, Object... objects) {
        if (!pixelConfig.coinDrops) {
            return;
        }

        if(eventType == EventType.BeatWildPokemon) {
            int doDrop = (int) (Math.random() * (pixelConfig.coinDropRate * 25));
            if(doDrop < 25 && doDrop != 10) {
                int amount = random.nextInt(2) + 1;
                entityPlayer.inventory.addItemStackToInventory(new ItemStack(PixelUtilitysItems.PokeCoin1Item, amount));
            }
            if(doDrop == 10) {
                entityPlayer.inventory.addItemStackToInventory(new ItemStack(PixelUtilitysItems.PokeCoin10Item, 1));
            }
        }
    }
}