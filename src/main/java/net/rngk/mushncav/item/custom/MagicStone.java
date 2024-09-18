package net.rngk.mushncav.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MagicStone extends Item {
    public MagicStone(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }
}
