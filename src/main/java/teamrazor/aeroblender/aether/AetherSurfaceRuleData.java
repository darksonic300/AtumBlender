/**
 * Copyright (C) Glitchfiend
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package teamrazor.aeroblender.aether;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import terrablender.api.SurfaceRuleManager;

//Default SurfaceRules of Aether
public class AetherSurfaceRuleData {
    private static final SurfaceRules.RuleSource AETHER_GRASS_BLOCK = makeStateRule(AetherBlocks.AETHER_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource AETHER_DIRT = makeStateRule(AetherBlocks.AETHER_DIRT.get());

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true));
    }

    public static SurfaceRules.RuleSource aether() {
         SurfaceRules.RuleSource surface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), AETHER_GRASS_BLOCK), AETHER_DIRT);
         return SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, surface), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, AETHER_DIRT));
    }
}