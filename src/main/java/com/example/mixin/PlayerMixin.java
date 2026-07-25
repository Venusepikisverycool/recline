package com.example.mixin;

import com.example.ReclineManager;

import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;stopSleepInBed(ZZ)V"
            ),
            cancellable = true
    )
    private void preventDayWake(CallbackInfo ci) {

        Player player = (Player)(Object)this;

        if (ReclineManager.isReclining(player)
                && player.level().isDay()) {


            ci.cancel();
        }
    }
}