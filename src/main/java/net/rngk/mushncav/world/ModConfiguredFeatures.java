package net.rngk.mushncav.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.root.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.*;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.block.custom.GlowingBlueberryBushBlock;
import net.rngk.mushncav.util.ModTags;
import net.rngk.mushncav.world.tree.FungiTreeDecorator;

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

        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookupConfiguredFeature = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntryLookup<Block> registryEntryLookupBlock = context.getRegistryLookup(RegistryKeys.BLOCK);



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
                ).dirtProvider(BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD)).decorators(ImmutableList.of(new FungiTreeDecorator(0.9f))).build());
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
        register(context, GLOWING_BLUEBERRY_BUSH_KEY, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.GLOWING_BLUEBERRY_BUSH.getDefaultState().with(GlowingBlueberryBushBlock.AGE, 3))), List.of(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK)));
        register(context, GLOWING_MUSHROOM_GRASS_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.GLOWING_SHORT_GRASS.getDefaultState())));
        register(context, GLOWING_MUSHROOM_VEGETATION_KEY, Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK),
                PlacedFeatures.createEntry(registryEntryLookupConfiguredFeature.getOrThrow(GLOWING_MUSHROOM_GRASS_KEY)),
                VerticalSurfaceType.FLOOR,
                ConstantIntProvider.create(1),
                0.0f,
                5,
                0.9f,
                UniformIntProvider.create(5, 9),
                0.4f
        ));

        register(context, GLOWING_MUSHROOM_VINES_KEY, Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(List.of(BlockColumnFeatureConfig.createLayer(UniformIntProvider.create(1, 3), BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_VINES_PLANT)), BlockColumnFeatureConfig.createLayer(ConstantIntProvider.create(1), BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_VINES))), Direction.DOWN, BlockPredicate.IS_AIR, true));

        // Note: Berries will naturally grow on generated vines through the growth/fertilization mechanism
        register(context, GLOWING_MUSHROOM_VEGETATION_CEILING_KEY, Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.of(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK),
                PlacedFeatures.createEntry(registryEntryLookupConfiguredFeature.getOrThrow(GLOWING_MUSHROOM_VINES_KEY)),
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
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MushroomsAndCaverns.MOD_ID, name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}