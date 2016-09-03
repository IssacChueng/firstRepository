package com.imooc.baseadaptertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv_main);
        List<ItemBean> dataList = new ArrayList<>();
        // 创建假数据
        for (int i = 0; i < 20; i++) {
            dataList.add(new ItemBean(
                    R.mipmap.ic_launcher,
                    "我是标题" + i,
                    "我是内容" + i));
        }
        // 设置适配器
        listView.setAdapter(new MyAdapter(this, dataList));
    }
}
