package com.issac.administrator.myhttpclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DownloadActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        imageView = (ImageView) findViewById(R.id.image1);
        final String url = "http://192.168.0.101:8081/web/126676.jpg";
        Glide.with(this).load("http://192.168.0.101:8081/web/126676.jpg").into(imageView);
        new Thread(){
            @Override
            public void run() {
                new Downloader(url).download();
            }
        }.start();
    }
}
