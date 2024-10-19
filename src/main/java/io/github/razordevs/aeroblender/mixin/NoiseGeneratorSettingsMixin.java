package io.github.razordevs.aeroblender.mixin;


import io.github.razordevs.aeroblender.aether.AetherRuleCategory;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import io.github.razordevs.aeroblender.Aeroblender;
import terrablender.api.SurfaceRuleManager;
import terrablender.worldgen.IExtendedNoiseGeneratorSettings;

@Mixin(value = NoiseGeneratorSettings.class, priority = 900)
public class NoiseGeneratorSettingsMixin implements IExtendedNoiseGeneratorSettings {

    @Final
    @Shadow
    private SurfaceRules.RuleSource surfaceRule;

    @Unique
    private SurfaceRuleManager.RuleCategory aeroBlender$ruleCategory = null;

    @Unique
    private SurfaceRules.RuleSource aeroBlender$namespacedSurfaceRuleSource = null;

    @Inject(method = "surfaceRule", at = @At("HEAD"), cancellable = true)
    private void surfaceRule(CallbackInfoReturnable<SurfaceRules.RuleSource> cir) {
        if (this.aeroBlender$ruleCategory == AetherRuleCategory.THE_AETHER) {
            if (this.aeroBlender$namespacedSurfaceRuleSource == null)
                this.aeroBlender$namespacedSurfaceRuleSource = Aeroblender.getAetherNamespacedRules(AetherRuleCategory.THE_AETHER, this.surfaceRule);
            cir.setReturnValue(this.aeroBlender$namespacedSurfaceRuleSource);
        }
    }

    @Override
    public void setRuleCategory(SurfaceRuleManager.RuleCategory ruleCategory) {
        this.aeroBlender$ruleCategory = ruleCategory;
    }
}
