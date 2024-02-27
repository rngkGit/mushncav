package net.rngk.mushncav.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.rngk.mushncav.world.ModPlacedFeatures;
import net.rngk.mushncav.world.biome.ModBiomes;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.FUNGI_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FUNGI_TREE_PLACED_KEY);
    }
}
