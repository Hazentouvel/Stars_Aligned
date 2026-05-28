package com.hazen.stars_aligned.SAUtils;

import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SATags {

    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("stars_aligned", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ARMORS = createTag("armors");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("stars_aligned", name));
        }
    }

}
