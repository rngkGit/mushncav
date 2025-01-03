package net.rngk.mushncav.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
//import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
//import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
//import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.custom.MagicStone;

public class ModItems {
    public static final Item FUNGI_STICK = registerItem("fungi_stick", new Item(new FabricItemSettings()));
    //public static final Item AMETHYST_MUSHROOM_STICK = registerItem("amethyst_mushroom_stick", new Item(new FabricItemSettings()));
    //public static final Item FUNGI_BONE = registerItem("fungi_bone", new Item(new FabricItemSettings()));
    //public static final Item MUSHROOM_BONE = registerItem("mushroom_bone", new Item(new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_SHARD = registerItem("glowing_sapphire_shard", new Item(new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_STONE = registerItem("glowing_sapphire_stone", new Item(new FabricItemSettings()));
    public static final Item MAGIC_STONE = registerItem("magic_stone", new MagicStone(new FabricItemSettings()));
    public static final Item FUNGI_APPLE = registerItem("fungi_apple", new Item(new FabricItemSettings().food(ModFoodComponents.FUNGI_APPLE)));
    public static final Item FUNGI = registerItem("fungi", new AliasedBlockItem(ModBlocks.FUNGI_BLOCK, new FabricItemSettings().food(ModFoodComponents.FUNGI)));

    public static final Item FUNGI_AXE = registerItem("fungi_axe", new ModAxeItem(ToolMaterials.WOOD, 3.0F, -3.0F, new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_SWORD = registerItem("glowing_sapphire_sword", new SwordItem(ModToolMaterial.GLOWING_SAPPHIRE, 4, -2.1F, new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_PICKAXE = registerItem("glowing_sapphire_pickaxe", new PickaxeItem(ModToolMaterial.GLOWING_SAPPHIRE, 1, -2.8F, new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_SHOVEL = registerItem("glowing_sapphire_shovel", new ShovelItem(ModToolMaterial.GLOWING_SAPPHIRE, 1.5F, -3.0F, new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_AXE = registerItem("glowing_sapphire_axe", new AxeItem(ModToolMaterial.GLOWING_SAPPHIRE, 5.5F, -3.0F, new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_HOE = registerItem("glowing_sapphire_hoe", new HoeItem(ModToolMaterial.GLOWING_SAPPHIRE, 1, 0.0F, new FabricItemSettings()));

    public static final Item GLOWING_SAPPHIRE_HELMET = registerItem("glowing_sapphire_helmet", new ArmorItem(ModArmorMaterials.GLOWING_SAPPHIRE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_CHESTPLATE = registerItem("glowing_sapphire_chestplate", new ArmorItem(ModArmorMaterials.GLOWING_SAPPHIRE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_LEGGINGS = registerItem("glowing_sapphire_leggings", new ArmorItem(ModArmorMaterials.GLOWING_SAPPHIRE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item GLOWING_SAPPHIRE_BOOTS = registerItem("glowing_sapphire_boots", new ArmorItem(ModArmorMaterials.GLOWING_SAPPHIRE, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item FUNGI_SIGN = registerItem("fungi_sign", new SignItem(new FabricItemSettings().maxCount(16), ModBlocks.FUNGI_SIGN, ModBlocks.FUNGI_WALL_SIGN));
    public static final Item FUNGI_HANGING_SIGN = registerItem("fungi_hanging_sign", new HangingSignItem(ModBlocks.FUNGI_HANGING_SIGN, ModBlocks.FUNGI_WALL_HANGING_SIGN, new FabricItemSettings().maxCount(16)));

    // Glowing Mushroom Stuff
    //public static final Item GLOWING_MUSHROOM_STICK = registerItem("glowing_mushroom_stick", new Item(new FabricItemSettings()));
    public static final Item GLOWING_GRAPES = registerItem("glowing_grapes", new AliasedBlockItem(ModBlocks.GLOWING_MUSHROOM_VINES, new FabricItemSettings().food(ModFoodComponents.GLOWING_GRAPES)));
    public static final Item GLOWING_BLUEBERRY = registerItem("glowing_blueberry", new AliasedBlockItem(ModBlocks.GLOWING_BLUEBERRY_BUSH, new FabricItemSettings().food(ModFoodComponents.GLOWING_BLUEBERRY)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MushroomsAndCaverns.MOD_ID, name), item);
    }

    public static void registerModItems(){
        MushroomsAndCaverns.LOGGER.info("Registering mod items for " + MushroomsAndCaverns.MOD_ID);
    }
}
