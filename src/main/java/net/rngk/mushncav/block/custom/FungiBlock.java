package net.rngk.mushncav.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import net.rngk.mushncav.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class FungiBlock extends HorizontalFacingBlock implements Fertilizable {
    public static final MapCodec<FungiBlock> CODEC = FungiBlock.createCodec(FungiBlock::new);
    public static final IntProperty AGE = Properties.AGE_2;
    public static final IntProperty COUNT = IntProperty.of("count", 1, 4);
    // COUNT 1 AGE 1-3
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE_1 = new VoxelShape[]{Block.createCuboidShape(12.0, 2.0, 6.0, 16.0, 4.0, 12.0), Block.createCuboidShape(12.0, 2.0, 5.0, 16.0, 4.0, 13.0), Block.createCuboidShape(10.0, 2.0, 4.0, 16.0, 4.0, 14.0)};
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE_1 = new VoxelShape[]{Block.createCuboidShape(0.0, 2.0, 4.0, 4.0, 4.0, 10.0), Block.createCuboidShape(0.0, 2.0, 3.0, 4.0, 4.0, 11.0), Block.createCuboidShape(0.0, 2.0, 2.0, 6.0, 4.0, 12.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE_1 = new VoxelShape[]{Block.createCuboidShape(6.0, 2.0, 0.0, 12.0, 4.0, 4.0), Block.createCuboidShape(5.0, 2.0, 0.0, 13.0, 4.0, 4.0), Block.createCuboidShape(4.0, 2.0, 0.0, 14.0, 4.0, 6.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE_1 = new VoxelShape[]{Block.createCuboidShape(4.0, 2.0, 12.0, 10.0, 4.0, 16.0), Block.createCuboidShape(3.0, 2.0, 12.0, 11.0, 4.0, 16.0), Block.createCuboidShape(2.0, 2.0, 10.0, 12.0, 4.0, 16.0)};
    // COUNT 2 AGE 1-3
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE_2 = new VoxelShape[]{Block.createCuboidShape(12.0, 2.0, 2.0, 16.0, 7.0, 12.0), Block.createCuboidShape(12.0, 2.0, 1.0, 16.0, 7.0, 13.0), Block.createCuboidShape(10.0, 2.0, 0.0, 16.0, 7.0, 14.0)};
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE_2 = new VoxelShape[]{Block.createCuboidShape(0.0, 2.0, 4.0, 4.0, 7.0, 14.0), Block.createCuboidShape(0.0, 2.0, 3.0, 4.0, 7.0, 15.0), Block.createCuboidShape(0.0, 2.0, 2.0, 6.0, 7.0, 16.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE_2 = new VoxelShape[]{Block.createCuboidShape(2.0, 2.0, 0.0, 12.0, 7.0, 4.0), Block.createCuboidShape(1.0, 2.0, 0.0, 13.0, 7.0, 4.0), Block.createCuboidShape(0.0, 2.0, 0.0, 14.0, 7.0, 6.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE_2 = new VoxelShape[]{Block.createCuboidShape(4.0, 2.0, 12.0, 14.0, 7.0, 16.0), Block.createCuboidShape(3.0, 2.0, 12.0, 15.0, 7.0, 16.0), Block.createCuboidShape(2.0, 2.0, 10.0, 16.0, 7.0, 16.0)};
    // COUNT 3 AGE 1-3
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE_3 = new VoxelShape[]{Block.createCuboidShape(12.0, 2.0, 2.0, 16.0, 15.0, 12.0), Block.createCuboidShape(12.0, 2.0, 1.0, 16.0, 15.0, 13.0), Block.createCuboidShape(10.0, 2.0, 0.0, 16.0, 15.0, 14.0)};
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE_3 = new VoxelShape[]{Block.createCuboidShape(0.0, 2.0, 4.0, 4.0, 15.0, 14.0), Block.createCuboidShape(0.0, 2.0, 3.0, 4.0, 15.0, 15.0), Block.createCuboidShape(0.0, 2.0, 2.0, 6.0, 15.0, 16.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE_3 = new VoxelShape[]{Block.createCuboidShape(2.0, 2.0, 0.0, 12.0, 15.0, 4.0), Block.createCuboidShape(3.0, 2.0, 0.0, 13.0, 15.0, 4.0), Block.createCuboidShape(0.0, 2.0, 0.0, 14.0, 15.0, 6.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE_3 = new VoxelShape[]{Block.createCuboidShape(4.0, 2.0, 12.0, 14.0, 15.0, 16.0), Block.createCuboidShape(3.0, 2.0, 12.0, 15.0, 15.0, 16.0), Block.createCuboidShape(2.0, 2.0, 10.0, 16.0, 15.0, 16.0)};
    // COUNT 4 AGE 1-3
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE_4 = new VoxelShape[]{Block.createCuboidShape(12.0, 2.0, 2.0, 16.0, 15.0, 12.0), Block.createCuboidShape(12.0, 2.0, 1.0, 16.0, 15.0, 13.0), Block.createCuboidShape(10.0, 2.0, 0.0, 16.0, 15.0, 14.0)};
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE_4 = new VoxelShape[]{Block.createCuboidShape(0.0, 2.0, 4.0, 4.0, 15.0, 14.0), Block.createCuboidShape(0.0, 2.0, 3.0, 4.0, 15.0, 15.0), Block.createCuboidShape(0.0, 2.0, 2.0, 6.0, 15.0, 16.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE_4 = new VoxelShape[]{Block.createCuboidShape(2.0, 2.0, 0.0, 12.0, 15.0, 4.0), Block.createCuboidShape(3.0, 2.0, 0.0, 13.0, 15.0, 4.0), Block.createCuboidShape(0.0, 2.0, 0.0, 14.0, 15.0, 6.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE_4 = new VoxelShape[]{Block.createCuboidShape(4.0, 2.0, 12.0, 14.0, 15.0, 16.0), Block.createCuboidShape(3.0, 2.0, 12.0, 15.0, 15.0, 16.0), Block.createCuboidShape(2.0, 2.0, 10.0, 16.0, 15.0, 16.0)};

    public MapCodec<FungiBlock> getCodec() {
        return CODEC;
    }

    public FungiBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(AGE, 0).with(COUNT, 1));
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < 2;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i;
        if (world.random.nextInt(5) == 0 && (i = state.get(AGE).intValue()) < 2) {
            world.setBlockState(pos, (BlockState)state.with(AGE, i + 1), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.offset(state.get(FACING)));
        return blockState.isIn(ModTags.Blocks.FUNGI_LOGS);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int i = state.get(AGE);
        int j = state.get(COUNT);
        switch (state.get(FACING)) {
            case SOUTH: {
                if (j == 1) return AGE_TO_SOUTH_SHAPE_1[i];
                if (j == 2) return AGE_TO_SOUTH_SHAPE_2[i];
                if (j == 3) return AGE_TO_SOUTH_SHAPE_3[i];
                if (j == 4) return AGE_TO_SOUTH_SHAPE_4[i];
            }
            default: {
                if (j == 1) return AGE_TO_NORTH_SHAPE_1[i];
                if (j == 2) return AGE_TO_NORTH_SHAPE_2[i];
                if (j == 3) return AGE_TO_NORTH_SHAPE_3[i];
                if (j == 4) return AGE_TO_NORTH_SHAPE_4[i];
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
        }
        return AGE_TO_EAST_SHAPE_1[i];
    }

    /*private void breakFungi(World world, BlockPos pos, BlockState state) {
        world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7f, 0.9f + world.random.nextFloat() * 0.2f);
        int i = state.get(COUNT);
        if (i <= 1) {
            world.breakBlock(pos, false);
        } else {
            world.setBlockState(pos, (BlockState)state.with(COUNT, i - 1), Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(state));
            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
        }
    }*/

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        World worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState2 = ctx.getWorld().getBlockState(ctx.getBlockPos());
        for (Direction direction : ctx.getPlacementDirections()) {
            if (!direction.getAxis().isHorizontal() || !(blockState = (BlockState)blockState.with(FACING, direction)).canPlaceAt(worldView, blockPos)) continue;
            //return blockState;
            if (blockState2.isOf(this)) {
                return (BlockState) blockState2.with(COUNT, Math.min(4, blockState2.get(COUNT) + 1));
            }
            return blockState;
            //return super.getPlacementState(ctx).with(COUNT, Math.min(4, blockState.get(COUNT) + 1));
        }
        return null;
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (!context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && state.get(COUNT) < 4) {
            return true;
        }
        return super.canReplace(state, context);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < 2;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, (BlockState)state.with(AGE, state.get(AGE) + 1), Block.NOTIFY_LISTENERS);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE, COUNT);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
