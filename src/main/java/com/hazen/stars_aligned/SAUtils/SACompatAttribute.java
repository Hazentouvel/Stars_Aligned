package com.hazen.stars_aligned.SAUtils;

import cn.leolezury.eternalstarlight.common.item.armor.*;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;

public class SACompatAttribute {

    public static void addGlaciteAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof GlaciteArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.ICE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "glacite_ice_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );

        event.addModifier(
                HLAttributeRegistry.COSMIC_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "glacite_cosmic_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addAethersentAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof AethersentArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.ENDER_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "aethersent_ender_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );

        event.addModifier(
                HLAttributeRegistry.COSMIC_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "aethersent_cosmic_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addThermalAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof ThermalSpringstoneArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.FIRE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "thermal_fire_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );

        event.addModifier(
                HLAttributeRegistry.COSMIC_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "thermal_cosmic_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addDeepsilverAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof DeepsilverArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.SPELL_RESIST.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "deepsilver_spell_resist"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    public static void addUnrealiumAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof UnrealiumArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        event.addModifier(
                AttributeRegistry.ELDRITCH_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "unrealium_eldritch_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );

        event.addModifier(
                HLAttributeRegistry.COSMIC_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "unrealium_cosmic_spell_power"),
                        0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

}