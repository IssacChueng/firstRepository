package com.issac.administrator.myfile;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String FILENAME = "file.txt";
    private File file;
    private EditText text;
    private Button btnWrite,btnRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.text);
        btnWrite = (Button) findViewById(R.id.write);
        btnRead = (Button) findViewById(R.id.read);
        btnWrite.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        file = new File(getFilesDir(),FILENAME);
        //getFilsdir()得到内部的app全包名下file目录，是APP默认存储目录
        /*
        getCacheDir()默认缓存文件存放位置，存放不重要文件，内存空间不足时系统自动删除

         */
        this.getDir("imooc",MODE_APPEND);//自定义包名下的目录--全包名/imooc/
        /*
        权限，MODE_PRIVATE,私有，写入内容覆盖原内容
            MODE_APPEND，私有，追加内容
            MODE_WORLD_READABLE,MODE_WORLD_WRITEABLE控制其他应用是否有读写权限
            this.getExternalFilesDir(type)得到外部目录
            如果应用要用到外部存储，需要声明权限
         */
//        this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!file.exists()){
            try {
                boolean success = file.createNewFile();
                Log.i("Main",success+"");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            Toast.makeText(this,"文件已存在",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.write:
                if (file.exists()){
                    try {
                        FileWriter writer = new FileWriter(file);
                        String strText = text.getText()+"";
                        writer.write(strText);
                        Toast.makeText(this,"strText"+strText,Toast.LENGTH_SHORT).show();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case R.id.read:
                if (file.exists()){
                    try {
                        FileReader reader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String str = "";
                        String line =bufferedReader.readLine();
                        while (line != null){
                            str+=line;
                            line = bufferedReader.readLine();

                        }
                        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
