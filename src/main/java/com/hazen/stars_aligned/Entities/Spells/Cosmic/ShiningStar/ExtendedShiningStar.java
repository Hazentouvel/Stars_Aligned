package com.hazen.stars_aligned.Entities.Spells.Cosmic.ShiningStar;

import cn.leolezury.eternalstarlight.common.particle.RippleParticleOptions;
import cn.leolezury.eternalstarlight.common.util.ESEntityUtil;
import com.hazen.stars_aligned.Registries.SAEntityRegistry;
import com.hazen.stars_aligned.Spells.SASpellRegistries;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ExtendedShiningStar extends AbstractMagicProjectile {
    private float damage;
    private int delay = 0;
    private int age = 0;
    private Vec3 spawnPos;
    private LivingEntity lockedTarget = null;

    public ExtendedShiningStar(EntityType<? extends ExtendedShiningStar> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    public ExtendedShiningStar(EntityType<? extends ExtendedShiningStar> type, double x, double y, double z, Level level) {
        super(type, level);
        this.setPos(x, y, z);
    }

    public ExtendedShiningStar(Level level, LivingEntity shooter) {
        this(SAEntityRegistry.SHINING_STAR_SPELL.get(), shooter.getX(), shooter.getEyeY(), shooter.getZ(), level);
        this.setOwner(shooter);
    }

    public void setSpawnPos(Vec3 pos) {
        this.spawnPos = pos;
        if (pos != null) {
            this.setPos(pos);
        }
    }

    private LivingEntity findNearestTarget() {
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12),
                e -> e != this.getOwner() && e.isAlive() && !e.isInvisible());

        return entities.stream()
                .min(Comparator.comparingDouble(e -> e.distanceToSqr(this)))
                .orElse(null);
    }


    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return this.damage;
    }

    @Override
    public void trailParticles() {
    }

    @Override
    public void impactParticles(double v, double v1, double v2) {
    }

    @Override
    public float getSpeed() {
        return 1.4f;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    public void tick() {

        if (!level.isClientSide) {
            if (this.tickCount > 10) {
                if (lockedTarget == null || !lockedTarget.isAlive()) {
                    lockedTarget = findNearestTarget();
                }

                if (lockedTarget != null) {
                    Vec3 toTarget = lockedTarget.getEyePosition().subtract(this.position());
                    Vec3 currentMotion = this.getDeltaMovement();

                    double homingStrength = 0.05;
                    double speed = currentMotion.length();

                    Vec3 desiredDirection = toTarget.normalize();

                    Vec3 newDirection = currentMotion.normalize().scale(1.0 - homingStrength)
                            .add(desiredDirection.scale(homingStrength))
                            .normalize();

                    Vec3 newMotion = newDirection.scale(speed);
                    this.setDeltaMovement(newMotion);
                }
            }
        }

        if (!this.level().isClientSide) {
            if (this.tickCount > 300) {
                LivingEntity owner = this.getOwner() instanceof LivingEntity livingOwner ? livingOwner : null;

                explode(this.position(), owner, 3.0F);
            }
        }

        this.age++;

        if (this.age < this.delay) {
            if (this.spawnPos != null) {
                setPos(this.spawnPos);
            }
            return;
        }

        super.tick();
    }

    protected void onHitEntity(EntityHitResult pResult) {
        if (this.level.isClientSide) return;

        Entity hitEntity = pResult.getEntity();
        LivingEntity owner = (LivingEntity) this.getOwner();

        Vec3 center = hitEntity.position();

        explode(center, owner, 3.0F);

        discard();
    }

    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        switch (hitResult.getDirection().getAxis()) {
            case X -> this.setDeltaMovement(this.getDeltaMovement().multiply((double)-0.5F, (double)1.0F, (double)1.0F));
            case Y -> this.setDeltaMovement(this.getDeltaMovement().multiply((double)1.0F, (double)-0.5F, (double)1.0F));
            case Z -> this.setDeltaMovement(this.getDeltaMovement().multiply((double)1.0F, (double)1.0F, (double)-0.5F));
        }

    }

    private void explode(Vec3 center, LivingEntity owner, float radius) {
        List<LivingEntity> targets = this.level.getEntitiesOfClass(
                LivingEntity.class,
                AABB.ofSize(center, (double)radius * 2.0D, (double)radius * 2.0D, (double)radius * 2.0D));

        for (LivingEntity target : targets) {
            if (target == null || !target.isAlive()) continue;
            if (target == owner) continue;

            Vec3 toTarget = target.position().subtract(center);
            if (toTarget.lengthSqr() > radius * radius) continue;

            DamageSources.applyDamage(target, damage, SASpellRegistries.SHINING_STAR.get().getDamageSource(this, owner));
        }

        Level var4 = this.level();
        if (var4 instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)var4;
            RippleParticleOptions.addBlueExplosionRippleParticles(serverLevel, this.getX(), this.getY() + (double)(this.getBbHeight() / 2.0F), this.getZ(), this.random);
        }

        impactParticles(center.x, center.y, center.z);
    }


    public boolean isOnFire() {
        return false;
    }

}
