package net.rngk.mushncav.block.custom;

import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWallSignBlock extends WallSignBlock {
    public ModWallSignBlock(WoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType, properties);
    }
}
