package io.github.razordevs.aeroblender.mixin;


import io.github.razordevs.aeroblender.AeroBlenderConfig;
import org.spongepowered.asm.mixin.Mixin;
import io.github.razordevs.aeroblender.aether.DefaultAetherRegion;
import terrablender.api.Regions;

import static terrablender.api.Regions.register;

@Mixin(value = Regions.class, remap = false)
public abstract class RegionMixin {
    static {
        register(new DefaultAetherRegion(AeroBlenderConfig.COMMON.vanillaAetherRegionWeight.get()));
    }
}
