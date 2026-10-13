package net.rngk.mushncav.item;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.rngk.mushncav.MushroomsAndCaverns;

import java.util.Map;

public class ModArmorMaterials {
    public static final ResourceKey<EquipmentAsset> GLOWING_SAPPHIRE_KEY = ResourceKey.create(
            EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, "glowing_sapphire")
    );

    public static final ArmorMaterial GLOWING_SAPPHIRE = new ArmorMaterial(
            33,
            Map.of(
                    ArmorType.BOOTS, 3,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.HELMET, 3,
                    ArmorType.BODY, 6
            ),
            25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            2.0f,
            0.1f,
            ItemTags.REPAIRS_NETHERITE_ARMOR,
            GLOWING_SAPPHIRE_KEY
    );
}
