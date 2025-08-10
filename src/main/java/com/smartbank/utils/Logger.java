package com.smartbank.utils;

import java.time.LocalDateTime;

public interface Logger {
    default void logInfo(String message) {
        System.out.println("[INFO] " + LocalDateTime.now() + ": " + message);
    }

    static String format(String message) {
        return "[FORMATTED LOG] " + message.toUpperCase();
    }
}