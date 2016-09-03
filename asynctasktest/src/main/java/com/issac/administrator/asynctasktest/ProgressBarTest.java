package com.issac.administrator.asynctasktest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressBarTest extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private MyAsyncTask mMyAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_test);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar2);
        mMyAsyncTask = new MyAsyncTask();
        mMyAsyncTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMyAsyncTask != null &&
                mMyAsyncTask.getStatus() == AsyncTask.Status.RUNNING){
            mMyAsyncTask.cancel(true);
        }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                if (isCancelled()){
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){
                return;
            }
            mProgressBar.setProgress(values[0]);
        }
    }
}
