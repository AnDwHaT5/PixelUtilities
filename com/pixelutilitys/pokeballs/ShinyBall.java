package com.pixelutilitys.pokeballs;

import java.lang.reflect.InvocationTargetException;

import com.pixelmonmod.pixelmon.api.PixelmonApi;
import com.pixelmonmod.pixelmon.api.ReflectionUtil;
import com.pixelmonmod.pixelmon.enums.EnumApricorns;
import com.pixelmonmod.pixelmon.enums.EnumPokeballs;

public class ShinyBall extends PixelmonApi{
	
	public ShinyBall(){
		EnumApricorns apricorns[] = {EnumApricorns.Yellow,  EnumApricorns.Yellow, EnumApricorns.Yellow};
		registerPokeball("ShinyBall", 20, 2, "pixelutilitys:shinyball", "shinyball", apricorns, 1, 2);
		Class[] fields = { String.class, Integer.TYPE, Integer.TYPE, Double.TYPE, String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE };
		EnumPokeballs newBall;
		try {
			newBall = (EnumPokeballs) ReflectionUtil.invokeEnumConstructor(EnumPokeballs.class, fields,
					new Object[] { "ShinyBall", 20, 21, 3, "ShinyBall", 0, 0, 0, 0, 0, 0/*never used: iconIndexX, iconIndexY, lidIconIndexX, lidIconIndexY, discIconIndexX,
					discIconIndexY*/, apricorns, 1, 2});
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
}
