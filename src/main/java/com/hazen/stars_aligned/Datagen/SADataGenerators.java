package com.hazen.stars_aligned.Datagen;

import com.hazen.stars_aligned.Datagen.ItemGenerator.SABlockStateProvider;
import com.hazen.stars_aligned.Datagen.ItemGenerator.SABlockTagProvider;
import com.hazen.stars_aligned.Datagen.ItemGenerator.SAItemModelProvider;
import com.hazen.stars_aligned.Datagen.ItemGenerator.SAItemTagProvider;
import com.hazen.stars_aligned.Datagen.LootTables.SABlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(value = Dist.CLIENT)
public class SADataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //generator.addProvider(event.includeServer(), new SARecipeProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(SABlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        BlockTagsProvider blockTagsProvider = new SABlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new SAItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        //generator.addProvider(event.includeClient(), new SAItemModelProvider(packOutput, existingFileHelper));
        //generator.addProvider(event.includeClient(), new SABlockStateProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeServer(), new SADatapackProvider(packOutput, lookupProvider));
    }
}