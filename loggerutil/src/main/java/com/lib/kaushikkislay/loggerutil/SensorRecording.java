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

public class SensorRecording extends Service {

    private SensorManager SM;
    private Sensor sn;
    private SensorEvent se = null;

    final class MyThreadClass implements Runnable, SensorEventListener{

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
                    if (se !=null){
                        Log.d("TAG X: ", String.valueOf(se.values[0]));
                        Log.d("TAG Y: ", String.valueOf(se.values[1]));
                        Log.d("TAG Z: ", String.valueOf(se.values[2]));
                    }
                    try {
                        wait(1000);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                stopSelf(service_id);
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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Service started", Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new MyThreadClass(startId));
        thread.start();
        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
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


}
