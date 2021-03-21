package com.madlab.exno5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public static final String channelId1 = "Channel1";
    public static final String channelId2 = "Channel2";
    private TextInputEditText titleText , messageText;
    private Button sendCh1, sendCh2;
    private NotificationManager notificationManager;
    private int id = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.notification_title);
        messageText = findViewById(R.id.notification_message);
        sendCh1 = findViewById(R.id.notifyButton);
        sendCh2 = findViewById(R.id.notifyButton2);

        //Notification Manager
        notificationManager = (NotificationManager) getSystemService(NotificationManager.class);

        //Create Channels
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel1 = new NotificationChannel(channelId1, "Channel 1", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel1.setDescription("This is Channel 1");

            NotificationChannel notificationChannel2 = new NotificationChannel(channelId2, "Channel 2", NotificationManager.IMPORTANCE_LOW);
            notificationChannel2.setDescription("This is Channel 2");

            notificationManager.createNotificationChannel(notificationChannel1);
            notificationManager.createNotificationChannel(notificationChannel2);
        }

        sendCh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                String title = titleText.getText().toString().trim();
                String message = messageText.getText().toString().trim();
                createNotification(title,message);
            }
        });

        sendCh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id++;
                String title = titleText.getText().toString().trim();
                String message = messageText.getText().toString().trim();
                createNotification2(title,message);
            }
        });

    }

    private void createNotification(String title, String message) {
        if(title.length() == 0 || message.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter all the details to create a notification", Toast.LENGTH_LONG).show();
        }else{
            NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this,channelId1)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_notifications))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_MAX);

            notificationManager.notify(id, builder1.build());
        }
    }


    private void createNotification2(String title, String message) {
        if(title.length() == 0 || message.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter all the details to create a notification", Toast.LENGTH_LONG).show();
        }else{
            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this,channelId2)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_notifications))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_LOW);

            notificationManager.notify(id, builder2.build());
        }
    }

}