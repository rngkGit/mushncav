package net.rngk.mushncav.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
//import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
//import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
//import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;

public class ModItems {
    //Items (unorganized, I will do that later)
    public static final Item FUNGI_STICK = registerItem("fungistick", new Item(new FabricItemSettings()));
    public static final Item AMETHYST_MUSHROOM_STICK = registerItem("amethystmushroomstick", new Item(new FabricItemSettings()));
    public static final Item GLOWING_MUSHROOM_STICK = registerItem("glowingmushroomstick", new Item(new FabricItemSettings()));
    //public static final Item FUNGI_APPLE = registerItem("fungiapple", new Item(new FabricItemSettings()));
    public static final Item FUNGI_BONE = registerItem("fungibone", new Item(new FabricItemSettings()));
    public static final Item MUSHROOM_BONE = registerItem("mushroombone", new Item(new FabricItemSettings()));
    public static final Item GLOWING_SAPHIRE_SHARD = registerItem("glowingsaphireshard", new Item(new FabricItemSettings()));
    public static final Item GLOWING_SAPHIRE_CRYSTAL = registerItem("glowingsaphirecrystal", new Item(new FabricItemSettings()));
    public static final Item MAGIC_STONE = registerItem("magicstone", new Item(new FabricItemSettings()));

    /*private static void addItemsToIngredientTab(FabricItemGroupEntries entries){
        //add stuff to the existing ingredients tab, this code is for reference and probally wont be used ever, but Ill leave it here.
        entries.add(MAGIC_STONE);
    }*/

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MushroomsAndCaverns.MOD_ID, name), item);
    }

    public static void registerModItems(){
        MushroomsAndCaverns.LOGGER.info("Registering mod items for " + MushroomsAndCaverns.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTab);
    }
}
