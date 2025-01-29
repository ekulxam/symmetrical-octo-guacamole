package survivalblock.offhanddisabler.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Slot.class)
public abstract class SlotMixin {

    @Shadow public abstract ItemStack getStack();

    @ModifyReturnValue(method = "canTakeItems", at = @At("RETURN"))
    protected boolean bindingOffhandSlot(boolean original, PlayerEntity player) {
        return original;
    }
}
