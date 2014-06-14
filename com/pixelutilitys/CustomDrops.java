package com.pixelutilitys;

import java.util.Random;

import net.minecraftforge.event.entity.living.LivingDropsEvent;


import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelutilitys.config.PixelUtilitysConfig;
import com.pixelutilitys.config.PixelUtilitysItems;

public class CustomDrops {
	
	public static Random random;
	public static int dropped;
	public static int doDrop;
	
	@Deprecated
	public void pixelmonDrop(LivingDropsEvent e){
		if(!PixelUtilitysConfig.coinDrops){
			return;
		}
		else if(e.entityLiving instanceof EntityPixelmon) {
			EntityPixelmon p = (EntityPixelmon) e.entityLiving;
			if(p.hasOwner()){
				return;
			}
			random = new Random();
			doDrop = (int) (Math.random()*(PixelUtilitysConfig.coinDropRate * 25));
			if(doDrop < 25 && doDrop != 10) {
				dropped = random.nextInt(2) + 1;
				p.dropItem(PixelUtilitysItems.PokeCoin1Item, dropped);
			}
			if(doDrop == 10){
				e.entityLiving.dropItem(PixelUtilitysItems.PokeCoin10Item, 1);
			}
		}
	}
}