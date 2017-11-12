package com.lib.kaushikkislay.loggerutil;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


/**
 * Created by kaushikkislay on 13/11/17.
 */

public class intentcaller {
    private static final String TAG = "AWESOME_LOGGER";
    public static void mylog (String str, Context ctx) {
        Log.d(TAG, str);
        Intent myintent = new Intent(ctx,myLogger.class);
        ctx.startService(myintent);
    }
}
