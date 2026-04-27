package com.hazen.stars_aligned.Blocks;

import com.hazen.stars_aligned.Registries.SAItemRegistry;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SABlocks {
        public static final DeferredRegister.Blocks BLOCKS =
                DeferredRegister.createBlocks(StarsAligned.MOD_ID);




        //Mithril Ore
        public static final DeferredBlock<Block> VOIDSTONE_MITHRIL_ORE = registerBlock("voidstone_mithril_ore",
                () -> new Block(BlockBehaviour.Properties.of().lightLevel(state -> 9)
                        .mapColor(MapColor.COLOR_BLACK)
                        .requiresCorrectToolForDrops()
                        .strength(20.0F, 1200.0F)
                        .sound(SoundType.ANCIENT_DEBRIS)
                ));

        private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
                DeferredBlock<T> toReturn = BLOCKS.register(name, block);
                registerBlockItem(name, toReturn);
                return toReturn;
        }

        private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
                SAItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}