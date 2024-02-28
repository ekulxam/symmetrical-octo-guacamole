package survivalblock.tridnet.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import survivalblock.tridnet.item.customitem.DuneEdgeItem;

@Debug(export = true)
@Mixin(targets = "net/minecraft/enchantment/EnchantmentTarget$6")
public abstract class EnchantmentTargetMixin {
    @ModifyReturnValue(method = "isAcceptableItem",
            at = @At("RETURN"))

    private boolean isDuneEdge(boolean original, Item item){
        return original || item instanceof DuneEdgeItem;
    }
}
