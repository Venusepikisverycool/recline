package com.example;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SleepWakeState {

    private static final Set<UUID> blockingPlayers = new HashSet<>();

    public static void setBlocking(UUID playerUUID, boolean value) {
        if (value) {
            blockingPlayers.add(playerUUID);
        } else {
            blockingPlayers.remove(playerUUID);
        }
    }

    public static boolean isBlocking(UUID playerUUID) {
        return blockingPlayers.contains(playerUUID);
    }

    public static void clear() {
        blockingPlayers.clear();
    }
}