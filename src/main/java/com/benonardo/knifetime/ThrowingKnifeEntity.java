package com.benonardo.knifetime;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
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
    protected void onBlockHit(BlockHitResult blockHitResult) {
        var blockState = this.getWorld().getBlockState(blockHitResult.getBlockPos());
        if (blockState.isIn(ConventionalBlockTags.GLASS_BLOCKS) || blockState.isIn(ConventionalBlockTags.GLASS_PANES)) {
            this.getWorld().breakBlock(blockHitResult.getBlockPos(), false);
            this.setVelocity(this.getVelocity().multiply(0.8));
            return;
        }
        super.onBlockHit(blockHitResult);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return KnifeTime.THROWING_KNIFE_ITEM.getDefaultStack();
    }
}
