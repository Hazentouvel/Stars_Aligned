package com.hazen.stars_aligned.Entities.Spells.Cosmic.ShiningStar;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.entity.projectile.ShiningStar;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.FastColor.ARGB32;

public class ExtendedShiningStarRenderer extends EntityRenderer<ExtendedShiningStar> {
    private static final ResourceLocation TEXTURE_LOCATION = EternalStarlight.id("textures/entity/solar_creeper/star.png");
    private static final RenderType RENDER_TYPE;

    public ExtendedShiningStarRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(ExtendedShiningStar entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, entity.getBbHeight() / 2.0F, 0.0F);
        float age = (float)entity.tickCount + partialTicks;
        float factor = Mth.sin(age * 0.1F + (float)entity.getId()) * 0.5F + 0.5F;
        int r = Mth.lerpInt(factor, 135, 255);
        int g = Mth.lerpInt(factor, 206, 215);
        int b = Mth.lerpInt(factor, 250, 0);
        renderStar(poseStack, buffer, age, entity.getId(), 1.0F, ARGB32.color(255, r, g, b));
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    public static void renderStar(PoseStack poseStack, MultiBufferSource buffer, float age, int seed, float scale, int color) {
        poseStack.pushPose();
        poseStack.mulPose(Minecraft.getInstance().gameRenderer.getMainCamera().rotation());
        VertexConsumer consumer = buffer.getBuffer(RENDER_TYPE);
        poseStack.translate(0.0F, 0.0F, 0.001F);
        renderDiamond(consumer, poseStack.last(), 1.8F * scale, 0.1F * scale, color);
        poseStack.translate(0.0F, 0.0F, 0.001F);
        renderDiamond(consumer, poseStack.last(), 0.1F * scale, 0.9F * scale, color);
        float pulseZRot = age * (seed % 2 == 0 ? 1.5F : -1.5F) + (float)(seed * 2);
        poseStack.mulPose(Axis.ZP.rotationDegrees(pulseZRot));
        float pulseScale = 1.2F + 0.3F * Mth.sin(age * 2.1F);
        poseStack.scale(pulseScale, pulseScale, 1.0F);
        poseStack.translate(0.0F, 0.0F, 0.001F);
        renderDiamond(consumer, poseStack.last(), 0.9F * scale, 0.05F * scale, color);
        poseStack.translate(0.0F, 0.0F, 0.001F);
        renderDiamond(consumer, poseStack.last(), 0.05F * scale, 0.45F * scale, color);
        poseStack.popPose();
    }

    private static void renderDiamond(VertexConsumer consumer, PoseStack.Pose pose, float xSize, float ySize, int color) {
        consumer.addVertex(pose, xSize, 0.0F, 0.0F).setColor(color).setUv(0.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0.0F, 1.0F, 0.0F);
        consumer.addVertex(pose, 0.0F, ySize, 0.0F).setColor(color).setUv(1.0F, 0.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0.0F, 1.0F, 0.0F);
        consumer.addVertex(pose, -xSize, 0.0F, 0.0F).setColor(color).setUv(1.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0.0F, 1.0F, 0.0F);
        consumer.addVertex(pose, 0.0F, -ySize, 0.0F).setColor(color).setUv(0.0F, 1.0F).setOverlay(OverlayTexture.NO_OVERLAY).setLight(15728880).setNormal(0.0F, 1.0F, 0.0F);
    }

    public ResourceLocation getTextureLocation(ExtendedShiningStar entity) {
        return TEXTURE_LOCATION;
    }

    static {
        RENDER_TYPE = RenderType.entityTranslucent(TEXTURE_LOCATION);
    }
}
