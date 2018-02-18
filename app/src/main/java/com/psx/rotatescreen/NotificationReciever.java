package com.psx.rotatescreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static com.psx.rotatescreen.MainActivity.LANDSCAPE_ACTION;
import static com.psx.rotatescreen.MainActivity.PORTRAIT_ACTION;
import static com.psx.rotatescreen.MainActivity.changeToLandScape;
import static com.psx.rotatescreen.MainActivity.changeToPortrait;

/**
 * Created by Pranav Sharma on 18-02-2018.
 */

public class NotificationReciever extends BroadcastReceiver {

    private static String TAG = NotificationReciever.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(PORTRAIT_ACTION)) {
            changeToPortrait(context);
            Toast.makeText(context,"Recieved",Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Portrait Intent Recieved");
        } else if (action.equals(LANDSCAPE_ACTION)) {
            changeToLandScape(context);
            Log.d(TAG,"Landscape Intent Recieved");
        }
    }
}
