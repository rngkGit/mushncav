package net.rngk.mushncav.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FUNGI_TREE_LEAVES);
        blockStateModelGenerator.registerLog(ModBlocks.FUNGI_TREE_LOG).log(ModBlocks.FUNGI_TREE_LOG).wood(ModBlocks.FUNGI_TREE_WOOD);
        //FUNGI_GRASS_BLOCK This is going to be its own .json file because this datagen is cool, but like not.
        blockStateModelGenerator.registerRotatable(ModBlocks.FUNGI_DIRT);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GLOWING_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GLOWING_SAPPHIRE_BLOCK);
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.FUNGI_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNGI_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNGI_APPLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGIC_STONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_MUSHROOM_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOWING_MUSHROOM_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOWING_SAPPHIRE_STONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOWING_SAPPHIRE_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSHROOM_BONE, Models.GENERATED);
    }
}
