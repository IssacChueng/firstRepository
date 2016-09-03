package com.issac.administrator.myservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent intent  = new Intent();
    private Intent intent2;
    private MyBindService service;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //当启动源与service成功连接，自动调用这个方法
            MainActivity.this.service = ((MyBindService.MyBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //当启动源与service连接意外丢失
        }
    };


    //只会初始化一次
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view) {
        switch (view.getId()){
            case R.id.startService:
                intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
                break;
            case R.id.stopService:
                stopService(intent);
                break;
            case R.id.bindService:
                intent2 = new Intent(MainActivity.this,MyBindService.class);
                startService(intent2);
                bindService(intent2, conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.play:
                service.play();
                break;
            case R.id.pause:
                service.pause();
                break;
            case R.id.previous:
                service.previous();
                break;
            case R.id.next:
                service.next();
                break;
            case R.id.unbindService:
                unbindService(conn);
                service=null;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        stopService(intent2);
        if (service != null) {
            unbindService(conn);
        }
        super.onDestroy();
    }
}
