package com.example.mazine.labor01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class MyReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.d(MainActivity.TAG," received " + intent.getAction());

        Intent serviceIntent = new Intent(context, MyIntentService.class);
        serviceIntent.setAction("httpReq");

        startWakefulService(context, serviceIntent);

        Log.d(MainActivity.TAG,"receive done");

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
