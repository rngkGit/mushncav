package net.rngk.mushncav.block.type;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;

public class ModWoodTypes {

    public static final WoodType FUNGI = WoodTypeBuilder.copyOf(WoodType.OAK).register(new Identifier(MushroomsAndCaverns.MOD_ID, "fungi"), ModBlockSetTypes.FUNGI);

    public static void registerWoodTypes() {
        MushroomsAndCaverns.LOGGER.info("Registering Wood Types for " + MushroomsAndCaverns.MOD_ID);
    }

}
