package com.issac.administrator.mybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Administrator on 2016/8/22.
 */
public class BC extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        Log.i("Main",s);
//        Bundle data = new Bundle();
//        data.putString("shit","处理的数据");
//        setResultExtras(data);
        abortBroadcast();//终端有序广播
    }
}
