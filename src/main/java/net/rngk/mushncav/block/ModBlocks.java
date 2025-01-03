package net.rngk.mushncav.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.rngk.mushncav.MushroomsAndCaverns;
import net.rngk.mushncav.block.custom.*;
import net.rngk.mushncav.block.type.ModBlockSetTypes;
import net.rngk.mushncav.block.type.ModWoodTypes;
import net.rngk.mushncav.world.tree.ModSaplingGenerators;

public class ModBlocks {
    // Fungi Blocks

    // Fungi Wood
    public static final Block FUNGI_TREE_LOG = registerBlock("fungi_tree_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block STRIPPED_FUNGI_TREE_LOG = registerBlock("stripped_fungi_tree_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block FUNGI_TREE_WOOD = registerBlock("fungi_tree_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_FUNGI_TREE_WOOD = registerBlock("stripped_fungi_tree_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block FUNGI_TREE_LEAVES = registerBlock("fungi_tree_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).nonOpaque()));
    public static final Block FUNGI_TREE_SAPLING = registerBlock("fungi_tree_sapling", new ModSaplingBlock(ModSaplingGenerators.FUNGI_TREE, FabricBlockSettings.copyOf(Blocks.JUNGLE_SAPLING)));

    public static final Block FUNGI_PLANKS = registerBlock("fungi_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block FUNGI_SLAB = registerBlock("fungi_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block FUNGI_STAIRS = registerBlock("fungi_stairs", new StairsBlock(FUNGI_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block FUNGI_FENCE = registerBlock("fungi_fence", new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block FUNGI_FENCE_GATE = registerBlock("fungi_fence_gate", new FenceGateBlock(ModWoodTypes.FUNGI, FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE)));
    public static final Block FUNGI_DOOR = registerBlock("fungi_door", new DoorBlock(ModBlockSetTypes.FUNGI, FabricBlockSettings.copyOf(Blocks.OAK_DOOR)));
    public static final Block FUNGI_TRAPDOOR = registerBlock("fungi_trapdoor", new TrapdoorBlock(ModBlockSetTypes.FUNGI, FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR)));
    public static final Block FUNGI_BUTTON = registerBlock("fungi_button", new ButtonBlock(ModBlockSetTypes.FUNGI, 30, FabricBlockSettings.copyOf(Blocks.OAK_BUTTON)));
    public static final Block FUNGI_SIGN = registerBlockNoItem("fungi_sign", new ModSignBlock(ModWoodTypes.FUNGI, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));
    public static final Block FUNGI_WALL_SIGN = registerBlockNoItem("fungi_wall_sign", new ModWallSignBlock(ModWoodTypes.FUNGI, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).dropsLike(FUNGI_SIGN)));
    public static final Block FUNGI_HANGING_SIGN = registerBlockNoItem("fungi_hanging_sign", new ModHangingSignBlock(ModWoodTypes.FUNGI, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));
    public static final Block FUNGI_WALL_HANGING_SIGN = registerBlockNoItem("fungi_wall_hanging_sign", new ModWallHangingSignBlock(ModWoodTypes.FUNGI, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN).dropsLike(FUNGI_HANGING_SIGN)));

    // Other
    public static final Block FUNGI_MUSHROOM_STEM = registerBlock("fungi_mushroom_stem", new PillarBlock(FabricBlockSettings.copyOf(Blocks.MUSHROOM_STEM)));
    public static final Block FUNGI_MUSHROOM_BLOCK = registerBlock("fungi_mushroom_block", new MushroomBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM_BLOCK)));
    public static final Block FUNGI_MUSHROOM = registerBlock("fungi_mushroom", new ModSaplingBlock(ModSaplingGenerators.FUNGI_MUSHROOM, FabricBlockSettings.copyOf(Blocks.JUNGLE_SAPLING)));
    public static final Block FUNGI_GRASS_BLOCK = registerBlock("fungi_grass_block", new FungiGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK)));
    public static final Block FUNGI_DIRT = registerBlock("fungi_dirt", new Block(FabricBlockSettings.copyOf(Blocks.DIRT)));
    public static final Block FUNGI_BLOCK = registerBlockNoItem("fungi_block", new FungiBlock(FabricBlockSettings.copyOf(Blocks.COCOA)));



    // Glowing Mushroom Blocks

    public static final Block GLOWING_MUSHROOM_STEM = registerBlock("glowing_mushroom_stem", new PillarBlock(FabricBlockSettings.copyOf(Blocks.MUSHROOM_STEM)));
    public static final Block GLOWING_MUSHROOM_BLOCK = registerBlock("glowing_mushroom_block", new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM_BLOCK).luminance(9)));
    public static final Block GLOWING_MUSHROOM = registerBlock("glowing_mushroom", new ModSaplingBlock(ModSaplingGenerators.GLOWING_MUSHROOM, FabricBlockSettings.copyOf(Blocks.JUNGLE_SAPLING).luminance(5)));
    public static final Block GLOWING_MUSHROOM_GRASS_BLOCK = registerBlock("glowing_mushroom_grass_block", new GlowingMushroomGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK)));
    public static final Block GLOWING_MUSHROOM_VINES_PLANT = registerBlock("glowing_mushroom_vines_plant", new GlowingMushroomVinesBodyBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLUE).noCollision().luminance(CaveVines.getLuminanceSupplier(14)).breakInstantly().sounds(BlockSoundGroup.CAVE_VINES).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block GLOWING_MUSHROOM_VINES = registerBlock("glowing_mushroom_vines", new GlowingMushroomVinesHeadBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLUE).ticksRandomly().noCollision().luminance(CaveVines.getLuminanceSupplier(14)).breakInstantly().sounds(BlockSoundGroup.CAVE_VINES).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block GLOWING_BLUEBERRY_BUSH = registerBlock("glowing_blueberry_bush", new GlowingBlueberryBushBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLUE).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block GLOWING_SHORT_GRASS = registerBlock("glowing_short_grass", new ShortPlantBlock(FabricBlockSettings.copyOf(Blocks.SHORT_GRASS)));
    public static final Block GLOWING_TALL_GRASS = registerBlock("glowing_tall_grass", new TallPlantBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS)));
    public static final Block GLOWING_SAPPHIRE_BLOCK = registerBlock("glowing_sapphire_block", new GlowingSapphireBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).luminance(15)));
    public static final Block GLOWING_SAPPHIRE_ORE = registerBlock("glowing_sapphire_ore", new GlowingSapphireOreBlock(UniformIntProvider.create(2, 5), FabricBlockSettings.copyOf(Blocks.STONE).strength(2f).luminance(9)));
    public static final Block DEEPSLATE_GLOWING_SAPPHIRE_ORE = registerBlock("deepslate_glowing_sapphire_ore", new GlowingSapphireOreBlock(UniformIntProvider.create(2, 5), FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f).luminance(9)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MushroomsAndCaverns.MOD_ID, name), block);
    }
    private static Block registerBlockNoItem(String name, Block block){
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
