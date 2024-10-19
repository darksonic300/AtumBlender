package io.github.razordevs.aeroblender.aether;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;

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