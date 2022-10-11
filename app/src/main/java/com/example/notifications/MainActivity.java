package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channelID", "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }
    }
    public void buttonClick(View view){
        Resources res = this.getResources();
        NotificationCompat.Builder builder
                = new NotificationCompat.Builder(MainActivity.this, "channelID");
        builder.setSmallIcon( R.drawable.notif);
        builder.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.notif));
        builder.setContentTitle("Колокольчик");
        builder.setContentText("Колокольчик звенит, колокольчик зовет");
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setProgress(0,0,true);
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        Intent result = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(result);

        PendingIntent resultPending =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(resultPending);

        Notification notification = builder.build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(1, builder.build());

    }
}