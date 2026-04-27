package com.hazen.stars_aligned.Registries;

import com.hazen.stars_aligned.Blocks.SABlocks;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SACreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StarsAligned.MOD_ID);


    public static final Supplier<CreativeModeTab> STARS_ALIGN_BLOCKS = CREATIVE_MODE_TAB.register("stars_aligned_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SABlocks.VOIDSTONE_MITHRIL_ORE.get()))
                    .title(Component.translatable("creativetab.stars_aligned.stars_aligned_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {

                        //Compact Blocks
                        output.accept(SABlocks.VOIDSTONE_MITHRIL_ORE);


                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
