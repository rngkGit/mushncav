package net.rngk.mushncav.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> GLOWING_SAPPHIRE_ORE_PLACED_KEY = registerKey("glowing_sapphire_ore_placed");

    public static final RegistryKey<PlacedFeature> FUNGI_TREE_PLACED_KEY = registerKey("fungi_tree_placed");
    public static final RegistryKey<PlacedFeature> HUGE_FUNGI_TREE_PLACED_KEY = registerKey("huge_fungi_tree_placed");
    public static final RegistryKey<PlacedFeature> FUNGI_MUSHROOM_PLACED_KEY = registerKey("fungi_mushroom_placed");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, GLOWING_SAPPHIRE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOWING_SAPPHIRE_ORE_KEY), ModOrePlacement.modifiersWithCount(3, //Veins per chunk
                HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(90))));
        register(context, FUNGI_MUSHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FUNGI_MUSHROOM_KEY), VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(32, 1f, 32), ModBlocks.FUNGI_MUSHROOM));
        register(context, HUGE_FUNGI_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HUGE_FUNGI_TREE_KEY), VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(32, .5f, 16), ModBlocks.FUNGI_TREE_SAPLING));
        register(context, FUNGI_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FUNGI_TREE_KEY), VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(16, 0.25f, 16), ModBlocks.FUNGI_TREE_SAPLING));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MushroomsAndCaverns.MOD_ID, name));
    }
    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}