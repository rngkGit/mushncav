package net.rngk.mushncav.item.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Glowing Grapes - applies Night Vision effect (90% chance, 30 seconds) when consumed
 */
public class GlowingGrapesItem extends BlockItem {
    public GlowingGrapesItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        // Apply night vision effect with 90% chance
        if (!world.isClient() && world.random.nextFloat() < 0.9f) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600, 0));
        }

        return result;
    }
}


