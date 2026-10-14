package net.rngk.mushncav.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.rngk.mushncav.world.tree.custom.ModTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;

public class FungiTreeTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<FungiTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(objectInstance ->
            trunkPlacerParts(objectInstance).apply(objectInstance, FungiTreeTrunkPlacer::new));

    public FungiTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.FUNGI_TREE_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        int height_ = height + random.nextInt(3) + heightRandA + random.nextInt(3) - 1 + heightRandB;

        for (int i = 0; i < height_; i++) {
            placeLog(level, replacer, random, startPos.above(i), config);
        }

        return List.of(new FoliagePlacer.FoliageAttachment(startPos.above(height_), 0, false));
    }
}
