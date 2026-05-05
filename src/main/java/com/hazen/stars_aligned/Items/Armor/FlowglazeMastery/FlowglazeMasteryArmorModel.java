package com.hazen.stars_aligned.Items.Armor.FlowglazeMastery;

import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class FlowglazeMasteryArmorModel extends DefaultedEntityGeoModel<FlowglazeMasteryArmorItem> {
    public FlowglazeMasteryArmorModel() {
        super(ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(FlowglazeMasteryArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "geo/armor/flowglaze_mastery_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlowglazeMasteryArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "textures/armor/flowglaze_mastery_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlowglazeMasteryArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "animations/wizard_armor_animation.json");
    }
}
