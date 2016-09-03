package com.issac.administrator.mybroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view) {
        switch (view.getId()){
            case R.id.send1: //发送一条普通广播
                Intent intent = new Intent();
                intent.putExtra("msg","这是一条普通广播");
                intent.setAction("com.issac.administrator.MainActivity");
                sendBroadcast(intent);
                break;
            case R.id.send2:
                Intent intent2 = new Intent();
                intent2.putExtra("msg","这是一条有序广播");
                intent2.setAction("com.issac.administrator.MainActivity");
                sendOrderedBroadcast(intent2,null);
                break;
            case R.id.send3:
                Intent intent3 = new Intent();
                intent3.putExtra("msg","这是一条异步广播");
                intent3.setAction("com.issac.administrator.MainActivity");
                sendStickyBroadcast(intent3);
                IntentFilter intentFilter = new IntentFilter("com.issac.administrator.MainActivity");
                BC3 broadCast3 = new BC3();
                registerReceiver(broadCast3,intentFilter);

                break;

        }
    }
}
