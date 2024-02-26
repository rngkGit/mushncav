package net.rngk.mushncav.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DIRT = createTag("dirt");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(MushroomsAndCaverns.MOD_ID, name));
        }
    }
    public static class Items {


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MushroomsAndCaverns.MOD_ID, name));
        }
    }
}
