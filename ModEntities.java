package net.istar.sword.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.istar.sword.SandSword;
import net.istar.sword.entity.custom.DuneEdgeEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class ModEntities {

    public static final EntityType<DuneEdgeEntity> DUNEEDGE;
    static {
        DUNEEDGE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SandSword.MOD_ID, "duneedge"), FabricEntityTypeBuilder.<DuneEdgeEntity>create(SpawnGroup.MISC, DuneEdgeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f,0.5f)).build());
    }
}
