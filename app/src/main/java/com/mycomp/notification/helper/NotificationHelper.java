package com.mycomp.notification.helper;


import static com.mycomp.notification.constants.NotificationConstants.ICON_IHC_GENERAL;
import static com.mycomp.notification.constants.NotificationConstants.ICON_ILC_UPDATES;
import static com.mycomp.notification.constants.NotificationConstants.IHC_GENERAL;
import static com.mycomp.notification.constants.NotificationConstants.ILC_UPDATES;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.mycomp.notification.mainactivity.MainActivity;
import com.mycomp.notification.mainactivity.R;
import com.mycomp.notification.service.NotificationReceiver;

public class NotificationHelper {
    private final Context context;

    private final NotificationManagerCompat notificationManagerCompat;
    private final String tag;

    public NotificationHelper(@NonNull Context context) {
        this.context = context;
        this.notificationManagerCompat = NotificationManagerCompat.from(context);
        this.tag = context.getClass().getName();
    }

    public void notifyGeneral(final int id, final String title, final String message) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.e("MainActivity", "no permission");
            return;
        }

        Log.e(tag, "has permission");
        final Notification notification = getNotification(IHC_GENERAL, ICON_IHC_GENERAL, title, message, NotificationCompat.PRIORITY_HIGH);
        Log.e(tag, "sendOnChannel1 title: " + title + " message: " + message);

        this.notificationManagerCompat.notify(id, notification);
    }

    public void notifyGeneralWithContent(final int id, final String title, final String message, Class<?> activityClass) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.e("MainActivity", "no permission");
            return;
        }

        Log.e(tag, "has permission");
        Intent activityIntent = new Intent(context, activityClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_MUTABLE);

        final Notification notification = new NotificationCompat.Builder(context, IHC_GENERAL)
                .setSmallIcon(ICON_IHC_GENERAL)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_launcher_background, "Toast", actionIntent)
                .setAutoCancel(true)
                .build();
        Log.e(tag, "sendOnChannel1 title: " + title + " message: " + message);

        this.notificationManagerCompat.notify(id, notification);
    }

    public void notifyUpdate(final int id, final String title, final String message) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.e(tag, "no permission");
            return;
        }

        Log.e(tag, "has permission");
        Notification notification = getNotification(ILC_UPDATES, ICON_ILC_UPDATES, title, message, NotificationCompat.PRIORITY_LOW);
        Log.e(tag, "sendOnChannel2 title: " + title + " message: " + message);
        this.notificationManagerCompat.notify(id, notification);
    }

    public void toastShort(final String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @NonNull
    private Notification getNotification(String channelId, int icon, String title, String message, int priority) {
        final Notification notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(priority)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        return notification;
    }
}
