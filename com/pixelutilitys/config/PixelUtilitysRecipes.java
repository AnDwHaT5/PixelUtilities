package com.pixelutilitys.config;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.pixelmonmod.pixelmon.config.PixelmonItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class PixelUtilitysRecipes {
	
	public static void addRecipes() {
		
		//Ruby Tools
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.rubyPickaxe, 1), new Object[] { "XXX", " Y ", " Y ", Character.valueOf('X'), PixelUtilitysItems.RubyItem, Character.valueOf('Y'),  Items.stick});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.rubyHammer, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelUtilitysItems.RubyItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.rubyAxe, 1), new Object[] { "XX ", "XY ", " Y ", Character.valueOf('X'), PixelUtilitysItems.RubyItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.rubyShovel, 1), new Object[] { " X ", " Y ", " Y ", Character.valueOf('X'), PixelUtilitysItems.RubyItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.rubyHoe, 1), new Object[] { "XX ", " Y ", " Y ", Character.valueOf('X'), PixelUtilitysItems.RubyItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.rubySword, 1), new Object[] { " X ", " X ", " Y ", Character.valueOf('X'), PixelUtilitysItems.RubyItem, Character.valueOf('Y'), Items.stick });
		
		//Saphire Tools
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.saphirePickaxe, 1), new Object[] { "XXX", " Y ", " Y ", Character.valueOf('X'), PixelUtilitysItems.SaphireItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.saphireHammer, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelUtilitysItems.SaphireItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.saphireAxe, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelUtilitysItems.SaphireItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.saphireShovel, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelUtilitysItems.SaphireItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.saphireHoe, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelUtilitysItems.SaphireItem, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.saphireSword, 1), new Object[] { " X ", " X ", " Y ", Character.valueOf('X'), PixelUtilitysItems.SaphireItem, Character.valueOf('Y'), Items.stick });
		
		//Amethyst Tools
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.amethystPickaxe, 1), new Object[] { "XXX", " Y ", " Y ", Character.valueOf('X'), PixelUtilitysBlocks.AmethystBlock, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.amethystHammer, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelUtilitysBlocks.AmethystBlock, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.amethystAxe, 1), new Object[] { "XX ", "XY ", " Y ", Character.valueOf('X'), PixelUtilitysBlocks.AmethystBlock, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.amethystShovel, 1), new Object[] { " X ", " Y ", " Y ", Character.valueOf('X'), PixelUtilitysBlocks.AmethystBlock, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.amethystHoe, 1), new Object[] { "XX ", " Y ", " Y ", Character.valueOf('X'), PixelUtilitysBlocks.AmethystBlock, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.amethystSword, 1), new Object[] { " X ", " X ", " Y ", Character.valueOf('X'), PixelUtilitysBlocks.AmethystBlock, Character.valueOf('Y'), Items.stick });
		
		//Evo Tools
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.FirestonePickaxe, 1), new Object[] { "XXX", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.fireStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.FirestoneHammer, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelmonItems.fireStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.FirestoneAxe, 1), new Object[] { "XX ", "XY ", " Y ", Character.valueOf('X'), PixelmonItems.fireStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.FirestoneShovel, 1), new Object[] { " X ", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.fireStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.FirestoneHoe, 1), new Object[] { "XX ", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.fireStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.FirestoneSword, 1), new Object[] { " X ", " X ", " Y ", Character.valueOf('X'), PixelmonItems.fireStone, Character.valueOf('Y'), Items.stick });
		
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.WaterstonePickaxe, 1), new Object[] { "XXX", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.waterStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.WaterstoneHammer, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelmonItems.waterStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.WaterstoneAxe, 1), new Object[] { "XX ", "XY ", " Y ", Character.valueOf('X'), PixelmonItems.waterStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.WaterstoneShovel, 1), new Object[] { " X ", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.waterStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.WaterstoneHoe, 1), new Object[] { "XX ", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.waterStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.WaterstoneSword, 1), new Object[] { " X ", " X ", " Y ", Character.valueOf('X'), PixelmonItems.waterStone, Character.valueOf('Y'), Items.stick });
		
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.LeafstonePickaxe, 1), new Object[] { "XXX", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.leafStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.LeafstoneHammer, 1), new Object[] { "XXX", "XYX", " Y ", Character.valueOf('X'), PixelmonItems.leafStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.LeafstoneAxe, 1), new Object[] { "XX ", "XY ", " Y ", Character.valueOf('X'), PixelmonItems.leafStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.LeafstoneShovel, 1), new Object[] { " X ", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.leafStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.LeafstoneHoe, 1), new Object[] { "XX ", " Y ", " Y ", Character.valueOf('X'), PixelmonItems.leafStone, Character.valueOf('Y'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.LeafstoneSword, 1), new Object[] { " X ", " X ", " Y ", Character.valueOf('X'), PixelmonItems.leafStone, Character.valueOf('Y'), Items.stick });
		
		
	//Armor
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.rubyHelm, 1), new Object[] { "XXX", "X X", "   ", Character.valueOf('X'), PixelUtilitysItems.RubyItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.rubyPlate, 1), new Object[] { "X X", "XXX", "XXX", Character.valueOf('X'), PixelUtilitysItems.RubyItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.rubyLegs, 1), new Object[] { "XXX", "X X", "X X", Character.valueOf('X'), PixelUtilitysItems.RubyItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.rubyBoots, 1), new Object[] { "X X", "X X", Character.valueOf('X'), PixelUtilitysItems.RubyItem});

		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.saphireHelm, 1), new Object[] { "XXX", "X X", "   ", Character.valueOf('X'), PixelUtilitysItems.SaphireItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.saphirePlate, 1), new Object[] { "X X", "XXX", "XXX", Character.valueOf('X'), PixelUtilitysItems.SaphireItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.saphireLegs, 1), new Object[] { "XXX", "X X", "X X", Character.valueOf('X'), PixelUtilitysItems.SaphireItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.saphireBoots, 1), new Object[] { "X X", "X X", Character.valueOf('X'), PixelUtilitysItems.SaphireItem});
		
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.galacticHelm, 1), new Object[] { "XXX", "X X", "   ", Character.valueOf('X'), PixelUtilitysItems.SiliconItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.galacticPlate, 1), new Object[] { "X X", "XXX", "XXX", Character.valueOf('X'), PixelUtilitysItems.SiliconItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.galacticLegs, 1), new Object[] { "XXX", "X X", "X X", Character.valueOf('X'), PixelUtilitysItems.SiliconItem});
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.galacticBoots, 1), new Object[] { "X X", "X X", Character.valueOf('X'), PixelUtilitysItems.SiliconItem});

	//Radio
		GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.RadioBlock), "  y", "xyx", "xzx", 'x', new ItemStack(Blocks.planks), 'y', new ItemStack(Items.iron_ingot), 'z', new ItemStack(Items.diamond));

	}
	
}
