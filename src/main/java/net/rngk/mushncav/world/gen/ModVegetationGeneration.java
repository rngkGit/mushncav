package net.rngk.mushncav.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.rngk.mushncav.world.ModPlacedFeatures;
import net.rngk.mushncav.world.biome.ModBiomes;

public class ModVegetationGeneration {
    public static void generateVegetation() {
        //Glowing Mushrooms
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOMS), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_BLUEBERRY_BUSH_PLACED_KEY);
    }
}
