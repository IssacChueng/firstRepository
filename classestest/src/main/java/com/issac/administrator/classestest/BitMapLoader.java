package com.issac.administrator.classestest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/16.
 */
public class BitMapLoader {

    private ImageView imageView;
    private String url;
    private ListView mListView;
    private Set<MAsyncTask> mTask;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             if (imageView.getTag().equals(url)) {
                 imageView.setImageBitmap((Bitmap) msg.obj);
            }

        }
    };

    private LruCache<String,Bitmap> mCache;

    public BitMapLoader(ListView listView){
        mListView = listView;
        mTask = new HashSet<>();
        int maxSize = ((int) Runtime.getRuntime().maxMemory())/4;
        Log.i("maxsize",maxSize+"");
        mCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候调用
                return value.getByteCount();

            }
        };

    }

    //增加到缓存
    public void addBitMapToCache(String url,Bitmap value){
        if (getBitmapFromCache(url) == null){
            mCache.put(url,value);
        }
    }

    //缓存中获取数据
    public Bitmap getBitmapFromCache(String url){
        return mCache.get(url);
    }
    public void showImageByThread(ImageView imageView,final String url){

        this.url=url;
        this.imageView = imageView;

        //先从缓存中取bitmap如果没有再联网获取
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = getBitmapFromUrl(url);
                        //将下载的图片放入缓存
                        addBitMapToCache(url, bitmap);
                        Message message = Message.obtain();
                        message.obj = bitmap;
                        handler.sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    public void showImageByAsyncTask(ImageView imageView,String url){
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null){
            imageView.setImageResource(R.mipmap.ic_launcher);
        }else{
            imageView.setImageBitmap(bitmap);
        }

    }

    public void cancelAllTask() {
        if (mTask != null){
            for (MAsyncTask task: mTask){
                task.cancel(false);
            }
        }
    }

    class MAsyncTask extends AsyncTask<String,Void,Bitmap>{

        private String url;
//        private ImageView imageView;
        public MAsyncTask(String url) {
//            this.imageView=imageView;
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                String url = params[0];
                Bitmap bitmap = getBitmapFromUrl(url);
                if (bitmap!=null) {
                    addBitMapToCache(url, bitmap);
                }
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mListView.findViewWithTag(url);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public Bitmap getBitmapFromUrl(String url) throws IOException {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL urlString = new URL(url);
            HttpURLConnection connection  = (HttpURLConnection) urlString.openConnection();
            is  = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            is.close();
        }
        return null;
    }

    public void loadImages(int start,int end){
        for (int i = start; i <end ; i++) {
            String url = MyBaseAdapter.URLs[i];
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null){
                MAsyncTask task = new MAsyncTask(url);
                task.execute(url);
                mTask.add(task);
            }else {
                ImageView mImageView = (ImageView) mListView.findViewWithTag(url);
                mImageView.setImageBitmap(bitmap);
            }
        }
    }
}
