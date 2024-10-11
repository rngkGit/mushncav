package net.rngk.mushncav.world.tree.custom;

import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.mixin.TrunkPlacerTypeInvoker;
import net.rngk.mushncav.world.tree.FungiTreeTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final TrunkPlacerType<?> FUNGI_TREE_PLACER = TrunkPlacerTypeInvoker.callRegister("fungi_tree_trunk_placer", FungiTreeTrunkPlacer.CODEC);

    public static void register() {
        MushroomsAndCaverns.LOGGER.info("Registering Trunk Placers for " + MushroomsAndCaverns.MOD_ID);
    }
}
