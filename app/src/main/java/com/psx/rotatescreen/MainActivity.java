package com.psx.rotatescreen;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent portrainIntent = new Intent();
    Intent landscapteIntent = new Intent();
    public static final String PORTRAIT_ACTION = "1";
    public static final String LANDSCAPE_ACTION = "2";
    PendingIntent intentToPortrait;
    PendingIntent intentToLandscape;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction(PORTRAIT_ACTION);
        intentFilter.addAction(LANDSCAPE_ACTION);
    }

    public void changeOrientation(View view) {
        changeToPortrait(view.getContext());
        portrainIntent.setAction(PORTRAIT_ACTION);
        intentToPortrait = PendingIntent.getBroadcast(view.getContext(),1,portrainIntent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void changeToPortrait (Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(context)) {
                // Do stuff here
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION,
                        0 //0 means off, 1 means on
                );
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.USER_ROTATION,
                        Surface.ROTATION_0 //Use any of the Surface.ROTATION_ constants
                );
            }
            else {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }

    public void changeOrientationLandscape(View view) {
        changeToLandScape(view.getContext());
        landscapteIntent.setAction(LANDSCAPE_ACTION);
        intentToLandscape = PendingIntent.getBroadcast(this,2,landscapteIntent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void changeToLandScape (Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(context)) {
                // Do stuff here
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION,
                        0 //0 means off, 1 means on
                );
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.USER_ROTATION,
                        Surface.ROTATION_90 //Use any of the Surface.ROTATION_ constants
                );
            }
            else {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }

    public void showNotification (View view) {
        portrainIntent.setAction(PORTRAIT_ACTION);
        intentToPortrait = PendingIntent.getBroadcast(view.getContext(),1,portrainIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        landscapteIntent.setAction(LANDSCAPE_ACTION);
        intentToLandscape = PendingIntent.getBroadcast(this,2,landscapteIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Rotation Fix")
                .setOngoing(true)
                .addAction(R.drawable.ic_stay_primary_portrait_black_24dp," Portrait ",intentToPortrait)
                .addAction(R.drawable.ic_stay_current_landscape_black_24dp," Landscape ",intentToLandscape);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(view.getContext());
        notificationManagerCompat.notify(1,builder.build());
    }
}
