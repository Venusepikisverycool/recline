package com.example.mixin;

import com.example.ReclineManager;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(
            method = "stopSleeping",
            at = @At("HEAD")
    )
    private void debugStopSleeping(CallbackInfo ci) {

        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity instanceof Player player
                && ReclineManager.isReclining(player)) {


        }
    }
}