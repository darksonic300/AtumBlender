package teamrazor.aeroblender.mixin;




import net.minecraft.core.Holder;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.aeroblender.AetherRegionType;
import teamrazor.aeroblender.DimensionTypeTags;
import terrablender.api.RegionType;
import terrablender.util.LevelUtils;

@Mixin(value = LevelUtils.class, remap = false)
public abstract class LevelUtilsMixin {

    @Inject(at = @At("HEAD"), cancellable = true, method = "Lterrablender/util/LevelUtils;getRegionTypeForDimension(Lnet/minecraft/core/Holder;)Lterrablender/api/RegionType;")
    private static void addAether(Holder<DimensionType> dimensionType, CallbackInfoReturnable<RegionType> cir) {
        if (dimensionType.is(DimensionTypeTags.AETHER_REGIONS)) {
            cir.setReturnValue(AetherRegionType.THE_AETHER);
        }
    }
}
