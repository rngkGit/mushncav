package net.rngk.mushncav.world.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.rngk.mushncav.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final TreeGrower FUNGI_TREE = new TreeGrower(
            "fungi_tree",
            Optional.of(ModConfiguredFeatures.HUGE_FUNGI_TREE_KEY),
            Optional.of(ModConfiguredFeatures.FUNGI_TREE_KEY),
            Optional.empty()
    );
    public static final TreeGrower FUNGI_MUSHROOM = new TreeGrower(
            "fungi_mushroom",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.FUNGI_MUSHROOM_KEY),
            Optional.empty()
    );
    public static final TreeGrower GLOWING_MUSHROOM = new TreeGrower(
            "glowing_mushroom",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.GLOWING_MUSHROOM_KEY),
            Optional.empty()
    );
}
