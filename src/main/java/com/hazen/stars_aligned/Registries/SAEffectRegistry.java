package com.hazen.stars_aligned.Registries;

import com.hazen.stars_aligned.Registries.Effects.TemperedEffect;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SAEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, StarsAligned.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> TEMPERED = MOB_EFFECTS.register("tempered", () -> new TemperedEffect(MobEffectCategory.BENEFICIAL, 3311322)

    );



    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}