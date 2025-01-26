package com.samvolvo.learning.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.samvolvo.learning.access.OriginalDurationAccess;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = StatusEffectInstance.class, priority = 2000)
public class StatusEffectInstanceMixin implements OriginalDurationAccess {

    @Unique
    private static final String modId$ORIGINAL_DURATION_KEY = "modIdOriginalDuration";
    @Unique
    private static final String modId$ORIGINAL_COMPOUND_KEY = "modIdOriginalCompound";

    @Unique
    private int modId$originalDuration = -1;

    @Inject(method = "<init>(Lnet/minecraft/registry/entry/RegistryEntry;IIZZZLnet/minecraft/entity/effect/StatusEffectInstance;)V", at = @At("RETURN"))
    private void addOriginalDurationOnInit(RegistryEntry<StatusEffect> effect, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon, StatusEffectInstance hiddenEffect, CallbackInfo ci) {
        this.modId$originalDuration = duration;
    }

    @ModifyReturnValue(method = "writeNbt", at = @At("RETURN"))
    private NbtElement writeOriginalDuration(NbtElement original) {
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.put(modId$ORIGINAL_COMPOUND_KEY, original);
        nbtCompound.putInt(modId$ORIGINAL_DURATION_KEY, this.modId$originalDuration);
        return nbtCompound;
    }

    @ModifyVariable(method = "fromNbt", at = @At("HEAD"), index = 0, argsOnly = true)
    private static NbtCompound readOriginalDuration(NbtCompound original, @Share("originalDuration") LocalIntRef intRef) {
        if (original.contains(modId$ORIGINAL_DURATION_KEY)) {
            intRef.set(original.getInt(modId$ORIGINAL_DURATION_KEY));
        }
        if (original.contains(modId$ORIGINAL_COMPOUND_KEY)) {
            return (NbtCompound) original.get(modId$ORIGINAL_COMPOUND_KEY);
        }
        return original;
    }

    @ModifyReturnValue(method = "fromNbt", at = @At("RETURN"))
    private static StatusEffectInstance saveOriginalDurationWhenReading(StatusEffectInstance original, @Share("originalDuration") LocalIntRef intRef) {
        ((OriginalDurationAccess) original).modId$setOriginalDuration(intRef.get());
        return original;
    }

    @Override
    public int modId$getOriginalDuration() {
        return this.modId$originalDuration;
    }

    @Override
    public void modId$setOriginalDuration(int originalDuration) {
        this.modId$originalDuration = originalDuration;
    }

    /*
    you might also want to inject into StatusEffectInstance#upgrade to capture the original duration there also
    I'm not going to handle that here though
     */
    @Inject(method = "copyFrom", at = @At("RETURN"))
    private void copyOriginalDuration(StatusEffectInstance that, CallbackInfo ci) {
        this.modId$originalDuration = ((OriginalDurationAccess) that).modId$getOriginalDuration();
    }
}
