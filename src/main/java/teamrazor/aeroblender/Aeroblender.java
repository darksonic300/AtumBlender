package teamrazor.aeroblender;

import com.google.common.collect.ImmutableMap;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;
import teamrazor.aeroblender.aether.AetherRegionType;
import teamrazor.aeroblender.aether.AetherRuleCategory;
import teamrazor.aeroblender.aether.AetherSurfaceRuleData;
import teamrazor.aeroblender.mixin.SurfaceRuleManagerAccessor;
import terrablender.api.RegionType;
import terrablender.api.SurfaceRuleManager;
import terrablender.worldgen.surface.NamespacedSurfaceRuleSource;

import java.util.Arrays;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Aeroblender.MODID)
public class Aeroblender
{ public static final String MODID = "aeroblender";
    public Aeroblender()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
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
