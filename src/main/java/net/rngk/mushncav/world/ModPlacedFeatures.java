package net.rngk.mushncav.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    // Ores
    public static final ResourceKey<PlacedFeature> GLOWING_SAPPHIRE_ORE_PLACED_KEY = registerKey("glowing_sapphire_ore_placed");

    // Trees
    public static final ResourceKey<PlacedFeature> FUNGI_TREE_PLACED_KEY = registerKey("fungi_tree_placed");
    public static final ResourceKey<PlacedFeature> HUGE_FUNGI_TREE_PLACED_KEY = registerKey("huge_fungi_tree_placed");
    public static final ResourceKey<PlacedFeature> FUNGI_MUSHROOM_PLACED_KEY = registerKey("fungi_mushroom_placed");
    public static final ResourceKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY = registerKey("glowing_mushroom_placed");
    public static final ResourceKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY_2 = registerKey("glowing_mushroom_placed_2");
    public static final ResourceKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY_3 = registerKey("glowing_mushroom_placed_3");
    public static final ResourceKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY_4 = registerKey("glowing_mushroom_placed_4");
    public static final ResourceKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY_5 = registerKey("glowing_mushroom_placed_5");

    // Grass
    public static final ResourceKey<PlacedFeature> GLOWING_MUSHROOM_VEGETATION_PLACED_KEY = registerKey("glowing_mushroom_vegetation_placed");
    public static final ResourceKey<PlacedFeature> GLOWING_MUSHROOM_VEGETATION_CEILING_PLACED_KEY = registerKey("glowing_mushroom_vegetation_ceiling_placed");
    public static final ResourceKey<PlacedFeature> GLOWING_BLUEBERRY_BUSH_PLACED_KEY = registerKey("glowing_blueberry_bush_placed");

    public static List<PlacementModifier> undergroundTreeModifiersWithWouldSurvive(PlacementModifier countModifier, Block block) {
        return List.of(
                countModifier,
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(120)),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(block.defaultBlockState(), BlockPos.ZERO))
        );
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        // Ores
        PlacementUtils.register(context, GLOWING_SAPPHIRE_ORE_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_SAPPHIRE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(6,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(90))));

        // Trees
        PlacementUtils.register(context, FUNGI_MUSHROOM_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.FUNGI_MUSHROOM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(32, 1f, 32), ModBlocks.FUNGI_MUSHROOM));

        PlacementUtils.register(context, HUGE_FUNGI_TREE_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.HUGE_FUNGI_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(32, 0.5f, 16), ModBlocks.FUNGI_TREE_SAPLING));

        PlacementUtils.register(context, FUNGI_TREE_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.FUNGI_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(16, 0.25f, 16), ModBlocks.FUNGI_TREE_SAPLING));

        PlacementUtils.register(context, GLOWING_MUSHROOM_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY),
                undergroundTreeModifiersWithWouldSurvive(PlacementUtils.countExtra(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));

        PlacementUtils.register(context, GLOWING_MUSHROOM_PLACED_KEY_2,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY),
                undergroundTreeModifiersWithWouldSurvive(PlacementUtils.countExtra(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));

        PlacementUtils.register(context, GLOWING_MUSHROOM_PLACED_KEY_3,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY),
                undergroundTreeModifiersWithWouldSurvive(PlacementUtils.countExtra(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));

        PlacementUtils.register(context, GLOWING_MUSHROOM_PLACED_KEY_4,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY),
                undergroundTreeModifiersWithWouldSurvive(PlacementUtils.countExtra(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));

        PlacementUtils.register(context, GLOWING_MUSHROOM_PLACED_KEY_5,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY),
                undergroundTreeModifiersWithWouldSurvive(PlacementUtils.countExtra(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));

        // Glowing Mushroom Vegetation Stuff
        PlacementUtils.register(context, GLOWING_MUSHROOM_VEGETATION_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_VEGETATION_KEY),
                List.of(
                        CountPlacement.of(250),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(120)),
                        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, GLOWING_MUSHROOM_VEGETATION_CEILING_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_VEGETATION_CEILING_KEY),
                List.of(
                        CountPlacement.of(125),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(120)),
                        EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                        RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
                        BiomeFilter.biome()
                ));

        PlacementUtils.register(context, GLOWING_BLUEBERRY_BUSH_PLACED_KEY,
                configuredFeatureLookup.getOrThrow(ModConfiguredFeatures.GLOWING_BLUEBERRY_BUSH_KEY),
                List.of(
                        CountPlacement.of(50),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(120)),
                        BiomeFilter.biome()
                ));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, name));
    }
}
