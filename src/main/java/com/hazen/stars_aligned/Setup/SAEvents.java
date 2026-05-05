package com.hazen.stars_aligned.Setup;

import com.hazen.stars_aligned.SAUtils.SACompatAttribute;
import com.hazen.stars_aligned.StarsAligned;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.*;
@EventBusSubscriber(modid = StarsAligned.MOD_ID)
public class SAEvents {

	@SubscribeEvent
	private static void onPostLivingHurt(LivingDamageEvent.Post event) {
		SAHandler.onPostLivingHurt(event.getEntity(), event.getSource(), event.getNewDamage());
	}

	@SubscribeEvent
	public static void giveItemAttributes(ItemAttributeModifierEvent event) {
		SACompatAttribute.addGlaciteAttributes(event);
		SACompatAttribute.addAethersentAttributes(event);
		SACompatAttribute.addDeepsilverAttributes(event);
		SACompatAttribute.addThermalAttributes(event);
		SACompatAttribute.addUnrealiumAttributes(event);
	}
}