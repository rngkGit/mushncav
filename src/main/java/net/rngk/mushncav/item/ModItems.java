package net.rngk.mushncav.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
//import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
//import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
//import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.custom.MagicStone;

public class ModItems {
    public static final Item FUNGI_STICK = registerItem("fungi_stick", new Item(new FabricItemSettings()));
    public static final Item AMETHYST_MUSHROOM_STICK = registerItem("amethyst_mushroom_stick", new Item(new FabricItemSettings()));
    //public static final Item FUNGI_APPLE = registerItem("fungiapple", new Item(new FabricItemSettings()));
    public static final Item FUNGI_BONE = registerItem("fungi_bone", new Item(new FabricItemSettings()));
    public static final Item MUSHROOM_BONE = registerItem("mushroom_bone", new Item(new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_SHARD = registerItem("glowing_sapphire_shard", new Item(new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_STONE = registerItem("glowing_sapphire_stone", new Item(new FabricItemSettings()));
    public static final Item MAGIC_STONE = registerItem("magic_stone", new MagicStone(new FabricItemSettings()));
    public static final Item FUNGI_APPLE = registerItem("fungi_apple", new Item(new FabricItemSettings().food(ModFoodComponents.FUNGI_APPLE)));

    // Glowing Mushroom Stuff
    public static final Item GLOWING_MUSHROOM_STICK = registerItem("glowing_mushroom_stick", new Item(new FabricItemSettings()));
    public static final Item GLOWING_BLUEBERRY = registerItem("glowing_blueberry", (Item)new AliasedBlockItem(ModBlocks.GLOWING_MUSHROOM_VINES, new Item.Settings().food(ModFoodComponents.GLOWING_BLUEBERRY)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MushroomsAndCaverns.MOD_ID, name), item);
    }

    public static void registerModItems(){
        MushroomsAndCaverns.LOGGER.info("Registering mod items for " + MushroomsAndCaverns.MOD_ID);
    }
}
