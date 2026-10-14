package net.rngk.mushncav.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

/**
 * Fungi - applies Poison effect (50% chance, 9 seconds) when consumed
 * Quick snack item
 */
public class FungiItem extends BlockItem {
    public FungiItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        // Apply poison effect with 50% chance
        if (!level.isClientSide() && level.getRandom().nextFloat() < 0.5f) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 180, 0));
        }

        return result;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
        // Vanilla food is typically 1.6s (32 ticks); snack is 0.8s (16 ticks).
        return 16;
    }
}
