package com.hazen.stars_aligned.Datagen;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.hazen.stars_aligned.Registries.SAItemRegistry;
import com.hazen.stars_aligned.StarsAligned;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class SARecipeProvider extends RecipeProvider implements IConditionBuilder {
    public SARecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

    /*
    *** Armor Sets
     */

        //Glacite Battlemage
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemRegistry.ICE_RUNE.get()),
                        Ingredient.of(ESItems.GLACITE_HELMET.get()),
                        Ingredient.of(ItemRegistry.WIZARD_HAT.get()),
                        RecipeCategory.COMBAT,
                        SAItemRegistry.GLACITE_BATTLEMAGE_HELMET.get())
                .unlocks("has_ice_rune", has(ItemRegistry.ICE_RUNE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "smithing/armor/glacite_battlemage/glacite_battlemage_helmet"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemRegistry.ICE_RUNE.get()),
                        Ingredient.of(ESItems.GLACITE_CHESTPLATE.get()),
                        Ingredient.of(ItemRegistry.WIZARD_CHESTPLATE.get()),
                        RecipeCategory.COMBAT,
                        SAItemRegistry.GLACITE_BATTLEMAGE_CHESTPLATE.get())
                .unlocks("has_ice_rune", has(ItemRegistry.ICE_RUNE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "smithing/armor/glacite_battlemage/glacite_battlemage_chestplate"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemRegistry.ICE_RUNE.get()),
                        Ingredient.of(ESItems.GLACITE_LEGGINGS.get()),
                        Ingredient.of(ItemRegistry.WIZARD_LEGGINGS.get()),
                        RecipeCategory.COMBAT,
                        SAItemRegistry.GLACITE_BATTLEMAGE_LEGGINGS.get())
                .unlocks("has_ice_rune", has(ItemRegistry.ICE_RUNE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "smithing/armor/glacite_battlemage/glacite_battlemage_leggings"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemRegistry.ICE_RUNE.get()),
                        Ingredient.of(ESItems.GLACITE_BOOTS.get()),
                        Ingredient.of(ItemRegistry.WIZARD_BOOTS.get()),
                        RecipeCategory.COMBAT,
                        SAItemRegistry.GLACITE_BATTLEMAGE_BOOTS.get())
                .unlocks("has_ice_rune", has(ItemRegistry.ICE_RUNE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "smithing/armor/glacite_battlemage/glacite_battlemage_boots"));


        //Flowglaze Mastery
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ESItems.FLOWGLAZE_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(SAItemRegistry.GLACITE_BATTLEMAGE_HELMET.get()),
                        Ingredient.of(ESItems.FLOWGLAZE.get()),
                        RecipeCategory.COMBAT,
                        SAItemRegistry.FLOWGLAZE_MASTERY_HELMET.get())
                .unlocks("has_flowglaze_upgrade_smithing_template", has(ESItems.FLOWGLAZE_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "smithing/armor/flowglaze_mastery/flowglaze_mastery_helmet"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ESItems.FLOWGLAZE_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(SAItemRegistry.GLACITE_BATTLEMAGE_CHESTPLATE.get()),
                        Ingredient.of(ESItems.FLOWGLAZE.get()),
                        RecipeCategory.COMBAT,
                        SAItemRegistry.FLOWGLAZE_MASTERY_CHESTPLATE.get())
                .unlocks("has_flowglaze_upgrade_smithing_template", has(ESItems.FLOWGLAZE_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "smithing/armor/flowglaze_mastery/flowglaze_mastery_chestplate"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ESItems.FLOWGLAZE_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(SAItemRegistry.GLACITE_BATTLEMAGE_LEGGINGS.get()),
                        Ingredient.of(ESItems.FLOWGLAZE.get()),
                        RecipeCategory.COMBAT,
                        SAItemRegistry.FLOWGLAZE_MASTERY_LEGGINGS.get())
                .unlocks("has_flowglaze_upgrade_smithing_template", has(ESItems.FLOWGLAZE_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "smithing/armor/flowglaze_mastery/flowglaze_mastery_leggings"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ESItems.FLOWGLAZE_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(SAItemRegistry.GLACITE_BATTLEMAGE_BOOTS.get()),
                        Ingredient.of(ESItems.FLOWGLAZE.get()),
                        RecipeCategory.COMBAT,
                        SAItemRegistry.FLOWGLAZE_MASTERY_BOOTS.get())
                .unlocks("has_flowglaze_upgrade_smithing_template", has(ESItems.FLOWGLAZE_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(StarsAligned.MOD_ID, "smithing/armor/flowglaze_mastery/flowglaze_mastery_boots"));


    }
}