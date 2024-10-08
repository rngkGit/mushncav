package net.rngk.mushncav.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.root.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.CocoaBeansTreeDecorator;
import net.minecraft.world.gen.trunk.*;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.block.custom.GlowingBlueberryBushBlock;
import net.rngk.mushncav.block.custom.GlowingMushroomVines;
import net.rngk.mushncav.block.custom.GlowingMushroomVinesHeadBlock;
import net.rngk.mushncav.util.ModTags;

import java.util.List;
import java.util.Optional;

public class ModConfiguredFeatures {
    // Ores
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_SAPPHIRE_ORE_KEY = registerKey("glowing_sapphire_ore");

    // Trees
    public static final RegistryKey<ConfiguredFeature<?, ?>> FUNGI_TREE_KEY = registerKey("fungi_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_FUNGI_TREE_KEY = registerKey("huge_fungi_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FUNGI_MUSHROOM_KEY = registerKey("fungi_mushroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_KEY = registerKey("glowing_mushroom");

    // Glowing Mushroom Biome Stuff
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_VEGETATION_KEY = registerKey("glowing_mushroom_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_VEGETATION_CEILING_KEY = registerKey("glowing_mushroom_vegetation_ceiling");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_GRASS_KEY = registerKey("glowing_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MUSHROOM_VINES_KEY = registerKey("glowing_mushroom_vines");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_BLUEBERRY_BUSH_KEY = registerKey("glowing_blueberry_bush");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldGlowingSapphireOres = List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.GLOWING_SAPPHIRE_ORE.getDefaultState()), OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE.getDefaultState()));

