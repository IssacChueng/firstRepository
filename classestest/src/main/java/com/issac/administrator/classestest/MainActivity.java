package com.issac.administrator.classestest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private String URL = "http://www.imooc.com/api/teacher/?type=4&num=30";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassesAsyncTask task = new ClassesAsyncTask();
        task.execute(URL);
    }

    class ClassesAsyncTask extends AsyncTask<String,Void,List<ClassBean>>{
        @Override
        protected List<ClassBean> doInBackground(String... params) {


            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<ClassBean> classBeanList) {
            super.onPostExecute(classBeanList);
            lv = (ListView) findViewById(R.id.lv);
            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(MainActivity.this,classBeanList,lv);

            lv.setAdapter(myBaseAdapter);
        }
    }

    private List<ClassBean> getJsonData(String param) {
        List<ClassBean> result = new ArrayList<>();

        try {
            String jsonString = readStream(new URL(param).openStream());
            JSONObject object = new JSONObject(jsonString);
            JSONArray data = object.getJSONArray("data");
            ClassBean bean=null;
            for (int i = 0; i <data.length() ; i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                bean = new ClassBean(jsonObject.getString("picSmall"),jsonObject.getString("name"),jsonObject.getString("description"));
                result.add(bean);
            }
            Log.i("Main",jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String readStream(InputStream is){
        String result = "";
        InputStreamReader isr;
        String line = "";

        try {
            isr=new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line=br.readLine())!= null){
                result += line;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
