package com.issac.administrator.mysystemservice;

import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view) {
        switch (view.getId()){
            case R.id.netInfo:
                String netInfo = getNetInfo(this);
                Toast.makeText(this,netInfo,Toast.LENGTH_SHORT).show();
                break;
            case R.id.voice:
                getStreamVoice(this);
                break;
            case R.id.getPackageName:
                getCurrentPackageName(this);
                break;
        }
    }

    private void getCurrentPackageName(Context context) {
        String processName ="";
        String[] pkgList = new String[0];
        if (context != null){
            ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            processName = manager.getRunningAppProcesses().get(0).pkgList[0];
//            for (String pkgName: pkgList){
//                processName+=pkgName;
//            }
        }
        Toast.makeText(context,processName,Toast.LENGTH_SHORT).show();

    }

    private String getNetInfo(Context context) {
        String netInfo = "";
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo!= null && networkInfo.isConnected()){
                netInfo = networkInfo.getTypeName();
            }else{
                netInfo =  "network is disconneted";
            }
        }else{
            netInfo = "context is not available";
        }
        return netInfo;
    }

    public void getStreamVoice(Context context) {
        if (context != null){
            AudioManager mAudioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
            int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
            int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
            Toast.makeText(context,"当前系统最大铃声音量为"+maxVolume+", 当前铃声音量为"+currentVolume,Toast.LENGTH_SHORT).show();
        }
    }
}
