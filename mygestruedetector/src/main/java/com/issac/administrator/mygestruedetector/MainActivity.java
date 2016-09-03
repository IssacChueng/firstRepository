package com.issac.administrator.mygestruedetector;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView sexy;
    private GestureDetector myGestureDetector;
    private GestureOverlayView gesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sexy = (ImageView) findViewById(R.id.sexy);
        //加载手势文件中的所有手势
        final GestureLibrary gestureLibrary = GestureLibraries.fromRawResource(this,R.raw.gestures);
        //这一步加载
        gestureLibrary.load();
        gesture = (GestureOverlayView) findViewById(R.id.gesture);
        gesture.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                //识别出手势，
                ArrayList<Prediction> list = gestureLibrary.recognize(gesture);
                Log.i("Main",list.size()+"");
                Prediction prediction = list.get(0);
                if (prediction.score>=5.0){
                    if (prediction.name.equals("exit")){
                        finish();
                    }else if(prediction.name.equals("forward")){
                        Toast.makeText(MainActivity.this,"前进",Toast.LENGTH_SHORT).show();
                    }else if(prediction.name.equals("backward")){
                        Toast.makeText(MainActivity.this, "后退", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"没有这个手势",Toast.LENGTH_SHORT).show();
                }
            }
        });
        myGestureDetector =new GestureDetector(this,new MyGestureListenner());
        sexy.setOnTouchListener(new View.OnTouchListener() {
            @Override//捕获到触摸屏幕事件
            public boolean onTouch(View v, MotionEvent event) {
                myGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    class MyGestureListenner extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("Main","main");
            if (e1.getX() - e2.getX() >50){
                Toast.makeText(MainActivity.this,"从右往左滑动",Toast.LENGTH_SHORT).show();
            }else if (e1.getX() - e2.getX()<-50){
                Toast.makeText(MainActivity.this,"从左往右滑动",Toast.LENGTH_SHORT).show();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
