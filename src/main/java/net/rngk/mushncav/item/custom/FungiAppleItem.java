package net.rngk.mushncav.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Fungi Apple - applies Poison effect (30% chance, 10 seconds) when consumed
 */
public class FungiAppleItem extends Item {
    public FungiAppleItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        // Apply poison effect with 30% chance
        if (!level.isClientSide() && level.getRandom().nextFloat() < 0.3f) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
        }

        return result;
    }
}
