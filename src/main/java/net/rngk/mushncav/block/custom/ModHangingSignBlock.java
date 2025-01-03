package net.rngk.mushncav.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.rngk.mushncav.block.entity.ModBlockEntities;
import net.rngk.mushncav.block.entity.ModHangingSignBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class ModHangingSignBlock extends AbstractSignBlock {
    public static final MapCodec<ModHangingSignBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    WoodType.CODEC.fieldOf("wood_type").forGetter(modSignBlock -> modSignBlock.getWoodType()),
                    createSettingsCodec()
            ).apply(instance, ModHangingSignBlock::new));
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final BooleanProperty ATTACHED = Properties.ATTACHED;
    protected static final float field_40302 = 5.0f;
    protected static final VoxelShape DEFAULT_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    private static final Map<Integer, VoxelShape> SHAPES_FOR_ROTATION = Maps.newHashMap(ImmutableMap.of(0, Block.createCuboidShape(1.0, 0.0, 7.0, 15.0, 10.0, 9.0), 4, Block.createCuboidShape(7.0, 0.0, 1.0, 9.0, 10.0, 15.0), 8, Block.createCuboidShape(1.0, 0.0, 7.0, 15.0, 10.0, 9.0), 12, Block.createCuboidShape(7.0, 0.0, 1.0, 9.0, 10.0, 15.0)));

    public MapCodec<ModHangingSignBlock> getCodec() {
        return CODEC;
    }

    public ModHangingSignBlock(WoodType woodType, AbstractBlock.Settings settings) {
        super(woodType, settings.sounds(woodType.hangingSignSoundType()));
        this.setDefaultState(this.stateManager.getDefaultState().with(ROTATION, 0).with(ATTACHED, false).with(WATERLOGGED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack;
        SignBlockEntity signBlockEntity;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SignBlockEntity && this.shouldTryAttaching(player, hit, signBlockEntity = (SignBlockEntity)blockEntity, itemStack = player.getStackInHand(hand))) {
            return ActionResult.PASS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    private boolean shouldTryAttaching(PlayerEntity player, BlockHitResult hitResult, SignBlockEntity sign, ItemStack stack) {
        return !sign.canRunCommandClickEvent(sign.isPlayerFacingFront(player), player) && stack.getItem() instanceof HangingSignItem && hitResult.getSide().equals(Direction.DOWN);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isSideSolid(world, pos.up(), Direction.DOWN, SideShapeType.CENTER);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean bl2;
        World world = ctx.getWorld();
        FluidState fluidState = world.getFluidState(ctx.getBlockPos());
        BlockPos blockPos = ctx.getBlockPos().up();
        BlockState blockState = world.getBlockState(blockPos);
        boolean bl = blockState.isIn(BlockTags.ALL_HANGING_SIGNS);
        Direction direction = Direction.fromRotation(ctx.getPlayerYaw());
        boolean bl3 = bl2 = !Block.isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.DOWN) || ctx.shouldCancelInteraction();
        if (bl && !ctx.shouldCancelInteraction()) {
            Optional<Direction> optional;
            if (blockState.contains(ModWallHangingSignBlock.FACING)) {
                Direction direction2 = blockState.get(ModWallHangingSignBlock.FACING);
                if (direction2.getAxis().test(direction)) {
                    bl2 = false;
                }
            } else if (blockState.contains(ROTATION) && (optional = RotationPropertyHelper.toDirection(blockState.get(ROTATION))).isPresent() && optional.get().getAxis().test(direction)) {
                bl2 = false;
            }
        }
        int i = !bl2 ? RotationPropertyHelper.fromDirection(direction.getOpposite()) : RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + 180.0f);
        return (BlockState)((BlockState)((BlockState)this.getDefaultState().with(ATTACHED, bl2)).with(ROTATION, i)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape voxelShape = SHAPES_FOR_ROTATION.get(state.get(ROTATION));
        return voxelShape == null ? DEFAULT_SHAPE : voxelShape;
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return this.getOutlineShape(state, world, pos, ShapeContext.absent());
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !this.canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public float getRotationDegrees(BlockState state) {
        return RotationPropertyHelper.toDegrees(state.get(ROTATION));
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(ROTATION, rotation.rotate(state.get(ROTATION), 16));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return (BlockState)state.with(ROTATION, mirror.mirror(state.get(ROTATION), 16));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, ATTACHED, WATERLOGGED);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ModHangingSignBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return HangingSignBlock.validateTicker(type, ModBlockEntities.MODHANGINGSIGN, SignBlockEntity::tick);
    }
}
