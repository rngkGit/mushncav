package net.rngk.mushncav.world.tree;

import net.minecraft.world.gen.root.RootPlacer;
import net.minecraft.world.gen.root.RootPlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.mixin.RootPlacerTypeInvoker;
import net.rngk.mushncav.mixin.TrunkPlacerTypeInvoker;
import net.rngk.mushncav.world.tree.custom.FungiTreeRootPlacer;
import net.rngk.mushncav.world.tree.custom.FungiTreeTrunkPlacer;

public class ModRootPlacerTypes {
    public static final RootPlacerType<?> FUNGI_TREE_ROOT_PLACER = RootPlacerTypeInvoker.callRegister("fungi_tree_root_placer", FungiTreeRootPlacer.CODEC);

    public static void register() {
        MushroomsAndCaverns.LOGGER.info("Registering Root Placers for " + MushroomsAndCaverns.MOD_ID);
    }
}
