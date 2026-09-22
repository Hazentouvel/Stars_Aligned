package com.hazen.stars_aligned.Entities.Spells.Cosmic.BouncingStar;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.client.model.entity.OrbModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ExtendedBouncyStarRenderer extends EntityRenderer<ExtendedBouncyStar> {
    private static final ResourceLocation SUN_TEXTURE = EternalStarlight.id("textures/entity/solar_creeper/sun.png");
    private final OrbModel<ExtendedBouncyStar> model;

    public ExtendedBouncyStarRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new OrbModel(context.bakeLayer(OrbModel.LAYER_LOCATION));
    }

    public void render(ExtendedBouncyStar entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        float bob = (float)entity.tickCount + partialTicks;
        poseStack.translate(0.0F, entity.getBbHeight() / 2.0F, 0.0F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.translate(0.0F, -1.5F, 0.0F);
        this.model.prepareMobModel(entity, 0.0F, 0.0F, partialTicks);
        this.model.setupAnim(entity, 0.0F, 0.0F, bob, 0.0F, 0.0F);
        this.model.renderToBuffer(poseStack, buffer.getBuffer(this.model.renderType(this.getTextureLocation(entity))), 15728880, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    public ResourceLocation getTextureLocation(ExtendedBouncyStar entity) {
        return SUN_TEXTURE;
    }
}
