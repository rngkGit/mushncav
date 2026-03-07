package net.rngk.mushncav;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.block.entity.ModBlockEntities;

public class MushroomsAndCavernsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerBlockRenderLayers();
        registerBlockEntityRenderers();
    }

    private static void registerBlockEntityRenderers() {
        // Register vanilla sign renderers for custom sign block entities
        BlockEntityRendererFactories.register(ModBlockEntities.MODSIGN, SignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MODHANGINGSIGN, HangingSignBlockEntityRenderer::new);
    }

    private static void registerBlockRenderLayers() {
        BlockRenderLayerMap.putBlocks(
                BlockRenderLayer.CUTOUT,
                ModBlocks.FUNGI_TREE_LEAVES,
                ModBlocks.FUNGI_MUSHROOM_BLOCK,
                ModBlocks.GLOWING_MUSHROOM_BLOCK
        );

        BlockRenderLayerMap.putBlocks(
                BlockRenderLayer.CUTOUT,
                ModBlocks.FUNGI_BLOCK,
                ModBlocks.FUNGI_TREE_SAPLING,
                ModBlocks.FUNGI_MUSHROOM,
                ModBlocks.FUNGI_DOOR,
                ModBlocks.FUNGI_TRAPDOOR,
                ModBlocks.FUNGI_SIGN,
                ModBlocks.FUNGI_WALL_SIGN,
                ModBlocks.FUNGI_HANGING_SIGN,
                ModBlocks.FUNGI_WALL_HANGING_SIGN,
                ModBlocks.GLOWING_MUSHROOM,
                ModBlocks.GLOWING_MUSHROOM_VINES_PLANT,
                ModBlocks.GLOWING_MUSHROOM_VINES,
                ModBlocks.GLOWING_BLUEBERRY_BUSH,
                ModBlocks.GLOWING_SHORT_GRASS,
                ModBlocks.GLOWING_TALL_GRASS
        );
    }
}