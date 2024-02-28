package survivalblock.tridnet.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import survivalblock.tridnet.item.ModItems;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
    @ModifyReturnValue(
            method = "isAcceptableItem",
            at = @At("RETURN")
    )
    private boolean checkForDuneSword (boolean original, ItemStack stack){
        return original || stack.isOf(ModItems.DUNE_EDGE);
    }
}
