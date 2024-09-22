package net.rngk.mushncav.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.world.biome.ModBiomes;

public class ModMaterialRules {
    // Fungi Forest
    private static final MaterialRules.MaterialRule FUNGI_GRASS_BLOCK = makeStateRule(ModBlocks.FUNGI_GRASS_BLOCK);
    private static final MaterialRules.MaterialRule FUNGI_DIRT = makeStateRule(ModBlocks.FUNGI_DIRT);

    // Glowing Mushroom
    private static final MaterialRules.MaterialRule GLOWING_MUSHROOM_GRASS_BLOCK = makeStateRule(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK);
    //private static final MaterialRules.MaterialRule GLOWING_MUSHROOM_DIRT = makeStateRule(ModBlocks.GLOWING_MUSHROOM_DIRT);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialCondition surface = MaterialRules.surface();

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, FUNGI_GRASS_BLOCK), FUNGI_DIRT);
        //MaterialRules.MaterialRule glowingMushroomGrassUnderground = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GLOWING_MUSHROOM_GRASS_BLOCK), GLOWING_MUSHROOM_DIRT);

        return MaterialRules.sequence(
                // Fungi Forest rules
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.FUNGI_FOREST),

                    // Default to a grass and dirt surface only
                    MaterialRules.condition(surface, MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, FUNGI_DIRT)))

                )),

                // Glowing Mushroom rules
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.GLOWING_MUSHROOMS),

                    // Underground grass
                    MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, GLOWING_MUSHROOM_GRASS_BLOCK)

                ))
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}