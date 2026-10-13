package net.rngk.mushncav.world.tree.custom;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.mixin.TrunkPlacerTypeInvoker;
import net.rngk.mushncav.world.tree.FungiTreeTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static TrunkPlacerType<FungiTreeTrunkPlacer> FUNGI_TREE_PLACER;

    public static void register() {
        MushroomsAndCaverns.LOGGER.info("Registering Trunk Placers for " + MushroomsAndCaverns.MOD_ID);

        FUNGI_TREE_PLACER = Registry.register(
                BuiltInRegistries.TRUNK_PLACER_TYPE,
                ResourceKey.create(Registries.TRUNK_PLACER_TYPE, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, "fungi_tree_trunk_placer")),
                TrunkPlacerTypeInvoker.callInit(FungiTreeTrunkPlacer.CODEC)
        );
    }
}
