package teamrazor.aeroblender;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import teamrazor.aeroblender.mixin.SurfaceRuleManagerAccessor;
import terrablender.api.SurfaceRuleManager;
import terrablender.handler.InitializationHandler;
import terrablender.worldgen.surface.NamespacedSurfaceRuleSource;

@Mod(Aeroblender.MODID)
public class Aeroblender {
    public static final String MODID = "aeroblender";
    public Aeroblender()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AeroBlenderConfig.COMMON_SPEC);
    }

   public static SurfaceRules.RuleSource getAetherNamespacedRules(SurfaceRuleManager.RuleCategory category, SurfaceRules.RuleSource fallback) {
       ImmutableMap.Builder<String, SurfaceRules.RuleSource> builder = ImmutableMap.builder();
       builder.put("aether", SurfaceRuleManager.getDefaultSurfaceRules(category));
       builder.putAll(SurfaceRuleManagerAccessor.getSurfaceRules().get(category));
       System.out.println(builder);
       return new NamespacedSurfaceRuleSource(fallback, builder.build());
   }
}
