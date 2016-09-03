package com.issac.administrator.myhttpurlconnection;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/8/29.
 */
public class HttpThread2 extends Thread {
    private String url;
    private String name="zhangsan";
    private int age=12;

    public HttpThread2(String url) {
        this.url = url;
    }

    @Override
    public void run() {

        try {
            String urlWithData = url+"/MyServlet?name="+name+"&age="+age;
            URL httpUrl = new URL(urlWithData);
            try {
                HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                connection.setReadTimeout(5000);
                connection.setRequestMethod("GET");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String line;
//                StringBuffer sb =new StringBuffer();
//                while ((line=bufferedReader.readLine())!=null){
//                    sb.append(line);
//                }
//                System.out.println(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
