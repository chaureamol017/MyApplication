package com.mycomp.notification.util;

public class ThreadUtil {

    public static void sleep(final int interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
