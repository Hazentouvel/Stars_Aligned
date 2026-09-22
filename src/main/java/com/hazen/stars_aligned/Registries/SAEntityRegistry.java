package com.hazen.stars_aligned.Registries;

import com.hazen.stars_aligned.Entities.Mobs.Summons.Freeze.SummonFreeze;
import com.hazen.stars_aligned.Entities.Mobs.Summons.Permafrost.SummonPermafrost;
import com.hazen.stars_aligned.Entities.Spells.Cosmic.BouncingStar.ExtendedBouncyStar;
import com.hazen.stars_aligned.Entities.Spells.Cosmic.ShiningStar.ExtendedShiningStar;
import com.hazen.stars_aligned.Entities.Spells.Nature.LunarMonstrosityBreath.ExtendedLunarMonstrosityBreathProjectile;
import com.hazen.stars_aligned.Entities.Spells.Nature.LunarThorn.ExtendedLunarThorn;
import com.hazen.stars_aligned.StarsAligned;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SAEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister
            .create(Registries.ENTITY_TYPE, StarsAligned.MOD_ID);


    /*
    *** Spells
     */

    // Lunar Monstrosity Breath Projectile
    public static final DeferredHolder<EntityType<?>, EntityType<ExtendedLunarMonstrosityBreathProjectile>> LUNAR_MONSTROSITY_BREATH_PROJECTILE =
            ENTITIES.register("lunar_monstrosity_breath_spell", () -> EntityType.Builder.<ExtendedLunarMonstrosityBreathProjectile>of(ExtendedLunarMonstrosityBreathProjectile::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "lunar_monstrosity_breath_spell").toString()));

    // Lunar Thorn Spell
    public static final DeferredHolder<EntityType<?>, EntityType<ExtendedLunarThorn>> LUNAR_THORN_SPELL =
            ENTITIES.register("lunar_thorn_spell", () -> EntityType.Builder.<ExtendedLunarThorn>of(ExtendedLunarThorn::new, MobCategory.MISC)
                    .sized(0.3f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "lunar_thorn_spell").toString())
            );

    // Shining Star Spell
    public static final DeferredHolder<EntityType<?>, EntityType<ExtendedShiningStar>> SHINING_STAR_SPELL =
            ENTITIES.register("shining_star_spell", () -> EntityType.Builder.<ExtendedShiningStar>of(ExtendedShiningStar::new, MobCategory.MISC)
                    .sized(0.3f, 0.3f)
                    .clientTrackingRange(10)
                    .updateInterval(1)
                    .build(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "shining_star_spell").toString())
            );

    // Bouncy Star Spell
    public static final DeferredHolder<EntityType<?>, EntityType<ExtendedBouncyStar>> BOUNCY_STAR_SPELL =
            ENTITIES.register("bouncy_star_spell", () -> EntityType.Builder.<ExtendedBouncyStar>of(ExtendedBouncyStar::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(10)
                    .updateInterval(1)
                    .build(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "bouncy_star_spell").toString())
            );

    /*
    *** Summons
     */

    // Summon Permafrost
    public static final DeferredHolder<EntityType<?>, EntityType<SummonPermafrost>> SUMMON_PERMAFROST =
            ENTITIES.register("summon_permafrost", () -> EntityType.Builder.<SummonPermafrost>of(SummonPermafrost::new, MobCategory.MISC)
                    .sized(0.75f, 2.25f)
                    .clientTrackingRange(32)
                    .fireImmune()
                    .build(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "summon_permafrost").toString())
            );

    // Summon Freeze
    public static final DeferredHolder<EntityType<?>, EntityType<SummonFreeze>> SUMMON_FREEZE =
            ENTITIES.register("summon_freeze", () -> EntityType.Builder.<SummonFreeze>of(SummonFreeze::new, MobCategory.MISC)
                    .sized(0.5f, 1.25f)
                    .fireImmune()
                    .build(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "summon_freeze").toString())
            );


    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}