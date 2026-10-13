package net.rngk.mushncav.world.tree.custom;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.mixin.TreeDecoratorTypeInvoker;
import net.rngk.mushncav.world.tree.FungiTreeDecorator;

public class ModTreeDecoratorTypes {
    public static TreeDecoratorType<FungiTreeDecorator> FUNGI_PLACER;

    public static void register() {
        MushroomsAndCaverns.LOGGER.info("Registering Tree Decorator Types for " + MushroomsAndCaverns.MOD_ID);

        FUNGI_PLACER = Registry.register(
                BuiltInRegistries.TREE_DECORATOR_TYPE,
                ResourceKey.create(Registries.TREE_DECORATOR_TYPE, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, "fungi_placer")),
                TreeDecoratorTypeInvoker.callInit(FungiTreeDecorator.CODEC)
        );
    }
}
