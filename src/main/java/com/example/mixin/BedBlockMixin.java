package com.example.mixin;

import com.example.ReclineManager;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.BlockHitResult;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public class BedBlockMixin {

    @Inject(method = "useWithoutItem", at = @At("HEAD"), cancellable = true)
    private void recline(
            BlockState blockState,
            Level level,
            BlockPos blockPos,
            Player player,
            BlockHitResult blockHitResult,
            CallbackInfoReturnable<InteractionResult> cir
    ) {

        if (!level.isClientSide) {

            if (blockState.getValue(BedBlock.PART) != BedPart.HEAD) {
                blockPos = blockPos.relative(blockState.getValue(BedBlock.FACING));
            }

            player.startSleeping(blockPos);



            ReclineManager.startRecline(player);



            if (player.level() instanceof ServerLevel serverLevel) {
                serverLevel.updateSleepingPlayerList();
            }

            cir.setReturnValue(InteractionResult.SUCCESS);
            return;
        }
    }
}