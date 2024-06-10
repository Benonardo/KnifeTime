package com.benonardo.knifetime;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ThrowingKnifeEntity extends PersistentProjectileEntity {

    public ThrowingKnifeEntity(EntityType<? extends ThrowingKnifeEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrowingKnifeEntity(World world, LivingEntity owner, ItemStack stack) {
        super(KnifeTime.THROWING_KNIFE_ENTITY, owner, world, stack, null);
    }

    public ThrowingKnifeEntity(World world, double x, double y, double z, ItemStack stack) {
        super(KnifeTime.THROWING_KNIFE_ENTITY, x, y, z, world, stack, stack);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return KnifeTime.THROWING_KNIFE_ITEM.getDefaultStack();
    }
}
