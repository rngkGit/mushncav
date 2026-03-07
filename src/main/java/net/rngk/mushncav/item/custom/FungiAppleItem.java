package net.rngk.mushncav.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Fungi Apple - applies Poison effect (30% chance, 10 seconds) when consumed
 */
public class FungiAppleItem extends Item {
    public FungiAppleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        // Apply poison effect with 30% chance
        if (!world.isClient() && world.random.nextFloat() < 0.3f) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 0));
        }

        return result;
    }
}


