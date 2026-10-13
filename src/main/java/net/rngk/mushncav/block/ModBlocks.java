package net.rngk.mushncav.block;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.custom.*;
import net.rngk.mushncav.block.type.ModBlockSetTypes;
import net.rngk.mushncav.block.type.ModWoodTypes;
import net.rngk.mushncav.world.biome.ModTerrablenderInitializer;
import net.rngk.mushncav.world.tree.ModSaplingGenerators;

public class ModBlocks {
    // Block references - initialized in registerModBlocks()
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

    private static ResourceKey<Block> blockKey(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, name));
    }

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MushroomsAndCaverns.MOD_ID, name));
    }

    private static BlockBehaviour.Properties blockSettings(String name, BlockBehaviour.Properties settings) {
        return settings.setId(blockKey(name));
    }

    private static Item.Properties itemSettings(String name) {
        return new Item.Properties().setId(itemKey(name));
    }

    private static Block registerBlock(String name, Block block) {
        ResourceKey<Block> bKey = blockKey(name);
        ResourceKey<Item> iKey = itemKey(name);
        Block registeredBlock = Registry.register(BuiltInRegistries.BLOCK, bKey, block);
        Registry.register(BuiltInRegistries.ITEM, iKey, new BlockItem(registeredBlock, itemSettings(name)));
        return registeredBlock;
    }

    private static Block registerBlockNoItem(String name, Block block) {
        ResourceKey<Block> bKey = blockKey(name);
        return Registry.register(BuiltInRegistries.BLOCK, bKey, block);
    }

    public static void registerModBlocks() {
        MushroomsAndCaverns.LOGGER.info("Registering mod blocks for " + MushroomsAndCaverns.MOD_ID);

        // Fungi terrain blocks
        FUNGI_GRASS_BLOCK = registerBlock("fungi_grass_block", new FungiGrassBlock(blockSettings("fungi_grass_block", BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK))));
        FUNGI_DIRT = registerBlock("fungi_dirt", new FungiDirtBlock(blockSettings("fungi_dirt", BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT))));

        FUNGI_BLOCK = registerBlock("fungi_block", new FungiBlock(blockSettings("fungi_block", BlockBehaviour.Properties.ofFullCopy(Blocks.COCOA))));

        FUNGI_TREE_LOG = registerBlock("fungi_tree_log", new FungiTreeLog(blockSettings("fungi_tree_log", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG))));
        STRIPPED_FUNGI_TREE_LOG = registerBlock("stripped_fungi_tree_log", new RotatedPillarBlock(blockSettings("stripped_fungi_tree_log", BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG))));
        FUNGI_TREE_WOOD = registerBlock("fungi_tree_wood", new FungiTreeWood(blockSettings("fungi_tree_wood", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD))));
        STRIPPED_FUNGI_TREE_WOOD = registerBlock("stripped_fungi_tree_wood", new RotatedPillarBlock(blockSettings("stripped_fungi_tree_wood", BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))));
        FUNGI_TREE_LEAVES = registerBlock("fungi_tree_leaves", new ModLeavesBlock(blockSettings("fungi_tree_leaves", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES))));
        FUNGI_TREE_SAPLING = registerBlock("fungi_tree_sapling", new ModSaplingBlock(ModSaplingGenerators.FUNGI_TREE, blockSettings("fungi_tree_sapling", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING))));

        FUNGI_MUSHROOM_STEM = registerBlock("fungi_mushroom_stem", new RotatedPillarBlock(blockSettings("fungi_mushroom_stem", BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM))));
        FUNGI_MUSHROOM_BLOCK = registerBlock("fungi_mushroom_block", new HugeMushroomBlock(blockSettings("fungi_mushroom_block", BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM_BLOCK))));
        FUNGI_MUSHROOM = registerBlock("fungi_mushroom", new ModSaplingBlock(ModSaplingGenerators.FUNGI_MUSHROOM, blockSettings("fungi_mushroom", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING))));

        FUNGI_PLANKS = registerBlock("fungi_planks", new Block(blockSettings("fungi_planks", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS))));
        FUNGI_SLAB = registerBlock("fungi_slab", new SlabBlock(blockSettings("fungi_slab", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB))));
        FUNGI_STAIRS = registerBlock("fungi_stairs", new StairBlock(FUNGI_PLANKS.defaultBlockState(), blockSettings("fungi_stairs", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS))));
        FUNGI_FENCE = registerBlock("fungi_fence", new FenceBlock(blockSettings("fungi_fence", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE))));
        FUNGI_FENCE_GATE = registerBlock("fungi_fence_gate", new FenceGateBlock(ModWoodTypes.FUNGI, blockSettings("fungi_fence_gate", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE))));
        FUNGI_DOOR = registerBlock("fungi_door", new DoorBlock(ModBlockSetTypes.FUNGI, blockSettings("fungi_door", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR))));
        FUNGI_TRAPDOOR = registerBlock("fungi_trapdoor", new TrapDoorBlock(ModBlockSetTypes.FUNGI, blockSettings("fungi_trapdoor", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR))));
        FUNGI_BUTTON = registerBlock("fungi_button", new ButtonBlock(ModBlockSetTypes.FUNGI, 30, blockSettings("fungi_button", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON))));
        FUNGI_SIGN = registerBlockNoItem("fungi_sign", new ModSignBlock(ModWoodTypes.FUNGI, blockSettings("fungi_sign", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN))));
        FUNGI_WALL_SIGN = registerBlockNoItem("fungi_wall_sign", new ModWallSignBlock(ModWoodTypes.FUNGI, blockSettings("fungi_wall_sign", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN))));
        FUNGI_HANGING_SIGN = registerBlockNoItem("fungi_hanging_sign", new ModHangingSignBlock(ModWoodTypes.FUNGI, blockSettings("fungi_hanging_sign", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN))));
        FUNGI_WALL_HANGING_SIGN = registerBlockNoItem("fungi_wall_hanging_sign", new ModWallHangingSignBlock(ModWoodTypes.FUNGI, blockSettings("fungi_wall_hanging_sign", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN))));

        GLOWING_MUSHROOM_STEM = registerBlock("glowing_mushroom_stem", new RotatedPillarBlock(blockSettings("glowing_mushroom_stem", BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM))));
        GLOWING_MUSHROOM_BLOCK = registerBlock("glowing_mushroom_block", new RotatedPillarBlock(blockSettings("glowing_mushroom_block", BlockBehaviour.Properties.ofFullCopy(Blocks.RED_MUSHROOM_BLOCK).lightLevel(state -> 9))));
        GLOWING_MUSHROOM = registerBlock("glowing_mushroom", new ModSaplingBlock(ModSaplingGenerators.GLOWING_MUSHROOM, blockSettings("glowing_mushroom", BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).lightLevel(state -> 5))));
        GLOWING_MUSHROOM_GRASS_BLOCK = registerBlock("glowing_mushroom_grass_block", new GlowingMushroomGrassBlock(blockSettings("glowing_mushroom_grass_block", BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK))));
        GLOWING_MUSHROOM_VINES_PLANT = registerBlock("glowing_mushroom_vines_plant", new GlowingMushroomVinesBodyBlock(blockSettings("glowing_mushroom_vines_plant", BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES_PLANT).mapColor(MapColor.COLOR_BLUE).lightLevel(GlowingMushroomVines.getLuminanceSupplier(14)))));
        GLOWING_MUSHROOM_VINES = registerBlock("glowing_mushroom_vines", new GlowingMushroomVinesHeadBlock(blockSettings("glowing_mushroom_vines", BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES).mapColor(MapColor.COLOR_BLUE).lightLevel(GlowingMushroomVines.getLuminanceSupplier(14)))));
        GLOWING_BLUEBERRY_BUSH = registerBlock("glowing_blueberry_bush", new GlowingBlueberryBushBlock(blockSettings("glowing_blueberry_bush", BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).mapColor(MapColor.COLOR_BLUE))));
        GLOWING_SHORT_GRASS = registerBlock("glowing_short_grass", new ModShortGrassBlock(blockSettings("glowing_short_grass", BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS))));
        GLOWING_TALL_GRASS = registerBlock("glowing_tall_grass", new DoublePlantBlock(blockSettings("glowing_tall_grass", BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS))));
        GLOWING_SAPPHIRE_BLOCK = registerBlock("glowing_sapphire_block", new GlowingSapphireBlock(blockSettings("glowing_sapphire_block", BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).lightLevel(state -> 15))));
        GLOWING_SAPPHIRE_ORE = registerBlock("glowing_sapphire_ore", new GlowingSapphireOreBlock(UniformInt.of(2, 5), blockSettings("glowing_sapphire_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).lightLevel(state -> 9))));
        DEEPSLATE_GLOWING_SAPPHIRE_ORE = registerBlock("deepslate_glowing_sapphire_ore", new GlowingSapphireOreBlock(UniformInt.of(2, 5), blockSettings("deepslate_glowing_sapphire_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).lightLevel(state -> 9))));

        // Register strippable blocks so all axes can strip them
        StrippableBlockRegistry.register(FUNGI_TREE_LOG, STRIPPED_FUNGI_TREE_LOG);
        StrippableBlockRegistry.register(FUNGI_TREE_WOOD, STRIPPED_FUNGI_TREE_WOOD);

        // Register surface rules after blocks are initialized
        ModTerrablenderInitializer.registerSurfaceRules();
    }
}
