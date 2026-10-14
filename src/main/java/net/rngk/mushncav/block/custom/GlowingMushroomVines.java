package net.rngk.mushncav.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rngk.mushncav.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public interface GlowingMushroomVines {
    VoxelShape SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
    BooleanProperty BERRIES = BlockStateProperties.BERRIES;

    static InteractionResult pickBerries(@Nullable Entity picker, BlockState state, Level level, BlockPos pos) {
        if (state.getValue(BERRIES)) {
            if (level instanceof ServerLevel serverLevel) {
                Block.popResource(serverLevel, pos, new ItemStack(ModItems.GLOWING_GRAPES, 1));
                float f = Mth.randomBetween(serverLevel.getRandom(), 0.8f, 1.2f);
                level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0f, f);
                BlockState blockState = state.setValue(BERRIES, false);
                level.setBlock(pos, blockState, 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(picker, blockState));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    static ToIntFunction<BlockState> getLuminanceSupplier(int luminance) {
        return state -> state.getValue(BERRIES) ? luminance : 0;
    }
}
