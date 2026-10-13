package net.rngk.mushncav;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.rngk.mushncav.block.entity.ModBlockEntities;

public class MushroomsAndCavernsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerBlockEntityRenderers();
    }

    private static void registerBlockEntityRenderers() {
        // Register vanilla sign renderers for custom sign block entities
        BlockEntityRendererRegistry.register(ModBlockEntities.MODSIGN, StandingSignRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.MODHANGINGSIGN, HangingSignRenderer::new);
    }
}