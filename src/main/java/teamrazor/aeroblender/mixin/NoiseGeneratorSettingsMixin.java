package teamrazor.aeroblender.mixin;


import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamrazor.aeroblender.Aeroblender;
import teamrazor.aeroblender.aether.AetherRegionType;
import teamrazor.aeroblender.aether.AetherRuleCategory;
import terrablender.api.RegionType;
import terrablender.worldgen.IExtendedNoiseGeneratorSettings;

@Mixin(value = NoiseGeneratorSettings.class, priority = 900)
public abstract class NoiseGeneratorSettingsMixin implements IExtendedNoiseGeneratorSettings {

    @Shadow
    private SurfaceRules.RuleSource surfaceRule;

    private RegionType regionType  = null;

    private SurfaceRules.RuleSource namespacedSurfaceRuleSource = null;

   @Inject(method = "surfaceRule", at = @At("HEAD"), cancellable = true)
   private void surfaceRule(CallbackInfoReturnable<SurfaceRules.RuleSource> cir) {
       if (this.regionType == AetherRegionType.THE_AETHER) {
           if (this.namespacedSurfaceRuleSource == null)
               this.namespacedSurfaceRuleSource = Aeroblender.getAetherNamespacedRules(AetherRuleCategory.THE_AETHER, this.surfaceRule);
           cir.setReturnValue(this.namespacedSurfaceRuleSource);
       }
   }

    @Override
    public void setRegionType(RegionType regionType)
    {
        this.regionType = regionType;
    }

    @Override
    public RegionType getRegionType()
    {
        return this.regionType ;
    }
}
