package net.rngk.mushncav.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_SAPPHIRE_ORE_KEY = registerKey("glowing_sapphire_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FUNGI_TREE_KEY = registerKey("fungi_tree");
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldGlowingSapphireOres = List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.GLOWING_SAPPHIRE_ORE.getDefaultState()), OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE.getDefaultState()));

        register(context, GLOWING_SAPPHIRE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldGlowingSapphireOres, 12));
        register(context, FUNGI_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.FUNGI_TREE_LOG), new StraightTrunkPlacer(12, 7, 6), BlockStateProvider.of(ModBlocks.FUNGI_TREE_LEAVES), new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2), new TwoLayersFeatureSize(1, 0, 2)).dirtProvider(BlockStateProvider.of(ModBlocks.FUNGI_DIRT)).build());
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MushroomsAndCaverns.MOD_ID, name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
