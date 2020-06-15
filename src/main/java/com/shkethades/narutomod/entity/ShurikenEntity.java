package com.shkethades.narutomod.entity;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.init.NarutoEntities;
import com.shkethades.narutomod.init.NarutoItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ShurikenEntity extends ProjectileItemEntity {

    public ShurikenEntity(EntityType<? extends ShurikenEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    public ShurikenEntity(World worldIn, LivingEntity throwerIn) {
        super(NarutoEntities.SHURIKEN_ENTITY.get(), throwerIn, worldIn);
    }

    public ShurikenEntity(World worldIn, double x, double y, double z) {
        super(NarutoEntities.SHURIKEN_ENTITY.get(), x, y, z, worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 8.0F);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return NarutoItems.SHURIKEN.get();
    }

    @Override
    protected void registerData() {

    }
}
