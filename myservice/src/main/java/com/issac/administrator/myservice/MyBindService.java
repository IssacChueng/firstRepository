package com.issac.administrator.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {
    public MyBindService() {
    }


    @Override
    public void onCreate() {
        Log.i("Main","MyBindService--onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i("Main","MyBindService--onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Main","MyBindService--onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("Main","MyBindService--onBind");
        return new MyBinder();
    }

    public void play(){
        Log.i("Main","music playing");
    }
    public void pause(){
        Log.i("Main","pausing");
    }
    public void next(){
        Log.i("Main","next");
    }
    public void previous(){
        Log.i("Main","previous");
    }


    public class MyBinder extends Binder{
        public MyBindService getService(){
            return MyBindService.this;
        }
    }
}
