package net.rngk.mushncav.item;

import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.custom.*;

public class ModItems {
    // All items - initialized lazily in registerModItems()
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

    private static Item.Settings itemSettings(String name) {
        Identifier id = Identifier.of(MushroomsAndCaverns.MOD_ID, name);
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id));
    }

    private static Item registerItem(String name, java.util.function.Function<Item.Settings, Item> factory){
        Identifier id = Identifier.of(MushroomsAndCaverns.MOD_ID, name);
        return Registry.register(Registries.ITEM, id, factory.apply(itemSettings(name)));
    }

    public static void registerModItems(){
        MushroomsAndCaverns.LOGGER.info("Registering mod items for " + MushroomsAndCaverns.MOD_ID);

        // Register deterministically; fail fast if anything is invalid.
        FUNGI_STICK = registerItem("fungi_stick", Item::new);
        GLOWING_SAPPHIRE_SHARD = registerItem("glowing_sapphire_shard", Item::new);
        GLOWING_SAPPHIRE_STONE = registerItem("glowing_sapphire_stone", Item::new);
        MAGIC_STONE = registerItem("magic_stone", MagicStone::new);

        // Food items with custom status effects
        FUNGI_APPLE = registerItem("fungi_apple", settings -> new FungiAppleItem(settings.food(ModFoodComponents.FUNGI_APPLE)));
        FUNGI = registerItem("fungi", settings -> new FungiItem(ModBlocks.FUNGI_BLOCK, settings.food(ModFoodComponents.FUNGI)));

        FUNGI_SIGN = registerItem("fungi_sign", settings -> new SignItem(ModBlocks.FUNGI_SIGN, ModBlocks.FUNGI_WALL_SIGN, settings.maxCount(16)));
        FUNGI_HANGING_SIGN = registerItem("fungi_hanging_sign", settings -> new HangingSignItem(ModBlocks.FUNGI_HANGING_SIGN, ModBlocks.FUNGI_WALL_HANGING_SIGN, settings.maxCount(16)));

        GLOWING_GRAPES = registerItem("glowing_grapes", settings -> new GlowingGrapesItem(ModBlocks.GLOWING_MUSHROOM_VINES, settings.food(ModFoodComponents.GLOWING_GRAPES)));
        GLOWING_BLUEBERRY = registerItem("glowing_blueberry", settings -> new GlowingBlueberryItem(ModBlocks.GLOWING_BLUEBERRY_BUSH, settings.food(ModFoodComponents.GLOWING_BLUEBERRY)));

        // Tool items - Fungi Axe registered as custom Item
        FUNGI_AXE = registerItem("fungi_axe", FungiAxeItem::new);

        // Glowing Sapphire tools - 1.21.11 pattern
        GLOWING_SAPPHIRE_SWORD = registerItem("glowing_sapphire_sword",
                settings -> new Item(settings.sword(ModToolMaterials.GLOWING_SAPPHIRE, 3, -2.4f)));
        GLOWING_SAPPHIRE_PICKAXE = registerItem("glowing_sapphire_pickaxe",
                settings -> new Item(settings.pickaxe(ModToolMaterials.GLOWING_SAPPHIRE, 1, -2.8f)));
        GLOWING_SAPPHIRE_SHOVEL = registerItem("glowing_sapphire_shovel",
                settings -> new ShovelItem(ModToolMaterials.GLOWING_SAPPHIRE, 1.5f, -3.0f, settings));
        GLOWING_SAPPHIRE_AXE = registerItem("glowing_sapphire_axe",
                settings -> new AxeItem(ModToolMaterials.GLOWING_SAPPHIRE, 5.0f, -3.0f, settings));
        GLOWING_SAPPHIRE_HOE = registerItem("glowing_sapphire_hoe",
                settings -> new HoeItem(ModToolMaterials.GLOWING_SAPPHIRE, -3.0f, 0.0f, settings));

        // Armor items - 1.21.11 pattern
        GLOWING_SAPPHIRE_HELMET = registerItem("glowing_sapphire_helmet",
                settings -> new Item(settings.armor(ModArmorMaterials.GLOWING_SAPPHIRE, EquipmentType.HELMET)));
        GLOWING_SAPPHIRE_CHESTPLATE = registerItem("glowing_sapphire_chestplate",
                settings -> new Item(settings.armor(ModArmorMaterials.GLOWING_SAPPHIRE, EquipmentType.CHESTPLATE)));
        GLOWING_SAPPHIRE_LEGGINGS = registerItem("glowing_sapphire_leggings",
                settings -> new Item(settings.armor(ModArmorMaterials.GLOWING_SAPPHIRE, EquipmentType.LEGGINGS)));
        GLOWING_SAPPHIRE_BOOTS = registerItem("glowing_sapphire_boots",
                settings -> new Item(settings.armor(ModArmorMaterials.GLOWING_SAPPHIRE, EquipmentType.BOOTS)));
    }
}
