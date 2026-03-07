package net.rngk.mushncav;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.block.entity.ModBlockEntities;
import net.rngk.mushncav.block.type.ModBlockSetTypes;
import net.rngk.mushncav.block.type.ModWoodTypes;
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
		LOGGER.info("Mushrooms and Caverns mod successfully initialized");

		ModBlockSetTypes.registerBlockSetTypes();
		ModWoodTypes.registerWoodTypes();

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();

		ModTrunkPlacerTypes.register();
		ModTreeDecoratorTypes.register();

		ModBlockEntities.registerBlockEntities();

		ModWorldGeneration.generateModWorldGen();
	}
}