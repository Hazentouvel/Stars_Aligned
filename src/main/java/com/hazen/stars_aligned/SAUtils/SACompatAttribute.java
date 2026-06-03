package com.hazen.stars_aligned.SAUtils;

import cn.leolezury.eternalstarlight.common.item.armor.*;
import cn.leolezury.eternalstarlight.common.item.combat.CandlashItem;
import cn.leolezury.eternalstarlight.common.item.combat.ColdsnapItem;
import cn.leolezury.eternalstarlight.common.item.combat.TentacleSpikeItem;
import cn.leolezury.eternalstarlight.neoforge.item.armor.NeoStarlitDiamondArmorItem;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;

public class SACompatAttribute {

    // Armors

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
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
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
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
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
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
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
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
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
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "max_mana_" + armor), 75, AttributeModifier.Operation.ADD_VALUE), slotGroup
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
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "max_mana_" + armor), 125, AttributeModifier.Operation.ADD_VALUE), slotGroup
        );
    }

    public static void addStarlitDiamondAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof NeoStarlitDiamondArmorItem armorItem)) {
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
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "max_mana_" + armor), 100, AttributeModifier.Operation.ADD_VALUE), slotGroup
        );
        event.addModifier(
                HLAttributeRegistry.COSMIC_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "cosmic_spell_power_" + armor), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), slotGroup
        );
    }

    //Weapons

    public static void addCandlashAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof CandlashItem)) {
            return;
        }
        event.addModifier(
                AttributeRegistry.FIRE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "fire_spell_power"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND
        );
        event.addModifier(
                AttributeRegistry.SUMMON_DAMAGE.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "summon_damage"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND
        );
    }

    public static void addColdsnapAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof ColdsnapItem)) {
            return;
        }
        event.addModifier(
                AttributeRegistry.ICE_SPELL_POWER.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "ice_spell_power"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND
        );
        event.addModifier(
                AttributeRegistry.SUMMON_DAMAGE.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "summon_damage"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND
        );
    }

    public static void addTentacleSpikeAttributes(ItemAttributeModifierEvent event) {
        if (!(event.getItemStack().getItem() instanceof TentacleSpikeItem)) {
            return;
        }
        event.addModifier(
                AttributeRegistry.SUMMON_DAMAGE.getDelegate(),
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "summon_damage"), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND
        );
    }

}