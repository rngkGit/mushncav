package net.rngk.mushncav.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.rngk.mushncav.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ModWallSignBlock extends WallSignBlock {
    //public static final MapCodec<WallSignBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(((MapCodec) WoodType.CODEC.fieldOf("wood_type")).forGetter(AbstractSignBlock::getWoodType), WallSignBlock.createSettingsCodec()).apply((Applicative<WallSignBlock, ?>)instance, WallSignBlock::new));
    public static final MapCodec<WallSignBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    WoodType.CODEC.fieldOf("wood_type").forGetter(modSignWallBlock -> modSignWallBlock.getWoodType()),
                    createSettingsCodec()
            ).apply(instance, ModWallSignBlock::new)
    );

    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    private static final Map<Direction, VoxelShape> FACING_TO_SHAPE = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(0.0, 4.5, 14.0, 16.0, 12.5, 16.0), Direction.SOUTH, Block.createCuboidShape(0.0, 4.5, 0.0, 16.0, 12.5, 2.0), Direction.EAST, Block.createCuboidShape(0.0, 4.5, 0.0, 2.0, 12.5, 16.0), Direction.WEST, Block.createCuboidShape(14.0, 4.5, 0.0, 16.0, 12.5, 16.0)));

    public MapCodec<WallSignBlock> getCodec() {
        return CODEC;
    }

    public ModWallSignBlock(WoodType woodType, AbstractBlock.Settings settings) {
        super(woodType, settings.sounds(woodType.soundType()));
        this.setDefaultState(((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(WATERLOGGED, false));
    }

    // Note: getTranslationKey() is now final in AbstractBlock - cannot override

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FACING_TO_SHAPE.get(state.get(FACING));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction facing = state.get(FACING);
        BlockPos attachPos = pos.offset(facing.getOpposite());
        BlockState attachState = world.getBlockState(attachPos);
        return attachState.isSideSolid(world, attachPos, facing, SideShapeType.RIGID);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        World worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();

        for (Direction direction : ctx.getPlacementDirections()) {
            if (!direction.getAxis().isHorizontal()) continue;

            Direction facing = direction.getOpposite();
            BlockState placementState = blockState.with(FACING, facing);
            if (placementState.canPlaceAt(worldView, blockPos)) {
                return placementState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            }
        }
        return null;
    }

    // getStateForNeighborUpdate signature changed - using parent implementation
    /*
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    */

    @Override
    public float getRotationDegrees(BlockState state) {
        // Manual conversion: SOUTH=0°, WEST=90°, NORTH=180°, EAST=270°
        Direction facing = state.get(FACING);
        return switch (facing) {
            case SOUTH -> 0.0F;
            case WEST -> 90.0F;
            case NORTH -> 180.0F;
            case EAST -> 270.0F;
            default -> 0.0F;
        };
    }

    @Override
    public Vec3d getCenter(BlockState state) {
        VoxelShape voxelShape = FACING_TO_SHAPE.get(state.get(FACING));
        return voxelShape.getBoundingBox().getCenter();
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
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
        return new SignBlockEntity(ModBlockEntities.MODSIGN, pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return AbstractSignBlock.validateTicker(type, ModBlockEntities.MODSIGN, SignBlockEntity::tick);
    }
}
