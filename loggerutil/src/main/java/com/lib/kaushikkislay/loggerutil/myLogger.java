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
    private SensorEvent se = null;

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
        //return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service stopped", Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        synchronized (this){

            for (int i=0;1<20;i++){
                Log.d("TAG Count: ", Integer.toString(i));
                if (se !=null){
                Log.d("TAG X: ", String.valueOf(se.values[0]));
                Log.d("TAG Y: ", String.valueOf(se.values[1]));
                Log.d("TAG Z: ", String.valueOf(se.values[2]));
                }
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

        se= event;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
