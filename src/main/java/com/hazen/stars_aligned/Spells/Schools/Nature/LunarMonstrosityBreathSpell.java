package com.hazen.stars_aligned.Spells.Schools.Nature;

import com.hazen.stars_aligned.Entities.Spells.Nature.LunarMonstrosityBreath.ExtendedLunarMonstrosityBreathProjectile;
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
import io.redspace.ironsspellbooks.entity.spells.AbstractConeProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.spells.EntityCastData;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class LunarMonstrosityBreathSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID,"lunar_monstrosity_breath");
    private final DefaultConfig defaultConfig;

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}));
    }

    public LunarMonstrosityBreathSpell() {
        this.defaultConfig = (new DefaultConfig())
                .setMinRarity(SpellRarity.RARE)
                .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
                .setMaxLevel(10)
                .setCooldownSeconds((double)12.0F)
                .build();
        this.manaCostPerLevel = 1;
        this.baseSpellPower = 0;
        this.spellPowerPerLevel = 1;
        this.castTime = 100;
        this.baseManaCost = 5;
    }

    public CastType getCastType() {
        return CastType.CONTINUOUS;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.empty();
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)SoundRegistry.POISON_BREATH_LOOP.get());
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.isCasting() && playerMagicData.getCastingSpellId().equals(this.getSpellId())) {
            ICastData var8 = playerMagicData.getAdditionalCastData();
            if (var8 instanceof EntityCastData) {
                EntityCastData entityCastData = (EntityCastData)var8;
                Entity var9 = entityCastData.getCastingEntity();
                if (var9 instanceof AbstractConeProjectile) {
                    AbstractConeProjectile cone = (AbstractConeProjectile)var9;
                    cone.setDealDamageActive();
                    return;
                }
            }
        }

        ExtendedLunarMonstrosityBreathProjectile breath = new ExtendedLunarMonstrosityBreathProjectile(world, entity);
        breath.setPos(entity.position().add((double)0.0F, (double)entity.getEyeHeight() * 0.7, (double)0.0F));
        breath.setDamage(this.getDamage(spellLevel, entity));
        world.addFreshEntity(breath);
        playerMagicData.setAdditionalCastData(new EntityCastData(breath));
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return 1.0F + this.getSpellPower(spellLevel, caster) * 0.75F;
    }

    public boolean shouldAIStopCasting(int spellLevel, Mob mob, LivingEntity target) {
        return mob.distanceToSqr(target) > (double)120.0F;
    }
}
