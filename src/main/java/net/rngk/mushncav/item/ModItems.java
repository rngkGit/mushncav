package net.rngk.mushncav.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.custom.*;

import java.util.function.Function;

public class ModItems {
    // All items - initialized in registerModItems()
    public static Item FUNGI_STICK;
    public static Item GLOWING_SAPPHIRE_SHARD;
    public static Item GLOWING_SAPPHIRE_STONE;
    public static Item MAGIC_STONE;
    public static Item FUNGI_APPLE;
    public static Item FUNGI;
    public static Item FUNGI_SIGN;
    public static Item FUNGI_HANGING_SIGN;
    public static Item GLOWING_GRAPES;
    public static Item GLOWING_BLUEBERRY;

    // Tool items
    public static Item FUNGI_AXE;
    public static Item GLOWING_SAPPHIRE_SWORD;
    public static Item GLOWING_SAPPHIRE_PICKAXE;
    public static Item GLOWING_SAPPHIRE_SHOVEL;
    public static Item GLOWING_SAPPHIRE_AXE;
    public static Item GLOWING_SAPPHIRE_HOE;

    // Armor items
    public static Item GLOWING_SAPPHIRE_HELMET;
    public static Item GLOWING_SAPPHIRE_CHESTPLATE;
    public static Item GLOWING_SAPPHIRE_LEGGINGS;
    public static Item GLOWING_SAPPHIRE_BOOTS;

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, name));
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> factory) {
        ResourceKey<Item> key = itemKey(name);
        Item.Properties properties = new Item.Properties().setId(key);
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(properties));
    }

    public static void registerModItems() {
        MushroomsAndCaverns.LOGGER.info("Registering mod items for " + MushroomsAndCaverns.MOD_ID);

        FUNGI_STICK = registerItem("fungi_stick", Item::new);
        GLOWING_SAPPHIRE_SHARD = registerItem("glowing_sapphire_shard", Item::new);
        GLOWING_SAPPHIRE_STONE = registerItem("glowing_sapphire_stone", Item::new);
        MAGIC_STONE = registerItem("magic_stone", MagicStone::new);

        // Food items with custom status effects
        FUNGI_APPLE = registerItem("fungi_apple", properties -> new FungiAppleItem(properties.food(ModFoodComponents.FUNGI_APPLE)));
        FUNGI = registerItem("fungi", properties -> new FungiItem(ModBlocks.FUNGI_BLOCK, properties.food(ModFoodComponents.FUNGI)));

        FUNGI_SIGN = registerItem("fungi_sign", properties -> new SignItem(ModBlocks.FUNGI_SIGN, ModBlocks.FUNGI_WALL_SIGN, properties.stacksTo(16)));
        FUNGI_HANGING_SIGN = registerItem("fungi_hanging_sign", properties -> new HangingSignItem(ModBlocks.FUNGI_HANGING_SIGN, ModBlocks.FUNGI_WALL_HANGING_SIGN, properties.stacksTo(16)));

        GLOWING_GRAPES = registerItem("glowing_grapes", properties -> new GlowingGrapesItem(ModBlocks.GLOWING_MUSHROOM_VINES, properties.food(ModFoodComponents.GLOWING_GRAPES)));
        GLOWING_BLUEBERRY = registerItem("glowing_blueberry", properties -> new GlowingBlueberryItem(ModBlocks.GLOWING_BLUEBERRY_BUSH, properties.food(ModFoodComponents.GLOWING_BLUEBERRY)));

        // Tool items
        FUNGI_AXE = registerItem("fungi_axe", FungiAxeItem::new);

        // Glowing Sapphire tools
        GLOWING_SAPPHIRE_SWORD = registerItem("glowing_sapphire_sword",
                properties -> new Item(properties.sword(ModToolMaterials.GLOWING_SAPPHIRE, 3, -2.4f)));
        GLOWING_SAPPHIRE_PICKAXE = registerItem("glowing_sapphire_pickaxe",
                properties -> new Item(properties.pickaxe(ModToolMaterials.GLOWING_SAPPHIRE, 1, -2.8f)));
        GLOWING_SAPPHIRE_SHOVEL = registerItem("glowing_sapphire_shovel",
                properties -> new ShovelItem(ModToolMaterials.GLOWING_SAPPHIRE, 1.5f, -3.0f, properties));
        GLOWING_SAPPHIRE_AXE = registerItem("glowing_sapphire_axe",
                properties -> new AxeItem(ModToolMaterials.GLOWING_SAPPHIRE, 5.0f, -3.0f, properties));
        GLOWING_SAPPHIRE_HOE = registerItem("glowing_sapphire_hoe",
                properties -> new HoeItem(ModToolMaterials.GLOWING_SAPPHIRE, -3.0f, 0.0f, properties));

        // Armor items
        GLOWING_SAPPHIRE_HELMET = registerItem("glowing_sapphire_helmet",
                properties -> new Item(properties.humanoidArmor(ModArmorMaterials.GLOWING_SAPPHIRE, ArmorType.HELMET)));
        GLOWING_SAPPHIRE_CHESTPLATE = registerItem("glowing_sapphire_chestplate",
                properties -> new Item(properties.humanoidArmor(ModArmorMaterials.GLOWING_SAPPHIRE, ArmorType.CHESTPLATE)));
        GLOWING_SAPPHIRE_LEGGINGS = registerItem("glowing_sapphire_leggings",
                properties -> new Item(properties.humanoidArmor(ModArmorMaterials.GLOWING_SAPPHIRE, ArmorType.LEGGINGS)));
        GLOWING_SAPPHIRE_BOOTS = registerItem("glowing_sapphire_boots",
                properties -> new Item(properties.humanoidArmor(ModArmorMaterials.GLOWING_SAPPHIRE, ArmorType.BOOTS)));
    }
}
