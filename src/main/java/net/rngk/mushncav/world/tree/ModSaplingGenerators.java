package net.rngk.mushncav.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.rngk.mushncav.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator FUNGI_TREE = new SaplingGenerator("fungi_tree", Optional.of(ModConfiguredFeatures.HUGE_FUNGI_TREE_KEY), Optional.of(ModConfiguredFeatures.FUNGI_TREE_KEY), Optional.empty());
}
