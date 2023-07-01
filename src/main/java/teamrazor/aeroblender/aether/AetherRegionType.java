package teamrazor.aeroblender.aether;


import terrablender.api.RegionType;

//based on https://gist.github.com/LlamaLad7/0b553d5ae04e4eb44d3a1e8558be9151
public class AetherRegionType {
    static {
        RegionType.values(); // Ensure class is loaded before the variant is accessed
    }
    public static RegionType THE_AETHER;
}