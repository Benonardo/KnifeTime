package com.benonardo.knifetime.mixin.client;

import com.benonardo.knifetime.KnifeTimeClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
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
            this.rightArm.pitch = -entity.getItemUseTime() * 3 % 360;
        }
    }

}
