package net.rngk.mushncav.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;

public class ModBlockEntities {

    public static final BlockEntityType<SignBlockEntity> MODSIGN = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(MushroomsAndCaverns.MOD_ID, "mod_sign"),
            FabricBlockEntityTypeBuilder.create(
                    SignBlockEntity::new,
                    ModBlocks.FUNGI_SIGN,
                    ModBlocks.FUNGI_WALL_SIGN
            ).build()
    );

    public static final BlockEntityType<SignBlockEntity> MODHANGINGSIGN = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(MushroomsAndCaverns.MOD_ID, "mod_hanging_sign"),
            FabricBlockEntityTypeBuilder.create(
                    SignBlockEntity::new,
                    ModBlocks.FUNGI_HANGING_SIGN,
                    ModBlocks.FUNGI_WALL_HANGING_SIGN
            ).build()
    );

    public static void registerBlockEntities() {
        MushroomsAndCaverns.LOGGER.info("Registering Block Entities for " + MushroomsAndCaverns.MOD_ID);
    }
}
