package com.lib.kaushikkislay.loggerutil;

import android.util.Log;

/**
 * Created by kaushikkislay on 12/11/17.
 */

public class myLogger {

    private static final String TAG = "AWESOME_LOGGER";
    public static void mylog (String str){
        Log.d(TAG,str);
    }
}
