package com.hazen.stars_aligned.SAUtils;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import cn.leolezury.eternalstarlight.common.registry.ESSoundEvents;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class SAArmorMaterials {
    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, StarsAligned.MOD_ID);


    public static DeferredHolder<ArmorMaterial, ArmorMaterial> FLOWGLAZE = register("flowglaze",
            flowglazeArmorMap(),
            15,
            ESSoundEvents.ARMOR_EQUIP_GLACITE.asHolder(),
            () -> Ingredient.of(ESItems.FLOWGLAZE.get()),
            3,
            0.2F);

    public static DeferredHolder<ArmorMaterial, ArmorMaterial> GLACITE = register("glacite",
            flowglazeArmorMap(),
            15,
            ESSoundEvents.ARMOR_EQUIP_GLACITE.asHolder(),
            () -> Ingredient.of(ESItems.GLACITE.get()),
            2,
            0F);


    private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            Supplier<Ingredient> repairIngredient,
            float toughness,
            float knockbackResistance
    )
    {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(StarsAligned.id(name)));
        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(defense, enchantmentValue, equipSound, repairIngredient, list, toughness, knockbackResistance));
    }

    public static EnumMap<ArmorItem.Type, Integer> makeArmorMap(int helmet, int chestplate, int leggings, int boots)
    {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), (typeIntegerEnumMap) -> {
            typeIntegerEnumMap.put(ArmorItem.Type.HELMET, helmet);
            typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, chestplate);
            typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, leggings);
            typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, boots);
        });
    }

    public static EnumMap<ArmorItem.Type, Integer> flowglazeArmorMap(){
        return makeArmorMap(4, 7, 6, 4);
    }
    public static EnumMap<ArmorItem.Type, Integer> glaciteArmorMap(){
        return makeArmorMap(3, 6, 5, 3);
    }

    public static void register(IEventBus eventBus)
    {
        ARMOR_MATERIALS.register(eventBus);
    }
}