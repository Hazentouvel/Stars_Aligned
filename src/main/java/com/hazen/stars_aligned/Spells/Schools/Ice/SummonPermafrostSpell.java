package com.hazen.stars_aligned.Spells.Schools.Ice;

import com.hazen.stars_aligned.Entities.Mobs.Summons.Permafrost.SummonPermafrost;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.events.SpellSummonEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.*;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SummonPermafrostSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "summon_permafrost");
    private final DefaultConfig defaultConfig;

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.hp",
                        new Object[]{Utils.stringTruncation(this.getTotalHealth(spellLevel, caster), 1)}),

                Component.translatable("ui.irons_spellbooks.damage",
                        new Object[]{Utils.stringTruncation(this.getTotalDamage(spellLevel, caster), 1)})
        );
    }

    public SummonPermafrostSpell() {
        this.defaultConfig = (new DefaultConfig())
                .setMinRarity(SpellRarity.RARE)
                .setSchoolResource(SchoolRegistry.ICE_RESOURCE)
                .setMaxLevel(5)
                .setCooldownSeconds((double)150.0F)
                .build();
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 4;
        this.spellPowerPerLevel = 1;
        this.castTime = 20;
        this.baseManaCost = 100;
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of(SoundRegistry.ICE_BLOCK_CAST.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundEvents.EVOKER_CAST_SPELL);
    }

    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 2;
    }

    public void onRecastFinished(ServerPlayer serverPlayer, RecastInstance recastInstance, RecastResult recastResult, ICastDataSerializable castDataSerializable) {
        if (SummonManager.recastFinishedHelper(serverPlayer, recastInstance, recastResult, castDataSerializable)) {
            super.onRecastFinished(serverPlayer, recastInstance, recastResult, castDataSerializable);
        }

    }

    public ICastDataSerializable getEmptyCastData() {
        return new SummonedEntitiesCastData();
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        PlayerRecasts recasts = playerMagicData.getPlayerRecasts();
        if (!recasts.hasRecastForSpell(this)) {
            SummonedEntitiesCastData summonedEntitiesCastData = new SummonedEntitiesCastData();
            int summonTime = 12000;
            AttributeModifier healthModifier = new AttributeModifier(IronsSpellbooks.id("spell_power_health_bonus"), this.getHealthBonus(spellLevel, entity), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            AttributeModifier damageModifier = new AttributeModifier(IronsSpellbooks.id("spell_power_damage_bonus"), this.getDamageBonus(spellLevel, entity), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

            SummonPermafrost permafrost = new SummonPermafrost(world, entity);
            permafrost.moveTo(entity.getEyePosition().add(new Vec3(Utils.getRandomScaled((double)2.0F), (double)1.0F, Utils.getRandomScaled((double)2.0F))));
            permafrost.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(damageModifier);
            permafrost.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(healthModifier);
            permafrost.setHealth(permafrost.getMaxHealth());
            permafrost.finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(permafrost.getOnPos()), MobSpawnType.MOB_SUMMONED, null);
            SummonPermafrost creature = (SummonPermafrost)((SpellSummonEvent)NeoForge.EVENT_BUS.post(new SpellSummonEvent(entity, permafrost, this.spellId, spellLevel))).getCreature();
            world.addFreshEntity(creature);
            SummonManager.initSummon(entity, creature, summonTime, summonedEntitiesCastData);

            RecastInstance recastInstance = new RecastInstance(this.getSpellId(), spellLevel, this.getRecastCount(spellLevel, entity), summonTime, castSource, summonedEntitiesCastData);
            recasts.addRecast(recastInstance, playerMagicData);
        }

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public double getHealthBonus(int spellLevel, LivingEntity caster) {
        return (double)(this.getSpellPower(spellLevel, caster) - 1.0F) * 0.25;
    }

    public double getDamageBonus(int spellLevel, LivingEntity caster) {
        return (double)(this.getSpellPower(spellLevel, caster) - 3.0F) * 0.25;
    }

    public double getTotalHealth(int spellLevel, LivingEntity caster) {
        return 55.0D * (1.0D + this.getHealthBonus(spellLevel, caster));
    }

    public double getTotalDamage(int spellLevel, LivingEntity caster) {
        return 12.0D * (1.0D + this.getDamageBonus(spellLevel, caster));
    }


}