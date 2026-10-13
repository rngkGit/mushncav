package net.rngk.mushncav.world;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.block.custom.GlowingBlueberryBushBlock;
import net.rngk.mushncav.util.ModTags;
import net.rngk.mushncav.world.tree.FungiTreeDecorator;

import java.util.List;
import java.util.Optional;

public class ModConfiguredFeatures {
    // Ores
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_SAPPHIRE_ORE_KEY = registerKey("glowing_sapphire_ore");

    // Trees
    public static final ResourceKey<ConfiguredFeature<?, ?>> FUNGI_TREE_KEY = registerKey("fungi_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_FUNGI_TREE_KEY = registerKey("huge_fungi_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FUNGI_MUSHROOM_KEY = registerKey("fungi_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_KEY = registerKey("glowing_mushroom");

    // Glowing Mushroom Biome Stuff
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_VEGETATION_KEY = registerKey("glowing_mushroom_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_VEGETATION_CEILING_KEY = registerKey("glowing_mushroom_vegetation_ceiling");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_GRASS_KEY = registerKey("glowing_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_VINES_KEY = registerKey("glowing_mushroom_vines");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_BLUEBERRY_BUSH_KEY = registerKey("glowing_blueberry_bush");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldGlowingSapphireOres = List.of(
                OreConfiguration.target(stoneReplacables, ModBlocks.GLOWING_SAPPHIRE_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE.defaultBlockState())
        );

        HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookupConfiguredFeature = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<Block> registryEntryLookupBlock = context.lookup(Registries.BLOCK);

        // Ores
        FeatureUtils.register(context, GLOWING_SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldGlowingSapphireOres, 12));

        // Trees
        FeatureUtils.register(context, FUNGI_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.FUNGI_TREE_LOG),
                        new CherryTrunkPlacer(6, 1, 2, UniformInt.of(2, 3), UniformInt.of(2, 3), UniformInt.of(-1, 0), ConstantInt.of(2)),
                        BlockStateProvider.simple(ModBlocks.FUNGI_TREE_LEAVES),
                        new CherryFoliagePlacer(UniformInt.of(3, 4), ConstantInt.of(1), ConstantInt.of(4), 0.1f, 0.25f, 0.4f, 0.25f),
                        Optional.of(new MangroveRootPlacer(UniformInt.of(2, 5), BlockStateProvider.simple(ModBlocks.FUNGI_TREE_WOOD), Optional.empty(), new MangroveRootPlacement(registryEntryLookupBlock.getOrThrow(ModTags.Blocks.DIRT), HolderSet.direct(Block::builtInRegistryHolder, ModBlocks.FUNGI_GRASS_BLOCK, ModBlocks.FUNGI_TREE_WOOD), BlockStateProvider.simple(ModBlocks.FUNGI_TREE_WOOD), 8, 12, 0.3f))),
                        new TwoLayersFeatureSize(3, 0, 2),
                        BlockStateProvider.simple(ModBlocks.FUNGI_TREE_WOOD)
                ).decorators(List.of(new FungiTreeDecorator(0.9f))).build());

