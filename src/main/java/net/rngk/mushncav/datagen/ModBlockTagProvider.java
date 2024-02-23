package net.rngk.mushncav.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    public void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.GLOWING_SAPPHIRE_ORE)
                .add(ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE)
                .add(ModBlocks.GLOWING_SAPPHIRE_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GLOWING_SAPPHIRE_ORE)
                .add(ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE)
                .add(ModBlocks.GLOWING_SAPPHIRE_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                ;
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
                ;

    }
}
