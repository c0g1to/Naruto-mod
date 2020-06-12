package com.shkethades.narutomod.entities;
import com.google.common.base.Predicates;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.*;

public class NinjaEntity extends MonsterEntity  {

    public NinjaEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this) {
            public boolean shouldExecute() {
                return NinjaEntity.this.isInWater() && NinjaEntity.this.getSubmergedHeight() > 0.6 || NinjaEntity.this.isInLava();
            }
        });
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 15.0F));
        this.goalSelector.addGoal(1, new NinjaEntity.AIMeleeAttack());
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AlliedNinjaEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, 90, true, true, Predicates.alwaysTrue()));
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
        return worldIn.getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();

            if (entity instanceof PlayerEntity) {
                this.setAttackTarget((PlayerEntity) entity);
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean isPreventingPlayerRest(PlayerEntity playerIn) {
        return this.world.getDifficulty() != Difficulty.PEACEFUL && this.getAttackingEntity() == playerIn;
    }

    public class AIMeleeAttack extends MeleeAttackGoal {

        public AIMeleeAttack() {
            super(NinjaEntity.this, 1.25D, true);
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
