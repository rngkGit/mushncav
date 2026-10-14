package net.rngk.mushncav.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodComponents {
    public static final FoodProperties FUNGI_APPLE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .build();

    public static final FoodProperties GLOWING_GRAPES = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build();

    public static final FoodProperties GLOWING_BLUEBERRY = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1f)
            .build();

    public static final FoodProperties FUNGI = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1f)
            .build();
}
