package com.hazen.stars_aligned.Spells.Schools.Nature;

import com.hazen.stars_aligned.Entities.Spells.Nature.LunarThorn.ExtendedLunarThorn;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.ICastData;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LunarThornSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID,"lunar_thorns");
    private final DefaultConfig defaultConfig;

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}), Component.translatable("ui.irons_spellbooks.spike_count", new Object[]{this.getCount(spellLevel, caster)}));
    }

    public LunarThornSpell() {
        this.defaultConfig = (new DefaultConfig())
                .setMinRarity(SpellRarity.COMMON)
                .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
                .setMaxLevel(10)
                .setCooldownSeconds((double)15.0F)
                .build();
        this.manaCostPerLevel = 10;
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 30;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        Utils.preCastTargetHelper(level, entity, playerMagicData, this, (int)((float)this.getCount(spellLevel, entity) * 1.25F), 0.15F, false);
        return true;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 forward = entity.getForward().multiply((double)1.0F, (double)0.0F, (double)1.0F).normalize();
        Vec3 start = entity.getEyePosition().add(forward.scale((double)1.5F));
        float damage = this.getDamage(spellLevel, entity);
        float minScale = 1.0F;
        float maxScale = 3.0F;
        int count = this.getCount(spellLevel, entity);
        start = Utils.moveToRelativeGroundLevel(level, start, 1, 3).add((double)0.0F, 0.1, (double)0.0F);
        double distance = (double)count;
        ICastData delay = playerMagicData.getAdditionalCastData();
        if (delay instanceof TargetEntityCastData castTargetingData) {
            LivingEntity target = castTargetingData.getTarget((ServerLevel)level);
            if (target != null) {
                distance = start.subtract(target.position()).horizontalDistance();
                Vec3 targetPos = target.position().add(target.getDeltaMovement().multiply(distance, (double)0.0F, distance));
                distance = targetPos.subtract(start).horizontalDistance();
            }
        }

        float distanceCovered = 0.0F;

        for(int i = 0; i < count; ++i) {
            float f = (float)Math.max((double)((float)i / (float)count), (double)(distanceCovered + 1.0F) / distance);
            float scale = Mth.lerp(f, minScale, maxScale);
            Vec3 spawn = start.add(forward.scale((double)i));
            Vec3 ground = Utils.moveToRelativeGroundLevel(level, spawn, 8);
            spawn = ground.subtract(spawn).scale((double)Mth.clamp((float)i / 3.0F, 0.0F, 1.0F)).add(spawn);
            boolean isFinalSpike = i == count - 1 || (double)(distanceCovered + 1.0F) > distance;
            if (isFinalSpike) {
                scale = maxScale * 1.5F;
            }

            distanceCovered += (float)forward.horizontalDistance();
            if (level.getBlockState(BlockPos.containing(spawn).below()).isFaceSturdy(level, BlockPos.containing(spawn).below(), Direction.UP)) {
                ExtendedLunarThorn spike = new ExtendedLunarThorn(level, entity);
                if (i % 2 == count % 2) {
                    spike.setSilent(true);
                }

                spike.moveTo(spawn.add((double)0.0F, (double)0.0F, (double)0.0F));
                spike.setWaitTime(i);
                spike.setDamage(damage * (isFinalSpike ? 1.0F : 0.5F));
                spike.setYRot(entity.getYRot() - 45.0F + (float)Utils.random.nextIntBetweenInclusive(-20, 20));
                spike.setXRot((float)Utils.random.nextIntBetweenInclusive(-15, 15));
                level.addFreshEntity(spike);
            }

            if (isFinalSpike) {
                break;
            }
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        float f = (float)this.getCount(spellLevel, mob) * 1.5F;
        return mob.distanceToSqr(target) > (double)(f * f);
    }

    private int getCount(int spellLevel, LivingEntity entity) {
        return 7 + 3 * spellLevel / 2;
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, entity);
    }
}
