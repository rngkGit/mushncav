package net.rngk.mushncav.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModLeavesBlock extends LeavesBlock {
    public static final MapCodec<ModLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(propertiesCodec()).apply(instance, ModLeavesBlock::new)
    );

    public ModLeavesBlock(BlockBehaviour.Properties properties) {
        super(7.0f, properties);
    }

    @Override
    public MapCodec<ModLeavesBlock> codec() {
        return CODEC;
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
        // No particles for now
    }
}
