package net.rngk.mushncav.world.tree;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.CocoaBeansTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.block.custom.FungiBlock;

public class FungiTreeDecorator extends CocoaBeansTreeDecorator {
    public static final Codec<FungiTreeDecorator> CODEC = Codec.floatRange(0.0f, 1.0f)
            .fieldOf("probability")
            .xmap(FungiTreeDecorator::new, decorator -> decorator.probability)
            .codec();
    private final float probability;

    /*public FungiTreeDecorator(float probability) {
        this.probability = probability;
    }*/
    public FungiTreeDecorator(float probability) {
        super(probability);
        this.probability = probability;
    }

    // THIS IS THE ISSUE RIGHT HERE CHANGE THIS PLZ
    @Override
    protected TreeDecoratorType<?> getType() {
        return TreeDecoratorType.COCOA;
    }

    @Override
    public void generate(TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        if (random.nextFloat() >= this.probability) {
            return;
        }
        ObjectArrayList<BlockPos> list = generator.getLogPositions();
        int i = ((BlockPos) list.get(0)).getY();
        list.stream().filter(pos -> pos.getY() - i <= 2).forEach(pos -> {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                Direction direction2;
                BlockPos blockPos;
                if (!(random.nextFloat() <= 0.25f) || !generator.isAir(blockPos = pos.add((direction2 = direction.getOpposite()).getOffsetX(), 0, direction2.getOffsetZ())))
                    continue;
                generator.replace(blockPos, (BlockState) ((BlockState) ModBlocks.FUNGI_BLOCK.getDefaultState().with(FungiBlock.AGE, random.nextInt(3))).with(FungiBlock.FACING, direction));
            }
        });
    }
}
