package com.hazen.stars_aligned.Datagen;

import com.hazen.stars_aligned.Datagen.Worldgen.Biomes.SABiomeModifiers;
import com.hazen.stars_aligned.Datagen.Worldgen.PlacementFeatures.SAPlacedFeatures;
import com.hazen.stars_aligned.Datagen.Worldgen.SAConfiguredFeatures;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SADatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, SAConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, SAPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, SABiomeModifiers::bootstrap)
            ;

    public SADatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(StarsAligned.MOD_ID));
    }
}
