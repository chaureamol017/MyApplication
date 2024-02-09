package com.mycomp.notification.helper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import com.mycomp.notification.util.ServiceUtil;

public class ServiceHelper {

    public static boolean startService(Context context, Class<? extends Service> serviceClass) {
        try {
            Intent service = new Intent(context, serviceClass);
            context.startService(service);

            return true;
        } catch (final Exception ex) {
            return false;
        }
    }

    public static boolean startForegroundService(Context context, Class<? extends Service> serviceClass) {
        try {
            Intent service = new Intent(context, serviceClass);
            context.startService(service);

            return true;
        } catch (final Exception ex) {
            return false;
        }
    }


    public static boolean checkAndStartForegroundService(Context context, Class<? extends Service> serviceClass) {
        if (!ServiceUtil.isForegroundServiceRunning(context, serviceClass)) {
            return startForegroundService(context, serviceClass);
        }
        return true;
    }
}
