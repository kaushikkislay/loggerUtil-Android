package com.lib.kaushikkislay.loggerutil;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kaushikkislay on 12/11/17.
 */

public class myLogger extends IntentService {



    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public myLogger() {
        super("Worker thread");
    }



    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service started", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service stopped", Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        synchronized (this){
            for (int i=0;i<20;i++){
                Log.d("TAG", Integer.toString(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
