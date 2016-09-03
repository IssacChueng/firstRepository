package com.issac.administrator.asynctasktest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

public class ImageTest extends AppCompatActivity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private String URL = "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);
        mImageView = (ImageView) findViewById(R.id.img);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        new MySyncTask().execute(URL);
    }

    class MySyncTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("Main","onPreExecute");
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //操作UI
            Log.i("Main","onPostExecute");
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Log.i("Main","doInBackground");
            String url = params[0];
            URLConnection connection;
            InputStream is;
            Bitmap bitmap = null;
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                Thread.sleep(3000);
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
