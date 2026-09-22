package com.hazen.stars_aligned.Entities.Spells.Nature.LunarThorn;

import cn.leolezury.eternalstarlight.common.entity.attack.LunarThorn;
import cn.leolezury.eternalstarlight.common.particle.ESSmokeParticleOptions;
import cn.leolezury.eternalstarlight.common.util.ESEntityUtil;
import cn.leolezury.eternalstarlight.common.util.Easing;
import com.hazen.stars_aligned.Registries.SAEntityRegistry;
import com.hazen.stars_aligned.Spells.SASpellRegistries;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.DamageSources;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ExtendedLunarThorn extends LunarThorn {
    private static final String TAG_OWNER = "owner";
    private @Nullable LivingEntity owner;
    private @Nullable UUID ownerId;

    private static final EntityDataAccessor<Integer> DATA_WAIT_TIME;

    private float damage;

    public ExtendedLunarThorn(EntityType<? extends ExtendedLunarThorn> type, Level level) {
        super(type, level);
    }

    public ExtendedLunarThorn(EntityType<? extends ExtendedLunarThorn> type, double x, double y, double z, Level level) {
        super(type, level);
        this.setPos(x, y, z);
    }

    public ExtendedLunarThorn(Level level, LivingEntity shooter) {
        this(SAEntityRegistry.LUNAR_THORN_SPELL.get(), shooter.getX(), shooter.getEyeY(), shooter.getZ(), level);
        this.setOwner(shooter);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return this.damage;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_WAIT_TIME, 10);
    }

    public int getWaitTime() {
        return this.entityData.get(DATA_WAIT_TIME);
    }

    public void setWaitTime(int waitTime) {
        this.entityData.set(DATA_WAIT_TIME, waitTime);
    }

    public void setOwner(LivingEntity owner) {
        this.ownerId = owner.getUUID();
        this.owner = owner;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {

            if (this.level() instanceof ServerLevel serverLevel) {
                if (this.owner == null && this.ownerId != null) {
                    Entity entity = serverLevel.getEntity(this.ownerId);

                    if (entity instanceof LivingEntity livingEntity) {
                        this.owner = livingEntity;
                    }

                    if (this.owner == null) {
                        this.ownerId = null;
                    }
                }
            }

            if (this.getSpawnedTicks() >= 200) {
                this.discard();
                return;
            }

            LivingEntity owner = this.getOwner();
            if (this.getSpawnedTicks() > 40 && owner != null) {

                for (LivingEntity target : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.5D))) {

                    if (!ESEntityUtil.shouldHarm(owner, target)) {
                        continue;
                    }

                    if (this.getAttackMode() == 1 && !target.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10));
                    }

                    DamageSources.applyDamage(target, this.getDamage(), ((AbstractSpell) SASpellRegistries.LUNAR_THORNS.get()).getDamageSource(this, owner));
                }
            }

            this.setSpawnedTicks(this.getSpawnedTicks() + 1);

        } else {

            float scale;

            if (this.getSpawnedTicks() <= 40) {
                scale = Easing.OUT_QUART.calculate((float) this.getSpawnedTicks() / 40.0F);
            } else {
                scale = 1.0F;
            }

            if (this.getSpawnedTicks() >= 160) {
                scale = Easing.OUT_QUART.calculate((float) (200 - this.getSpawnedTicks()) / 40.0F);
            }

            this.oldClientScale = this.clientScale;
            this.clientScale = scale;

            if (this.random.nextInt(20) == 0) {
                this.level().addParticle(ESSmokeParticleOptions.LUNAR_SHORT, this.getRandomX(1.5D), this.getRandomY(), this.getRandomZ(1.5D), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    static {
        DATA_WAIT_TIME = SynchedEntityData.defineId(
                ExtendedLunarThorn.class,
                EntityDataSerializers.INT
        );
    }
}