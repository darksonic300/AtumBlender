package io.github.razordevs.aeroblender;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class AeroBlenderConfig {
    public static class Common {
        public final ModConfigSpec.ConfigValue<Integer> aetherRegionSize;
        public final ModConfigSpec.ConfigValue<Integer> vanillaAetherRegionWeight;

        public Common(ModConfigSpec.Builder builder) {

            builder.push("general");
            aetherRegionSize = builder
                    .comment("The size of aether biome regions from each mod that uses AeroBlender.")
                    .translation("aether_region_size")
                    .define("Aether Region Size", 3);
            builder.pop();

            builder.push("general");
            vanillaAetherRegionWeight = builder
                    .comment("The weighting of vanilla biome regions in the aether.")
                    .translation("vanilla_aether_region_weight")
                    .define("Aether Region Weight", 10);
            builder.pop();
        }

        public static AeroBlenderConfig CONFIG;

        public static void setConfig(AeroBlenderConfig config) {
            CONFIG = config;
        }
    }
    public static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }

}