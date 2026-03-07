package net.rngk.mushncav.world.biome;

import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.world.biome.surface.ModMaterialRules;
import terrablender.api.SurfaceRuleManager;

public class ModTerrablenderInitializer {
    private static boolean surfaceRulesRegistered = false;

    /**
     * Register surface rules after blocks have been initialized.
     * This is called from ModBlocks after block registration to ensure
     * that block references are not null.
     */
    public static void registerSurfaceRules() {
        if (surfaceRulesRegistered) {
            return;
        }
        surfaceRulesRegistered = true;

        // Register custom surface rules for terrain generation
        // This applies custom terrain materials to the biomes
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MushroomsAndCaverns.MOD_ID, ModMaterialRules.makeRules());
    }
}

