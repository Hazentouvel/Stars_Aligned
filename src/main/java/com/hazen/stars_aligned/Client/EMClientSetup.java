package com.hazen.stars_aligned.Client;

import cn.leolezury.eternalstarlight.common.client.renderer.entity.FreezeRenderer;
import cn.leolezury.eternalstarlight.common.client.renderer.entity.LunarThornRenderer;
import cn.leolezury.eternalstarlight.common.client.renderer.entity.PermafrostRenderer;
import com.hazen.stars_aligned.Entities.Spells.Cosmic.BouncingStar.ExtendedBouncyStarRenderer;
import com.hazen.stars_aligned.Entities.Spells.Cosmic.ShiningStar.ExtendedShiningStarRenderer;
import com.hazen.stars_aligned.Registries.SAEntityRegistry;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = StarsAligned.MOD_ID)
public class EMClientSetup {

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {

        /*
        *** Spells
         */

        event.registerEntityRenderer(SAEntityRegistry.LUNAR_MONSTROSITY_BREATH_PROJECTILE.get(), NoopRenderer::new);
        event.registerEntityRenderer(SAEntityRegistry.LUNAR_THORN_SPELL.get(), LunarThornRenderer::new);
        event.registerEntityRenderer(SAEntityRegistry.SHINING_STAR_SPELL.get(), ExtendedShiningStarRenderer::new);
        event.registerEntityRenderer(SAEntityRegistry.BOUNCY_STAR_SPELL.get(), ExtendedBouncyStarRenderer::new);

        /*
        *** Summons
         */
        event.registerEntityRenderer(SAEntityRegistry.SUMMON_PERMAFROST.get(), PermafrostRenderer::new);
        event.registerEntityRenderer(SAEntityRegistry.SUMMON_FREEZE.get(), FreezeRenderer::new);

    }
}