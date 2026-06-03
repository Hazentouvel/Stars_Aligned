package com.hazen.stars_aligned.Setup;

import cn.leolezury.eternalstarlight.common.registry.ESMobEffects;
import com.hazen.stars_aligned.Items.Armor.FlowglazeMastery.FlowglazeMasteryArmorItem;
import com.hazen.stars_aligned.SAUtils.SACompatAttribute;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
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

	@EventBusSubscriber
	public static class setBonuses {

		@SubscribeEvent
		public static void flowglazeMastery(LivingDamageEvent.Post event) {

			LivingEntity target = event.getEntity();
			Entity sourceEntity = event.getSource().getEntity();

			if (!(sourceEntity instanceof LivingEntity attacker)) return;
			if (!isFlowglazeMastery(attacker)) return;
			if (attacker.level().isClientSide) return;

			Item chest = attacker.getItemBySlot(EquipmentSlot.CHEST).getItem();

			if (attacker instanceof Player player) {
				if (player.getCooldowns().isOnCooldown(chest)) {
					return;
				}
				player.getCooldowns().addCooldown(chest, 300);
			}
			target.addEffect(new MobEffectInstance(ESMobEffects.BRITTLE.asHolder(), 100, 0, false, true, true));
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