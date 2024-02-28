//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minecraft.enchantment;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Vanishable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import survivalblock.tridnet.item.customitem.DuneEdgeItem;

@Debug(
    export = true
)
public enum EnchantmentTarget$6 {
    public EnchantmentTarget$6() {
    }

    public boolean isAcceptableItem(Item item) {
        return this.modifyReturnValue$zmp000$tridnet$isDuneEdge(item instanceof Vanishable || Block.getBlockFromItem(item) instanceof Vanishable || BREAKABLE.isAcceptableItem(item), item);
    }

    @MixinMerged(
        mixin = "survivalblock.tridnet.mixin.EnchantmentTargetMixin",
        priority = 1000,
        sessionId = "8e9e1af4-94cc-489b-9edf-b61a79fd15d7"
    )
    private boolean modifyReturnValue$zmp000$tridnet$isDuneEdge(boolean original, Item item) {
        return original || item instanceof DuneEdgeItem;
    }
}
