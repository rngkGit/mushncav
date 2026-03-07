package net.rngk.mushncav.item.custom;

import net.minecraft.item.AxeItem;
import net.rngk.mushncav.item.ModToolMaterials;

/**
 * Fungi Axe item.
 * Uses standard AxeItem functionality - fungi wood stripping is registered via StrippableBlockRegistry.
 */
public class FungiAxeItem extends AxeItem {

    public FungiAxeItem(Settings settings) {
        super(ModToolMaterials.WOOD, 3.0f, -3.0f, settings);
    }
}
