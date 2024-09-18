package net.rngk.mushncav.world.biome;

import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.world.biome.surface.ModMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(new Identifier(MushroomsAndCaverns.MOD_ID, "overworld"), 4));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MushroomsAndCaverns.MOD_ID, ModMaterialRules.makeRules());
    }
}
