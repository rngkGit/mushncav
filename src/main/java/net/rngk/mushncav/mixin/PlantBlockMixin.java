package net.rngk.mushncav.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.rngk.mushncav.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantBlockMixin {

    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    protected void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Allow plants to be placed on our custom fungi grass and fungi dirt blocks
        if (floor.isOf(ModBlocks.FUNGI_GRASS_BLOCK) ||
            floor.isOf(ModBlocks.FUNGI_DIRT) ||
            floor.isOf(ModBlocks.GLOWING_MUSHROOM_GRASS_BLOCK)) {
            cir.setReturnValue(true);
        }
    }
}

