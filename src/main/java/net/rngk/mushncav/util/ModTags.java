package net.rngk.mushncav.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.rngk.mushncav.MushroomsAndCaverns;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DIRT = createTag("dirt");
        public static final TagKey<Block> FUNGI_LOGS = createTag("fungi_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FUNGI_LOGS = createTag("fungi_logs");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, name));
        }
    }
}
