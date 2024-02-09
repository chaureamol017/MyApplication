package com.mycomp.notification;

import static com.mycomp.notification.constants.NotificationConstants.IHC_GENERAL;
import static com.mycomp.notification.constants.NotificationConstants.ILC_UPDATES;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.util.Log;

import com.mycomp.notification.helper.ServiceHelper;

public class App extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        createNotificationChannels();
//        ServiceHelper.checkAndStartForegroundService(this, NotificationListener.class);
    }

    private void createNotificationChannels() {

        NotificationChannel channel1 = new NotificationChannel(IHC_GENERAL, IHC_GENERAL, NotificationManager.IMPORTANCE_HIGH);
        NotificationChannel channel2 = new NotificationChannel(ILC_UPDATES, ILC_UPDATES, NotificationManager.IMPORTANCE_LOW);
        channel1.setDescription("This is high importance channel: General");
        channel2.setDescription("This is low importance channel: Updates");

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel1);
        notificationManager.createNotificationChannel(channel2);

        Log.e("App", "Channel created successfully");


    }
}
