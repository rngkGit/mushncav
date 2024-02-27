package net.rngk.mushncav.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PropaguleBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.entry.RegistryEntryListCodec;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.root.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.UpwardsBranchingTrunkPlacer;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.util.ModTags;
import net.rngk.mushncav.world.tree.custom.FungiTreeRootPlacement;
import net.rngk.mushncav.world.tree.custom.FungiTreeRootPlacer;
import net.rngk.mushncav.world.tree.custom.FungiTreeTrunkPlacer;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_SAPPHIRE_ORE_KEY = registerKey("glowing_sapphire_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FUNGI_TREE_KEY = registerKey("fungi_tree");
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldGlowingSapphireOres = List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.GLOWING_SAPPHIRE_ORE.getDefaultState()), OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE.getDefaultState()));

        RegistryEntryLookup<Block> registryEntryLookup = context.getRegistryLookup(RegistryKeys.BLOCK);

        register(context, GLOWING_SAPPHIRE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldGlowingSapphireOres, 12));
        /*register(context, FUNGI_TREE_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.FUNGI_TREE_LOG),
                        new FungiTreeTrunkPlacer(18, 4, 8), BlockStateProvider.of(ModBlocks.FUNGI_TREE_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2),
                        Optional.of(new FungiTreeRootPlacer(UniformIntProvider.create(1, 3), BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD), Optional.empty(), new FungiTreeRootPlacement(registryEntryLookup.getOrThrow(ModTags.Blocks.DIRT), RegistryEntryList.of(Block::getRegistryEntry, ModBlocks.FUNGI_GRASS_BLOCK, ModBlocks.FUNGI_TREE_WOOD), BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD), 8, 15, 0.2f))),
                        new TwoLayersFeatureSize(1, 0, 2))
                        .dirtProvider(BlockStateProvider.of(ModBlocks.FUNGI_DIRT))
                .build()); just in case this goes horrible*/
        register(context, FUNGI_TREE_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.FUNGI_TREE_LOG),
                        new UpwardsBranchingTrunkPlacer(16, 4, 7, UniformIntProvider.create(1, 6), 0.5f, UniformIntProvider.create(0, 1), registryEntryLookup.getOrThrow(ModTags.Blocks.DIRT)), BlockStateProvider.of(ModBlocks.FUNGI_TREE_LEAVES),
                        new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 70),
                        Optional.of(new MangroveRootPlacer(UniformIntProvider.create(3, 7), BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD), Optional.empty(), new MangroveRootPlacement(registryEntryLookup.getOrThrow(ModTags.Blocks.DIRT), RegistryEntryList.of(Block::getRegistryEntry, ModBlocks.FUNGI_GRASS_BLOCK, ModBlocks.FUNGI_TREE_WOOD), BlockStateProvider.of(ModBlocks.FUNGI_TREE_WOOD), 8, 15, 0.2f))),
                        new TwoLayersFeatureSize(3, 0, 2)).build());
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MushroomsAndCaverns.MOD_ID, name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
