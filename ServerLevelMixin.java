package com.example.mixin;

import com.example.ReclineManager;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {

    @Inject(
            method = "wakeUpAllPlayers",
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventReclineWake(CallbackInfo ci) {

        ServerLevel level = (ServerLevel)(Object)this;

        for (ServerPlayer player : level.players()) {

            if (ReclineManager.isReclining(player)) {

                ci.cancel();
                return;
            }
        }
    }
}