package net.rngk.mushncav.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class FungiBlock extends HorizontalDirectionalBlock implements BonemealableBlock {
    public static final MapCodec<FungiBlock> CODEC = simpleCodec(FungiBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 1, 4);

    // COUNT 1 AGE 1-3
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE_1 = new VoxelShape[]{Block.box(12.0, 2.0, 6.0, 16.0, 4.0, 12.0), Block.box(12.0, 2.0, 5.0, 16.0, 4.0, 13.0), Block.box(10.0, 2.0, 4.0, 16.0, 4.0, 14.0)};
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE_1 = new VoxelShape[]{Block.box(0.0, 2.0, 4.0, 4.0, 4.0, 10.0), Block.box(0.0, 2.0, 3.0, 4.0, 4.0, 11.0), Block.box(0.0, 2.0, 2.0, 6.0, 4.0, 12.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE_1 = new VoxelShape[]{Block.box(6.0, 2.0, 0.0, 12.0, 4.0, 4.0), Block.box(5.0, 2.0, 0.0, 13.0, 4.0, 4.0), Block.box(4.0, 2.0, 0.0, 14.0, 4.0, 6.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE_1 = new VoxelShape[]{Block.box(4.0, 2.0, 12.0, 10.0, 4.0, 16.0), Block.box(3.0, 2.0, 12.0, 11.0, 4.0, 16.0), Block.box(2.0, 2.0, 10.0, 12.0, 4.0, 16.0)};

    // COUNT 2 AGE 1-3
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE_2 = new VoxelShape[]{Block.box(12.0, 2.0, 2.0, 16.0, 7.0, 12.0), Block.box(12.0, 2.0, 1.0, 16.0, 7.0, 13.0), Block.box(10.0, 2.0, 0.0, 16.0, 7.0, 14.0)};
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE_2 = new VoxelShape[]{Block.box(0.0, 2.0, 4.0, 4.0, 7.0, 14.0), Block.box(0.0, 2.0, 3.0, 4.0, 7.0, 15.0), Block.box(0.0, 2.0, 2.0, 6.0, 7.0, 16.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE_2 = new VoxelShape[]{Block.box(2.0, 2.0, 0.0, 12.0, 7.0, 4.0), Block.box(1.0, 2.0, 0.0, 13.0, 7.0, 4.0), Block.box(0.0, 2.0, 0.0, 14.0, 7.0, 6.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE_2 = new VoxelShape[]{Block.box(4.0, 2.0, 12.0, 14.0, 7.0, 16.0), Block.box(3.0, 2.0, 12.0, 15.0, 7.0, 16.0), Block.box(2.0, 2.0, 10.0, 16.0, 7.0, 16.0)};

    // COUNT 3 AGE 1-3
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE_3 = new VoxelShape[]{Block.box(12.0, 2.0, 2.0, 16.0, 15.0, 12.0), Block.box(12.0, 2.0, 1.0, 16.0, 15.0, 13.0), Block.box(10.0, 2.0, 0.0, 16.0, 15.0, 14.0)};
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE_3 = new VoxelShape[]{Block.box(0.0, 2.0, 4.0, 4.0, 15.0, 14.0), Block.box(0.0, 2.0, 3.0, 4.0, 15.0, 15.0), Block.box(0.0, 2.0, 2.0, 6.0, 15.0, 16.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE_3 = new VoxelShape[]{Block.box(2.0, 2.0, 0.0, 12.0, 15.0, 4.0), Block.box(3.0, 2.0, 0.0, 13.0, 15.0, 4.0), Block.box(0.0, 2.0, 0.0, 14.0, 15.0, 6.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE_3 = new VoxelShape[]{Block.box(4.0, 2.0, 12.0, 14.0, 15.0, 16.0), Block.box(3.0, 2.0, 12.0, 15.0, 15.0, 16.0), Block.box(2.0, 2.0, 10.0, 16.0, 15.0, 16.0)};

    // COUNT 4 AGE 1-3
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE_4 = new VoxelShape[]{Block.box(12.0, 2.0, 2.0, 16.0, 15.0, 12.0), Block.box(12.0, 2.0, 1.0, 16.0, 15.0, 13.0), Block.box(10.0, 2.0, 0.0, 16.0, 15.0, 14.0)};
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE_4 = new VoxelShape[]{Block.box(0.0, 2.0, 4.0, 4.0, 15.0, 14.0), Block.box(0.0, 2.0, 3.0, 4.0, 15.0, 15.0), Block.box(0.0, 2.0, 2.0, 6.0, 15.0, 16.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE_4 = new VoxelShape[]{Block.box(2.0, 2.0, 0.0, 12.0, 15.0, 4.0), Block.box(3.0, 2.0, 0.0, 13.0, 15.0, 4.0), Block.box(0.0, 2.0, 0.0, 14.0, 15.0, 6.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE_4 = new VoxelShape[]{Block.box(4.0, 2.0, 12.0, 14.0, 15.0, 16.0), Block.box(3.0, 2.0, 12.0, 15.0, 15.0, 16.0), Block.box(2.0, 2.0, 10.0, 16.0, 15.0, 16.0)};

    @Override
    public MapCodec<FungiBlock> codec() {
        return CODEC;
    }

    public FungiBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AGE, 0).setValue(COUNT, 1));
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int i;
        if (random.nextInt(5) == 0 && (i = state.getValue(AGE)) < 2) {
            level.setBlock(pos, state.setValue(AGE, i + 1), 2);
        }

        int j;
        if (random.nextInt(5) == 0 && (j = state.getValue(COUNT)) < 4) {
            level.setBlock(pos, state.setValue(COUNT, j + 1), 2);
        }

        if (random.nextFloat() < 0.3f) {
            spreadFungi(state, level, pos, random);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos.relative(state.getValue(FACING)));
        return blockState.is(ModTags.Blocks.FUNGI_LOGS);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int i = state.getValue(AGE);
        int j = state.getValue(COUNT);
        switch (state.getValue(FACING)) {
            case SOUTH: {
                if (j == 1) return AGE_TO_SOUTH_SHAPE_1[i];
                if (j == 2) return AGE_TO_SOUTH_SHAPE_2[i];
                if (j == 3) return AGE_TO_SOUTH_SHAPE_3[i];
                if (j == 4) return AGE_TO_SOUTH_SHAPE_4[i];
            }
            case WEST: {
                if (j == 1) return AGE_TO_WEST_SHAPE_1[i];
                if (j == 2) return AGE_TO_WEST_SHAPE_2[i];
                if (j == 3) return AGE_TO_WEST_SHAPE_3[i];
                if (j == 4) return AGE_TO_WEST_SHAPE_4[i];
            }
            case EAST: {
                if (j == 1) return AGE_TO_EAST_SHAPE_1[i];
                if (j == 2) return AGE_TO_EAST_SHAPE_2[i];
                if (j == 3) return AGE_TO_EAST_SHAPE_3[i];
                if (j == 4) return AGE_TO_EAST_SHAPE_4[i];
            }
            default: {
                if (j == 1) return AGE_TO_NORTH_SHAPE_1[i];
                if (j == 2) return AGE_TO_NORTH_SHAPE_2[i];
                if (j == 3) return AGE_TO_NORTH_SHAPE_3[i];
                if (j == 4) return AGE_TO_NORTH_SHAPE_4[i];
            }
        }
        return AGE_TO_EAST_SHAPE_1[i];
    }

    public void spreadFungi(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.is(ModBlocks.FUNGI_BLOCK) && canSpread(state)) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos blockPos = pos.relative(direction);

                if (level.getBlockState(blockPos).is(ModTags.Blocks.FUNGI_LOGS) && direction == state.getValue(FACING)) {
                    spreadFungiFromLog(level, blockPos, direction.getOpposite());
                    continue;
                }

                if (!level.getBlockState(blockPos).isAir()) {
                    continue;
                }

                if (random.nextFloat() < 0.5f) {
                    if (!level.getBlockState(blockPos).is(this) && canSurvive(state, level, blockPos) && level.getBlockState(blockPos.relative(state.getValue(FACING))).is(ModTags.Blocks.FUNGI_LOGS)) {
                        level.setBlock(blockPos, state.setValue(AGE, 0).setValue(COUNT, 1).setValue(FACING, state.getValue(FACING)), 2);
                        break;
                    }
                } else {
                    if (!level.getBlockState(blockPos).is(this) && canSurvive(state, level, blockPos) && level.getBlockState(blockPos.relative(direction)).is(ModTags.Blocks.FUNGI_LOGS)) {
                        level.setBlock(blockPos, state.setValue(AGE, 0).setValue(COUNT, 1).setValue(FACING, direction), 2);
                        break;
                    }
                }
            }
        }
    }

    public void spreadFungiFromLog(ServerLevel level, BlockPos pos, Direction directionFrom) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (direction == directionFrom) {
                continue;
            }
            BlockPos blockPos = pos.relative(direction);

            if (level.getBlockState(blockPos).is(ModTags.Blocks.FUNGI_LOGS)) {
                spreadFungiFromLog(level, blockPos, direction.getOpposite());
                continue;
            }

            if (!level.getBlockState(blockPos).isAir()) {
                continue;
            }

            BlockState newState = ModBlocks.FUNGI_BLOCK.defaultBlockState()
                    .setValue(AGE, 0)
                    .setValue(COUNT, 1)
                    .setValue(FACING, direction.getOpposite());

            level.setBlock(blockPos, newState, 2);
            break;
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState blockState = this.defaultBlockState();
        Level level = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        BlockState blockState2 = level.getBlockState(blockPos);
        for (Direction direction : ctx.getNearestLookingDirections()) {
            if (!direction.getAxis().isHorizontal()) continue;
            blockState = blockState.setValue(FACING, direction);
            if (!blockState.canSurvive(level, blockPos)) continue;
            if (blockState2.is(this)) {
                return blockState2.setValue(COUNT, Math.min(4, blockState2.getValue(COUNT) + 1));
            }
            return blockState;
        }
        return null;
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if (!context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && state.getValue(COUNT) < 4) {
            return true;
        }
        return super.canBeReplaced(state, context);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 2 || state.getValue(COUNT) < 4;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (state.getValue(AGE) < 2) {
            level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
        }
        if (state.getValue(COUNT) < 4) {
            level.setBlock(pos, state.setValue(COUNT, state.getValue(COUNT) + 1), 2);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE, COUNT);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    public boolean canSpread(BlockState state) {
        return state.getValue(AGE) == 2;
    }
}
