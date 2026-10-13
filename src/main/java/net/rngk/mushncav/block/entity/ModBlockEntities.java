package net.rngk.mushncav.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.ModBlocks;

public class ModBlockEntities {

    public static BlockEntityType<SignBlockEntity> MODSIGN;
    public static BlockEntityType<SignBlockEntity> MODHANGINGSIGN;

    public static void registerBlockEntities() {
        MushroomsAndCaverns.LOGGER.info("Registering Block Entities for " + MushroomsAndCaverns.MOD_ID);

        MODSIGN = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, "mod_sign"),
                FabricBlockEntityTypeBuilder.create(
                        SignBlockEntity::new,
                        ModBlocks.FUNGI_SIGN,
                        ModBlocks.FUNGI_WALL_SIGN
                ).build()
        );

        MODHANGINGSIGN = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, "mod_hanging_sign"),
                FabricBlockEntityTypeBuilder.<SignBlockEntity>create(
                        ModHangingSignBlockEntity::new,
                        ModBlocks.FUNGI_HANGING_SIGN,
                        ModBlocks.FUNGI_WALL_HANGING_SIGN
                ).build()
        );
    }
}
