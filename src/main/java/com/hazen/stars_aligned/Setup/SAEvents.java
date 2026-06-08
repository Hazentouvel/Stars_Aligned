package com.hazen.stars_aligned.Setup;

import com.hazen.stars_aligned.Items.Armor.FlowglazeMastery.FlowglazeMasteryArmorItem;
import com.hazen.stars_aligned.Registries.Effects.TemperedEffect;
import com.hazen.stars_aligned.SAUtils.SACompatAttribute;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.*;

@EventBusSubscriber(modid = StarsAligned.MOD_ID)
public class SAEvents {

	private static boolean isFlowglazeMastery(LivingEntity entity) {
		return entity.getItemBySlot(ArmorItem.Type.HELMET.getSlot()).getItem() instanceof FlowglazeMasteryArmorItem &&
				entity.getItemBySlot(ArmorItem.Type.CHESTPLATE.getSlot()).getItem() instanceof FlowglazeMasteryArmorItem &&
				entity.getItemBySlot(ArmorItem.Type.LEGGINGS.getSlot()).getItem() instanceof FlowglazeMasteryArmorItem &&
				entity.getItemBySlot(ArmorItem.Type.BOOTS.getSlot()).getItem() instanceof FlowglazeMasteryArmorItem;
	}

	@SubscribeEvent
	private static void onPostLivingHurt(LivingDamageEvent.Post event) {
		SAHandler.onPostLivingHurt(event.getEntity(), event.getSource(), event.getNewDamage());
	}

	@SubscribeEvent
	public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (!livingEntity.getCommandSenderWorld().isClientSide && isFlowglazeMastery(livingEntity)) {
			if (!event.isCanceled()) {
				TemperedEffect.addTemperedStack(livingEntity, event.getSource().getEntity());
			}
		}
	}


	@SubscribeEvent
	public static void giveItemAttributes(ItemAttributeModifierEvent event) {
		SACompatAttribute.addGlaciteAttributes(event);
		SACompatAttribute.addAethersentAttributes(event);
		SACompatAttribute.addDeepsilverAttributes(event);
		SACompatAttribute.addThermalAttributes(event);
		SACompatAttribute.addAlchemistAttributes(event);
		SACompatAttribute.addUnrealiumAttributes(event);
		SACompatAttribute.addStarlitDiamondAttributes(event);
		SACompatAttribute.addCandlashAttributes(event);
		SACompatAttribute.addColdsnapAttributes(event);
		SACompatAttribute.addTentacleSpikeAttributes(event);
	}
}