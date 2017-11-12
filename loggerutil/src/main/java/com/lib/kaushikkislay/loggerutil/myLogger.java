package com.lib.kaushikkislay.loggerutil;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;

/**
 * Created by kaushikkislay on 12/11/17.
 */

public class myLogger extends IntentService implements SensorEventListener {

    private SensorManager SM;
    private Sensor sn;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *  name the worker thread, important only for debugging.
     */
    public myLogger() {
        super("Worker thread");
    }



    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service started", Toast.LENGTH_LONG).show();
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        sn= SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, sn, SensorManager.SENSOR_DELAY_NORMAL);
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("TAG X: ", String.valueOf(event.values[0]));
        Log.d("TAG Y: ", String.valueOf(event.values[1]));
        Log.d("TAG Z: ", String.valueOf(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
