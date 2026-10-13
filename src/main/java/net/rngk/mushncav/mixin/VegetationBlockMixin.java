package net.rngk.mushncav.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.rngk.mushncav.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VegetationBlock.class)
public class VegetationBlockMixin {

    @Inject(method = "mayPlaceOn", at = @At("HEAD"), cancellable = true)
    protected void mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Allow plants to be placed on our custom fungi grass and fungi dirt blocks
        if (floor.is(ModBlocks.FUNGI_GRASS_BLOCK) ||
            floor.is(ModBlocks.FUNGI_DIRT) ||
            floor.is(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK)) {
            cir.setReturnValue(true);
        }
    }
}
