package com.psx.rotatescreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeOrientation(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(getApplicationContext())) {
                // Do stuff here
                Settings.System.putInt(
                        getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION,
                        0 //0 means off, 1 means on
                );
                Settings.System.putInt(
                        getContentResolver(),
                        Settings.System.USER_ROTATION,
                        Surface.ROTATION_0 //Use any of the Surface.ROTATION_ constants
                );
            }
            else {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }

    public void changeOrientationLandscape(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(getApplicationContext())) {
                // Do stuff here
                Settings.System.putInt(
                        getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION,
                        0 //0 means off, 1 means on
                );
                Settings.System.putInt(
                        getContentResolver(),
                        Settings.System.USER_ROTATION,
                        Surface.ROTATION_90 //Use any of the Surface.ROTATION_ constants
                );
            }
            else {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }
}
