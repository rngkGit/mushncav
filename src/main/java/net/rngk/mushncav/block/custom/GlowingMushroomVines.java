package net.rngk.mushncav.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.rngk.mushncav.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public interface GlowingMushroomVines {
    public static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    public static final BooleanProperty BERRIES = Properties.BERRIES;

    public static ActionResult pickBerries(@Nullable Entity picker, BlockState state, World world, BlockPos pos) {
        if (state.get(BERRIES).booleanValue()) {
            Block.dropStack(world, pos, new ItemStack(ModItems.GLOWING_BLUEBERRY, 3));
            float f = MathHelper.nextBetween(world.random, 0.8f, 1.2f);
            world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, f);
            BlockState blockState = (BlockState)state.with(BERRIES, false);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(picker, blockState));
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    public static boolean hasBerries(BlockState state) {
        return state.contains(BERRIES) && state.get(BERRIES) != false;
    }

    /**
     * {@return a function that receives a {@link BlockState} and returns the luminance for the state}
     * If there are no berries, it supplies the value 0.
     *
     * @apiNote The return value is meant to be passed to
     * {@link AbstractBlock.Settings#luminance} builder method.
     *
     * @param luminance luminance supplied when the block has berries
     */
    public static ToIntFunction<BlockState> getLuminanceSupplier(int luminance) {
        return state -> state.get(Properties.BERRIES) != false ? luminance : 0;
    }
}
