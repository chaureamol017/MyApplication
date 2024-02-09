package com.mycomp.notification.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

public class ServiceUtil {

    public static boolean isForegroundServiceRunning(Context context, Class<? extends Service> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service: manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }

        return false;
    }
}
