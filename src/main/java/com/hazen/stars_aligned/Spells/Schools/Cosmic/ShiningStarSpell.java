package com.hazen.stars_aligned.Spells.Schools.Cosmic;

import com.hazen.stars_aligned.Entities.Spells.Cosmic.ShiningStar.ExtendedShiningStar;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MultiTargetEntityCastData;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.hazen.hazentouvelib.Registries.HLSchoolRegistry;
import net.hazen.hazentouvelib.Registries.HLSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShiningStarSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "shining_star");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        var li = new ArrayList<>(super.getUniqueInfo(spellLevel, caster));

        li.add(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}));

        return li;
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(HLSchoolRegistry.COSMIC_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(6)
            .build();

    public ShiningStarSpell() {
        this.manaCostPerLevel = 6;
        this.baseSpellPower = 20;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 30;
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(HLSounds.COSMIC_CAST_INSTANT.value());
    }


    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        if (world.isClientSide) return;
        int count = 3;
        int delayBetween = 10;
        Vec3 castPos = caster.position().add(0, caster.getEyeHeight() - 0.8, 0);


        for (int i = 0; i < count; i++) {
            ExtendedShiningStar burst = new ExtendedShiningStar(world, caster);
            burst.setDelay(i * delayBetween);
            burst.setDamage(getDamage(spellLevel, caster));
            burst.setSpawnPos(castPos);
            burst.shootFromRotation(caster, caster.getXRot() + 0.0F, caster.getYRot() + 0.0F, 0.0F, 1.5F, 0.0F);
            burst.setCursorHoming(true);
            world.addFreshEntity(burst);
        }
        super.onCast(world, spellLevel, caster, castSource, playerMagicData);
    }

    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker).setIFrames(0);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity) * .3f;
    }

    public ICastDataSerializable getEmptyCastData() {
        return new MultiTargetEntityCastData(new Entity[0]);
    }
}