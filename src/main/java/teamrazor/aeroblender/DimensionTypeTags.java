package teamrazor.aeroblender;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.dimension.DimensionType;
import terrablender.core.TerraBlender;

public class DimensionTypeTags {
    public static final TagKey<DimensionType> AETHER_REGIONS = create("aether_regions");

    private static TagKey<DimensionType> create(String id) {
        return TagKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(TerraBlender.MOD_ID, id));
    }
}
