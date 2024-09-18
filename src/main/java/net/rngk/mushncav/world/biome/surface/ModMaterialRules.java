package net.rngk.mushncav.world.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.world.biome.ModBiomes;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule FUNGI_GRASS_BLOCK = makeStateRule(ModBlocks.FUNGI_GRASS_BLOCK);
    private static final MaterialRules.MaterialRule FUNGI_DIRT = makeStateRule(ModBlocks.FUNGI_DIRT);
    private static final MaterialRules.MaterialRule test = makeStateRule(Blocks.AMETHYST_BLOCK);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, FUNGI_GRASS_BLOCK), FUNGI_DIRT);
        MaterialRules.MaterialRule testUnderground = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, FUNGI_GRASS_BLOCK), FUNGI_DIRT);

        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.FUNGI_FOREST),

                    // Default to a grass and dirt surface
                    MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface))),

                    MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.GLOWING_MUSHROOMS),
                    testUnderground

                ))

        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}