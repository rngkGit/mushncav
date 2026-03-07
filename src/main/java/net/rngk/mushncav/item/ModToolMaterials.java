package net.rngk.mushncav.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

public class ModToolMaterials {
    public static final ToolMaterial WOOD = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            59,       // durability (same as vanilla wood)
            2.0F,     // mining speed
            0.0F,     // attack damage bonus
            15,       // enchantability
            ItemTags.PLANKS  // repair ingredient tag
    );

    public static final ToolMaterial GLOWING_SAPPHIRE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            600,      // durability
            7.0F,     // mining speed
            2.5F,     // attack damage bonus
            20,       // enchantability
            ItemTags.REPAIRS_NETHERITE_ARMOR  // repair ingredient tag
    );
}

