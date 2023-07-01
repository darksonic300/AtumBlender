package teamrazor.aeroblender.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import teamrazor.aeroblender.AeroBlenderConfig;
import teamrazor.aeroblender.DefaultAetherRegion;
import terrablender.api.Region;
import terrablender.api.Regions;
import terrablender.core.TerraBlender;
import terrablender.worldgen.DefaultNetherRegion;

import static terrablender.api.Regions.register;

@Mixin(value = Regions.class, remap = false)
public abstract class RegionMixin {
    static {
        register(new DefaultAetherRegion(AeroBlenderConfig.CONFIG.vanillaAetherRegionWeight));
    }
}
