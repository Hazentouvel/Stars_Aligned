package com.hazen.stars_aligned.Entities.Spells.Nature.LunarMonstrosityBreath;

import cn.leolezury.eternalstarlight.common.particle.ESSmokeParticleOptions;
import com.hazen.stars_aligned.Registries.SAEntityRegistry;
import com.hazen.stars_aligned.Spells.SASpellRegistries;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ExtendedLunarMonstrosityBreathProjectile extends AbstractConeProjectile {
    public ExtendedLunarMonstrosityBreathProjectile(EntityType<? extends AbstractConeProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ExtendedLunarMonstrosityBreathProjectile(Level level, LivingEntity entity) {
        super((EntityType) SAEntityRegistry.LUNAR_MONSTROSITY_BREATH_PROJECTILE.get(), level, entity);
    }

    public void spawnParticles() {
        Entity owner = this.getOwner();
        if (this.level().isClientSide && owner != null) {
            Vec3 rotation = owner.getLookAngle().normalize();

            Vec3 pos = owner.position().add(rotation.scale(1.6));

            double x = pos.x;
            double y = pos.y + owner.getEyeHeight() * 0.9F;
            double z = pos.z;

            double speed = this.random.nextDouble() * 0.4 + 0.45;

            for (int i = 0; i < 20; ++i) {
                double offset = 0.25F;

                double ox = Math.random() * 2.0D * offset - offset;
                double oy = Math.random() * 2.0D * offset - offset;
                double oz = Math.random() * 2.0D * offset - offset;

                double angularness = 0.8D;

                Vec3 randomVec = new Vec3(Math.random() * 2.0D * angularness - angularness, Math.random() * 2.0D * angularness - angularness, Math.random() * 2.0D * angularness - angularness).normalize();
                Vec3 result = rotation.scale(3.0D).add(randomVec).normalize().scale(speed);

                this.level().addParticle(ESSmokeParticleOptions.LUNAR_BREATH, x + ox, y + oy, z + oz, result.x, result.y, result.z);
            }
        }
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (DamageSources.applyDamage(entity, this.damage, ((AbstractSpell) SASpellRegistries.LUNAR_MONSTROSITY_BREATH.get()).getDamageSource(this, this.getOwner())) && entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
        }

    }
}
