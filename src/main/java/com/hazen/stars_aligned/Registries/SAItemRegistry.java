package com.hazen.stars_aligned.Registries;

import com.hazen.stars_aligned.Items.Armor.FlowglazeMastery.FlowglazeMasteryArmorItem;
import com.hazen.stars_aligned.Items.Armor.GlaciteBattlemage.GlaciteBattlemageArmorItem;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.hazentouvelib.Rarities.HLRarities;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class SAItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StarsAligned.MOD_ID);


    // Glacite Battlemage

    public static final DeferredHolder<Item, Item> GLACITE_BATTLEMAGE_HELMET = ITEMS.register("glacite_battlemage_helmet", () -> new GlaciteBattlemageArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HLRarities.ICE_RARITY.getValue())
            .durability(ArmorItem.Type.HELMET.getDurability(32))
    ));
    public static final DeferredHolder<Item, Item> GLACITE_BATTLEMAGE_CHESTPLATE = ITEMS.register("glacite_battlemage_chestplate", () -> new GlaciteBattlemageArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HLRarities.ICE_RARITY.getValue())
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(32))
    ));
    public static final DeferredHolder<Item, Item> GLACITE_BATTLEMAGE_LEGGINGS = ITEMS.register("glacite_battlemage_leggings", () -> new GlaciteBattlemageArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HLRarities.ICE_RARITY.getValue())
            .durability(ArmorItem.Type.LEGGINGS.getDurability(32))
    ));
    public static final DeferredHolder<Item, Item> GLACITE_BATTLEMAGE_BOOTS = ITEMS.register("glacite_battlemage_boots", () -> new GlaciteBattlemageArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HLRarities.ICE_RARITY.getValue())
            .durability(ArmorItem.Type.BOOTS.getDurability(32))

    ));


    // Flowglaze Mastery

    public static final DeferredHolder<Item, Item> FLOWGLAZE_MASTERY_HELMET = ITEMS.register("flowglaze_mastery_helmet", () -> new FlowglazeMasteryArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
            .equipment(1)
            .rarity(HLRarities.ICE_RARITY.getValue())
            .durability(ArmorItem.Type.HELMET.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> FLOWGLAZE_MASTERY_CHESTPLATE = ITEMS.register("flowglaze_mastery_chestplate", () -> new FlowglazeMasteryArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
            .equipment(1)
            .rarity(HLRarities.ICE_RARITY.getValue())
            .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> FLOWGLAZE_MASTERY_LEGGINGS = ITEMS.register("flowglaze_mastery_leggings", () -> new FlowglazeMasteryArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HLRarities.ICE_RARITY.getValue())
            .durability(ArmorItem.Type.LEGGINGS.getDurability(64))
    ));
    public static final DeferredHolder<Item, Item> FLOWGLAZE_MASTERY_BOOTS = ITEMS.register("flowglaze_mastery_boots", () -> new FlowglazeMasteryArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
            .equipment(1)
            .rarity(HLRarities.ICE_RARITY.getValue())
            .durability(ArmorItem.Type.BOOTS.getDurability(64))

    ));


    public static Collection<DeferredHolder<Item, ? extends Item>> getSAItems()
    {
        return ITEMS.getEntries();
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
