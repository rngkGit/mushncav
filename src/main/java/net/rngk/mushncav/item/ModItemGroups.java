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
                        entries.add(ModItems.GLOWING_SAPHIRE_SHARD);
                        entries.add(ModItems.GLOWING_SAPHIRE_CRYSTAL);

                        entries.add(ModItems.FUNGI_APPLE);

                        entries.add(ModBlocks.FUNGI_TREE_LOG);
                        entries.add(ModBlocks.FUNGI_TREE_LEAVES);

                        entries.add(ModBlocks.GLOWING_SAPHIRE_ORE);
                        entries.add(ModBlocks.DEEPSLATE_GLOWING_SAPHIRE_ORE);
                    }).build());
    public static void registerItemGroups(){
        MushroomsAndCaverns.LOGGER.info("Registering item groups for " + MushroomsAndCaverns.MOD_ID);
    }
}
