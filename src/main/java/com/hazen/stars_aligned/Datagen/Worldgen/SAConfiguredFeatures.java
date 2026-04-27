package com.hazen.stars_aligned.Datagen.Worldgen;

import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import com.hazen.stars_aligned.Blocks.SABlocks;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class SAConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> VOIDSTONE_MITHRIL_ORE_KEY = registerKey("voidstone_mithril_ore");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest voidstoneReplaceables = new BlockMatchTest(ESBlocks.VOIDSTONE.get());


        List<OreConfiguration.TargetBlockState> endZenaliteOres = List.of(
                OreConfiguration.target(voidstoneReplaceables, SABlocks.VOIDSTONE_MITHRIL_ORE.get().defaultBlockState())
        );

        register(context, VOIDSTONE_MITHRIL_ORE_KEY, Feature.ORE, new OreConfiguration(endZenaliteOres, 6));


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, name));

    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
