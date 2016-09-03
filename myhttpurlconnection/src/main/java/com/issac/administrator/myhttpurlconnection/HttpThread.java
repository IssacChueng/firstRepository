package com.issac.administrator.myhttpurlconnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/27.
 */
public class HttpThread extends Thread{
    private String url;
    private WebView webView;
    private Handler handler;
    private ImageView photo;

    public HttpThread(String url, WebView webView, Handler handler) {
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }
    public HttpThread(String url, ImageView photo, Handler handler) {
        this.url = url;
        this.photo = photo;
        this.handler = handler;
    }


    @Override
    public void run() {
        try {

            URL httpUrl = new URL(url);
            try {


                HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                connection.setReadTimeout(5000);//等待超时时间
                connection.setRequestMethod("GET");
//                final StringBuffer sb = new StringBuffer();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String str;
//                while ((str = reader.readLine()) != null) {
//                    sb.append(str);
//                }
//
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                       // webView.loadData(sb.toString(), "text/html,utf-8", null);
//                        webView.loadDataWithBaseURL(url,sb.toString(),"text/html","utf-8",null);
//                    }
//                });
                InputStream in = connection.getInputStream();
                File dir,fileName;
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    dir = Environment.getExternalStorageDirectory();
                    fileName = new File(dir,"downloadPic");
                    OutputStream os = new FileOutputStream(fileName);
                    BufferedOutputStream buf = new BufferedOutputStream(os);
                    byte[] bytes = new byte[1];
                    if (os!=null){
                        while(in.read(bytes)!= -1){
                            buf.write(bytes);
                        }
                    }
                    buf.flush();
                    in.close();
                    buf.close();
                    final Bitmap bitmap = BitmapFactory.decodeFile(fileName.getAbsolutePath());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            photo.setImageBitmap(bitmap);
                        }
                    });
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
}
