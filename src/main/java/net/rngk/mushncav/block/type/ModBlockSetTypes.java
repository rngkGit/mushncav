package net.rngk.mushncav.block.type;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.rngk.mushncav.MushroomsAndCaverns;

public class ModBlockSetTypes {

    public static BlockSetType FUNGI;

    public static void registerBlockSetTypes() {
        MushroomsAndCaverns.LOGGER.info("Registering Block Set Types for " + MushroomsAndCaverns.MOD_ID);
        FUNGI = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, "fungi"));
    }
}
