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
 * Glowing Blueberry - applies Glowing effect (90% chance, 5 seconds) when consumed
 * Quick snack item
 */
public class GlowingBlueberryItem extends BlockItem {
    public GlowingBlueberryItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        // Apply glowing effect with 90% chance
        if (!level.isClientSide() && level.getRandom().nextFloat() < 0.9f) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
        }

        return result;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
        // Vanilla food is typically 1.6s; snack is 0.8s.
        return 16;
    }
}
