package xkzuto.prehurtcam.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow @Final Minecraft minecraft;

    /**
     * Replaces the modern directional damage tilt with the classic pre-1.19.4
     * simple left/right roll behavior. Respects the damage tilt accessibility slider.
     *
     * Pre-1.19.4 behavior: Simple z-axis roll (left/right tilt only)
     * Post-1.19.4 behavior: Directional tilt based on damage source (REPLACED BY THIS MOD)
     */
    @Inject(
        method = "bobHurt",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onBobHurt(PoseStack matrices, float tickDelta, CallbackInfo ci) {
        // Always cancel modern behavior and apply classic pre-1.19.4 tilt
        ci.cancel();

        if (this.minecraft.getCameraEntity() instanceof LivingEntity livingEntity) {
            float hurtTime = (float)livingEntity.hurtTime - tickDelta;

            // Get damage tilt strength from accessibility settings
            float damageTiltStrength = this.minecraft.options.damageTiltStrength().get().floatValue();

            if (livingEntity.isDeadOrDying()) {
                // Death tilt with accessibility slider applied
                float deathTime = Math.min((float)livingEntity.deathTime + tickDelta, 20.0F);
                float deathTilt = (40.0F - 8000.0F / (deathTime + 200.0F)) * damageTiltStrength;
                matrices.mulPose(Axis.ZP.rotationDegrees(deathTilt));
            } else if (hurtTime >= 0.0F) {
                // Classic pre-1.19.4: Simple left/right roll with accessibility slider
                hurtTime /= (float)livingEntity.hurtDuration;
                hurtTime = Mth.sin(hurtTime * hurtTime * hurtTime * hurtTime * (float)Math.PI);
                float rollAngle = livingEntity.getHurtDir();
                matrices.mulPose(Axis.YP.rotationDegrees(-rollAngle));
                float tiltAmount = (float)((double)(-hurtTime) * 14.0) * damageTiltStrength;
                matrices.mulPose(Axis.ZP.rotationDegrees(tiltAmount));
                matrices.mulPose(Axis.YP.rotationDegrees(rollAngle));
            }
        }
    }
}
