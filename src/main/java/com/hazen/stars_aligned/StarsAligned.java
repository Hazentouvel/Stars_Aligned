package com.hazen.stars_aligned;

import com.hazen.stars_aligned.Blocks.SABlocks;
import com.hazen.stars_aligned.Registries.SACreativeModeTabs;
import com.hazen.stars_aligned.Registries.SAEffectRegistry;
import com.hazen.stars_aligned.Registries.SAItemRegistry;
import com.hazen.stars_aligned.SAUtils.SAArmorMaterials;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(StarsAligned.MOD_ID)
public class StarsAligned {
    public static final String MOD_ID = "stars_aligned";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public StarsAligned(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        SACreativeModeTabs.register(modEventBus);
        SABlocks.register(modEventBus);
        SAItemRegistry.register(modEventBus);
        SAArmorMaterials.register(modEventBus);
        SAEffectRegistry.register(modEventBus);


        NeoForge.EVENT_BUS.register(this);


        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        
    }

    @EventBusSubscriber(value = Dist.CLIENT)
    static class ClientModEvents {
        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
            
        }
        
    }

    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, path);
    }
}
