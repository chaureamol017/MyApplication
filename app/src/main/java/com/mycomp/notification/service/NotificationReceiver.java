package com.mycomp.notification.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.mycomp.notification.helper.NotificationHelper;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("toastMessage");
        Log.e("NotificationReceiver", "message: " + message);
        NotificationHelper notificationHelper =  new NotificationHelper(context);
        notificationHelper.toastShort(message);
    }
}
