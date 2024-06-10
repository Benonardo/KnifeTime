package com.benonardo.knifetime;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;

public class KnifeTime implements ModInitializer {
	public static final Item THROWING_KNIFE_ITEM = new ThrowingKnifeItem(new Item.Settings().maxCount(16));
	public static final EntityType<ThrowingKnifeEntity> THROWING_KNIFE_ENTITY = EntityType.Builder
			.<ThrowingKnifeEntity>create(ThrowingKnifeEntity::new, SpawnGroup.MISC)
			.dimensions(0.2f, 0.2f)
			.eyeHeight(0.13f)
			.maxTrackingRange(4)
			.trackingTickInterval(20)
			.build();
	public static final UseAction THROWING_KNIFE_USE_ACTION =
			ClassTinkerers.getEnum(UseAction.class, KnifeTimeEarlyRiser.THROWING_KNIFE);

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, KnifeTime.id("throwing_knife"), THROWING_KNIFE_ITEM);
		Registry.register(Registries.ENTITY_TYPE, KnifeTime.id("throwing_knife"), THROWING_KNIFE_ENTITY);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(THROWING_KNIFE_ITEM));

		DispenserBlock.registerProjectileBehavior(THROWING_KNIFE_ITEM);
	}

	public static Identifier id(String path) {
		return Identifier.of("knifetime", path);
	}

}