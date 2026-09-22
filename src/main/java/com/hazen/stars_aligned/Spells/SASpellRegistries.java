package com.hazen.stars_aligned.Spells;

import com.hazen.stars_aligned.Spells.Schools.Ice.*;
import com.hazen.stars_aligned.Spells.Schools.Nature.*;
import com.hazen.stars_aligned.Spells.Schools.Cosmic.*;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY;

public class SASpellRegistries {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(SPELL_REGISTRY_KEY, StarsAligned.MOD_ID);

    public static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    /*
    *** Ice
     */

    // *** Starlight Golem *********************************************************************************************

    // Summon Permafrost
    public static final Supplier<AbstractSpell> SUMMON_PERMAFROST = registerSpell(new SummonPermafrostSpell());

    // Summon Freeze
    public static final Supplier<AbstractSpell> SUMMON_FREEZE = registerSpell(new SummonFreezeSpell());


    /*
    *** Nature
     */

    // *** Lunar Monstrosity *******************************************************************************************

    // Moonring Vines
    //public static final Supplier<AbstractSpell> MOONRING_VINES = registerSpell(new MoonringVinesSpell());

    // Lunar Monstrosity Breath
    public static final Supplier<AbstractSpell> LUNAR_MONSTROSITY_BREATH = registerSpell(new LunarMonstrosityBreathSpell());

    // Lunar Thorns
    public static final Supplier<AbstractSpell> LUNAR_THORNS = registerSpell(new LunarThornSpell());

    // Conjure Tangled Skulls
    //public static final Supplier<AbstractSpell> CONJURE_TANGLED_SKULLS = registerSpell(new ConjustTangledSkullsSpell());


    /*
     *** Evocation
     */

    // Goop
    //public static final Supplier<AbstractSpell> GOOP = registerSpell(new GoopSpell());


    /*
    *** Cosmic
     */

    // *** That one creeper boss spells: *******************************************************************************

    // Bouncing Stars
    public static final Supplier<AbstractSpell> BOUNCING_STARS = registerSpell(new BouncingStarsSpell());

    // Shining Star
    public static final Supplier<AbstractSpell> SHINING_STAR = registerSpell(new ShiningStarSpell());

    // Starlight Barrage
    //public static final Supplier<AbstractSpell> STARLIGHT_BARRAGE = registerSpell(new StarlightBarrageSpell());

    // Orbital Planet
    //public static final Supplier<AbstractSpell> ORBITAL_PLANET = registerSpell(new OrbitalPlanetSpell());

    // Solar Strike
    //public static final Supplier<AbstractSpell> SOLAR_STRIKE = registerSpell(new SolarStrikeSpell());



    public static void register(IEventBus eventBus)
    {
        SPELLS.register(eventBus);
    }

}