package net.rngk.mushncav;

import net.fabricmc.api.ModInitializer;
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