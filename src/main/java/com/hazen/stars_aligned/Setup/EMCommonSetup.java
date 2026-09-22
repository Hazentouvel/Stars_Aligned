package com.hazen.stars_aligned.Setup;

import cn.leolezury.eternalstarlight.common.entity.living.boss.golem.Permafrost;
import cn.leolezury.eternalstarlight.common.entity.living.monster.Freeze;
import com.hazen.stars_aligned.Registries.SAEntityRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber
public class EMCommonSetup {

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(SAEntityRegistry.SUMMON_PERMAFROST.get(), Permafrost.createAttributes().build());
        event.put(SAEntityRegistry.SUMMON_FREEZE.get(), Freeze.createAttributes().build());
    }
}