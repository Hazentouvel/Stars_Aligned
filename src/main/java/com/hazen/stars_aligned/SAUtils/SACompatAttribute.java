package com.hazen.stars_aligned.SAUtils;

import cn.leolezury.eternalstarlight.common.item.armor.*;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
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

        String armor = switch (slotGroup) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "finish";
        };
        if (armor.equals("finish")) {
            return;
        }

        event.addModifier(
                AttributeRegistry.MAX_MANA.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "glacite_max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
        );
    }

    public static void addAethersentAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof AethersentArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        String armor = switch (slotGroup) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "finish";
        };
        if (armor.equals("finish")) {
            return;
        }

        event.addModifier(
                AttributeRegistry.MAX_MANA.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "aethersent_max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
        );
    }

    public static void addThermalAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof ThermalSpringstoneArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        String armor = switch (slotGroup) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "finish";
        };
        if (armor.equals("finish")) {
            return;
        }

        event.addModifier(
                AttributeRegistry.MAX_MANA.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "thermal_max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
        );
    }

    public static void addDeepsilverAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof DeepsilverArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        String armor = switch (slotGroup) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "finish";
        };
        if (armor.equals("finish")) {
            return;
        }

        event.addModifier(
                AttributeRegistry.MAX_MANA.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "deepsilver_max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
        );
    }

    public static void addAlchemistAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof AlchemistArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        String armor = switch (slotGroup) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "finish";
        };
        if (armor.equals("finish")) {
            return;
        }

        event.addModifier(
                AttributeRegistry.MAX_MANA.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "alchemist_max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
        );
    }

    public static void addUnrealiumAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof UnrealiumArmorItem armorItem)) {
            return;
        }

        EquipmentSlot slot = armorItem.getEquipmentSlot();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(slot);

        String armor = switch (slotGroup) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "finish";
        };
        if (armor.equals("finish")) {
            return;
        }

        event.addModifier(
                AttributeRegistry.MAX_MANA.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "unrealium_max_mana_" + armor), 125, AttributeModifier.Operation.ADD_VALUE), slotGroup
        );
    }

}