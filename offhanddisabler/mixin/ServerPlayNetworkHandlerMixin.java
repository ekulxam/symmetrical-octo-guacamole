package survivalblock.offhanddisabler.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @Shadow public ServerPlayerEntity player;

    @ModifyExpressionValue(method = "onPlayerAction", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isSpectator()Z", ordinal = 0))
    private boolean stopOffhandSwap(boolean original) {
        if (original) {
            return true;
        }
        ItemStack stack = this.player.getStackInHand(Hand.OFF_HAND);
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        return stack.isOf(/* offhand disabler item */);
    }
}
