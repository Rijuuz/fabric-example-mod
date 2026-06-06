package com.backport.mod;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;

public class WindChargeItem extends Item {
    public WindChargeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        if (!level.isClientSide) {
            // Safe Snowball projectile method taaki sodium crash na ho
            Snowball windChargeEntity = new Snowball(level, player) {
                @Override
                protected void onHit(net.minecraft.phys.HitResult result) {
                    super.onHit(result);
                    // Hit hote hi wind explosion blast hoga
                    this.level().explode(this, this.getX(), this.getY(), this.getZ(), 2.0F, ExplosionInteraction.NONE);
                    this.discard();
                }
            };
            
            windChargeEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(windChargeEntity);
        }

        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
  }
