package net.rngk.mushncav.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.root.RootPlacer;
import net.minecraft.world.gen.root.RootPlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RootPlacerType.class)
public interface RootPlacerTypeInvoker {
    @Invoker("register")
    static <P extends RootPlacer> RootPlacerType<P> callRegister(String id, Codec<P> codec) {
        throw new IllegalStateException();
    }
}
