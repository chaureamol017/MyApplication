package com.mycomp.notification.mainactivity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mycomp.notification.helper.NotificationHelper;
import com.mycomp.notification.helper.ServiceHelper;

public class MainActivity extends AppCompatActivity {
    private NotificationHelper notificationHelper;
    private EditText editTextTitle;
    private EditText editTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationHelper = new NotificationHelper(this);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextMessage = findViewById(R.id.editTextMessage);

//        ServiceHelper.startService(this, MyBackgroundService.class);
//        ServiceHelper.checkAndStartForegroundService(this, MyForegroundService.class);

    }

    public void sendOnChannel1(View view) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        this.notificationHelper.notifyGeneralWithContent(1, title, message, MainActivity.class);
//        this.notificationHelper.toastShort(message);
    }

    public void sendOnChannel2(View view) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        this.notificationHelper.notifyUpdate(2, title, message);
    }

}