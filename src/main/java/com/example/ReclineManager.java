package com.example;

import net.minecraft.world.entity.player.Player;

import java.util.HashSet;
import java.util.UUID;

public class ReclineManager {

    private static final HashSet<UUID> recliningPlayers = new HashSet<>();

    public static void startRecline(Player player) {
        recliningPlayers.add(player.getUUID());
    }

    public static void stopRecline(Player player) {
        recliningPlayers.remove(player.getUUID());
    }

    public static boolean isReclining(Player player) {
        return recliningPlayers.contains(player.getUUID());
    }
}