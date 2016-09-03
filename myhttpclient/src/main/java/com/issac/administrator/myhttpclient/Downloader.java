package com.issac.administrator.myhttpclient;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Downloader {
    private String url;


    public Downloader(String url){
        this.url=url;
    }

    private Executor threadPool = Executors.newFixedThreadPool(3);
    public void download(){
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn= (HttpURLConnection) httpUrl.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            System.out.println("请求发送");
            long streamLength = conn.getContentLength();
            long block= streamLength/3;
            File filePath = getAbsoluteFileName(url);
            for (int i=0;i<3;i++){
                long start =i*block;
                long end=(i+1)*block-1;
                if (i==2){
                    end=streamLength-1;
                }
                DownloadRunnable runnable = new DownloadRunnable(start,end,url,filePath);
                threadPool.execute(runnable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getAbsoluteFileName(String url) {
        String fileName = url.substring(url.lastIndexOf('/')+1);
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        return file;
    }

    class DownloadRunnable implements Runnable{
        private long start;
        private long end;
        private String url;
        private File filePath;
        public DownloadRunnable(long start, long end, String url, File filePath) {
            this.start = start;
            this.end = end;
            this.url = url;
            this.filePath = filePath;
        }

        @Override
        public void run() {
            try {
                URL httpUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range","bytes="+start+"-"+end);
                RandomAccessFile accessFile = new RandomAccessFile(filePath,"rwd");
                accessFile.seek(start);
                InputStream inputStream = conn.getInputStream();

                byte[] bytes = new byte[1024*4];
                int len;
                while((len=inputStream.read(bytes))!=-1){
                    accessFile.write(bytes,0,len);
                }


                    accessFile.close();


                    inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
