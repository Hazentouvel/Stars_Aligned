package com.hazen.stars_aligned.Entities.Spells.Cosmic.BouncingStar;

import cn.leolezury.eternalstarlight.common.particle.RippleParticleOptions;
import com.hazen.stars_aligned.Registries.SAEntityRegistry;
import com.hazen.stars_aligned.Spells.SASpellRegistries;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;

public class ExtendedBouncyStar extends AbstractMagicProjectile {
    private float damage;

    public ExtendedBouncyStar(EntityType<? extends ExtendedBouncyStar> type, Level level) {
        super(type, level);
    }

    public ExtendedBouncyStar(EntityType<? extends ExtendedBouncyStar> type, double x, double y, double z, Level level) {
        super(type, level);
        this.setPos(x, y, z);
    }

    public ExtendedBouncyStar(Level level, LivingEntity shooter) {
        this(SAEntityRegistry.BOUNCY_STAR_SPELL.get(), shooter.getX(), shooter.getEyeY(), shooter.getZ(), level);
        this.setOwner(shooter);
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

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return this.damage;
    }


    public void tick() {
        if (!this.level().isClientSide) {
            if (this.tickCount > 300) {
                LivingEntity owner = this.getOwner() instanceof LivingEntity livingOwner ? livingOwner : null;

                explode(this.position(), owner, 3.0F);
            }
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

            DamageSources.applyDamage(target, damage, SASpellRegistries.BOUNCING_STARS.get().getDamageSource(this, owner));
        }

        Level var4 = this.level();
        if (var4 instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)var4;
            RippleParticleOptions.addBlueExplosionRippleParticles(serverLevel, this.getX(), this.getY() + (double)(this.getBbHeight() / 2.0F), this.getZ(), this.random);
        }

        impactParticles(center.x, center.y, center.z);
    }

}