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

public class GlowingMushroomVinesHeadBlock extends AbstractPlantStemBlock implements Fertilizable, GlowingMushroomVines{
    public static final MapCodec<GlowingMushroomVinesHeadBlock> CODEC = GlowingMushroomVinesHeadBlock.createCodec(GlowingMushroomVinesHeadBlock::new);

    public MapCodec<GlowingMushroomVinesHeadBlock> getCodec() {
        return CODEC;
    }

    public GlowingMushroomVinesHeadBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false, 0.1);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(BERRIES, false));
    }

    @Override
    protected int getGrowthLength(Random random) {
        return 1;
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return state.isAir();
    }

    @Override
    protected Block getPlant() {
        return ModBlocks.GLOWING_MUSHROOM_VINES_PLANT;
    }

    @Override
    protected BlockState copyState(BlockState from, BlockState to) {
        return to.with(BERRIES, from.get(BERRIES));
    }

    @Override
    protected BlockState age(BlockState state, Random random) {
        BlockState agedState = super.age(state, random);
        // Only add berries, don't remove them during aging
        if (!state.get(BERRIES) && random.nextFloat() < 0.11f) {
            agedState = agedState.with(BERRIES, true);
        }
        return agedState;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Try to extend the vine downward naturally over time
        BlockPos downPos = pos.down();
        BlockState downState = world.getBlockState(downPos);

        if (downState.isAir() && random.nextFloat() < 0.1f) {
            // Convert current head to body block
            world.setBlockState(pos, ModBlocks.GLOWING_MUSHROOM_VINES_PLANT.getDefaultState().with(BERRIES, state.get(BERRIES)), Block.NOTIFY_LISTENERS);
            // Place a new head block below
            world.setBlockState(downPos, ModBlocks.GLOWING_MUSHROOM_VINES.getDefaultState().with(BERRIES, false), Block.NOTIFY_LISTENERS);
        }

        // Grow berries independently
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
        super.appendProperties(builder);
        builder.add(BERRIES);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        // Always allow bonemeal to extend the vine
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        // Can grow if there's air below
        return world.getBlockState(pos.down()).isAir();
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        // Try to extend the vine downward
        BlockPos downPos = pos.down();
        BlockState downState = world.getBlockState(downPos);

        if (downState.isAir()) {
            // Convert current head to body block, preserving berries
            world.setBlockState(pos, ModBlocks.GLOWING_MUSHROOM_VINES_PLANT.getDefaultState().with(BERRIES, state.get(BERRIES)), Block.NOTIFY_LISTENERS);
            // Place a new head block below
            world.setBlockState(downPos, ModBlocks.GLOWING_MUSHROOM_VINES.getDefaultState().with(BERRIES, false), Block.NOTIFY_LISTENERS);
        }
    }
}