        RegistryEntryLookup<Block> registryEntryLookupBlock = context.getRegistryLookup(RegistryKeys.BLOCK);
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookupConfiguredFeature = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);



        // Ores
        register(context, GLOWING_SAPPHIRE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldGlowingSapphireOres, 12));



        // Trees
        register(context, FUNGI_TREE_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ModBlocks.FUNGI_TREE_LOG),
                        new CherryTrunkPlacer(6, 1, 2, UniformIntProvider.create(2, 3), UniformIntProvider.create(2, 3), UniformIntProvider.create(-1, 0), ConstantIntProvider.create(2)),
                        BlockStateProvider.of(ModBlocks.FUNGI_TREE_LEAVES),
                        /*new RandomSpreadFoliagePlacer(ConstantIntProvider.create(5), ConstantIntProvider.create(0), ConstantIntProvider.create(4), 120),*/
                        new CherryFoliagePlacer(UniformIntProvider.create(3, 4), ConstantIntProvider.create(1), ConstantIntProvider.create(4), 0.1f, 0.25f, 0.4f, 0.25f),
                        Optional.of(new MangroveRootPlacer(UniformIntProvider.create(2, 5), BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD), Optional.empty(), new MangroveRootPlacement(registryEntryLookupBlock.getOrThrow(ModTags.Blocks.DIRT), RegistryEntryList.of(Block::getRegistryEntry, ModBlocks.FUNGI_GRASS_BLOCK, ModBlocks.FUNGI_TREE_WOOD), BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD), 8, 12, 0.3f))),
                        new TwoLayersFeatureSize(3, 0, 2)
                ).dirtProvider(BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD)).decorators(ImmutableList.of(new CocoaBeansTreeDecorator(0.2f))).build());
        register(context, HUGE_FUNGI_TREE_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ModBlocks.FUNGI_TREE_LOG),
                        new MegaJungleTrunkPlacer(16, 2, 24),
                        BlockStateProvider.of(ModBlocks.FUNGI_TREE_LEAVES),
                        new JungleFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), 2),
                        Optional.of(new MangroveRootPlacer(UniformIntProvider.create(4, 9), BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD), Optional.empty(), new MangroveRootPlacement(registryEntryLookupBlock.getOrThrow(ModTags.Blocks.DIRT), RegistryEntryList.of(Block::getRegistryEntry, ModBlocks.FUNGI_GRASS_BLOCK, ModBlocks.FUNGI_TREE_WOOD), BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD), 12, 16, 0.5f))),
                        new TwoLayersFeatureSize(1, 1, 2)
                ).dirtProvider(BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD)).build());
        register(context, FUNGI_MUSHROOM_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ModBlocks.FUNGI_MUSHROOM_STEM),
                        new StraightTrunkPlacer(4, 0, 1),
                        BlockStateProvider.of(ModBlocks.FUNGI_MUSHROOM_BLOCK),
                        new CherryFoliagePlacer(UniformIntProvider.create(4, 6), UniformIntProvider.create(0, 2), ConstantIntProvider.create(4), 0.8f, 0.2f, 1f, 0.75f),
                        Optional.of(new MangroveRootPlacer(UniformIntProvider.create(1, 3), BlockStateProvider.of(ModBlocks.FUNGI_MUSHROOM_STEM), Optional.empty(), new MangroveRootPlacement(registryEntryLookupBlock.getOrThrow(ModTags.Blocks.DIRT), RegistryEntryList.of(Block::getRegistryEntry, ModBlocks.FUNGI_GRASS_BLOCK, ModBlocks.FUNGI_MUSHROOM_STEM), BlockStateProvider.of(ModBlocks.FUNGI_MUSHROOM_STEM), 6, 9, 0.4f))),
                        new TwoLayersFeatureSize(2, 1, 2)
                ).dirtProvider(BlockStateProvider.of(ModBlocks.FUNGI_MUSHROOM_STEM)).build());
        register(context, GLOWING_MUSHROOM_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_STEM),
                        new StraightTrunkPlacer(4, 0, 1),
                        BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_BLOCK),
                        new CherryFoliagePlacer(UniformIntProvider.create(4, 6), UniformIntProvider.create(0, 2), ConstantIntProvider.create(4), 0.8f, 0.2f, 1f, 0.75f),
                        Optional.of(new MangroveRootPlacer(UniformIntProvider.create(1, 3), BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_STEM), Optional.empty(), new MangroveRootPlacement(registryEntryLookupBlock.getOrThrow(ModTags.Blocks.DIRT), RegistryEntryList.of(Block::getRegistryEntry, ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK, ModBlocks.GLOWING_MUSHROOM_STEM), BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_STEM), 4, 7, 0.2f))),
                        new TwoLayersFeatureSize(2, 1, 2)
                ).dirtProvider(BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_STEM)).build());



        // Mushroom Biome Stuff
        /*register(context, GLOWING_MUSHROOM_GRASS_KEY, Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(
                BlockTags.DIRT,
                BlockStateProvider.of(Blocks.SHORT_GRASS.getDefaultState()),
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.SHORT_GRASS.getDefaultState()))),
                VerticalSurfaceType.FLOOR,
                UniformIntProvider.create(1, 2),
                0.1f,
                8,
                0.7f,
                UniformIntProvider.create(4, 8),
                0.2f
        ));*/
        /*register(context, GLOWING_MUSHROOM_GRASS_KEY, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(32, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.SHORT_GRASS.getDefaultState())))
        ));*/
        register(context, GLOWING_BLUEBERRY_BUSH_KEY, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of((BlockState)ModBlocks.GLOWING_BLUEBERRY_BUSH.getDefaultState().with(GlowingBlueberryBushBlock.AGE, 3))), List.of(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK)));
        register(context, GLOWING_MUSHROOM_GRASS_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                //.add(Blocks.AIR.getDefaultState(), 1)
                //.add(Blocks.SHORT_GRASS.getDefaultState(), 50)
                //.add(Blocks.TALL_GRASS.getDefaultState(), 10)
                .add(ModBlocks.GLOWING_SHORT_GRASS.getDefaultState(), 50)
                .add(ModBlocks.GLOWING_TALL_GRASS.getDefaultState(), 10)
                .add(ModBlocks.GLOWING_BLUEBERRY_BUSH.getDefaultState().with(GlowingBlueberryBushBlock.AGE, 3), 5)
                .build())
        ));
        register(context, GLOWING_MUSHROOM_VEGETATION_KEY, Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK),
                PlacedFeatures.createEntry(registryEntryLookupConfiguredFeature.getOrThrow(GLOWING_MUSHROOM_GRASS_KEY), new PlacementModifier[0]),
                VerticalSurfaceType.FLOOR,
                ConstantIntProvider.create(1),
                0.0f,
                5,
                0.9f,
                UniformIntProvider.create(5, 9),
                0.4f
        ));

        //MushroomsAndCaverns.LOGGER.info("Available properties for GLOWING_MUSHROOM_VINES: {}", ModBlocks.GLOWING_MUSHROOM_VINES.getStateManager().getProperties());
        WeightedBlockStateProvider weightedBlockStateProvider = new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.GLOWING_MUSHROOM_VINES_PLANT.getDefaultState(), 4).add(ModBlocks.GLOWING_MUSHROOM_VINES_PLANT.getDefaultState().with(CaveVines.BERRIES, true), 1));
        RandomizedIntBlockStateProvider randomizedIntBlockStateProvider = new RandomizedIntBlockStateProvider(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(ModBlocks.GLOWING_MUSHROOM_VINES.getDefaultState(), 4).add(ModBlocks.GLOWING_MUSHROOM_VINES.getDefaultState().with(GlowingMushroomVines.BERRIES, true), 1)), GlowingMushroomVinesHeadBlock.AGE, UniformIntProvider.create(23, 25));
        register(context, GLOWING_MUSHROOM_VINES_KEY, Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(List.of(BlockColumnFeatureConfig.createLayer(new WeightedListIntProvider(DataPool.<IntProvider>builder().add(UniformIntProvider.create(0, 3), 5).add(UniformIntProvider.create(1, 7), 1).build()), weightedBlockStateProvider), BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1), randomizedIntBlockStateProvider)), Direction.DOWN, BlockPredicate.IS_AIR, true));
        register(context, GLOWING_MUSHROOM_VEGETATION_CEILING_KEY, Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK),
                PlacedFeatures.createEntry(registryEntryLookupConfiguredFeature.getOrThrow(GLOWING_MUSHROOM_VINES_KEY), new PlacementModifier[0]),
                VerticalSurfaceType.CEILING,
                UniformIntProvider.create(1, 2),
                0.0f,
                5,
                0.08f,
                UniformIntProvider.create(4, 7),
                0.3f
        ));
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MushroomsAndCaverns.MOD_ID, name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}