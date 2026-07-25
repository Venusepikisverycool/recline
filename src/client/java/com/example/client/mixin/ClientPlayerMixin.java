package com.example.client.mixin;

import com.example.ReclineManager;

import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class ClientPlayerMixin {

    @Inject(
            method = "stopSleepInBed",
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventAutomaticWake(
            boolean skipSleepTimer,
            boolean updateSleepCounter,
            CallbackInfo ci
    ) {

        Player player = (Player)(Object)this;

        if (ReclineManager.isReclining(player)
                && player.level().isDay()
                && !updateSleepCounter) {


            ci.cancel();
        }
    }
}