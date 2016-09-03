package com.issac.administrator.asynctasktest;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Administrator on 2016/8/15.
 */
public class MyAsyncTask extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... params) {
        Log.i("Main","doInBackground");
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("Main","onPreExecute");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i("Main","onPostExecute");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.i("Main","onProgressUpdate");
    }
}
