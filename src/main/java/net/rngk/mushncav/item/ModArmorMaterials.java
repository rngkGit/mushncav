package net.rngk.mushncav.item;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.rngk.mushncav.MushroomsAndCaverns;

import java.util.EnumMap;

public class ModArmorMaterials {
    static RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("equipment_asset"));
    public static final RegistryKey<EquipmentAsset> GLOWING_SAPPHIRE_KEY = RegistryKey.of(REGISTRY_KEY, Identifier.of(MushroomsAndCaverns.MOD_ID, "glowing_sapphire"));

    public static final ArmorMaterial GLOWING_SAPPHIRE = new ArmorMaterial(33, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 3);
        map.put(EquipmentType.LEGGINGS, 6);
        map.put(EquipmentType.CHESTPLATE, 8);
        map.put(EquipmentType.HELMET, 3);
        map.put(EquipmentType.BODY, 6);
    }), 25, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0f, 0.1f, ItemTags.REPAIRS_NETHERITE_ARMOR, GLOWING_SAPPHIRE_KEY);
}