        FeatureUtils.register(context, HUGE_FUNGI_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.FUNGI_TREE_LOG),
                        new MegaJungleTrunkPlacer(16, 2, 24),
                        BlockStateProvider.simple(ModBlocks.FUNGI_TREE_LEAVES),
                        new MegaJungleFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 2),
                        Optional.of(new MangroveRootPlacer(UniformInt.of(4, 9), BlockStateProvider.simple(ModBlocks.FUNGI_TREE_WOOD), Optional.empty(), new MangroveRootPlacement(registryEntryLookupBlock.getOrThrow(ModTags.Blocks.DIRT), HolderSet.direct(Block::builtInRegistryHolder, ModBlocks.FUNGI_GRASS_BLOCK, ModBlocks.FUNGI_TREE_WOOD), BlockStateProvider.simple(ModBlocks.FUNGI_TREE_WOOD), 12, 16, 0.5f))),
                        new TwoLayersFeatureSize(1, 1, 2),
                        BlockStateProvider.simple(ModBlocks.FUNGI_TREE_WOOD)
                ).build());

        FeatureUtils.register(context, FUNGI_MUSHROOM_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.FUNGI_MUSHROOM_STEM),
                        new StraightTrunkPlacer(4, 0, 1),
                        BlockStateProvider.simple(ModBlocks.FUNGI_MUSHROOM_BLOCK),
                        new CherryFoliagePlacer(UniformInt.of(4, 6), UniformInt.of(0, 2), ConstantInt.of(4), 0.8f, 0.2f, 1f, 0.75f),
                        Optional.of(new MangroveRootPlacer(UniformInt.of(1, 3), BlockStateProvider.simple(ModBlocks.FUNGI_MUSHROOM_STEM), Optional.empty(), new MangroveRootPlacement(registryEntryLookupBlock.getOrThrow(ModTags.Blocks.DIRT), HolderSet.direct(Block::builtInRegistryHolder, ModBlocks.FUNGI_GRASS_BLOCK, ModBlocks.FUNGI_MUSHROOM_STEM), BlockStateProvider.simple(ModBlocks.FUNGI_MUSHROOM_STEM), 6, 9, 0.4f))),
                        new TwoLayersFeatureSize(2, 1, 2),
                        BlockStateProvider.simple(ModBlocks.FUNGI_MUSHROOM_STEM)
                ).build());

        FeatureUtils.register(context, GLOWING_MUSHROOM_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_STEM),
                        new StraightTrunkPlacer(4, 0, 1),
                        BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_BLOCK),
                        new CherryFoliagePlacer(UniformInt.of(4, 6), UniformInt.of(0, 2), ConstantInt.of(4), 0.8f, 0.2f, 1f, 0.75f),
                        Optional.of(new MangroveRootPlacer(UniformInt.of(1, 3), BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_STEM), Optional.empty(), new MangroveRootPlacement(registryEntryLookupBlock.getOrThrow(ModTags.Blocks.DIRT), HolderSet.direct(Block::builtInRegistryHolder, ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK, ModBlocks.GLOWING_MUSHROOM_STEM), BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_STEM), 4, 7, 0.2f))),
                        new TwoLayersFeatureSize(2, 1, 2),
                        BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_STEM)
                ).build());

        // Mushroom Biome Stuff
        FeatureUtils.register(context, GLOWING_BLUEBERRY_BUSH_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GLOWING_BLUEBERRY_BUSH.defaultBlockState().setValue(GlowingBlueberryBushBlock.AGE, 3))));

        FeatureUtils.register(context, GLOWING_MUSHROOM_GRASS_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GLOWING_SHORT_GRASS.defaultBlockState())));

        FeatureUtils.register(context, GLOWING_MUSHROOM_VEGETATION_KEY, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
                registryEntryLookupBlock.getOrThrow(BlockTags.MOSS_REPLACEABLE),
                BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK),
                PlacementUtils.inlinePlaced(registryEntryLookupConfiguredFeature.getOrThrow(GLOWING_MUSHROOM_GRASS_KEY)),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0.0f,
                5,
                0.9f,
                UniformInt.of(5, 9),
                0.4f
        ));

        FeatureUtils.register(context, GLOWING_MUSHROOM_VINES_KEY, Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                        BlockColumnConfiguration.layer(UniformInt.of(1, 3), BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_VINES_PLANT)),
                        BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_VINES))
                ),
                Direction.DOWN,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true
        ));

        // Note: Berries will naturally grow on generated vines through the growth/fertilization mechanism
        FeatureUtils.register(context, GLOWING_MUSHROOM_VEGETATION_CEILING_KEY, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
                registryEntryLookupBlock.getOrThrow(BlockTags.MOSS_REPLACEABLE),
                BlockStateProvider.simple(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK),
                PlacementUtils.inlinePlaced(registryEntryLookupConfiguredFeature.getOrThrow(GLOWING_MUSHROOM_VINES_KEY)),
                CaveSurface.CEILING,
                UniformInt.of(1, 2),
                0.0f,
                5,
                0.08f,
                UniformInt.of(4, 7),
                0.3f
        ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, name));
    }
}