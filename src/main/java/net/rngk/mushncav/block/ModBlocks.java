package net.rngk.mushncav.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.custom.GlowingSapphireBlock;
import net.rngk.mushncav.block.custom.GlowingSapphireOreBlock;

public class ModBlocks {
    public static final Block FUNGI_TREE_LOG = registerBlock("fungi_tree_log", new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block FUNGI_TREE_LEAVES = registerBlock("fungi_tree_leaves", new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block GLOWING_SAPPHIRE_BLOCK = registerBlock("glowing_sapphire_block", new GlowingSapphireBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).luminance(11)));

    //Ores
    public static final Block GLOWING_SAPPHIRE_ORE = registerBlock("glowing_sapphire_ore", new GlowingSapphireOreBlock(UniformIntProvider.create(2, 5), FabricBlockSettings.copyOf(Blocks.STONE).strength(2f).luminance(9)));
    public static final Block DEEPSLATE_GLOWING_SAPPHIRE_ORE = registerBlock("deepslate_glowing_sapphire_ore", new GlowingSapphireOreBlock(UniformIntProvider.create(2, 5), FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f).luminance(9)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MushroomsAndCaverns.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(MushroomsAndCaverns.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks(){
        MushroomsAndCaverns.LOGGER.info("Registering mod blocks for " + MushroomsAndCaverns.MOD_ID);
    }
}
