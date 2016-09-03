package com.issac.administrator.myhttpclient;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class HttpThread extends Thread{

    private String url;
    private Context context;
    private Handler handler;
    private PersonAdapter personAdapter;
    private ListView listView;

    public HttpThread(String url,Context context,ListView listView,Handler handler) {
        this.url = url;
        this.context = context;
        this.listView = listView;
        this.handler = handler;
    }

    @Override
    public void run() {

        try {
            URL httpUrl = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                connection.setReadTimeout(5000);
                connection.setRequestMethod("GET");
                BufferedReader bufr = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer sb = new StringBuffer();
                String line;
                while ((line=bufr.readLine())!= null){
                    sb.append(line);
                }

                JSONObject json=new JSONObject(sb.toString());
                final List<Person> persons = new ArrayList<>();
                if (json.getString("result").equals("1")){

                    JSONArray personData = json.getJSONArray("personData");
                    for (int i=0; i<personData.length();i++){
                        Person person = new Person();
                        JSONObject personObject = personData.getJSONObject(i);
                        person.setName(personObject.getString("name"));
                        person.setAge(personObject.getInt("age"));
                        person.setUrl(personObject.getString("url"));
                        List<SchoolInfo> schoolInfos = new ArrayList<>();
                        SchoolInfo schoolInfo1 = new SchoolInfo();
                        SchoolInfo schoolInfo2 = new SchoolInfo();
                        schoolInfo1.setName(personObject.getJSONArray("schoolInfo").getJSONObject(0).getString("name"));
                        schoolInfo2.setName(personObject.getJSONArray("schoolInfo").getJSONObject(1).getString("name"));
                        schoolInfos.add(schoolInfo1);
                        schoolInfos.add(schoolInfo2);
                        person.setSchoolInfo(schoolInfos);
                        persons.add(person);
                    }

                    //TODO 将persons，context传入，显示
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            personAdapter = new PersonAdapter(persons,context);
                            listView.setAdapter(personAdapter);
                        }
                    });

                }else{
                    Toast.makeText(context,"结果返回错误",Toast.LENGTH_SHORT).show();
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
