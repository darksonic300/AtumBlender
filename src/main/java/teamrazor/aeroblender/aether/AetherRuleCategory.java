package teamrazor.aeroblender.aether;



import terrablender.api.SurfaceRuleManager;

//based on https://gist.github.com/LlamaLad7/0b553d5ae04e4eb44d3a1e8558be9151
public class AetherRuleCategory {
    static {
        SurfaceRuleManager.RuleCategory.values(); // Ensure class is loaded before the variant is accessed
    }
    public static SurfaceRuleManager.RuleCategory THE_AETHER;
}