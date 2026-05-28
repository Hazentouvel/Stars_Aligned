package com.hazen.stars_aligned.Datagen.ItemGenerator;

import com.hazen.stars_aligned.Registries.SAItemRegistry;
import com.hazen.stars_aligned.SAUtils.SATags;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SAItemTagProvider extends ItemTagsProvider {
    public SAItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, StarsAligned.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(SATags.Items.ARMORS)
                .add(
                        SAItemRegistry.GLACITE_BATTLEMAGE_HELMET.get(),
                        SAItemRegistry.GLACITE_BATTLEMAGE_CHESTPLATE.get(),
                        SAItemRegistry.GLACITE_BATTLEMAGE_LEGGINGS.get(),
                        SAItemRegistry.GLACITE_BATTLEMAGE_BOOTS.get(),

                        SAItemRegistry.FLOWGLAZE_MASTERY_HELMET.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_CHESTPLATE.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_LEGGINGS.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_BOOTS.get()
                );

        tag(ItemTags.FREEZE_IMMUNE_WEARABLES)
                .add(
                        SAItemRegistry.GLACITE_BATTLEMAGE_HELMET.get(),
                        SAItemRegistry.GLACITE_BATTLEMAGE_CHESTPLATE.get(),
                        SAItemRegistry.GLACITE_BATTLEMAGE_LEGGINGS.get(),
                        SAItemRegistry.GLACITE_BATTLEMAGE_BOOTS.get(),

                        SAItemRegistry.FLOWGLAZE_MASTERY_HELMET.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_CHESTPLATE.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_LEGGINGS.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_BOOTS.get()
                );

        tag(ItemTags.HEAD_ARMOR)
                .add(
                        SAItemRegistry.GLACITE_BATTLEMAGE_HELMET.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_HELMET.get()
                );
        tag(ItemTags.CHEST_ARMOR)
                .add(

                        SAItemRegistry.GLACITE_BATTLEMAGE_CHESTPLATE.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_CHESTPLATE.get()
                );
        tag(ItemTags.LEG_ARMOR)
                .add(

                        SAItemRegistry.GLACITE_BATTLEMAGE_LEGGINGS.get(),
                        SAItemRegistry.GLACITE_BATTLEMAGE_LEGGINGS.get()
                );
        tag(ItemTags.FOOT_ARMOR)
                .add(

                        SAItemRegistry.GLACITE_BATTLEMAGE_BOOTS.get(),
                        SAItemRegistry.FLOWGLAZE_MASTERY_BOOTS.get()
                );
    }

}