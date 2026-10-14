package net.rngk.mushncav.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.rngk.mushncav.block.ModBlocks;

public class FungiGrassBlock extends GrassBlock {
    public FungiGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    private static boolean canGrassSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);
        if (aboveState.is(Blocks.SNOW) && aboveState.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        }
        if (aboveState.getFluidState().getAmount() == 8) {
            return false;
        }
        int dampening = LightEngine.getLightDampeningInto(state, aboveState, Direction.UP, aboveState.getLightDampening());
        return dampening < 15;
    }

    private static boolean canSpread(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        return canGrassSurvive(state, level, pos) && !level.getFluidState(above).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canGrassSurvive(state, level, pos)) {
            level.setBlockAndUpdate(pos, ModBlocks.FUNGI_DIRT.defaultBlockState());
            return;
        }

        for (int i = 0; i < 4; ++i) {
            BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
            BlockState targetState = level.getBlockState(targetPos);
            if ((targetState.is(Blocks.DIRT) || targetState.is(ModBlocks.FUNGI_DIRT)) && canSpread(state, level, targetPos)) {
                level.setBlockAndUpdate(targetPos, state.setValue(SNOWY, level.getBlockState(targetPos.above()).is(Blocks.SNOW)));
            }
        }
    }
}
