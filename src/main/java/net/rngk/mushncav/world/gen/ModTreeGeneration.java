package net.rngk.mushncav.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.rngk.mushncav.world.ModPlacedFeatures;
import net.rngk.mushncav.world.biome.ModBiomes;

public class ModTreeGeneration {
    public static void generateTrees() {
        //Fungi Forest
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.FUNGI_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FUNGI_MUSHROOM_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.FUNGI_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.HUGE_FUNGI_TREE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.FUNGI_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FUNGI_TREE_PLACED_KEY);

        //Glowing Mushrooms
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOMS), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOMS), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_PLACED_KEY_2);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOMS), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_PLACED_KEY_3);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOMS), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_PLACED_KEY_4);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOMS), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_PLACED_KEY_5);
    }
}
