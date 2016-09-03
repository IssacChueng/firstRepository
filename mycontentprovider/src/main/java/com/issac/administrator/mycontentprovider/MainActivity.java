package com.issac.administrator.mycontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver resolver = getContentResolver();
//        Cursor query = resolver.query(Contacts.CONTENT_URI, new String[]{Contacts._ID,
//                Contacts.DISPLAY_NAME}, null, null, null);
//
//        if (query !=null){
//            while (query.moveToNext()){
//                int id = query.getInt(query.getColumnIndex(Contacts._ID));
//                Log.i("Main","_id: "+id);
//                Log.i("Main","name: "+query.getString(query.getColumnIndex(Contacts.DISPLAY_NAME)));
//                //根据联系人id查询号码
//                Cursor query1 = resolver.query(Phone.CONTENT_URI,
//                        new String[]{Phone.NUMBER, Phone.TYPE},
//                        Phone.CONTACT_ID + "=" + id, null, null);
//                if (query1!=null){
//                    while (query1.moveToNext()){
//                        int type = query1.getInt(query1.getColumnIndex(Phone.TYPE));
//                        if (type == Phone.TYPE_HOME){
//                            Log.i("Main","PHONE NUMBER: "+query1.getString(query1.getColumnIndex(Phone.NUMBER)));
//                        }
//                    }
//                    query1.close();
//                }
//                //根据联系人id查询email
//                Cursor query2 = resolver.query(Email.CONTENT_URI, new String[]{Email.DATA, Email.TYPE}, Email.CONTACT_ID + "=" + id, null, null);
//                if (query2 != null){
//                    while(query2.moveToNext()) {
//                        int type = query2.getInt(query2.getColumnIndex(Email.TYPE));
//                        if (type == Email.TYPE_WORK) {
//                            Log.i("Main", "工作邮箱： " + query2.getString(query2.getColumnIndex(Email.DATA)));
//                        }
//                    }
//
//                    query2.close();
//                }
//            }
//            query.close();
//        }

        ContentValues values = new ContentValues();
        Uri uri = resolver.insert(RawContacts.CONTENT_URI, values);
        Long id = ContentUris.parseId(uri);
        values.clear();
        //插入人名
        values.put(StructuredName.RAW_CONTACT_ID,id);
        values.put(StructuredName.DISPLAY_NAME,"Issac");
        values.put(StructuredName.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE);
        uri = resolver.insert(ContactsContract.Data.CONTENT_URI,values);
        //插入电话信息
        values.clear();
        values.put(Phone.RAW_CONTACT_ID,id);
        values.put(Phone.NUMBER,"13222222222");
        values.put(Phone.TYPE,Phone.TYPE_HOME);
        values.put(Phone.MIMETYPE,Phone.CONTENT_ITEM_TYPE);
        resolver.insert(ContactsContract.Data.CONTENT_URI,values);
        
        
    }
}
