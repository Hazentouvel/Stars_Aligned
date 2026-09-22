package com.hazen.stars_aligned.Entities.Mobs.Summons.Permafrost;

import cn.leolezury.eternalstarlight.common.block.entity.spawner.BossSpawnerBlockEntity;
import cn.leolezury.eternalstarlight.common.config.ESConfig;
import cn.leolezury.eternalstarlight.common.entity.living.boss.golem.Permafrost;
import cn.leolezury.eternalstarlight.common.entity.living.goal.RandomFlyGoal;
import cn.leolezury.eternalstarlight.common.util.ESMathUtil;
import com.hazen.stars_aligned.Registries.SAEntityRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.SummonManager;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.mobs.goals.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SummonPermafrost extends Permafrost implements IMagicSummon {
    private final Vec3[] deathParticlePos;

    public SummonPermafrost(EntityType<? extends Permafrost> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.deathParticlePos = new Vec3[6];
        this.xpReward = 0;
    }

    public SummonPermafrost(Level pLevel, LivingEntity owner) {
        this((EntityType) SAEntityRegistry.SUMMON_PERMAFROST.get(), pLevel);
        this.setSummoner(owner);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PermafrostFlyToTargetGoal());
        this.goalSelector.addGoal(2, new RandomFlyGoal(this) {
            public boolean canUse() {
                return super.canUse() && SummonPermafrost.this.getTarget() == null && SummonPermafrost.this.getBehaviorState() == 0;
            }

            public boolean canContinueToUse() {
                return super.canContinueToUse() && SummonPermafrost.this.getTarget() == null && SummonPermafrost.this.getBehaviorState() == 0;
            }
        });
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

    @Override
    protected BlockState getBossSpawnerForRespawn() {
        return null;
    }

    @Override
    protected void tickDeath() {
        this.setDeltaMovement(Vec3.ZERO);
        if (this.deathTime == 0) {
            this.stopAllAnimStates();
            this.setBehaviorState(0);

            for(int i = 0; i < 6; ++i) {
                this.deathParticlePos[i] = this.position().add((double)0.0F, (double)(this.getBbHeight() / 2.0F), (double)0.0F);
            }
        }

        ++this.deathTime;
        if (this.deathTime == 70 && !this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }

    }

    @Override
    protected void bossDefeat() {
    }

    @Override
    protected boolean shouldSpawnLootChest() {
        return false;
    }

}