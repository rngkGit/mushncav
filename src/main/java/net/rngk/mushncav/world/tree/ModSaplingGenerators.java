package net.rngk.mushncav.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.rngk.mushncav.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator FUNGI_TREE = new SaplingGenerator("fungi_tree", Optional.of(ModConfiguredFeatures.HUGE_FUNGI_TREE_KEY), Optional.of(ModConfiguredFeatures.FUNGI_TREE_KEY), Optional.empty());
    public static final SaplingGenerator FUNGI_MUSHROOM = new SaplingGenerator("fungi_mushroom", Optional.empty(), Optional.of(ModConfiguredFeatures.FUNGI_MUSHROOM_KEY), Optional.empty());
    public static final SaplingGenerator GLOWING_MUSHROOM = new SaplingGenerator("glowing_mushroom", Optional.empty(), Optional.of(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY), Optional.empty());
}
