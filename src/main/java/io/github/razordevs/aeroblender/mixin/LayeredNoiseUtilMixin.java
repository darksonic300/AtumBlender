package io.github.razordevs.aeroblender.mixin;

import io.github.razordevs.aeroblender.AeroBlenderConfig;
import io.github.razordevs.aeroblender.aether.AetherRegionType;
import net.minecraft.core.RegistryAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terrablender.api.RegionType;
import terrablender.worldgen.noise.*;

@Mixin(value = LayeredNoiseUtil.class, remap = false)
public abstract class LayeredNoiseUtilMixin {

    @Shadow
    public static Area createZoomedArea(long seed, int zooms, AreaTransformer0 initialTransformer) {
        return null;
    }

    @Inject(at = @At("HEAD"), method = "uniqueness", cancellable = true)
    private static void uniqueness(RegistryAccess registryAccess, RegionType regionType, long seed, CallbackInfoReturnable<Area> cir) {
        if (regionType == AetherRegionType.THE_AETHER) {
            int numZooms1 = AeroBlenderConfig.COMMON.aetherRegionSize.get();
            cir.setReturnValue(createZoomedArea(seed, numZooms1, new InitialLayer(registryAccess, regionType)));
        }
    }
}

