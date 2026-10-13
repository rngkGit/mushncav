package net.rngk.mushncav.block.custom;

import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModSignBlock extends StandingSignBlock {
    public ModSignBlock(WoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType, properties);
    }
}
