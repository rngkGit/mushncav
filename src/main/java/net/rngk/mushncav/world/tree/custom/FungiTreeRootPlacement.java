package net.rngk.mushncav.world.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record FungiTreeRootPlacement(RegistryEntryList<Block> canGrowThrough, RegistryEntryList<Block> muddyRootsIn, BlockStateProvider muddyRootsProvider, int maxRootWidth, int maxRootLength, float randomSkewChance) {
    public static final Codec<FungiTreeRootPlacement> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(RegistryCodecs.entryList(RegistryKeys.BLOCK).fieldOf("can_grow_through").forGetter((rootPlacement) -> {
            return rootPlacement.canGrowThrough;
        }), RegistryCodecs.entryList(RegistryKeys.BLOCK).fieldOf("muddy_roots_in").forGetter((rootPlacement) -> {
            return rootPlacement.muddyRootsIn;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("muddy_roots_provider").forGetter((rootPlacement) -> {
            return rootPlacement.muddyRootsProvider;
        }), Codec.intRange(1, 12).fieldOf("max_root_width").forGetter((rootPlacement) -> {
            return rootPlacement.maxRootWidth;
        }), Codec.intRange(1, 64).fieldOf("max_root_length").forGetter((rootPlacement) -> {
            return rootPlacement.maxRootLength;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("random_skew_chance").forGetter((rootPlacement) -> {
            return rootPlacement.randomSkewChance;
        })).apply(instance, FungiTreeRootPlacement::new);
    });

    public RegistryEntryList<Block> canGrowThrough() {
        return canGrowThrough;
    }

    public RegistryEntryList<Block> muddyRootsIn() {
        return muddyRootsIn;
    }

    public BlockStateProvider muddyRootsProvider() {
        return muddyRootsProvider;
    }

    public int maxRootWidth() {
        return maxRootWidth;
    }

    public int maxRootLength() {
        return maxRootLength;
    }

    public float randomSkewChance() {
        return randomSkewChance;
    }
}