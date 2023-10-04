package teamrazor.aeroblender.mixin;


import net.minecraft.core.RegistryAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.aeroblender.AeroBlenderConfig;
import teamrazor.aeroblender.aether.AetherRegionType;
import terrablender.api.RegionType;
import terrablender.worldgen.noise.*;

import java.util.function.LongFunction;



@Mixin(value = LayeredNoiseUtil.class, remap = false)
public abstract class LayeredNoiseUtilMixin {

    @Inject(at = @At("HEAD"), method = "uniqueness", cancellable = true)
    private static void uniqueness(RegistryAccess registryAccess, RegionType regionType, long worldSeed, CallbackInfoReturnable<Area> cir) {
        if(regionType == AetherRegionType.THE_AETHER) {
            int numZooms1 = AeroBlenderConfig.COMMON.aetherRegionSize.get();

            LongFunction<AreaContext> contextFactory = (seedModifier) -> new AreaContext(25, worldSeed, seedModifier);
            AreaFactory factory = new InitialLayer(registryAccess, regionType).run(contextFactory.apply(1L));
            factory = ZoomLayer.FUZZY.run(contextFactory.apply(2000L), factory);
            factory = zoom(2001L, ZoomLayer.NORMAL, factory, 3, contextFactory);
            factory = zoom(1001L, ZoomLayer.NORMAL, factory, numZooms1, contextFactory);
            cir.setReturnValue(factory.make());
            cir.cancel();
        }
    }

    @Shadow
    public static AreaFactory zoom(long seedModifier, AreaTransformer1 transformer, AreaFactory initialAreaFactory, int times, LongFunction<AreaContext> contextFactory) {
        AreaFactory areaFactory = initialAreaFactory;

        for(int i = 0; i < times; ++i) {
            areaFactory = transformer.run(contextFactory.apply(seedModifier + (long)i), areaFactory);
        }

        return areaFactory;
    }
}

