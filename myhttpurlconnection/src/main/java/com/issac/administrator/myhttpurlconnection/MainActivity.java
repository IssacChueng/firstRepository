package com.issac.administrator.myhttpurlconnection;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Handler handler=new Handler();
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        photo = (ImageView) findViewById(R.id.photo);


        //new HttpThread("http://img.mukewang.com/56551e450001afcd09600720-240-180.jpg",photo,handler).start();
        new HttpThread2("http://192.168.0.101:8080/web").start();
    }
}
