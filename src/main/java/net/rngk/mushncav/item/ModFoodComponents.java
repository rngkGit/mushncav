package net.rngk.mushncav.item;

import net.minecraft.component.type.FoodComponent;

/**
 * Food components for Mushrooms & Caverns mod.
 *
 * In Minecraft 1.21.11+, FoodComponent only handles nutrition and saturation.
 * Status effects are now added via Item.Settings.component() when registering items.
 *
 * Status effects to add in ModItems registration:
 * - FUNGI_APPLE: Poison (200 ticks = 10 sec, 30% chance)
 * - GLOWING_GRAPES: Night Vision (600 ticks = 30 sec, 90% chance)
 * - GLOWING_BLUEBERRY: Glowing (100 ticks = 5 sec, 90% chance) - snack (fast eating)
 * - FUNGI: Poison (180 ticks = 9 sec, 50% chance) - snack (fast eating)
 */
public class ModFoodComponents {
    public static final FoodComponent FUNGI_APPLE = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .build();

    public static final FoodComponent GLOWING_GRAPES = new FoodComponent.Builder()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build();

    public static final FoodComponent GLOWING_BLUEBERRY = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.1f)
            .build();

    public static final FoodComponent FUNGI = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.1f)
            .build();
}


