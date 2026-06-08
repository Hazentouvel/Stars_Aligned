package com.hazen.stars_aligned.Registries;

import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SASounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, StarsAligned.MOD_ID);

    public static DeferredHolder<SoundEvent, SoundEvent> WATER_SPEAR_CAST = registerSoundEvent("water_spear_cast");

    public static final Supplier<SoundEvent> STELLAR_LULLABY = registerSoundEvent("stellar_lullaby");
    public static final ResourceKey<JukeboxSong> STELLAR_LULLABY_KEY = createSong("stellar_lullaby");

    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, name));
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name)
    {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent
                (ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, name)));
    }


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}