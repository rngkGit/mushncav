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
 * Glowing Grapes - applies Night Vision effect (90% chance, 30 seconds) when consumed
 */
public class GlowingGrapesItem extends BlockItem {
    public GlowingGrapesItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        // Apply night vision effect with 90% chance
        if (!level.isClientSide() && level.getRandom().nextFloat() < 0.9f) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0));
        }

        return result;
    }
}
