package io.github.razordevs.aeroblender.mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import terrablender.api.SurfaceRuleManager;

import java.util.Map;

@Mixin(value = SurfaceRuleManager.class, remap = false)
public interface SurfaceRuleManagerAccessor {

    @Accessor
    static Map<SurfaceRuleManager.RuleCategory, Map<String, SurfaceRules.RuleSource>> getSurfaceRules() {
        throw new UnsupportedOperationException();
    }
}
