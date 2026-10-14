package net.rngk.mushncav.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;

public class ModItemGroups {
    public static final ResourceKey<CreativeModeTab> MUSHNCAV_GROUP_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, "mushncav")
    );

    public static final CreativeModeTab MUSHNCAV_GROUP = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            MUSHNCAV_GROUP_KEY,
            FabricCreativeModeTab.builder()
                    .title(Component.translatable("itemgroup.mushncav"))
                    .icon(() -> new ItemStack(ModItems.MAGIC_STONE))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.FUNGI_TREE_LOG);
                        output.accept(ModBlocks.STRIPPED_FUNGI_TREE_LOG);
                        output.accept(ModBlocks.FUNGI_TREE_WOOD);
                        output.accept(ModBlocks.STRIPPED_FUNGI_TREE_WOOD);
                        output.accept(ModBlocks.FUNGI_TREE_LEAVES);
                        output.accept(ModBlocks.FUNGI_TREE_SAPLING);
                        output.accept(ModItems.FUNGI_STICK);
                        output.accept(ModBlocks.FUNGI_PLANKS);
                        output.accept(ModBlocks.FUNGI_SLAB);
                        output.accept(ModBlocks.FUNGI_STAIRS);
                        output.accept(ModBlocks.FUNGI_FENCE);
                        output.accept(ModBlocks.FUNGI_FENCE_GATE);
                        output.accept(ModBlocks.FUNGI_DOOR);
                        output.accept(ModBlocks.FUNGI_TRAPDOOR);
                        output.accept(ModBlocks.FUNGI_BUTTON);
                        output.accept(ModItems.FUNGI_SIGN);
                        output.accept(ModItems.FUNGI_HANGING_SIGN);
                        output.accept(ModItems.FUNGI_APPLE);
                        output.accept(ModItems.FUNGI);

                        output.accept(ModItems.FUNGI_AXE);

                        output.accept(ModBlocks.FUNGI_MUSHROOM);
                        output.accept(ModBlocks.FUNGI_MUSHROOM_STEM);
                        output.accept(ModBlocks.FUNGI_MUSHROOM_BLOCK);
                        output.accept(ModBlocks.FUNGI_GRASS_BLOCK);
                        output.accept(ModBlocks.FUNGI_DIRT);

                        output.accept(ModBlocks.GLOWING_MUSHROOM);
                        output.accept(ModBlocks.GLOWING_MUSHROOM_STEM);
                        output.accept(ModBlocks.GLOWING_MUSHROOM_BLOCK);
                        output.accept(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK);
                        output.accept(ModBlocks.GLOWING_SHORT_GRASS);
                        output.accept(ModBlocks.GLOWING_TALL_GRASS);

                        output.accept(ModItems.GLOWING_GRAPES);
                        output.accept(ModItems.GLOWING_BLUEBERRY);

                        output.accept(ModItems.GLOWING_SAPPHIRE_SWORD);
                        output.accept(ModItems.GLOWING_SAPPHIRE_PICKAXE);
                        output.accept(ModItems.GLOWING_SAPPHIRE_AXE);
                        output.accept(ModItems.GLOWING_SAPPHIRE_SHOVEL);
                        output.accept(ModItems.GLOWING_SAPPHIRE_HOE);

                        output.accept(ModItems.GLOWING_SAPPHIRE_HELMET);
                        output.accept(ModItems.GLOWING_SAPPHIRE_CHESTPLATE);
                        output.accept(ModItems.GLOWING_SAPPHIRE_LEGGINGS);
                        output.accept(ModItems.GLOWING_SAPPHIRE_BOOTS);

                        output.accept(ModBlocks.GLOWING_SAPPHIRE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE);

                        output.accept(ModItems.GLOWING_SAPPHIRE_SHARD);
                        output.accept(ModItems.GLOWING_SAPPHIRE_STONE);

                        output.accept(ModBlocks.GLOWING_SAPPHIRE_BLOCK);

                        output.accept(ModItems.MAGIC_STONE);
                    }).build()
    );

    public static void registerItemGroups() {
        MushroomsAndCaverns.LOGGER.info("Registering item groups for " + MushroomsAndCaverns.MOD_ID);
    }
}
