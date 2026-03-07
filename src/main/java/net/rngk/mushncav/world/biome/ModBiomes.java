package net.rngk.mushncav.world.biome;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.world.ModPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> FUNGI_FOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(MushroomsAndCaverns.MOD_ID, "fungi_forest"));
    public static final RegistryKey<Biome> GLOWING_MUSHROOMS = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(MushroomsAndCaverns.MOD_ID, "glowing_mushrooms"));

    public static void boostrap(Registerable<Biome> context) {
        context.register(FUNGI_FOREST, fungiForest(context));
        context.register(GLOWING_MUSHROOMS, glowingMushrooms(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome fungiForest(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        //spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.PORCUPINE, 2, 3, 5)); not yet

        // TODO 1.21.11: SpawnEntry constructor signature unclear from errors - temporarily disabled
        // spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        // TODO 1.21.11: addBatsAndMonsters removed - add spawns manually if needed

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

        //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS); adds oak trees

        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder, false);

        //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.GRASS_BONEMEAL);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x33237d)
                        .grassColor(0x47167a)
                        .foliageColor(0x47167a)
                        /*.music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL)))*/.build())
                .build();
    }

    public static Biome glowingMushrooms(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        //spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.PORCUPINE, 2, 3, 5)); not yet

        //DefaultBiomeFeatures.addFarmAnimals(spawnBuilder); no need
        // TODO 1.21.11: addBatsAndMonsters removed - add spawns manually if needed

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

        //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS); adds oak trees

        //DefaultBiomeFeatures.addLushCavesDecoration(biomeBuilder);

        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_VEGETATION_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_VEGETATION_CEILING_PLACED_KEY);

        //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_BLUEBERRY_BUSH_PLACED_KEY);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder, false);


        //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOWING_MUSHROOM_PLACED_KEY);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.5f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x152aeb)
                        .grassColor(0x152aeb)
                        .foliageColor(0x152aeb)
                        /*.music(MusicType.createIngameMusic(RegistryEntry.of(ModSounds.BAR_BRAWL)))*/.build())
                .build();
    }
}
