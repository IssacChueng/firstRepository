package com.issac.administrator.mythread;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDefault;
    private ImageView img;
    private Handler handler = new Handler();
    private int[] imgs = new int[]{R.mipmap.i126442,R.mipmap.i87305,R.mipmap.i135648546180};
    private int index=0;
    private MyRunnable myRunnable = new MyRunnable();

    public void doClick(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            index++;
            index=index%3;
            img.setImageResource(imgs[index]);
            handler.postDelayed(myRunnable,1000);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDefault = (TextView) findViewById(R.id.handler);
        img = (ImageView) findViewById(R.id.img);
        new Thread(myRunnable).start();

//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            tvDefault.setText("update by handler");
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }
}
