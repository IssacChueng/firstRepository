package com.issac.administrator.myanimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView car;
    private Button btn_scale;
    private Button btn_rotate;
    private Button btn_translate;
    private Button btn_alpha;
    private Animation anim;
    private ValueAnimator valueAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        car = (ImageView) findViewById(R.id.car);
        anim= AnimationUtils.loadAnimation(this,R.anim.scaleanim);
        car.startAnimation(anim);
        btn_scale = (Button) findViewById(R.id.scale);
        btn_rotate = (Button) findViewById(R.id.rotate);
        btn_translate = (Button) findViewById(R.id.translate);
        btn_alpha = (Button) findViewById(R.id.alpha);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"this is a car",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public ValueAnimator doAnim(){
        valueAnimator = ValueAnimator.ofInt(0,360,180,360);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);//倒序播放
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                car.layout(curValue,curValue,curValue+car.getWidth(),curValue+car.getHeight());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i("Main","onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("Main","onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i("Main","onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i("Main","onAnimationRepeat");
            }
        });
        valueAnimator.start();
        return valueAnimator;
    }

    public void doCancel(ValueAnimator animator){
        if (animator!=null){
            animator.cancel();
            animator.removeAllUpdateListeners();
        }
    }

    public void doClick(View view) {
        switch (view.getId()){
            case R.id.scale:
                anim = AnimationUtils.loadAnimation(this,R.anim.scaleanim);
                car.startAnimation(anim);
                break;
            case R.id.rotate:
                anim = AnimationUtils.loadAnimation(this,R.anim.rotateanim);
                car.startAnimation(anim);
                break;
            case R.id.translate:
                anim = AnimationUtils.loadAnimation(this,R.anim.translateanim);
                car.startAnimation(anim);
                break;
            case R.id.alpha:
                anim = AnimationUtils.loadAnimation(this,R.anim.alphaanim);
                car.startAnimation(anim);
                break;
            case R.id.btn_code_anim:
                anim =new RotateAnimation(0f,180f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                anim.setDuration(1000);
                car.startAnimation(anim);
                break;
            case R.id.btn_code_valueAnimator:
                valueAnimator = doAnim();
                break;
            case R.id.btn_cancel:
                doCancel(valueAnimator);
                break;

        }
    }


}
