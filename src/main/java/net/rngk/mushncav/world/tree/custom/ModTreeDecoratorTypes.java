package net.rngk.mushncav.world.tree.custom;

import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.mixin.TreeDecoratorTypeInvoker;
import net.rngk.mushncav.world.tree.FungiTreeDecorator;

public class ModTreeDecoratorTypes {
    public static final TreeDecoratorType<?> FUNGI_PLACER = TreeDecoratorTypeInvoker.callRegister("fungi_placer", FungiTreeDecorator.CODEC);

    public static void register() {
        MushroomsAndCaverns.LOGGER.info("Registering Tree Decorator Types for " + MushroomsAndCaverns.MOD_ID);
    }
}
