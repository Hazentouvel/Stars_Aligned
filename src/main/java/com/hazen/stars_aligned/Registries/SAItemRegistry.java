package com.hazen.stars_aligned.Registries;

import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class SAItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StarsAligned.MOD_ID);


    public static Collection<DeferredHolder<Item, ? extends Item>> getSAItems()
    {
        return ITEMS.getEntries();
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
