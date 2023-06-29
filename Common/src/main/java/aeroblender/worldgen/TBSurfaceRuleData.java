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
package aeroblender.worldgen;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.resources.builders.AetherBiomeBuilders;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import aeroblender.api.SurfaceRuleManager;

public class TBSurfaceRuleData
{
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(AetherBlocks.AETHER_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(AetherBlocks.AETHER_DIRT.get());

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState().setValue(AetherBlockStateProperties.DOUBLE_DROPS, true));
    }

    public static SurfaceRules.RuleSource aether() {
        SurfaceRules.RuleSource surface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), GRASS_BLOCK), DIRT);
        return SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, surface), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT));
    }

    /*public static SurfaceRules.RuleSource aether()
    {
        SurfaceRules.ConditionSource isAbove31 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0);
        SurfaceRules.ConditionSource yStart30 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0);
        SurfaceRules.ConditionSource isBelow35 = SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0));
        SurfaceRules.ConditionSource isTop5Blocks = SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
        SurfaceRules.ConditionSource isHole = SurfaceRules.hole();
        SurfaceRules.ConditionSource isSuitableSoulSandNoise = SurfaceRules.noiseCondition(Noises.SOUL_SAND_LAYER, -0.012D);
        SurfaceRules.ConditionSource surfacerules$conditionsource7 = SurfaceRules.noiseCondition(Noises.GRAVEL_LAYER, -0.012D);
        SurfaceRules.ConditionSource isSuitablePatchNoise = SurfaceRules.noiseCondition(Noises.PATCH, -0.012D);
        SurfaceRules.ConditionSource isSuitableNetherrackNoise = SurfaceRules.noiseCondition(Noises.NETHERRACK, 0.54D);
        SurfaceRules.ConditionSource surfacerules$conditionsource10 = SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17D);
        SurfaceRules.ConditionSource isStateSelectorNoiseSuitable = SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0D);

        SurfaceRules.RuleSource gravelPatchRules = SurfaceRules.ifTrue(isSuitablePatchNoise, SurfaceRules.ifTrue(yStart30, SurfaceRules.ifTrue(isBelow35, GRAVEL)));

        SurfaceRules.RuleSource bedrockRules = SurfaceRules.sequence(
            SurfaceRules.ifTrue(
                    SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
                    BEDROCK
            ),
            SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK)
        );

        SurfaceRules.RuleSource surfaceRules = SurfaceRules.sequence(
            SurfaceRules.ifTrue(isTop5Blocks, NETHERRACK),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.BASALT_DELTAS),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, BASALT),
                    SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.sequence(
                            gravelPatchRules,
                            SurfaceRules.ifTrue(isStateSelectorNoiseSuitable, BASALT),
                            BLACKSTONE
                        )
                    )
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.SOUL_SAND_VALLEY),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_CEILING,
                        SurfaceRules.sequence(
                            SurfaceRules.ifTrue(isStateSelectorNoiseSuitable, SOUL_SAND), SOUL_SOIL
                        )
                    ),
                    SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.sequence(
                            gravelPatchRules,
                            SurfaceRules.ifTrue(isStateSelectorNoiseSuitable, SOUL_SAND),
                            SOUL_SOIL
                        )
                    )
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                        SurfaceRules.not(surfacerules$conditionsource1),
                        SurfaceRules.ifTrue(isHole, LAVA)
                    ),
                    SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WARPED_FOREST),
                        SurfaceRules.ifTrue(
                            SurfaceRules.not(isSuitableNetherrackNoise),
                            SurfaceRules.ifTrue(
                                isAbove31,
                                SurfaceRules.sequence(
                                    SurfaceRules.ifTrue(surfacerules$conditionsource10, WARPED_WART_BLOCK),
                                    WARPED_NYLIUM
                                )
                            )
                        )
                    ),
                    SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.CRIMSON_FOREST),
                        SurfaceRules.ifTrue(
                            SurfaceRules.not(isSuitableNetherrackNoise),
                            SurfaceRules.ifTrue(isAbove31,
                                SurfaceRules.sequence(
                                    SurfaceRules.ifTrue(surfacerules$conditionsource10, NETHER_WART_BLOCK),
                                    CRIMSON_NYLIUM
                                )
                            )
                        )
                    )
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.NETHER_WASTES),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.ifTrue(
                            isSuitableSoulSandNoise,
                            SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                    SurfaceRules.not(isHole),
                                    SurfaceRules.ifTrue(yStart30, SurfaceRules.ifTrue(isBelow35, SOUL_SAND))
                                ),
                                NETHERRACK
                            )
                        )
                    ),
                    SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                            isAbove31,
                            SurfaceRules.ifTrue(
                                isBelow35,
                                SurfaceRules.ifTrue(
                                    surfacerules$conditionsource7,
                                    SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(surfacerules$conditionsource1, GRAVEL),
                                        SurfaceRules.ifTrue(SurfaceRules.not(isHole), GRAVEL)
                                    )
                                )
                            )
                        )
                    )
                )
            ),
            NETHERRACK
        );

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        builder.addAll(SurfaceRuleManager.getDefaultSurfaceRuleAdditionsForStage(SurfaceRuleManager.RuleCategory.AETHER, SurfaceRuleManager.RuleStage.BEFORE_BEDROCK));
        builder.add(bedrockRules);
        builder.addAll(SurfaceRuleManager.getDefaultSurfaceRuleAdditionsForStage(SurfaceRuleManager.RuleCategory.AETHER, SurfaceRuleManager.RuleStage.AFTER_BEDROCK));
        builder.add(surfaceRules);
        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }*/
}
