/*package PixelUtilitys.config;

import net.minecraftforge.common.Configuration;
import pixelmon.entities.pixelmon.EntityPixelmon;
public class PixelUtilitysPokeKit {


	public static String pkit11;
	public static String pkit12;
	public static String pkit13;
	public static String pkit14;
	public static String pkit15;
	public static String pkit16;
	
	public static String pkit21;
	public static String pkit22;
	public static String pkit23;
	public static String pkit24;
	public static String pkit25;
	public static String pkit26;
	
	public static String pkit31;
	public static String pkit32;
	public static String pkit33;
	public static String pkit34;
	public static String pkit35;
	public static String pkit36;
	
	public static String pkit41;
	public static String pkit42;
	public static String pkit43;
	public static String pkit44;
	public static String pkit45;
	public static String pkit46;
	
	public static String pkit51;
	public static String pkit52;
	public static String pkit53;
	public static String pkit54;
	public static String pkit55;
	public static String pkit56;
	
	public static String pkit61;
	public static String pkit62;
	public static String pkit63;
	public static String pkit64;
	public static String pkit65;
	public static String pkit66;
	
	public static String pkit71;
	public static String pkit72;
	public static String pkit73;
	public static String pkit74;
	public static String pkit75;
	public static String pkit76;
	
	public static String pkit81;
	public static String pkit82;
	public static String pkit83;
	public static String pkit84;
	public static String pkit85;
	public static String pkit86;
	
	public static String pkit91;
	public static String pkit92;
	public static String pkit93;
	public static String pkit94;
	public static String pkit95;
	public static String pkit96;
	
	public static String pkit101;
	public static String pkit102;
	public static String pkit103;
	public static String pkit104;
	public static String pkit105;
	public static String pkit106;
public static Configuration config;
	public static void load(Configuration cfg){
		config = cfg;
		
		
		pkit11 = cfg.get("Pokekit", "Kit1 Pokemon11", "null").getString();
		pkit12 = cfg.get("Pokekit", "Kit1 Pokemon12" , "null").getString();
		pkit13 = cfg.get("Pokekit", "Kit1 Pokemon13", "null").getString();
		pkit14 = cfg.get("Pokekit", "Kit1 Pokemon14", "null").getString();
		pkit15 = cfg.get("Pokekit", "Kit1 Pokemon15", "null").getString();
		pkit16 = cfg.get("Pokekit", "Kit1 Pokemon16", "null").getString();
		
		pkit21 = cfg.get("Pokekit", "Kit2 Pokemon1", "null").getString();
		pkit22 = cfg.get("Pokekit", "Kit2 Pokemon2", "null").getString();
		pkit23 = cfg.get("Pokekit", "Kit2 Pokemon3", "null").getString();
		pkit24 = cfg.get("Pokekit", "Kit2 Pokemon4", "null").getString();
		pkit25 = cfg.get("Pokekit", "Kit2 Pokemon5", "null").getString();
		pkit26 = cfg.get("Pokekit", "Kit2 Pokemon6", "null").getString();
		
		pkit31 = cfg.get("Pokekit", "Kit3 Pokemon1", "null").getString();
		pkit32 = cfg.get("Pokekit", "Kit3 Pokemon2", "null").getString();
		pkit33 = cfg.get("Pokekit", "Kit3 Pokemon3", "null").getString();
		pkit34 = cfg.get("Pokekit", "Kit3 Pokemon4", "null").getString();
		pkit35 = cfg.get("Pokekit", "Kit3 Pokemon5", "null").getString();
		pkit36 = cfg.get("Pokekit", "Kit3 Pokemon6", "null").getString();
		
		pkit41 = cfg.get("Pokekit", "Kit4 Pokemon1", "null").getString();
		pkit42 = cfg.get("Pokekit", "Kit4 Pokemon2", "null").getString();
		pkit43 = cfg.get("Pokekit", "Kit4 Pokemon3", "null").getString();
		pkit44 = cfg.get("Pokekit", "Kit4 Pokemon4", "null").getString();
		pkit45 = cfg.get("Pokekit", "Kit4 Pokemon5", "null").getString();
		pkit46 = cfg.get("Pokekit", "Kit4 Pokemon6", "null").getString();
	
		pkit51 = cfg.get("Pokekit", "Kit5 Pokemon1", "null").getString();
		pkit52 = cfg.get("Pokekit", "Kit5 Pokemon2", "null").getString();
		pkit53 = cfg.get("Pokekit", "Kit5 Pokemon3", "null").getString();
		pkit54 = cfg.get("Pokekit", "Kit5 Pokemon4", "null").getString();
		pkit55 = cfg.get("Pokekit", "Kit5 Pokemon5", "null").getString();
		pkit56 = cfg.get("Pokekit", "Kit5 Pokemon6", "null").getString();
		
		pkit61 = cfg.get("Pokekit", "Kit6 Pokemon1", "null").getString();
		pkit62 = cfg.get("Pokekit", "Kit6 Pokemon2", "null").getString();
		pkit63 = cfg.get("Pokekit", "Kit6 Pokemon3", "null").getString();
		pkit64 = cfg.get("Pokekit", "Kit6 Pokemon4", "null").getString();
		pkit65 = cfg.get("Pokekit", "Kit6 Pokemon5", "null").getString();
		pkit66 = cfg.get("Pokekit", "Kit6 Pokemon6", "null").getString();
		
		pkit71 = cfg.get("Pokekit", "Kit7 Pokemon1", "null").getString();
		pkit72 = cfg.get("Pokekit", "Kit7 Pokemon2", "null").getString();
		pkit73 = cfg.get("Pokekit", "Kit7 Pokemon3", "null").getString();
		pkit74 = cfg.get("Pokekit", "Kit7 Pokemon4", "null").getString();
		pkit75 = cfg.get("Pokekit", "Kit7 Pokemon5", "null").getString();
		pkit76 = cfg.get("Pokekit", "Kit7 Pokemon6", "null").getString();
		
		pkit81 = cfg.get("Pokekit", "Kit8 Pokemon1", "null").getString();
		pkit82 = cfg.get("Pokekit", "Kit8 Pokemon2", "null").getString();
		pkit83 = cfg.get("Pokekit", "Kit8 Pokemon3", "null").getString();
		pkit84 = cfg.get("Pokekit", "Kit8 Pokemon4", "null").getString();
		pkit85 = cfg.get("Pokekit", "Kit8 Pokemon5", "null").getString();
		pkit86 = cfg.get("Pokekit", "Kit8 Pokemon6", "null").getString();
		
		pkit91 = cfg.get("Pokekit", "Kit9 Pokemon1", "null").getString();
		pkit92 = cfg.get("Pokekit", "Kit9 Pokemon2", "null").getString();
		pkit93 = cfg.get("Pokekit", "Kit9 Pokemon3", "null").getString();
		pkit94 = cfg.get("Pokekit", "Kit9 Pokemon4", "null").getString();
		pkit95 = cfg.get("Pokekit", "Kit9 Pokemon5", "null").getString();
		pkit96 = cfg.get("Pokekit", "Kit9 Pokemon6", "null").getString();
		
		pkit101 = cfg.get("Pokekit", "Kit10 Pokemon1", "null").getString();
		pkit102 = cfg.get("Pokekit", "Kit10 Pokemon2", "null").getString();
		pkit103 = cfg.get("Pokekit", "Kit10 Pokemon3", "null").getString();
		pkit104 = cfg.get("Pokekit", "Kit10 Pokemon4", "null").getString();
		pkit105 = cfg.get("Pokekit", "Kit10 Pokemon5", "null").getString();
		pkit106 = cfg.get("Pokekit", "Kit10 Pokemon6", "null").getString();
	}
	
}
*/