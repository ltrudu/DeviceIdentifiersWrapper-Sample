package com.zebra.emdk_deviceidentifiers_sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.util.Log;

public class BootCompletedBroadcastReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(MainActivity.TAG, "BootCompletedBroadcastReceiver::onReceive");
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {

            Intent myStarterIntent = new Intent(context, MainActivity.class);
            myStarterIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myStarterIntent);
        }
    }
}
