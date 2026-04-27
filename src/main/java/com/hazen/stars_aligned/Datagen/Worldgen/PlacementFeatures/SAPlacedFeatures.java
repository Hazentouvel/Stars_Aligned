package com.hazen.stars_aligned.Datagen.Worldgen.PlacementFeatures;

import com.hazen.stars_aligned.Datagen.Worldgen.SAConfiguredFeatures;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class SAPlacedFeatures {
    public static final ResourceKey<PlacedFeature> VOIDSTONE_MITHRIL_ORE_PLACED_KEY = registerKey("voidstone_mithril_ore_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, VOIDSTONE_MITHRIL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(SAConfiguredFeatures.VOIDSTONE_MITHRIL_ORE_KEY),
                SAOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement
                                .uniform(VerticalAnchor.absolute(-63),
                                        VerticalAnchor.absolute(-38))
                ));


    }


    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
