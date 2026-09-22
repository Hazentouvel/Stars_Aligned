package com.hazen.stars_aligned.Spells.Schools.Cosmic;

import com.hazen.stars_aligned.Entities.Spells.Cosmic.BouncingStar.ExtendedBouncyStar;
import com.hazen.stars_aligned.Entities.Spells.Cosmic.ShiningStar.ExtendedShiningStar;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.hazen.hazentouvelib.Registries.HLSchoolRegistry;
import net.hazen.hazentouvelib.Registries.HLSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BouncingStarsSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "bouncing_stars");
    private final DefaultConfig defaultConfig;

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        var li = new ArrayList<>(super.getUniqueInfo(spellLevel, caster));

        li.add(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}));

        return li;
    }

    public BouncingStarsSpell() {
        this.defaultConfig = (
                new DefaultConfig())
                .setMinRarity(SpellRarity.UNCOMMON)
                .setSchoolResource(HLSchoolRegistry.COSMIC_RESOURCE)
                .setMaxLevel(5)
                .setCooldownSeconds((double)2.5F)
                .build();
        this.manaCostPerLevel = 4;
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 15;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(HLSounds.COSMIC_CAST_INSTANT.get());
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        final int maxLevel = 10;
        final int extraMax = 2;
        int extra = Math.round((float)(Math.max(1, Math.min(spellLevel, maxLevel)) - 1) * extraMax / (float)(maxLevel - 1));
        int starCount = 3 + extra * 2;

        // Spread configuration
        float totalSpread = 30f;
        float step = starCount > 1 ? totalSpread / (starCount - 1) : 0f;
        float startAngle = -totalSpread / 2f;

        Vec3 look = entity.getLookAngle();

        for (int i = 0; i < starCount; i++) {
            float angleOffset = startAngle + (step * i);
            Vec3 spreadLook = look.yRot(angleOffset * Mth.DEG_TO_RAD);

            int pick = Utils.random.nextInt(4);
            ExtendedBouncyStar star = new ExtendedBouncyStar(world, entity);

            // Set position, direction and damage
            star.setPos(entity.position().add(0.0, entity.getEyeHeight() - star.getBoundingBox().getYsize() * 0.5, 0.0));
            star.shoot(spreadLook);
            star.setDamage(this.getDamage(spellLevel, entity));
            world.addFreshEntity(star);
        }

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker)
                .setIFrames(0)
                ;
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, entity) * 0.5F;
    }
}