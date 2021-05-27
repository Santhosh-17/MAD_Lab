package com.madlab.exno9;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NotificationManager nm;
    NotificationChannel notificationChannel;
    final static String CHANNEL_ID = "krish_the_dev.notiflistner";

    public static TextView status;
    

    private boolean isNotificationServiceRunning() {
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        String enabledNotificationListeners =
                Settings.Secure.getString(contentResolver, "enabled_notification_listeners");
        String packageName = getPackageName();
        return enabledNotificationListeners != null && enabledNotificationListeners.contains(packageName);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager)getSystemService(NotificationManager.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ID,"General", NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(notificationChannel);
        }

        if(!isNotificationServiceRunning()) {
            Toast.makeText(getApplicationContext(),
                    "Please select Notification Listener app and give notification access permission",
                    Toast.LENGTH_SHORT)
                    .show();
            startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
        }

        Intent intent = new Intent(MainActivity.this, NotificationListener.class);
        startService(intent);

        status = findViewById(R.id.status);

    }
}
