package com.issac.administrator.mythread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private TextView tv;
    private Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tv.setText("OK");
        }
    };
    private void updateUI1(){
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             tv.setText("ok");
                         }
                     }
        );
    }

    private void updateUI2(){
        handler.sendEmptyMessage(1);
    }

    private void updateUI3(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText("ok");
            }
        });
    }

    private void updateUI4(){
        tv.post(new Runnable() {
            @Override
            public void run() {
                tv.setText("ok");
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tv = (TextView) findViewById(R.id.textView1);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    updateUI4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
