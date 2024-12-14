package net.rngk.mushncav.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

public class FungiTreeLog extends PillarBlock {

    public static final BooleanProperty ISINFECTED = BooleanProperty.of("isinfected");

    public FungiTreeLog(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ISINFECTED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ISINFECTED);
    }
}
