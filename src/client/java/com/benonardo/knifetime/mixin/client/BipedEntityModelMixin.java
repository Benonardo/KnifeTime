package com.benonardo.knifetime.mixin.client;

import com.benonardo.knifetime.KnifeTimeClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin<T extends LivingEntity> {

    @Shadow public BipedEntityModel.ArmPose rightArmPose;
    @Shadow @Final public ModelPart rightArm;

    @Inject(method = "positionRightArm", at = @At("TAIL"))
    private void checkForKnifeThrowing(T entity, CallbackInfo ci) {
        if (this.rightArmPose == KnifeTimeClient.THROWING_KNIFE_ARM_POSE) {
            //this.rightArm.pitch = (entity.getItemUseTime() * Math.min(entity.getItemUseTime() / 10f, 2.0F));
        }
    }

    @Inject(
            method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;positionRightArm(Lnet/minecraft/entity/LivingEntity;)V")
    )
    private void setAngles(T entity, float f, float g, float animationProgress, float i, float j, CallbackInfo ci) {
        if (this.rightArmPose == KnifeTimeClient.THROWING_KNIFE_ARM_POSE) {
            this.rightArm.pitch = animationProgress;
        }
    }

}
