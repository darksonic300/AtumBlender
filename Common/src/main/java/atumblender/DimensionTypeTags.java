package atumblender;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.dimension.DimensionType;
import atumblender.core.AtumBlender;

public class DimensionTypeTags {
    public static final TagKey<DimensionType> AETHER_REGIONS = create("aether_regions");

    private static TagKey<DimensionType> create(String id) {
        return TagKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(AtumBlender.MOD_ID, id));
    }

    public static void init() {}
}
