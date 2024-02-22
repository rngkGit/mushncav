package net.rngk.mushncav.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FUNGI_TREE_LOG);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FUNGI_TREE_LEAVES);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GLOWING_SAPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_GLOWING_SAPHIRE_ORE);
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.AMETHYST_MUSHROOM_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNGI_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNGI_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNGI_APPLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGIC_STONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOWING_MUSHROOM_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOWING_SAPHIRE_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOWING_SAPHIRE_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSHROOM_BONE, Models.GENERATED);
    }
}
