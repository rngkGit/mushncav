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
 * Glowing Blueberry - applies Glowing effect (90% chance, 5 seconds) when consumed
 * Quick snack item
 */
public class GlowingBlueberryItem extends BlockItem {
    public GlowingBlueberryItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        // Apply glowing effect with 90% chance
        if (!world.isClient() && world.random.nextFloat() < 0.9f) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
        }

        return result;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        // Vanilla food is typically 1.6s; snack is 0.8s.
        return 16;
    }
}
