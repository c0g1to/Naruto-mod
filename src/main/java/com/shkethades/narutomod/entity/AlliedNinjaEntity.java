package com.shkethades.narutomod.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class AlliedNinjaEntity extends MonsterEntity {

    public AlliedNinjaEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this) {
            public boolean shouldExecute() {
                return AlliedNinjaEntity.this.isInWater() && AlliedNinjaEntity.this.getSubmergedHeight() > 0.6 || AlliedNinjaEntity.this.isInLava();
            }
        });
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 15.0F));
        this.goalSelector.addGoal(1, new AlliedNinjaEntity.AIMeleeAttack());
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, NinjaEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, false));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1D);
    }

    public boolean canSpawn(IWorld worldIn, SpawnReason reason) {
        return true;
    }

    public class AIMeleeAttack extends MeleeAttackGoal {

        public AIMeleeAttack() {
            super(AlliedNinjaEntity.this, 1.25D, true);
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double d0 = this.getAttackReachSqr(enemy);
            if (distToEnemySqr <= d0 && this.attackTick <= 0) {
                this.attackTick = 20;
                this.attacker.attackEntityAsMob(enemy);
            } else if (distToEnemySqr <= d0 * 2.0D) {
                if (this.attackTick <= 0) {
                    this.attackTick = 20;
                }
            } else {
                this.attackTick = 20;
            }
        }

        @Override
        public void resetTask() {
            super.resetTask();
        }

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return 10.0F + attackTarget.getWidth();
        }
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.95F;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
    }

}
