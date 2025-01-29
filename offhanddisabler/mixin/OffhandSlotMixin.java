package survivalblock.offhanddisabler.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

@Debug(export = true)
@Mixin(targets = "net.minecraft.screen.PlayerScreenHandler$1")
public abstract class OffhandSlotMixin extends SlotMixin {

    @Override
    protected boolean bindingOffhandSlot(boolean original, PlayerEntity player) {
        ItemStack stack = this.getStack();
        if (!stack.isEmpty() && !player.isCreative() && stack.isOf(/* offhand disabler item */)) {
            return false;
        }
        return original;
    }
}