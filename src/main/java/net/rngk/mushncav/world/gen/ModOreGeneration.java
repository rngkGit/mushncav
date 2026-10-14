package net.rngk.mushncav.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.rngk.mushncav.world.ModPlacedFeatures;
import net.rngk.mushncav.world.biome.ModBiomes;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.GLOWING_MUSHROOMS), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.GLOWING_SAPPHIRE_ORE_PLACED_KEY);
    }
}
