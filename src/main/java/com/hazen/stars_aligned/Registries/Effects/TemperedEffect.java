package com.hazen.stars_aligned.Registries.Effects;

import com.hazen.stars_aligned.Registries.SAEffectRegistry;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber
public class TemperedEffect extends MagicMobEffect {

    public TemperedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @SubscribeEvent
    public static void reduceDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effect = entity.getEffect(SAEffectRegistry.TEMPERED);
        if (effect != null) {
            int lvl = effect.getAmplifier() + 1;
            float multiplier = 1.0F - getReductionAmount(lvl);
            event.setAmount(event.getAmount() * multiplier);
        }

    }

    public static MobEffectInstance addTemperedStack(LivingEntity entity, @Nullable Entity afflicter) {
        MobEffectInstance previous = entity.getEffect(SAEffectRegistry.TEMPERED);
        MobEffectInstance inst;
        final int MAX_STACKS = 5;
        final int MAX_AMPLIFIER = MAX_STACKS - 1;
        if (previous != null) {
            int prevAmp = previous.getAmplifier();
            if (prevAmp >= MAX_AMPLIFIER) {
                inst = new MobEffectInstance(SAEffectRegistry.TEMPERED, 100, prevAmp, previous.isAmbient(), previous.isVisible(), previous.showIcon());
            } else {
                inst = new MobEffectInstance(SAEffectRegistry.TEMPERED, 100, prevAmp + 1, previous.isAmbient(), previous.isVisible(), previous.showIcon());
            }
        } else {
            inst = new MobEffectInstance(SAEffectRegistry.TEMPERED, 100, 0, false, false, true);
        }

        entity.addEffect(inst);
        return inst;
    }

    public static float getReductionAmount(int level) {
        return 0.1F + 0.05F * (float)level;
    }
}
