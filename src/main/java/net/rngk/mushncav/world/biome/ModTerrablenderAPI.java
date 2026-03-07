package net.rngk.mushncav.world.biome;

import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        // Register custom biome regions
        // This makes the custom biomes spawn in the world
        Regions.register(new ModOverworldRegion(Identifier.of(MushroomsAndCaverns.MOD_ID, "overworld"), 4));

        // Surface rules are registered later in ModTerrablenderInitializer
        // after blocks have been initialized to avoid NullPointerException
    }
}


