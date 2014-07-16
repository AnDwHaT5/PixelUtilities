package com.pixelutilitys.config;

import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelutilitys.Basemod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PixelUtilitysRecipes {


    public void addRecipes() {

        //Ore Smelting
        GameRegistry.addSmelting(PixelUtilitysBlocks.AmethystOre, new ItemStack(PixelUtilitysItems.AmethystItem), 10);
        GameRegistry.addSmelting(PixelUtilitysBlocks.CrystalOre, new ItemStack(PixelUtilitysItems.CrystalItem), 10);
        GameRegistry.addSmelting(PixelUtilitysBlocks.RubyOre, new ItemStack(PixelUtilitysItems.RubyItem), 10);
        GameRegistry.addSmelting(PixelUtilitysBlocks.SaphireOre, new ItemStack(PixelUtilitysItems.SaphireItem), 10);
        GameRegistry.addSmelting(PixelUtilitysBlocks.SiliconOre, new ItemStack(PixelUtilitysItems.SiliconItem), 10);
        //We need to add a stupid af smelting method here for a easter egg. What? Not sure.

        //Block
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.RubyBlock, 1), new Object[]{"XXX", "XXX", "XXX", 'X', PixelUtilitysItems.RubyItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.SaphireBlock, 1), new Object[]{"XXX", "XXX", "XXX", 'X', PixelUtilitysItems.SaphireItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.AmethystBlock, 1), new Object[]{"XXX", "XXX", "XXX", 'X', PixelUtilitysItems.AmethystItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.CrystalBlock, 1), new Object[]{"XXX", "XXX", "XXX", 'X', PixelUtilitysItems.CrystalItem});


        GameRegistry.addRecipe(new ItemStack(PixelUtilitysItems.RubyItem, 9), new Object[]{"   ", " X ", "   ", 'X', PixelUtilitysBlocks.RubyBlock});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysItems.SaphireItem, 9), new Object[]{"   ", " X ", "   ", 'X', PixelUtilitysBlocks.SaphireBlock});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysItems.AmethystItem, 9), new Object[]{"   ", " X ", "   ", 'X', PixelUtilitysBlocks.AmethystBlock});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysItems.CrystalItem, 9), new Object[]{"   ", " X ", "   ", 'X', PixelUtilitysBlocks.CrystalBlock});


        //Ruby Tools
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().rubyPickaxe, 1), new Object[]{"XXX", " Y ", " Y ", 'X', PixelUtilitysItems.RubyItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().rubyHammer, 1), new Object[]{"XXX", "XYX", " Y ", 'X', PixelUtilitysItems.RubyItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().rubyAxe, 1), new Object[]{"XX ", "XY ", " Y ", 'X', PixelUtilitysItems.RubyItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().rubyShovel, 1), new Object[]{" X ", " Y ", " Y ", 'X', PixelUtilitysItems.RubyItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().rubyHoe, 1), new Object[]{"XX ", " Y ", " Y ", 'X', PixelUtilitysItems.RubyItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().rubySword, 1), new Object[]{" X ", " X ", " Y ", 'X', PixelUtilitysItems.RubyItem, 'Y', Items.stick});

        //Saphire Tools
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().saphirePickaxe, 1), new Object[]{"XXX", " Y ", " Y ", 'X', PixelUtilitysItems.SaphireItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().saphireHammer, 1), new Object[]{"XXX", "XYX", " Y ", 'X', PixelUtilitysItems.SaphireItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().saphireAxe, 1), new Object[]{"XX ", "XY ", " Y ", 'X', PixelUtilitysItems.SaphireItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().saphireShovel, 1), new Object[]{" X ", " Y ", " Y ", 'X', PixelUtilitysItems.SaphireItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().saphireHoe, 1), new Object[]{"XX ", " Y ", " Y ", 'X', PixelUtilitysItems.SaphireItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().saphireSword, 1), new Object[]{" X ", " X ", " Y ", 'X', PixelUtilitysItems.SaphireItem, 'Y', Items.stick});

        //Amethyst Tools
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().amethystPickaxe, 1), new Object[]{"XXX", " Y ", " Y ", 'X', PixelUtilitysItems.AmethystItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().amethystHammer, 1), new Object[]{"XXX", "XYX", " Y ", 'X', PixelUtilitysItems.AmethystItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().amethystAxe, 1), new Object[]{"XX ", "XY ", " Y ", 'X', PixelUtilitysItems.AmethystItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().amethystShovel, 1), new Object[]{" X ", " Y ", " Y ", 'X', PixelUtilitysItems.AmethystItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().amethystHoe, 1), new Object[]{"XX ", " Y ", " Y ", 'X', PixelUtilitysItems.AmethystItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().amethystSword, 1), new Object[]{" X ", " X ", " Y ", 'X', PixelUtilitysItems.AmethystItem, 'Y', Items.stick});

        //Evo Tools
        if (Basemod.pixelmonPresent) {
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().FirestonePickaxe, 1), new Object[]{"XXX", " Y ", " Y ", 'X', PixelmonItems.fireStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().FirestoneHammer, 1), new Object[]{"XXX", "XYX", " Y ", 'X', PixelmonItems.fireStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().FirestoneAxe, 1), new Object[]{"XX ", "XY ", " Y ", 'X', PixelmonItems.fireStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().FirestoneShovel, 1), new Object[]{" X ", " Y ", " Y ", 'X', PixelmonItems.fireStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().FirestoneHoe, 1), new Object[]{"XX ", " Y ", " Y ", 'X', PixelmonItems.fireStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().FirestoneSword, 1), new Object[]{" X ", " X ", " Y ", 'X', PixelmonItems.fireStone, 'Y', Items.stick});

            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().WaterstonePickaxe, 1), new Object[]{"XXX", " Y ", " Y ", 'X', PixelmonItems.waterStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().WaterstoneHammer, 1), new Object[]{"XXX", "XYX", " Y ", 'X', PixelmonItems.waterStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().WaterstoneAxe, 1), new Object[]{"XX ", "XY ", " Y ", 'X', PixelmonItems.waterStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().WaterstoneShovel, 1), new Object[]{" X ", " Y ", " Y ", 'X', PixelmonItems.waterStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().WaterstoneHoe, 1), new Object[]{"XX ", " Y ", " Y ", 'X', PixelmonItems.waterStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().WaterstoneSword, 1), new Object[]{" X ", " X ", " Y ", 'X', PixelmonItems.waterStone, 'Y', Items.stick});

            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().LeafstonePickaxe, 1), new Object[]{"XXX", " Y ", " Y ", 'X', PixelmonItems.leafStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().LeafstoneHammer, 1), new Object[]{"XXX", "XYX", " Y ", 'X', PixelmonItems.leafStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().LeafstoneAxe, 1), new Object[]{"XX ", "XY ", " Y ", 'X', PixelmonItems.leafStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().LeafstoneShovel, 1), new Object[]{" X ", " Y ", " Y ", 'X', PixelmonItems.leafStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().LeafstoneHoe, 1), new Object[]{"XX ", " Y ", " Y ", 'X', PixelmonItems.leafStone, 'Y', Items.stick});
            GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().LeafstoneSword, 1), new Object[]{" X ", " X ", " Y ", 'X', PixelmonItems.leafStone, 'Y', Items.stick});
        }//TODO alternative recipies!

        //Crystal Tools
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().CrystalPickaxe, 1), new Object[]{"XXX", " Y ", " Y ", 'X', PixelUtilitysItems.CrystalItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().CrystalHammer, 1), new Object[]{"XXX", "XYX", " Y ", 'X', PixelUtilitysItems.CrystalItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().CrystalAxe, 1), new Object[]{"XX ", "XY ", " Y ", 'X', PixelUtilitysItems.CrystalItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().CrystalShovel, 1), new Object[]{" X ", " Y ", " Y ", 'X', PixelUtilitysItems.CrystalItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().CrystalHoe, 1), new Object[]{"XX ", " Y ", " Y ", 'X', PixelUtilitysItems.CrystalItem, 'Y', Items.stick});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysTools.getInstance().CrystalSword, 1), new Object[]{" X ", " X ", " Y ", 'X', PixelUtilitysItems.CrystalItem, 'Y', Items.stick});

        //Armor
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.rubyHelm, 1), new Object[]{"XXX", "X X", "   ", 'X', PixelUtilitysItems.RubyItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.rubyPlate, 1), new Object[]{"X X", "XXX", "XXX", 'X', PixelUtilitysItems.RubyItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.rubyLegs, 1), new Object[]{"XXX", "X X", "X X", 'X', PixelUtilitysItems.RubyItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.rubyBoots, 1), new Object[]{"X X", "X X", 'X', PixelUtilitysItems.RubyItem});

        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.saphireHelm, 1), new Object[]{"XXX", "X X", "   ", 'X', PixelUtilitysItems.SaphireItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.saphirePlate, 1), new Object[]{"X X", "XXX", "XXX", 'X', PixelUtilitysItems.SaphireItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.saphireLegs, 1), new Object[]{"XXX", "X X", "X X", 'X', PixelUtilitysItems.SaphireItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.saphireBoots, 1), new Object[]{"X X", "X X", 'X', PixelUtilitysItems.SaphireItem});

        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.galacticHelm, 1), new Object[]{"XXX", "X X", "   ", 'X', PixelUtilitysItems.SiliconItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.galacticPlate, 1), new Object[]{"X X", "XXX", "XXX", 'X', PixelUtilitysItems.SiliconItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.galacticLegs, 1), new Object[]{"XXX", "X X", "X X", 'X', PixelUtilitysItems.SiliconItem});
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysArmor.galacticBoots, 1), new Object[]{"X X", "X X", 'X', PixelUtilitysItems.SiliconItem});

        //Radio
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.RadioBlock), "  y", "xyx", "xzx", 'x', new ItemStack(Blocks.planks), 'y', new ItemStack(Items.iron_ingot), 'z', new ItemStack(Items.diamond));


        //Lights

        //GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.blueLightBlock), new Object[] {"X X", "X X", Character.valueOf('X'), BlockStainedGlass});
    }

}
