package net.rngk.mushncav.block;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.custom.*;
import net.rngk.mushncav.block.type.ModBlockSetTypes;
import net.rngk.mushncav.block.type.ModWoodTypes;
import net.rngk.mushncav.world.biome.ModTerrablenderInitializer;
import net.rngk.mushncav.world.tree.ModSaplingGenerators;

public class ModBlocks {
    // Block references - initialized lazily in registerModBlocks()
    // Fungi Blocks
    public static Block FUNGI_GRASS_BLOCK;
    public static Block FUNGI_DIRT;
    public static Block FUNGI_BLOCK;

    // Fungi Wood
    public static Block FUNGI_TREE_LOG;
    public static Block STRIPPED_FUNGI_TREE_LOG;
    public static Block FUNGI_TREE_WOOD;
    public static Block STRIPPED_FUNGI_TREE_WOOD;
    public static Block FUNGI_TREE_LEAVES;
    public static Block FUNGI_TREE_SAPLING;

    // Fungi Mushroom
    public static Block FUNGI_MUSHROOM_STEM;
    public static Block FUNGI_MUSHROOM_BLOCK;
    public static Block FUNGI_MUSHROOM;

    // Fungi Wood
    public static Block FUNGI_PLANKS;
    public static Block FUNGI_SLAB;
    public static Block FUNGI_STAIRS;
    public static Block FUNGI_FENCE;
    public static Block FUNGI_FENCE_GATE;
    public static Block FUNGI_DOOR;
    public static Block FUNGI_TRAPDOOR;
    public static Block FUNGI_BUTTON;
    public static Block FUNGI_SIGN;
    public static Block FUNGI_WALL_SIGN;
    public static Block FUNGI_HANGING_SIGN;
    public static Block FUNGI_WALL_HANGING_SIGN;

    // Glowing Mushroom Blocks
    public static Block GLOWING_MUSHROOM_STEM;
    public static Block GLOWING_MUSHROOM_BLOCK;
    public static Block GLOWING_MUSHROOM;
    public static Block GLOWING_MUSHROOM_GRASS_BLOCK;
    public static Block GLOWING_MUSHROOM_VINES_PLANT;
    public static Block GLOWING_MUSHROOM_VINES;
    public static Block GLOWING_BLUEBERRY_BUSH;
    public static Block GLOWING_SHORT_GRASS;
    public static Block GLOWING_TALL_GRASS;
    public static Block GLOWING_SAPPHIRE_BLOCK;
    public static Block GLOWING_SAPPHIRE_ORE;
    public static Block DEEPSLATE_GLOWING_SAPPHIRE_ORE;

