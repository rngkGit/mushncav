package net.rngk.mushncav.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.rngk.mushncav.block.entity.ModBlockEntities;
import net.rngk.mushncav.block.entity.ModHangingSignBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ModWallHangingSignBlock extends AbstractSignBlock {
    public static final MapCodec<ModWallHangingSignBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    WoodType.CODEC.fieldOf("wood_type").forGetter(modSignBlock -> modSignBlock.getWoodType()),
                    createSettingsCodec()
            ).apply(instance, ModWallHangingSignBlock::new));    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final VoxelShape NORTH_SOUTH_COLLISION_SHAPE = Block.createCuboidShape(0.0, 14.0, 6.0, 16.0, 16.0, 10.0);
    public static final VoxelShape EAST_WEST_COLLISION_SHAPE = Block.createCuboidShape(6.0, 14.0, 0.0, 10.0, 16.0, 16.0);
    public static final VoxelShape NORTH_SOUTH_SHAPE = VoxelShapes.union(NORTH_SOUTH_COLLISION_SHAPE, Block.createCuboidShape(1.0, 0.0, 7.0, 15.0, 10.0, 9.0));
    public static final VoxelShape EAST_WEST_SHAPE = VoxelShapes.union(EAST_WEST_COLLISION_SHAPE, Block.createCuboidShape(7.0, 0.0, 1.0, 9.0, 10.0, 15.0));
    private static final Map<Direction, VoxelShape> OUTLINE_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, NORTH_SOUTH_SHAPE, Direction.SOUTH, NORTH_SOUTH_SHAPE, Direction.EAST, EAST_WEST_SHAPE, Direction.WEST, EAST_WEST_SHAPE));

    public MapCodec<ModWallHangingSignBlock> getCodec() {
        return CODEC;
    }

    public ModWallHangingSignBlock(WoodType woodType, AbstractBlock.Settings settings) {
        super(woodType, settings.sounds(woodType.hangingSignSoundType()));
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(WATERLOGGED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack;
        SignBlockEntity signBlockEntity;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SignBlockEntity && this.shouldTryAttaching(state, player, hit, signBlockEntity = (SignBlockEntity)blockEntity, itemStack = player.getStackInHand(hand))) {
            return ActionResult.PASS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    private boolean shouldTryAttaching(BlockState state, PlayerEntity player, BlockHitResult hitResult, SignBlockEntity sign, ItemStack stack) {
        return !sign.canRunCommandClickEvent(sign.isPlayerFacingFront(player), player) && stack.getItem() instanceof HangingSignItem && !this.isHitOnFacingAxis(hitResult, state);
    }

    private boolean isHitOnFacingAxis(BlockHitResult hitResult, BlockState state) {
        return hitResult.getSide().getAxis() == state.get(FACING).getAxis();
    }

    @Override
    public String getTranslationKey() {
        return this.asItem().getTranslationKey();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPES.get(state.get(FACING));
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return this.getOutlineShape(state, world, pos, ShapeContext.absent());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case EAST:
            case WEST: {
                return EAST_WEST_COLLISION_SHAPE;
            }
        }
        return NORTH_SOUTH_COLLISION_SHAPE;
    }

    public boolean canAttachAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING).rotateYClockwise();
        Direction direction2 = state.get(FACING).rotateYCounterclockwise();
        return this.canAttachTo(world, state, pos.offset(direction), direction2) || this.canAttachTo(world, state, pos.offset(direction2), direction);
    }

    public boolean canAttachTo(WorldView world, BlockState state, BlockPos toPos, Direction direction) {
        BlockState blockState = world.getBlockState(toPos);
        if (blockState.isIn(BlockTags.WALL_HANGING_SIGNS)) {
            return blockState.get(FACING).getAxis().test(state.get(FACING));
        }
        return blockState.isSideSolid(world, toPos, direction, SideShapeType.FULL);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        World worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        for (Direction direction : ctx.getPlacementDirections()) {
            Direction direction2;
            if (!direction.getAxis().isHorizontal() || direction.getAxis().test(ctx.getSide()) || !(blockState = (BlockState)blockState.with(FACING, direction2 = direction.getOpposite())).canPlaceAt(worldView, blockPos) || !this.canAttachAt(blockState, worldView, blockPos)) continue;
            return (BlockState)blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() == state.get(FACING).rotateYClockwise().getAxis() && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public float getRotationDegrees(BlockState state) {
        return state.get(FACING).asRotation();
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ModHangingSignBlockEntity(pos, state);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return WallHangingSignBlock.validateTicker(type, ModBlockEntities.MODHANGINGSIGN, SignBlockEntity::tick);
    }
}
