package com.pixelutilitys.renderers;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockHandler {
    public static Block NewGrassBlock;
    public static Block PokeDirtBlock;

    public static void configureBlocks(Configuration config) {    }

    public static void registerBlocks(GameRegistry registry) {
        registry.registerBlock(NewGrassBlock, "PokeGrass");
        registry.registerBlock(PokeDirtBlock, "PokeDirt");
    }

}
