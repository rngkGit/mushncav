package net.rngk.mushncav.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.rngk.mushncav.block.ModBlocks;
import net.rngk.mushncav.block.custom.FungiBlock;
import net.rngk.mushncav.world.tree.custom.ModTreeDecoratorTypes;

public class FungiTreeDecorator extends TreeDecorator {
    public static final Codec<FungiTreeDecorator> CODEC =
            Codec.floatRange(0.0f, 1.0f)
            .fieldOf("probability")
            .xmap(FungiTreeDecorator::new, decorator -> decorator.probability)
            .codec();
    /*public static final Codec<FungiTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.floatRange(0.0f, 1.0f)
                    .fieldOf("probability")
                    .forGetter(FungiTreeDecorator::getProbability),

            (Codec.intRange(0, 16)
                    .fieldOf("exclusion_radius_xz")
                    .forGetter(FungiTreeDecorator::getExclusionRadiusXZ)
    )).apply(instance, FungiTreeDecorator::new));*/

    protected final float probability;
    //protected final int exclusionRadiusXZ;

    // Getter for probability
    public float getProbability() {
        return probability;
    }

    // Getter for exclusionRadiusXZ
    /*public int getExclusionRadiusXZ() {
        return exclusionRadiusXZ;
    }*/

    // Constructor
    /*public FungiTreeDecorator(float probability, int exclusionRadiusXZ) {
        this.probability = probability;
        this.exclusionRadiusXZ = exclusionRadiusXZ;
    }*/
    public FungiTreeDecorator(float probability) {
        this.probability = probability;
    }

    // THIS IS THE ISSUE RIGHT HERE CHANGE THIS PLZ
    @Override
    protected TreeDecoratorType<?> getType() {
        return ModTreeDecoratorTypes.FUNGI_PLACER;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        if (random.nextFloat() >= this.probability) {
            return;
        }
        ObjectArrayList<BlockPos> list = generator.getLogPositions();
        ObjectArrayList<BlockPos> listRoot = generator.getRootPositions();
        int i = ((BlockPos) listRoot.get(0)).getY();
        listRoot.stream().filter(pos -> pos.getY() - i <= 9).forEach(pos -> {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                Direction direction2;
                BlockPos blockPos;
                if (!(random.nextFloat() <= 0.25f) || !generator.isAir(blockPos = pos.add((direction2 = direction.getOpposite()).getOffsetX(), 0, direction2.getOffsetZ())))
                    continue;
                generator.replace(blockPos, (BlockState) ((BlockState) ModBlocks.FUNGI_BLOCK.getDefaultState().with(FungiBlock.AGE, random.nextInt(3))).with(FungiBlock.FACING, direction).with(FungiBlock.COUNT, random.nextBetween(1, 3)));
            }
        });
    }
}