    private static AbstractBlock.Settings blockSettings(String name, AbstractBlock.Settings settings) {
        Identifier id = Identifier.of(MushroomsAndCaverns.MOD_ID, name);
        return settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id));
    }

    private static Item.Settings itemSettings(String name) {
        Identifier id = Identifier.of(MushroomsAndCaverns.MOD_ID, name);
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id));
    }

    private static Block registerBlock(String name, Block block){
        Identifier id = Identifier.of(MushroomsAndCaverns.MOD_ID, name);
        Block registeredBlock = Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(registeredBlock, itemSettings(name)));
        return registeredBlock;
    }
    private static Block registerBlockNoItem(String name, Block block){
        Identifier id = Identifier.of(MushroomsAndCaverns.MOD_ID, name);
        return Registry.register(Registries.BLOCK, id, block);
    }
    public static void registerModBlocks(){
        MushroomsAndCaverns.LOGGER.info("Registering mod blocks for " + MushroomsAndCaverns.MOD_ID);

        // Fungi terrain blocks
        FUNGI_GRASS_BLOCK = registerBlock("fungi_grass_block", new FungiGrassBlock(blockSettings("fungi_grass_block", AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK))));
        FUNGI_DIRT = registerBlock("fungi_dirt", new FungiDirtBlock(blockSettings("fungi_dirt", AbstractBlock.Settings.copy(Blocks.DIRT))));

        FUNGI_BLOCK = registerBlock("fungi_block", new FungiBlock(blockSettings("fungi_block", AbstractBlock.Settings.copy(Blocks.COCOA))));

        // Copying from vanilla blocks preserves required metadata used during construction in 1.21.11.
        FUNGI_TREE_LOG = registerBlock("fungi_tree_log", new PillarBlock(blockSettings("fungi_tree_log", AbstractBlock.Settings.copy(Blocks.OAK_LOG))));
        STRIPPED_FUNGI_TREE_LOG = registerBlock("stripped_fungi_tree_log", new PillarBlock(blockSettings("stripped_fungi_tree_log", AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG))));
        FUNGI_TREE_WOOD = registerBlock("fungi_tree_wood", new PillarBlock(blockSettings("fungi_tree_wood", AbstractBlock.Settings.copy(Blocks.OAK_WOOD))));
        STRIPPED_FUNGI_TREE_WOOD = registerBlock("stripped_fungi_tree_wood", new PillarBlock(blockSettings("stripped_fungi_tree_wood", AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD))));
        FUNGI_TREE_LEAVES = registerBlock("fungi_tree_leaves", new ModLeavesBlock(blockSettings("fungi_tree_leaves", AbstractBlock.Settings.copy(Blocks.OAK_LEAVES))));
        FUNGI_TREE_SAPLING = registerBlock("fungi_tree_sapling", new ModSaplingBlock(ModSaplingGenerators.FUNGI_TREE, blockSettings("fungi_tree_sapling", AbstractBlock.Settings.copy(Blocks.OAK_SAPLING))));

        FUNGI_MUSHROOM_STEM = registerBlock("fungi_mushroom_stem", new PillarBlock(blockSettings("fungi_mushroom_stem", AbstractBlock.Settings.copy(Blocks.MUSHROOM_STEM))));
        FUNGI_MUSHROOM_BLOCK = registerBlock("fungi_mushroom_block", new MushroomBlock(blockSettings("fungi_mushroom_block", AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK))));
        FUNGI_MUSHROOM = registerBlock("fungi_mushroom", new ModSaplingBlock(ModSaplingGenerators.FUNGI_MUSHROOM, blockSettings("fungi_mushroom", AbstractBlock.Settings.copy(Blocks.OAK_SAPLING))));

        FUNGI_PLANKS = registerBlock("fungi_planks", new Block(blockSettings("fungi_planks", AbstractBlock.Settings.copy(Blocks.OAK_PLANKS))));
        FUNGI_SLAB = registerBlock("fungi_slab", new SlabBlock(blockSettings("fungi_slab", AbstractBlock.Settings.copy(Blocks.OAK_SLAB))));
        FUNGI_STAIRS = registerBlock("fungi_stairs", new StairsBlock(FUNGI_PLANKS.getDefaultState(), blockSettings("fungi_stairs", AbstractBlock.Settings.copy(Blocks.OAK_STAIRS))));
        FUNGI_FENCE = registerBlock("fungi_fence", new FenceBlock(blockSettings("fungi_fence", AbstractBlock.Settings.copy(Blocks.OAK_FENCE))));
        FUNGI_FENCE_GATE = registerBlock("fungi_fence_gate", new FenceGateBlock(ModWoodTypes.FUNGI, blockSettings("fungi_fence_gate", AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE))));
        FUNGI_DOOR = registerBlock("fungi_door", new DoorBlock(ModBlockSetTypes.FUNGI, blockSettings("fungi_door", AbstractBlock.Settings.copy(Blocks.OAK_DOOR))));
        FUNGI_TRAPDOOR = registerBlock("fungi_trapdoor", new TrapdoorBlock(ModBlockSetTypes.FUNGI, blockSettings("fungi_trapdoor", AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR))));
        FUNGI_BUTTON = registerBlock("fungi_button", new ButtonBlock(ModBlockSetTypes.FUNGI, 30, blockSettings("fungi_button", AbstractBlock.Settings.copy(Blocks.OAK_BUTTON))));
        FUNGI_SIGN = registerBlockNoItem("fungi_sign", new ModSignBlock(ModWoodTypes.FUNGI, blockSettings("fungi_sign", AbstractBlock.Settings.copy(Blocks.OAK_SIGN))));
        FUNGI_WALL_SIGN = registerBlockNoItem("fungi_wall_sign", new ModWallSignBlock(ModWoodTypes.FUNGI, blockSettings("fungi_wall_sign", AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN))));
        FUNGI_HANGING_SIGN = registerBlockNoItem("fungi_hanging_sign", new ModHangingSignBlock(ModWoodTypes.FUNGI, blockSettings("fungi_hanging_sign", AbstractBlock.Settings.copy(Blocks.OAK_HANGING_SIGN))));
        FUNGI_WALL_HANGING_SIGN = registerBlockNoItem("fungi_wall_hanging_sign", new ModWallHangingSignBlock(ModWoodTypes.FUNGI, blockSettings("fungi_wall_hanging_sign", AbstractBlock.Settings.copy(Blocks.OAK_WALL_HANGING_SIGN))));

        GLOWING_MUSHROOM_STEM = registerBlock("glowing_mushroom_stem", new PillarBlock(blockSettings("glowing_mushroom_stem", AbstractBlock.Settings.copy(Blocks.MUSHROOM_STEM))));
        GLOWING_MUSHROOM_BLOCK = registerBlock("glowing_mushroom_block", new PillarBlock(blockSettings("glowing_mushroom_block", AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).luminance(state -> 9))));
        GLOWING_MUSHROOM = registerBlock("glowing_mushroom", new ModSaplingBlock(ModSaplingGenerators.GLOWING_MUSHROOM, blockSettings("glowing_mushroom", AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).luminance(state -> 5))));
        GLOWING_MUSHROOM_GRASS_BLOCK = registerBlock("glowing_mushroom_grass_block", new GlowingMushroomGrassBlock(blockSettings("glowing_mushroom_grass_block", AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK))));
        GLOWING_MUSHROOM_VINES_PLANT = registerBlock("glowing_mushroom_vines_plant", new GlowingMushroomVinesBodyBlock(blockSettings("glowing_mushroom_vines_plant", AbstractBlock.Settings.copy(Blocks.CAVE_VINES_PLANT).mapColor(MapColor.BLUE).luminance(state -> CaveVines.getLuminanceSupplier(14).applyAsInt(state)))));
        GLOWING_MUSHROOM_VINES = registerBlock("glowing_mushroom_vines", new GlowingMushroomVinesHeadBlock(blockSettings("glowing_mushroom_vines", AbstractBlock.Settings.copy(Blocks.CAVE_VINES).mapColor(MapColor.BLUE).luminance(state -> CaveVines.getLuminanceSupplier(14).applyAsInt(state)))));
        GLOWING_BLUEBERRY_BUSH = registerBlock("glowing_blueberry_bush", new GlowingBlueberryBushBlock(blockSettings("glowing_blueberry_bush", AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH).mapColor(MapColor.BLUE))));
        GLOWING_SHORT_GRASS = registerBlock("glowing_short_grass", new ShortPlantBlock(blockSettings("glowing_short_grass", AbstractBlock.Settings.copy(Blocks.SHORT_GRASS))));
        GLOWING_TALL_GRASS = registerBlock("glowing_tall_grass", new TallPlantBlock(blockSettings("glowing_tall_grass", AbstractBlock.Settings.copy(Blocks.TALL_GRASS))));
        GLOWING_SAPPHIRE_BLOCK = registerBlock("glowing_sapphire_block", new GlowingSapphireBlock(blockSettings("glowing_sapphire_block", AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).luminance(state -> 15))));
        GLOWING_SAPPHIRE_ORE = registerBlock("glowing_sapphire_ore", new GlowingSapphireOreBlock(UniformIntProvider.create(2, 5), blockSettings("glowing_sapphire_ore", AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE).luminance(state -> 9))));
        DEEPSLATE_GLOWING_SAPPHIRE_ORE = registerBlock("deepslate_glowing_sapphire_ore", new GlowingSapphireOreBlock(UniformIntProvider.create(2, 5), blockSettings("deepslate_glowing_sapphire_ore", AbstractBlock.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE).luminance(state -> 9))));

        // Register strippable blocks so all axes can strip them
        StrippableBlockRegistry.register(FUNGI_TREE_LOG, STRIPPED_FUNGI_TREE_LOG);
        StrippableBlockRegistry.register(FUNGI_TREE_WOOD, STRIPPED_FUNGI_TREE_WOOD);

        // Register surface rules after blocks are initialized
        ModTerrablenderInitializer.registerSurfaceRules();
    }
}
