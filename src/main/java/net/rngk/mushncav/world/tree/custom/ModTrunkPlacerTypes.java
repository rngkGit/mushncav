package net.rngk.mushncav.world.tree.custom;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.world.tree.FungiTreeTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static TrunkPlacerType<?> FUNGI_TREE_PLACER;

    public static void register() {
        MushroomsAndCaverns.LOGGER.info("Registering Trunk Placers for " + MushroomsAndCaverns.MOD_ID);

        // Register directly to the registry
        FUNGI_TREE_PLACER = Registry.register(
                Registries.TRUNK_PLACER_TYPE,
                Identifier.of(MushroomsAndCaverns.MOD_ID, "fungi_tree_trunk_placer"),
                new TrunkPlacerType<>(FungiTreeTrunkPlacer.CODEC)
        );
    }
}
