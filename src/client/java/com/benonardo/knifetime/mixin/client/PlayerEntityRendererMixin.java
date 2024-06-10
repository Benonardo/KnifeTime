package com.benonardo.knifetime.mixin.client;

import com.benonardo.knifetime.KnifeTime;
import com.benonardo.knifetime.KnifeTimeClient;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Cancellable;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @ModifyExpressionValue(
            method = "getArmPose",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/util/UseAction;"
            )
    )
    private static UseAction changeArmPoseToThrowingKnife(UseAction original,
                                                          @Cancellable
                                                          CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        if (original == KnifeTime.THROWING_KNIFE_USE_ACTION) {
            cir.setReturnValue(KnifeTimeClient.THROWING_KNIFE_ARM_POSE);
        }
        return original;
    }

}
