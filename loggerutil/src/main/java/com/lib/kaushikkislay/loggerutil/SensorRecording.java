package com.lib.kaushikkislay.loggerutil;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kaushikkislay on 13/11/17.
 */

public class SensorRecording extends Service implements SensorEventListener{

    private SensorManager SM;
    private Sensor sn;
    private SensorEvent se = null;

    final class MyThreadClass implements Runnable{

        int service_id;
        MyThreadClass(int _service_id){
            this.service_id = _service_id;
        }

        @Override
        public void run() {
            int i=0;
            synchronized (this){
                while (i<=20){
                    Log.d("TAG", Integer.toString(i));
                    try {
                        wait(1500);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                stopSelf(service_id);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        Toast.makeText(this,"Service started", Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new MyThreadClass(startId));
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service stopped", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
