package net.rngk.mushncav;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.rngk.mushncav.datagen.*;
import net.rngk.mushncav.world.ModConfiguredFeatures;
import net.rngk.mushncav.world.ModPlacedFeatures;
import net.rngk.mushncav.world.biome.ModBiomes;

public class MushroomsAndCavernsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		// Models are loaded directly from assets/mushncav/models/item/*.json and textures

		pack.addProvider(ModWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::boostrap);
	}
}