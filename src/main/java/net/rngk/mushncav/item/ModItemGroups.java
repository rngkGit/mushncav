package net.rngk.mushncav.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup MUSHNCAVE_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(MushroomsAndCaverns.MOD_ID, "mushncav"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mushncav"))
                    .icon(() -> new ItemStack(ModItems.MAGIC_STONE)).entries((displayContext, entries) -> {
                        //In order
                        entries.add(ModItems.MAGIC_STONE);
                        entries.add(ModItems.FUNGI_BONE);
                        entries.add(ModItems.MUSHROOM_BONE);
                        entries.add(ModItems.FUNGI_STICK);
                        entries.add(ModItems.AMETHYST_MUSHROOM_STICK);
                        entries.add(ModItems.GLOWING_MUSHROOM_STICK);
                        entries.add(ModItems.GLOWING_SAPPHIRE_SHARD);
                        entries.add(ModItems.GLOWING_SAPPHIRE_STONE);

                        entries.add(ModItems.FUNGI_APPLE);

                        entries.add(ModBlocks.FUNGI_TREE_LOG);
                        entries.add(ModBlocks.FUNGI_TREE_WOOD);
                        entries.add(ModBlocks.FUNGI_TREE_LEAVES);
                        entries.add(ModBlocks.FUNGI_TREE_SAPLING);
                        entries.add(ModBlocks.FUNGI_MUSHROOM_STEM);
                        entries.add(ModBlocks.FUNGI_MUSHROOM_BLOCK);
                        entries.add(ModBlocks.FUNGI_MUSHROOM);
                        entries.add(ModBlocks.FUNGI_GRASS_BLOCK);
                        entries.add(ModBlocks.FUNGI_DIRT);
                        entries.add(ModItems.FUNGI);

                        entries.add(ModBlocks.GLOWING_MUSHROOM);
                        entries.add(ModBlocks.GLOWING_MUSHROOM_STEM);
                        entries.add(ModBlocks.GLOWING_MUSHROOM_BLOCK);
                        entries.add(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK);
                        entries.add(ModBlocks.GLOWING_SHORT_GRASS);
                        entries.add(ModBlocks.GLOWING_TALL_GRASS);

                        entries.add(ModItems.GLOWING_GRAPES);

                        entries.add(ModBlocks.GLOWING_SAPPHIRE_ORE);
                        entries.add(ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE);

                        entries.add(ModBlocks.GLOWING_SAPPHIRE_BLOCK);
                    }).build());
    public static void registerItemGroups(){
        MushroomsAndCaverns.LOGGER.info("Registering item groups for " + MushroomsAndCaverns.MOD_ID);
    }
}
