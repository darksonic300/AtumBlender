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
package aeroblender.config;

import java.nio.file.Path;

public class AeroBlenderConfig extends ConfigFile
{
    public final int aetherRegionSize;
    public final int vanillaAetherRegionWeight;

    public AeroBlenderConfig(Path path)
    {
        super(path);

        Config generalConfig = this.getSubConfig("general");
        this.addSubConfig("General settings", "general", generalConfig);

        Config generationSettings = this.getSubConfig("generation_settings");
        this.aetherRegionSize = generationSettings.addNumber("The size of aether biome regions from each mod that uses AeroBlender.", "aether_region_size", 2, 2, 6);
        this.vanillaAetherRegionWeight = generationSettings.addNumber("The weighting of vanilla biome regions in the aether.", "vanilla_aether_region_weight", 10, 0, Integer.MAX_VALUE);
        this.addSubConfig("Generation settings", "generation_settings", generationSettings);

        this.save();
    }
}
