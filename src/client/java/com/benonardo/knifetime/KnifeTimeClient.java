package com.benonardo.knifetime;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.BipedEntityModel;

public class KnifeTimeClient implements ClientModInitializer {
	public static final BipedEntityModel.ArmPose THROWING_KNIFE_ARM_POSE =
			ClassTinkerers.getEnum(BipedEntityModel.ArmPose.class, KnifeTimeEarlyRiser.THROWING_KNIFE);

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(KnifeTime.THROWING_KNIFE_ENTITY, ThrowingKnifeEntityRenderer::new);
	}
}