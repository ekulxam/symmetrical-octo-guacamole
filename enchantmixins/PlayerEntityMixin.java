package survivalblock.tridnet.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import survivalblock.tridnet.item.customitem.DuneEdgeItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

// aka sweep mixin
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @WrapOperation(method = "attack",
            constant = @Constant(classValue = SwordItem.class))

    private boolean isDuneEdge(Object obj, Operation<Boolean> original){
        return original.call(obj) || obj instanceof DuneEdgeItem;
    }
}
