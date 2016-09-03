package com.issac.administrator.baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<ItemBean> itemBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemBeanList.add(new ItemBean(R.mipmap.ic_launcher,
                    "标题"+i,"我是内容"+i));
        }

        ListView view = (ListView) findViewById(R.id.lv1);
        view.setAdapter(new MyBaseAdapter(this,itemBeanList));
    }
}