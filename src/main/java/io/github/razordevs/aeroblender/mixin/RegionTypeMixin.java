package io.github.razordevs.aeroblender.mixin;

import io.github.razordevs.aeroblender.aether.AetherRegionType;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terrablender.api.RegionType;

import java.util.ArrayList;
import java.util.Arrays;
//Based on https://gist.github.com/LlamaLad7/0b553d5ae04e4eb44d3a1e8558be9151
@Mixin(value = RegionType.class, remap = false)
abstract class RegionTypeMixin {

    @Shadow
    @Final
    @Mutable
    private static RegionType[] $VALUES;

    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static RegionType newVariant(String internalName, int internalId) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lterrablender/api/RegionType;$VALUES:[Lterrablender/api/RegionType;", shift = At.Shift.AFTER))
    private static void addCustomVariant(CallbackInfo ci) {
        var variants = new ArrayList<>(Arrays.asList(RegionType.values()));
        var aether = newVariant("THE_AETHER", variants.getLast().ordinal() + 1);
        AetherRegionType.THE_AETHER = aether;
        variants.add(aether);
        $VALUES = variants.toArray(new RegionType[0]);
    }
}