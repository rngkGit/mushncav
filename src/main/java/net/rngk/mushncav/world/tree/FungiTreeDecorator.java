package net.rngk.mushncav.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.block.custom.FungiBlock;
import net.rngk.mushncav.world.tree.custom.ModTreeDecoratorTypes;

public class FungiTreeDecorator extends TreeDecorator {
    public static final MapCodec<FungiTreeDecorator> CODEC =
            Codec.floatRange(0.0f, 1.0f)
                    .fieldOf("probability")
                    .xmap(FungiTreeDecorator::new, decorator -> decorator.probability);

    protected final float probability;

    public float getProbability() {
        return probability;
    }

    public FungiTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.FUNGI_PLACER;
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource random = context.random();
        if (random.nextFloat() >= this.probability) {
            return;
        }
        ObjectArrayList<BlockPos> list = context.logs();
        if (list.isEmpty()) {
            return;
        }
        int minY = list.get(0).getY();
        list.stream().filter(pos -> pos.getY() - minY <= 9).forEach(pos -> {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (random.nextFloat() > 0.25f) continue;
                Direction opposite = direction.getOpposite();
                BlockPos blockPos = pos.offset(opposite.getStepX(), 0, opposite.getStepZ());
                if (!context.isAir(blockPos)) continue;

                BlockState fungiState = ModBlocks.FUNGI_BLOCK.defaultBlockState()
                        .setValue(FungiBlock.AGE, random.nextInt(3))
                        .setValue(FungiBlock.FACING, direction)
                        .setValue(FungiBlock.COUNT, 1 + random.nextInt(3));
                context.setBlock(blockPos, fungiState);
            }
        });
    }
}
