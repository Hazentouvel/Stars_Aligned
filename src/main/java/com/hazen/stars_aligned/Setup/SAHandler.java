package com.hazen.stars_aligned.Setup;

import com.hazen.stars_aligned.Items.Armor.FlowglazeMastery.FlowglazeMasteryArmorItem;
import com.hazen.stars_aligned.Items.Armor.GlaciteBattlemage.GlaciteBattlemageArmorItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class SAHandler {



    public static void onPostLivingHurt(LivingEntity entity, DamageSource source, float amount) {


        if (entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof GlaciteBattlemageArmorItem
                || entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof GlaciteBattlemageArmorItem
                || entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof GlaciteBattlemageArmorItem
                || entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof GlaciteBattlemageArmorItem
        ) {
            if (source.getDirectEntity() instanceof LivingEntity livingEntity) {
                livingEntity.setTicksFrozen(Math.min(livingEntity.getTicksFrozen() + 80, 300));
            }
        }


    }

}
