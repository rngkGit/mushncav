package net.rngk.mushncav.block.custom;

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
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.rngk.mushncav.block.entity.ModBlockEntities;

public class ModSignBlock extends SignBlock {
    /*public static final MapCodec<ModSignBlock> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(
                    WoodType.CODEC.fieldOf("wood_type").forGetter(modSignBlock -> modSignBlock.getWoodType()),
                    createSettingsCodec()
            ).apply(instance, ModSignBlock::new));*/
    public static final MapCodec<SignBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    WoodType.CODEC.fieldOf("wood_type").forGetter(modSignBlock -> modSignBlock.getWoodType()),
                    createSettingsCodec()
            ).apply(instance, ModSignBlock::new));
    
    public static final IntProperty ROTATION = Properties.ROTATION;

    public MapCodec<SignBlock> getCodec() {
        return CODEC;
    }

    public ModSignBlock(WoodType woodType, AbstractBlock.Settings settings) {
        super(woodType, settings.sounds(woodType.soundType()));
        this.setDefaultState(((this.stateManager.getDefaultState()).with(ROTATION, 0)).with(WATERLOGGED, false));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos below = pos.down();
        BlockState belowState = world.getBlockState(below);
        return belowState.isSideSolid(world, below, Direction.UP, SideShapeType.RIGID);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // Standing signs should only be placed when clicking on the top of a block
        if (ctx.getSide() != Direction.UP) {
            return null;
        }

        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockState state = this.getDefaultState()
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + 180.0f))
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);

        return state.canPlaceAt(ctx.getWorld(), ctx.getBlockPos()) ? state : null;
    }

    // getStateForNeighborUpdate signature changed to use ScheduledTickView - parent implementation handles breaking

    @Override
    public float getRotationDegrees(BlockState state) {
        return RotationPropertyHelper.toDegrees(state.get(ROTATION));
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(ROTATION, rotation.rotate(state.get(ROTATION), 16));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.with(ROTATION, mirror.mirror(state.get(ROTATION), 16));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, WATERLOGGED);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SignBlockEntity(ModBlockEntities.MODSIGN, pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.MODSIGN, SignBlockEntity::tick);
    }
}
