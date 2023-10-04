/**
 * Copyright (C) Glitchfiend
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package teamrazor.aeroblender;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import terrablender.config.Config;
import terrablender.config.ConfigFile;

import java.nio.file.Path;

public class AeroBlenderConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Integer> aetherRegionSize;
        public final ForgeConfigSpec.ConfigValue<Integer> vanillaAetherRegionWeight;

        public Common(ForgeConfigSpec.Builder builder) {

            builder.push("general");
            aetherRegionSize = builder
                    .comment("The size of aether biome regions from each mod that uses AeroBlender.")
                    .translation("aether_region_size")
                    .define("Aether Region Size", 2);
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
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }

}