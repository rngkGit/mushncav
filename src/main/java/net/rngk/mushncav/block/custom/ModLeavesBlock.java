package net.rngk.mushncav.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ModLeavesBlock extends LeavesBlock {
    public static final MapCodec<ModLeavesBlock> CODEC = createCodec(ModLeavesBlock::new);

    public ModLeavesBlock(Settings settings) {
        super(7.0f, settings); // 7.0f is the default distance for leaf decay
    }

    @Override
    public MapCodec<ModLeavesBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void spawnLeafParticle(World world, BlockPos pos, Random random) {
        // No particles for now
    }
}





