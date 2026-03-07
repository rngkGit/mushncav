package net.rngk.mushncav.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.rngk.mushncav.block.ModBlocks;

public class GlowingMushroomVinesBodyBlock extends AbstractPlantBlock implements Fertilizable, GlowingMushroomVines{
    public static final MapCodec<GlowingMushroomVinesBodyBlock> CODEC = GlowingMushroomVinesBodyBlock.createCodec(GlowingMushroomVinesBodyBlock::new);

    public MapCodec<GlowingMushroomVinesBodyBlock> getCodec() {
        return CODEC;
    }

    public GlowingMushroomVinesBodyBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
        this.setDefaultState(this.stateManager.getDefaultState().with(BERRIES, false));
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock)ModBlocks.GLOWING_MUSHROOM_VINES;
    }

    @Override
    protected BlockState copyState(BlockState from, BlockState to) {
        return to.with(BERRIES, from.get(BERRIES));
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(BERRIES) && random.nextFloat() < 0.11f) {
            world.setBlockState(pos, state.with(BERRIES, true), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return GlowingMushroomVines.pickBerries(player, state, world, pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BERRIES);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !state.get(BERRIES);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        // Try to extend the vine downward
        BlockPos downPos = pos.down();
        if (world.getBlockState(downPos).isAir()) {
            world.setBlockState(downPos, ModBlocks.GLOWING_MUSHROOM_VINES_PLANT.getDefaultState().with(BERRIES, false), Block.NOTIFY_LISTENERS);
        }
    }
}
