package net.rngk.mushncav.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class GlowingSapphireBlock extends Block {
    public GlowingSapphireBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        spawnParticles(level, pos, random);
    }

    private static void spawnParticles(Level level, BlockPos pos, RandomSource random) {
        for (Direction direction : Direction.values()) {
            BlockPos blockPos = pos.relative(direction);
            if (level.getBlockState(blockPos).canOcclude()) {
                continue;
            }

            Direction.Axis axis = direction.getAxis();
            double x = axis == Direction.Axis.X ? 0.5 + 0.5625 * direction.getStepX() : random.nextDouble();
            double y = axis == Direction.Axis.Y ? 0.5 + 0.5625 * direction.getStepY() : random.nextDouble();
            double z = axis == Direction.Axis.Z ? 0.5 + 0.5625 * direction.getStepZ() : random.nextDouble();

            level.addParticle(
                    new DustParticleOptions(0x0000FF, 1.0f),
                    pos.getX() + x,
                    pos.getY() + y,
                    pos.getZ() + z,
                    0.0,
                    0.0,
                    0.0
            );
        }
    }
}
