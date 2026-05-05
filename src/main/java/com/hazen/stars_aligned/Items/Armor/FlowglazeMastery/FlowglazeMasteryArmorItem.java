package com.hazen.stars_aligned.Items.Armor.FlowglazeMastery;

import com.hazen.stars_aligned.SAUtils.ImbuableSAArmorItem;
import com.hazen.stars_aligned.SAUtils.SAArmorMaterials;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import net.hazen.hazentouvelib.Registries.HLAttributeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;

public class FlowglazeMasteryArmorItem extends ImbuableSAArmorItem implements IDisableJacket, IDisableHat {
    public FlowglazeMasteryArmorItem(Type type, Properties settings) {
        super(SAArmorMaterials.FLOWGLAZE, type, settings, pureTierDual(
                AttributeRegistry.ICE_SPELL_POWER,
                HLAttributeRegistry.COSMIC_SPELL_POWER
        ));
    }

    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(CommonComponents.EMPTY);
        list.add(Component.translatable("item.modifiers.armor").withStyle(ChatFormatting.GRAY));
        list.add(Component.literal(" ").append(Component.translatable("tooltip.eternal_starlight.glacite_armor")).withStyle(ChatFormatting.AQUA));
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
    }

    // Just supply the armor model here; you don't have to worry about making a new renderer
    // ISS already has a custom renderer used for armor models
    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new FlowglazeMasteryArmorModel());
    }
}