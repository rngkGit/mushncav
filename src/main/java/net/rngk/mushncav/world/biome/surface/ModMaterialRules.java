package net.rngk.mushncav.world.biome.surface;

import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.world.biome.ModBiomes;

public class ModMaterialRules {

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialCondition surface = MaterialRules.surface();

        // Fungi Forest biome uses custom fungi grass and fungi dirt
        MaterialRules.MaterialRule FUNGI_GRASS = MaterialRules.block(ModBlocks.FUNGI_GRASS_BLOCK.getDefaultState());
        MaterialRules.MaterialRule FUNGI_DIRT = MaterialRules.block(ModBlocks.FUNGI_DIRT.getDefaultState());

        // Glowing Mushrooms biome uses glowing mushroom grass
        MaterialRules.MaterialRule GLOWING_GRASS = MaterialRules.block(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK.getDefaultState());
        MaterialRules.MaterialRule GLOWING_DIRT = MaterialRules.block(net.minecraft.block.Blocks.DIRT.getDefaultState());

        // Fungi Forest surface rules
        MaterialRules.MaterialRule fungiForesSurface = MaterialRules.sequence(
                MaterialRules.condition(isAtOrAboveWaterLevel, FUNGI_GRASS),
                FUNGI_DIRT
        );

        // Glowing Mushrooms surface rules
        MaterialRules.MaterialRule glowingMushroomsSurface = MaterialRules.sequence(
                MaterialRules.condition(isAtOrAboveWaterLevel, GLOWING_GRASS),
                GLOWING_DIRT
        );

        return MaterialRules.sequence(
                // Glowing Mushrooms biome rules
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.GLOWING_MUSHROOMS),
                        MaterialRules.condition(surface, MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, glowingMushroomsSurface),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, GLOWING_DIRT)
                        ))
                )),
                // Fungi Forest biome rules
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.FUNGI_FOREST),
                        MaterialRules.condition(surface, MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, fungiForesSurface),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, FUNGI_DIRT)
                        ))
                ))
        );
    }
}