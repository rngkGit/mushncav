package net.rngk.mushncav;

import net.fabricmc.api.ModInitializer;

import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.ModItemGroups;
import net.rngk.mushncav.item.ModItems;
import net.rngk.mushncav.world.gen.ModWorldGeneration;
import net.rngk.mushncav.world.tree.custom.ModTreeDecoratorTypes;
import net.rngk.mushncav.world.tree.custom.ModTrunkPlacerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MushroomsAndCaverns implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "mushncav";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Mushrooms and Caverns mod successfully initialized");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModItemGroups.registerItemGroups();

		ModTrunkPlacerTypes.register();
		ModTreeDecoratorTypes.register();

		ModWorldGeneration.generateModWorldGen();
	}
}