package net.rngk.mushncav.block.type;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.block.BlockSetType;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;

public class ModBlockSetTypes {

    public static final BlockSetType FUNGI = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(new Identifier(MushroomsAndCaverns.MOD_ID, "fungi"));

    public static void registerBlockSetTypes() {
        MushroomsAndCaverns.LOGGER.info("Registering Block Set Types for " + MushroomsAndCaverns.MOD_ID);
    }

}
