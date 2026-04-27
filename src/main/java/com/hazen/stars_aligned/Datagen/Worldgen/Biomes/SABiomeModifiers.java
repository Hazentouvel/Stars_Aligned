package com.hazen.stars_aligned.Datagen.Worldgen.Biomes;

import cn.leolezury.eternalstarlight.common.data.ESBiomes;
import com.hazen.stars_aligned.Datagen.Worldgen.PlacementFeatures.SAPlacedFeatures;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class SABiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_VOIDSTONE_MITHRIL_ORE = registerKey("add_voidstone_mithril_ore");


    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);


        context.register(ADD_VOIDSTONE_MITHRIL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "is_eternal_starlight"))),
                HolderSet.direct(placedFeatures.getOrThrow(SAPlacedFeatures.VOIDSTONE_MITHRIL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

    }



    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, name));
    }
}
