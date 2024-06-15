package com.benonardo.knifetime;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class ThrowingKnifeItem extends Item implements ProjectileItem {
    public ThrowingKnifeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    @Override
    public void usageTick(World world, LivingEntity boringUser, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks == 1) {
            if (world.isClient() || !(boringUser instanceof PlayerEntity user)) return;

            System.out.println(remainingUseTicks);
            var entity = new ThrowingKnifeEntity(world, user, stack.copyWithCount(1));
            entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.0F, 0.0F);
            world.spawnEntity(entity);

            user.getItemCooldownManager().set(KnifeTime.THROWING_KNIFE_ITEM, 20);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            stack.decrementUnlessCreative(1, user);
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 10;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new ThrowingKnifeEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return KnifeTime.THROWING_KNIFE_USE_ACTION;
    }
}
