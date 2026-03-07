package net.rngk.mushncav.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class GlowingSapphireBlock extends Block {
    public GlowingSapphireBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        spawnParticles(world, pos);
    }

    private static void spawnParticles(World world, BlockPos pos) {
        Random random = world.random;
        for (Direction direction : Direction.values()) {
            BlockPos blockPos = pos.offset(direction);
            if (world.getBlockState(blockPos).isOpaqueFullCube()) {
                continue;
            }

            Direction.Axis axis = direction.getAxis();
            double x = axis == Direction.Axis.X ? 0.5 + 0.5625 * direction.getOffsetX() : random.nextDouble();
            double y = axis == Direction.Axis.Y ? 0.5 + 0.5625 * direction.getOffsetY() : random.nextDouble();
            double z = axis == Direction.Axis.Z ? 0.5 + 0.5625 * direction.getOffsetZ() : random.nextDouble();

            world.addParticleClient(
                    new DustParticleEffect(0x0000FF, 1.0f),
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
