package com.hazen.stars_aligned.Items.Armor.GlaciteBattlemage;

import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GlaciteBattlemageArmorModel extends DefaultedEntityGeoModel<GlaciteBattlemageArmorItem> {
    public GlaciteBattlemageArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(GlaciteBattlemageArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "geo/armor/glacite_battlemage_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GlaciteBattlemageArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "textures/armor/glacite_battlemage_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GlaciteBattlemageArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}
