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
 * Fungi - applies Poison effect (50% chance, 9 seconds) when consumed
 * Quick snack item
 */
public class FungiItem extends BlockItem {
    public FungiItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        // Apply poison effect with 50% chance
        if (!world.isClient() && world.random.nextFloat() < 0.5f) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 180, 0));
        }

        return result;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        // Vanilla food is typically 1.6s; snack is 0.8s.
        return 16;
    }
}
