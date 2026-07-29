package com.example.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.DeltaTracker;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class ClientFadeMixin {

    @Inject(
            method = "renderSleepOverlay",
            at = @At("HEAD"),
            cancellable = true
    )
    private void removeSleepFade(
            GuiGraphics guiGraphics,
            DeltaTracker deltaTracker,
            CallbackInfo ci
    ) {

        LocalPlayer player = Minecraft.getInstance().player;

        if (player != null && player.isSleeping()) {
            ci.cancel();
        }
    }
}