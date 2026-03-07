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

    // Lazy-initialized block entity types
    public static BlockEntityType<SignBlockEntity> MODSIGN;
    public static BlockEntityType<ModHangingSignBlockEntity> MODHANGINGSIGN;

    public static void registerBlockEntities() {
        MushroomsAndCaverns.LOGGER.info("Registering Block Entities for " + MushroomsAndCaverns.MOD_ID);

        // Register block entity types after blocks are initialized
        MODSIGN = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(MushroomsAndCaverns.MOD_ID, "mod_sign"),
                FabricBlockEntityTypeBuilder.create(
                        SignBlockEntity::new,
                        ModBlocks.FUNGI_SIGN,
                        ModBlocks.FUNGI_WALL_SIGN
                ).build()
        );

        MODHANGINGSIGN = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(MushroomsAndCaverns.MOD_ID, "mod_hanging_sign"),
                FabricBlockEntityTypeBuilder.create(
                        ModHangingSignBlockEntity::new,
                        ModBlocks.FUNGI_HANGING_SIGN,
                        ModBlocks.FUNGI_WALL_HANGING_SIGN
                ).build()
        );
    }
}
