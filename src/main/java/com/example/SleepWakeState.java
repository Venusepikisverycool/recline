package com.example;

public class SleepWakeState {

    private static boolean blockingSleepWake = false;

    public static void setBlocking(boolean value) {
        blockingSleepWake = value;
    }

    public static boolean isBlocking() {
        return blockingSleepWake;
    }
}