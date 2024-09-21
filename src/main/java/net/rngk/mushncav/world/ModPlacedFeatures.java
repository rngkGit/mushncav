package net.rngk.mushncav.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    //private static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);

    // Ores
    public static final RegistryKey<PlacedFeature> GLOWING_SAPPHIRE_ORE_PLACED_KEY = registerKey("glowing_sapphire_ore_placed");

    // Trees
    public static final RegistryKey<PlacedFeature> FUNGI_TREE_PLACED_KEY = registerKey("fungi_tree_placed");
    public static final RegistryKey<PlacedFeature> HUGE_FUNGI_TREE_PLACED_KEY = registerKey("huge_fungi_tree_placed");
    public static final RegistryKey<PlacedFeature> FUNGI_MUSHROOM_PLACED_KEY = registerKey("fungi_mushroom_placed");
    public static final RegistryKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY = registerKey("glowing_mushroom_placed");
    public static final RegistryKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY_2 = registerKey("glowing_mushroom_placed_2");
    public static final RegistryKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY_3 = registerKey("glowing_mushroom_placed_3");
    public static final RegistryKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY_4 = registerKey("glowing_mushroom_placed_4");
    public static final RegistryKey<PlacedFeature> GLOWING_MUSHROOM_PLACED_KEY_5 = registerKey("glowing_mushroom_placed_5");

    // Grass
    public static final RegistryKey<PlacedFeature> GLOWING_MUSHROOM_VEGETATION_PLACED_KEY = registerKey("glowing_mushroom_vegetation_placed");


    // Extra stuff
    private static ImmutableList.Builder<PlacementModifier> undergroundTreeModifiersBuilder(PlacementModifier countModifier) {
        return ((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)((ImmutableList.Builder)ImmutableList.builder().add(countModifier)).add(SquarePlacementModifier.of()))/*.add(NOT_IN_SURFACE_WATER_MODIFIER)*/).add(PlacedFeatures.BOTTOM_TO_120_RANGE)).add(BiomePlacementModifier.of());
    }

    private static ImmutableList.Builder<PlacementModifier> undergroundTreeModifiersBuilder2(PlacementModifier countModifier) {
        return (ImmutableList.Builder)ImmutableList.builder()
                .add(countModifier)
                .add(SquarePlacementModifier.of())
                //.add(NOT_IN_SURFACE_WATER_MODIFIER)
                .add(PlacedFeatures.BOTTOM_TO_120_RANGE)
                .add(BiomePlacementModifier.of()
        );
    }

    public static List<PlacementModifier> undergroundTreeModifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
        return ((ImmutableList.Builder)undergroundTreeModifiersBuilder2(modifier).add((PlacementModifier) BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN)))).build();
    }

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Ores
        register(context, GLOWING_SAPPHIRE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_SAPPHIRE_ORE_KEY), ModOrePlacement.modifiersWithCount(3, //Veins per chunk
                HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(90))));

        // Trees
        register(context, FUNGI_MUSHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FUNGI_MUSHROOM_KEY), VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(32, 1f, 32), ModBlocks.FUNGI_MUSHROOM));
        register(context, HUGE_FUNGI_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HUGE_FUNGI_TREE_KEY), VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(32, .5f, 16), ModBlocks.FUNGI_TREE_SAPLING));
        register(context, FUNGI_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FUNGI_TREE_KEY), VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(16, 0.25f, 16), ModBlocks.FUNGI_TREE_SAPLING));
        register(context, GLOWING_MUSHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY), undergroundTreeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));
        register(context, GLOWING_MUSHROOM_PLACED_KEY_2, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY), undergroundTreeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));
        register(context, GLOWING_MUSHROOM_PLACED_KEY_3, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY), undergroundTreeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));
        register(context, GLOWING_MUSHROOM_PLACED_KEY_4, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY), undergroundTreeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));
        register(context, GLOWING_MUSHROOM_PLACED_KEY_5, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY), undergroundTreeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(128, 1f, 128), ModBlocks.GLOWING_MUSHROOM));
        //register(context, GLOWING_MUSHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY), undergroundTreeModifiersWithWouldSurvive(CountPlacementModifier.of(new WeightedListIntProvider(DataPool.<IntProvider>builder().add(ConstantIntProvider.create(192), 0).add(ConstantIntProvider.create(256), 1).build())), ModBlocks.GLOWING_MUSHROOM));


        // Grass
        register(context, GLOWING_MUSHROOM_VEGETATION_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_MUSHROOM_VEGETATION_KEY), List.of(CountPlacementModifier.of(250), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_120_RANGE, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)), BiomePlacementModifier.of()));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MushroomsAndCaverns.MOD_ID, name));
    }
    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}