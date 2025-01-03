package net.rngk.mushncav.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent FUNGI_APPLE = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 200), 0.3f).build();
    public static final FoodComponent GLOWING_GRAPES = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600), 0.9f).build();
    public static final FoodComponent GLOWING_BLUEBERRY = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).snack().statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100), 0.9f).build();
    public static final FoodComponent FUNGI = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).snack().statusEffect(new StatusEffectInstance(StatusEffects.POISON, 180), 0.5f).build();

}
