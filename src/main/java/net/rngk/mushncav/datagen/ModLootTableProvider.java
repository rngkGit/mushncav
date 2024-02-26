package net.rngk.mushncav.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    @Override
    public void generate() {
        addDrop(ModBlocks.FUNGI_TREE_LOG);
        addDrop(ModBlocks.FUNGI_TREE_WOOD);
        addDrop(ModBlocks.FUNGI_TREE_LEAVES, leavesDrops(ModBlocks.FUNGI_TREE_LEAVES, Blocks.OAK_SAPLING, 0.06f).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS).with((LootPoolEntry.Builder<?>) ((LeafEntry.Builder) this.addSurvivesExplosionCondition(ModBlocks.FUNGI_TREE_LEAVES, ItemEntry.builder(ModItems.FUNGI_APPLE))).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.005f, 0.0055555557f, 0.00625f, 0.008333334f, 0.025f)))));
        addDrop(ModBlocks.FUNGI_GRASS_BLOCK);
        addDrop(ModBlocks.FUNGI_DIRT);

        addDrop(ModBlocks.GLOWING_SAPPHIRE_ORE, manyOreLikeDrops(ModBlocks.GLOWING_SAPPHIRE_ORE, ModItems.GLOWING_SAPPHIRE_SHARD));
        addDrop(ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE, manyOreLikeDrops(ModBlocks.DEEPSLATE_GLOWING_SAPPHIRE_ORE, ModItems.GLOWING_SAPPHIRE_SHARD));
    }
    public LootTable.Builder manyOreLikeDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop, ((LeafEntry.Builder) ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 5.0f)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
    public LootTable.Builder singleOreLikeDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop, (LootPoolEntry.Builder)this.applyExplosionDecay(drop, ((LeafEntry.Builder) ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
