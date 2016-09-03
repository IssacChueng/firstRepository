package com.issac.administrator.mysqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //每个程序都有一个自己的数据库,默认情况下互相不干扰
        //创建一个数据库并打开
//        SQLiteDatabase db =  openOrCreateDatabase("user.db",MODE_PRIVATE,null);
//        db.execSQL("create table if not exists usertb(_id integer primary key autoincrement, name text" +
//                " not null, sex text not null, age integer not null)");
//        db.execSQL("insert into usertb(name,sex,age) values('张三','女',18)");
//        db.execSQL("insert into usertb(name,sex,age) values('李四','女',20)");
//        db.execSQL("insert into usertb(name,sex,age) values('王五','男',23)");
//        Cursor c = db.rawQuery("select * from usertb",null);
//        if (c!=null){
//            while(c.moveToNext()){
//                Log.i("Main", "id: "+c.getInt(c.getColumnIndex("_id")));
//                Log.i("Main", "name: "+c.getString(c.getColumnIndex("name")));
//                Log.i("Main", "sex: "+c.getString(c.getColumnIndex("sex")));
//                Log.i("Main", "age: "+c.getInt(c.getColumnIndex("age")));
//            }
//            c.close();
//        }
//        db.close();

        //ContentValues
//        ContentValues values = new ContentValues();
//        values .put("name","张三");
//        values .put("sex","男");
//        values .put("age",24);
//        db.insert("usertb",null,values);//返回插入的rawid
//        values.clear();//清除里面的值,再插入新的值
//        values .put("name","张四");
//        values .put("sex","男");
//        values .put("age",24);
//        db.insert("usertb",null,values);//返回插入的rawid
//        values.clear();//清除里面的值,再插入新的值
//        values .put("name","张五");
//        values .put("sex","男");
//        values .put("age",24);
//        db.insert("usertb",null,values);//返回插入的rawid
//        values.clear();//清除里面的值,再插入新的值
//        values .put("name","张六");
//        values .put("sex","男");
//        values .put("age",24);
//        db.insert("usertb",null,values);//返回插入的rawid
//        values.clear();
//        values.put("sex","女");
//        db.update("usertb",values,"_id>?",new String[]{"3"});
//        db.delete("usertb","name like ?",new String[]{"%三%"});
//        Cursor c = db.query("usertb",null,"_id>?",new String[]{"0"},null,null,"age");
//        if (c!=null){
//            String[] colms =c.getColumnNames();
//            while (c.moveToNext()){
//                for (String colnumName: colms){
//                    Log.i("Main",colnumName+""+c.getString(c.getColumnIndex(colnumName)));
//                }
//            }
//            c.close();
//        }
//
//        db.close();


        DBOpenHelper helper = new DBOpenHelper(this,"stu.db");
        helper.getReadableDatabase();//可读,只能查询,不能更新插入
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from stutb",null);
        if (c!=null){
            String[] names = c.getColumnNames();
            while (c.moveToNext()){
                for (String name:names){
                    Log.i("Main",name+": "+c.getString(c.getColumnIndex(name)));
                }
            }
            c.close();
        }
        db.close();
    }
}
