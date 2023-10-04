package teamrazor.aeroblender.mixin;


import org.spongepowered.asm.mixin.Mixin;
import teamrazor.aeroblender.AeroBlenderConfig;
import teamrazor.aeroblender.aether.DefaultAetherRegion;
import terrablender.api.Regions;

import static terrablender.api.Regions.register;

@Mixin(value = Regions.class, remap = false)
public abstract class RegionMixin {
    static {
        register(new DefaultAetherRegion(AeroBlenderConfig.COMMON.vanillaAetherRegionWeight.get()));
    }
}
