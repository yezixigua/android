package com.example.contactstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
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

    ListView contactsView;
    ArrayAdapter<String> adapter;
    List<String> contactList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsView = findViewById(R.id.contacts_view);


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
        contactsView.setAdapter(adapter);

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
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
