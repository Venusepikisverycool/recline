package com.example.client.mixin;

import com.example.ReclineManager;
import com.example.SleepWakeState;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(
            method = "handleAnimate",
            at = @At("HEAD")
    )
    private void beforeAnimate(
            ClientboundAnimatePacket packet,
            CallbackInfo ci
    ) {

        LocalPlayer player = Minecraft.getInstance().player;

        if (player != null
                && player.level().isDay()
                && ReclineManager.isReclining(player)) {

            SleepWakeState.setBlocking(true);
        }
    }


    @Inject(
            method = "handleAnimate",
            at = @At("TAIL")
    )
    private void afterAnimate(
            ClientboundAnimatePacket packet,
            CallbackInfo ci
    ) {

        SleepWakeState.setBlocking(false);
    }
}