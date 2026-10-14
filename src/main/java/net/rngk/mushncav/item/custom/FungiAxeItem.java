package net.rngk.mushncav.item.custom;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.rngk.mushncav.item.ModToolMaterials;

public class FungiAxeItem extends AxeItem {
    public FungiAxeItem(Item.Properties properties) {
        super(ModToolMaterials.WOOD, 3.0f, -3.0f, properties);
    }
}
