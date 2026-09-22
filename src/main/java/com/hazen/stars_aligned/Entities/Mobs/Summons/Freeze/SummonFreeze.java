package com.hazen.stars_aligned.Entities.Mobs.Summons.Freeze;

import cn.leolezury.eternalstarlight.common.config.ESConfig;
import cn.leolezury.eternalstarlight.common.entity.living.goal.RandomFlyGoal;
import cn.leolezury.eternalstarlight.common.entity.living.monster.Freeze;
import com.hazen.stars_aligned.Registries.SAEntityRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.SummonManager;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.goals.GenericFollowOwnerGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.GenericOwnerHurtByTargetGoal;
import io.redspace.ironsspellbooks.entity.mobs.goals.GenericOwnerHurtTargetGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SummonFreeze extends Freeze implements IMagicSummon {
    private final Vec3[] deathParticlePos;

    public SummonFreeze(EntityType<? extends Freeze> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.deathParticlePos = new Vec3[6];
        this.xpReward = 0;
    }

    public SummonFreeze(Level pLevel, LivingEntity owner) {
        this((EntityType) SAEntityRegistry.SUMMON_FREEZE.get(), pLevel);
        this.setSummoner(owner);
    }

    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FreezeAttackGoal(this, (double)1.5F, 120, 15.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.3));
        this.goalSelector.addGoal(7, new GenericFollowOwnerGoal(this, this::getSummoner, (double)0.65F, 35.0F, 10.0F, true, 50.0F));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));

        this.targetSelector.addGoal(1, new GenericOwnerHurtByTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(2, new GenericOwnerHurtTargetGoal(this, this::getSummoner));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ESConfig.INSTANCE.mobsConfig.permafrost.maxHealth())
                .add(Attributes.ARMOR, ESConfig.INSTANCE.mobsConfig.permafrost.armor())
                .add(Attributes.FOLLOW_RANGE, ESConfig.INSTANCE.mobsConfig.permafrost.followRange())
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.FLYING_SPEED, 0.8)
                .add(Attributes.ATTACK_DAMAGE, (double)6.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, (double)0.75F);
    }


    public boolean isPreventingPlayerRest(Player pPlayer) {
        return !this.isAlliedTo(pPlayer);
    }

    public void setSummoner(@Nullable LivingEntity owner) {
        if (owner != null) {
            SummonManager.setOwner(this, owner);
        }
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        return this.shouldIgnoreDamage(pSource) ? false : super.hurt(pSource, pAmount);
    }

    public void die(DamageSource pDamageSource) {
        this.onDeathHelper();
        super.die(pDamageSource);
    }

    public void onRemovedFromLevel() {
        this.onRemovedHelper(this);
        super.onRemovedFromLevel();
    }


    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity) || this.isAlliedHelper(pEntity);
    }

    public void onUnSummon() {
        if (!this.level.isClientSide) {
            MagicManager.spawnParticles(this.level, ParticleTypes.POOF, this.getX(), this.getY(), this.getZ(), 25, 0.4, 0.8, 0.4, 0.03, false);
            this.setRemoved(RemovalReason.DISCARDED);
        }

    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (target == this.getSummoner()) {
            return false;
        }

        if (this.isAlliedTo(target)) {
            return false;
        }

        return target.isAlive()
                && target != this
                && !target.isInvulnerable()
                && this.getSensing().hasLineOfSight(target);
    }

    private static class FreezeAttackGoal extends RangedAttackGoal {
        private final Freeze freeze;

        public FreezeAttackGoal(Freeze freeze, double speed, int interval, float radius) {
            super(freeze, speed, interval, radius);
            this.freeze = freeze;
        }

        public void start() {
            super.start();
            this.freeze.setAggressive(true);
        }

        public void stop() {
            super.stop();
            this.freeze.setAggressive(false);
        }
    }

}