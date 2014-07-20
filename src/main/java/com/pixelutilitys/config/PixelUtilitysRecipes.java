package com.pixelutilitys.config;

import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelutilitys.Basemod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import static com.pixelutilitys.items.recipes.RecipeHelper.*;

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
        makeItemToBlockRecipe(PixelUtilitysBlocks.RubyBlock, PixelUtilitysItems.RubyItem);
        makeItemToBlockRecipe(PixelUtilitysBlocks.SaphireBlock, PixelUtilitysItems.SaphireItem);
        makeItemToBlockRecipe(PixelUtilitysBlocks.AmethystBlock, PixelUtilitysItems.AmethystItem);
        makeItemToBlockRecipe(PixelUtilitysBlocks.CrystalBlock, PixelUtilitysItems.CrystalItem);


        makeItemsFromBlock(PixelUtilitysItems.RubyItem, PixelUtilitysBlocks.RubyBlock);
        makeItemsFromBlock(PixelUtilitysItems.SaphireItem, PixelUtilitysBlocks.SaphireBlock);
        makeItemsFromBlock(PixelUtilitysItems.AmethystItem, PixelUtilitysBlocks.AmethystBlock);
        makeItemsFromBlock(PixelUtilitysItems.CrystalItem, PixelUtilitysBlocks.CrystalBlock);


        //Ruby Tools
        makePickaxeRecipe(PixelUtilitysTools.getInstance().rubyPickaxe, PixelUtilitysItems.RubyItem);
        makeHammerRecipe(PixelUtilitysTools.getInstance().rubyHammer, PixelUtilitysItems.RubyItem);
        makeAxeRecipe(PixelUtilitysTools.getInstance().rubyAxe, PixelUtilitysItems.RubyItem);
        makeShovelRecipe(PixelUtilitysTools.getInstance().rubyShovel, PixelUtilitysItems.RubyItem);
        makeHoeRecipe(PixelUtilitysTools.getInstance().rubyHoe, PixelUtilitysItems.RubyItem);
        makeSwordRecipe(PixelUtilitysTools.getInstance().rubySword, PixelUtilitysItems.RubyItem);

        //Saphire Tools
        makePickaxeRecipe(PixelUtilitysTools.getInstance().saphirePickaxe, PixelUtilitysItems.SaphireItem);
        makeHammerRecipe(PixelUtilitysTools.getInstance().saphireHammer, PixelUtilitysItems.SaphireItem);
        makeAxeRecipe(PixelUtilitysTools.getInstance().saphireAxe, PixelUtilitysItems.SaphireItem);
        makeShovelRecipe(PixelUtilitysTools.getInstance().saphireShovel, PixelUtilitysItems.SaphireItem);
        makeHoeRecipe(PixelUtilitysTools.getInstance().saphireHoe, PixelUtilitysItems.SaphireItem);
        makeSwordRecipe(PixelUtilitysTools.getInstance().saphireSword, PixelUtilitysItems.SaphireItem);

        //Amethyst Tools
        makePickaxeRecipe(PixelUtilitysTools.getInstance().amethystPickaxe, PixelUtilitysItems.AmethystItem);
        makeHammerRecipe(PixelUtilitysTools.getInstance().amethystHammer, PixelUtilitysItems.AmethystItem);
        makeAxeRecipe(PixelUtilitysTools.getInstance().amethystAxe, PixelUtilitysItems.AmethystItem);
        makeShovelRecipe(PixelUtilitysTools.getInstance().amethystShovel, PixelUtilitysItems.AmethystItem);
        makeHoeRecipe(PixelUtilitysTools.getInstance().amethystHoe, PixelUtilitysItems.AmethystItem);
        makeSwordRecipe(PixelUtilitysTools.getInstance().amethystSword, PixelUtilitysItems.AmethystItem);

        //Evo Tools
        if (Basemod.pixelmonPresent) {
            makePickaxeRecipe(PixelUtilitysTools.getInstance().FirestonePickaxe, PixelmonItems.fireStone);
            makeHammerRecipe(PixelUtilitysTools.getInstance().FirestoneHammer, PixelmonItems.fireStone);
            makeAxeRecipe(PixelUtilitysTools.getInstance().FirestoneAxe, PixelmonItems.fireStone);
            makeShovelRecipe(PixelUtilitysTools.getInstance().FirestoneShovel, PixelmonItems.fireStone);
            makeHoeRecipe(PixelUtilitysTools.getInstance().FirestoneHoe, PixelmonItems.fireStone);
            makeSwordRecipe(PixelUtilitysTools.getInstance().FirestoneSword, PixelmonItems.fireStone);

            makePickaxeRecipe(PixelUtilitysTools.getInstance().WaterstonePickaxe, PixelmonItems.waterStone);
            makeHammerRecipe(PixelUtilitysTools.getInstance().WaterstoneHammer, PixelmonItems.waterStone);
            makeAxeRecipe(PixelUtilitysTools.getInstance().WaterstoneAxe, PixelmonItems.waterStone);
            makeShovelRecipe(PixelUtilitysTools.getInstance().WaterstoneShovel, PixelmonItems.waterStone);
            makeHoeRecipe(PixelUtilitysTools.getInstance().WaterstoneHoe, PixelmonItems.waterStone);
            makeSwordRecipe(PixelUtilitysTools.getInstance().WaterstoneSword, PixelmonItems.waterStone);

            makePickaxeRecipe(PixelUtilitysTools.getInstance().LeafstonePickaxe, PixelmonItems.leafStone);
            makeHammerRecipe(PixelUtilitysTools.getInstance().LeafstoneHammer, PixelmonItems.leafStone);
            makeAxeRecipe(PixelUtilitysTools.getInstance().LeafstoneAxe, PixelmonItems.leafStone);
            makeShovelRecipe(PixelUtilitysTools.getInstance().LeafstoneShovel, PixelmonItems.leafStone);
            makeHoeRecipe(PixelUtilitysTools.getInstance().LeafstoneHoe, PixelmonItems.leafStone);
            makeSwordRecipe(PixelUtilitysTools.getInstance().LeafstoneSword, PixelmonItems.leafStone);
        }//TODO alternative recipies!

        //Crystal Tools
        makePickaxeRecipe(PixelUtilitysTools.getInstance().CrystalPickaxe, PixelUtilitysItems.CrystalItem);
        makeHammerRecipe(PixelUtilitysTools.getInstance().CrystalHammer, PixelUtilitysItems.CrystalItem);
        makeAxeRecipe(PixelUtilitysTools.getInstance().CrystalAxe, PixelUtilitysItems.CrystalItem);
        makeShovelRecipe(PixelUtilitysTools.getInstance().CrystalShovel, PixelUtilitysItems.CrystalItem);
        makeHoeRecipe(PixelUtilitysTools.getInstance().CrystalHoe, PixelUtilitysItems.CrystalItem);
        makeSwordRecipe(PixelUtilitysTools.getInstance().CrystalSword, PixelUtilitysItems.CrystalItem);

        //Armor
        makeHelmRecipe(PixelUtilitysArmor.rubyHelm, PixelUtilitysItems.RubyItem);
        makePlateRecipe(PixelUtilitysArmor.rubyPlate,PixelUtilitysItems.RubyItem);
        makeLegsRecipe(PixelUtilitysArmor.rubyLegs, PixelUtilitysItems.RubyItem);
        makeBootsRecipe(PixelUtilitysArmor.rubyBoots, PixelUtilitysItems.RubyItem);

        makeHelmRecipe(PixelUtilitysArmor.saphireHelm, PixelUtilitysItems.SaphireItem);
        makePlateRecipe(PixelUtilitysArmor.saphirePlate,PixelUtilitysItems.SaphireItem);
        makeLegsRecipe(PixelUtilitysArmor.saphireLegs, PixelUtilitysItems.SaphireItem);
        makeBootsRecipe(PixelUtilitysArmor.saphireBoots, PixelUtilitysItems.SaphireItem);

        makeHelmRecipe(PixelUtilitysArmor.galacticHelm, PixelUtilitysItems.SiliconItem);
        makePlateRecipe(PixelUtilitysArmor.galacticPlate,PixelUtilitysItems.SiliconItem);
        makeLegsRecipe(PixelUtilitysArmor.galacticLegs, PixelUtilitysItems.SiliconItem);
        makeBootsRecipe(PixelUtilitysArmor.galacticBoots, PixelUtilitysItems.SiliconItem);

        //Radio
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.RadioBlock), "  y", "xyx", "xzx", 'x', new ItemStack(Blocks.planks), 'y', new ItemStack(Items.iron_ingot), 'z', new ItemStack(Items.diamond));

        //Lights
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.blueLightBlock, 1), "xxx","xyx","xxx", 'x', new ItemStack(Blocks.stained_glass_pane, 1, 11), 'y', new ItemStack(Blocks.redstone_torch, 1));
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.redLightBlock, 1), "xxx","xyx","xxx", 'x', new ItemStack(Blocks.stained_glass_pane, 1, 14), 'y', new ItemStack(Blocks.redstone_torch, 1));
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.purpleLightBlock, 1), "xxx","xyx","xxx", 'x', new ItemStack(Blocks.stained_glass_pane, 1, 10), 'y', new ItemStack(Blocks.redstone_torch, 1));
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.yellowLightBlock, 1), "xxx","xyx","xxx", 'x', new ItemStack(Blocks.stained_glass_pane, 1, 4), 'y', new ItemStack(Blocks.redstone_torch, 1));
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.orangeLightBlock, 1), "xxx","xyx","xxx", 'x', new ItemStack(Blocks.stained_glass_pane, 1, 1), 'y', new ItemStack(Blocks.redstone_torch, 1));
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.whiteLightBlock, 1), "xxx","xyx","xxx", 'x', new ItemStack(Blocks.stained_glass_pane, 1, 0), 'y', new ItemStack(Blocks.redstone_torch, 1));
        GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.greenLightBlock, 1), "xxx","xyx","xxx", 'x', new ItemStack(Blocks.stained_glass_pane, 1, 5), 'y', new ItemStack(Blocks.redstone_torch, 1));

        //GameRegistry.addRecipe(new ItemStack(PixelUtilitysBlocks.blueLightBlock), new Object[] {"X X", "X X", Character.valueOf('X'), BlockStainedGlass});
    }

}
