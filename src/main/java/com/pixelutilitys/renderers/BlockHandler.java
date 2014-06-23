package com.pixelutilitys.renderers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {
    public static Block NewGrassBlock;
public static Block PokeDirtBlock;
    public static void configureBlocks(Configuration config) {    }

    public static void registerBlocks(GameRegistry registry) {
        registry.registerBlock(NewGrassBlock, "PokeGrass");
        registry.registerBlock(PokeDirtBlock, "PokeDirt");
    }

    public static void setNames(LanguageRegistry registry) {
        registry.addName(NewGrassBlock, "PokeGrass");
        registry.addName(PokeDirtBlock, "PokeDirt");
    }
}
