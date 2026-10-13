package net.rngk.mushncav.world.biome.surface;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.world.biome.ModBiomes;

public class ModMaterialRules {

    public static SurfaceRules.RuleSource makeRules(HolderGetter<Biome> biomeGetter) {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource surface = SurfaceRules.abovePreliminarySurface();

        // Fungi Forest biome uses custom fungi grass and fungi dirt
        SurfaceRules.RuleSource FUNGI_GRASS = SurfaceRules.state(ModBlocks.FUNGI_GRASS_BLOCK.defaultBlockState());
        SurfaceRules.RuleSource FUNGI_DIRT = SurfaceRules.state(ModBlocks.FUNGI_DIRT.defaultBlockState());

        // Glowing Mushrooms biome uses glowing mushroom grass
        SurfaceRules.RuleSource GLOWING_GRASS = SurfaceRules.state(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK.defaultBlockState());
        SurfaceRules.RuleSource GLOWING_DIRT = SurfaceRules.state(Blocks.DIRT.defaultBlockState());

        // Fungi Forest surface rules
        SurfaceRules.RuleSource fungiForestSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, FUNGI_GRASS),
                FUNGI_DIRT
        );

        // Glowing Mushrooms surface rules
        SurfaceRules.RuleSource glowingMushroomsSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GLOWING_GRASS),
                GLOWING_DIRT
        );

        return SurfaceRules.sequence(
                // Glowing Mushrooms biome rules
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(biomeGetter, ModBiomes.GLOWING_MUSHROOMS),
                        SurfaceRules.ifTrue(surface, SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, glowingMushroomsSurface),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, GLOWING_DIRT)
                        ))
                )),
                // Fungi Forest biome rules
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(biomeGetter, ModBiomes.FUNGI_FOREST),
                        SurfaceRules.ifTrue(surface, SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, fungiForestSurface),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FUNGI_DIRT)
                        ))
                ))
        );
    }
}