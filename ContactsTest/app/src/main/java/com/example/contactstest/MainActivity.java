package com.example.contactstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.security.Permission;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

/**
  *
  * @package:        com.example.contactstest
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/3 0003
  * @updateUser:     更新者：
  * @updateDate:     2020/1/3 0003
  * @updateRemark:   更新说明：
  * @version:        1.0
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "zz";

    private String newId;

    ListView contactsView;
    ArrayAdapter<String> adapter;
    List<String> contactList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.contactstest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("page", 1040);
                values.put("price", 22.85);
                ContentResolver resolver =  getContentResolver();
                Uri newUri = resolver.insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: query");
                
                Uri uri = Uri.parse("content://com.example.contactstest.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d(TAG, "name: " + name);
                        Log.d(TAG, "author: " + author);
                        Log.d(TAG, "pages: " + pages);
                        Log.d(TAG, "price: " + price);
                    }
                    cursor.close();
                }

            }
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.contactstest.provider/book" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of swords");
                values.put("author", "George Martin");
                values.put("page", 1216);
                values.put("price", 24.05);
                getContentResolver().update(uri, values, null, null);
            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.contactstest.provider/book" + newId);
                getContentResolver().delete(uri, null, null);
            }
        });


//        原本申请权限和从系统通讯录中读取联系人的部分
//        contactsView = findViewById(R.id.contacts_view);
//
//
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
//        } else {
//            readContacts();
//        }
//
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
//        contactsView.setAdapter(adapter);

    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(displayName + ": " + number);
                Log.d(TAG, "readContacts: " + displayName + ": " + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(MainActivity.this, "Your permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
