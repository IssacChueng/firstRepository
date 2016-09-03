package com.issac.administrator.mysharedprefrences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //默认的SP对象
//        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        //自定义文件名,权限
        SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name","张三");
        editor.putInt("age",30);
        editor.putLong("time", System.currentTimeMillis());
        editor.putBoolean("default",true);
        editor.apply();
        editor.remove("default");
        editor.apply();
    }
}
