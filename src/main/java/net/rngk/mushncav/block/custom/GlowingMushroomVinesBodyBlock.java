package net.rngk.mushncav.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.item.ModItems;

public class GlowingMushroomVinesBodyBlock extends AbstractPlantBlock implements Fertilizable, GlowingMushroomVines{
    public static final MapCodec<GlowingMushroomVinesBodyBlock> CODEC = GlowingMushroomVinesBodyBlock.createCodec(GlowingMushroomVinesBodyBlock::new);

    public MapCodec<GlowingMushroomVinesBodyBlock> getCodec() {
        return CODEC;
    }

    public GlowingMushroomVinesBodyBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(BERRIES, false));
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock)ModBlocks.GLOWING_MUSHROOM_VINES;
    }

    @Override
    protected BlockState copyState(BlockState from, BlockState to) {
        return (BlockState)to.with(BERRIES, from.get(BERRIES));
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.GLOWING_BLUEBERRY);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return GlowingMushroomVines.pickBerries(player, state, world, pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BERRIES);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(BERRIES) == false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, (BlockState)state.with(BERRIES, true), Block.NOTIFY_LISTENERS);
    }
}
