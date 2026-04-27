package com.hazen.stars_aligned.Datagen.ItemGenerator;

import com.hazen.stars_aligned.Blocks.SABlocks;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SABlockTagProvider extends BlockTagsProvider {
    public SABlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, StarsAligned.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        //Mining Tags
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SABlocks.VOIDSTONE_MITHRIL_ORE.get())
        ;

        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .add(SABlocks.VOIDSTONE_MITHRIL_ORE.get())
        ;

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .add(SABlocks.VOIDSTONE_MITHRIL_ORE.get())
        ;

        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .add(SABlocks.VOIDSTONE_MITHRIL_ORE.get())
        ;

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .add(SABlocks.VOIDSTONE_MITHRIL_ORE.get())
        ;

        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .add(SABlocks.VOIDSTONE_MITHRIL_ORE.get())
        ;

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(SABlocks.VOIDSTONE_MITHRIL_ORE.get())
        ;

    }
}