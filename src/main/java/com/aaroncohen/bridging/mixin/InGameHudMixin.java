package com.aaroncohen.bridging.mixin;

import com.aaroncohen.bridging.raytrace.ReacharoundTracker;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Shadow private int scaledHeight;
    @Shadow private int scaledWidth;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderCrosshair(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
    public void renderPlacementAssistText(MatrixStack matrices, float tickDelta, CallbackInfo ci) {

        if(ReacharoundTracker.currentTarget != null) {
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.ONE_MINUS_DST_COLOR, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);

            //determine what text to draw
            String text = ReacharoundTracker.isInVerticalOrientation() ? "- + -" : "| + |";

            //divide scaledWidth and scaledHeight by 2, and if there is a remainder, round up regardless of if it is easier to round down
            //these values are used to properly line up the text with the crosshair, using (scaledWidth / 2f) can sometimes lead to the icon being 1 pixel off
            float halfScreenWidth = (scaledWidth / 2f) - Math.floor(scaledWidth / 2f) > 0 ? (float) (Math.floor(scaledWidth / 2f) + 1) : (scaledWidth / 2f);
            float halfScreenHeight = (scaledHeight / 2f) - Math.floor(scaledHeight / 2f) > 0 ? (float) (Math.floor(scaledHeight / 2f) + 1) : (scaledHeight / 2f);

            //draw text
            matrices.push();
            {
                MinecraftClient.getInstance().textRenderer.draw(matrices, text, halfScreenWidth - (MinecraftClient.getInstance().textRenderer.getWidth(text)/ 2f), halfScreenHeight - 4f, 0xFFFFFF);
            }
            matrices.pop();
        }
    }

}