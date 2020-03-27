package com.zz.myadsplayer.contentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
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

import com.zz.myadsplayer.R;

import java.util.ArrayList;
import java.util.List;

/**     
  *
  * @package:        com.zz.myadsplayer.contentprovider
  * @className:      TestMyProvider
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/3/16 0016
  * @updateUser:     更新者：
  * @updateDate:     2020/3/16 0016
  * @updateRemark:   更新说明：
  * @version:        1.0
 */

public class TestMyProvider extends AppCompatActivity {

    private static final String TAG = "zz";

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_my_provider);

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.zz.myadsplayer.contentprovider.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
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

                Uri uri = Uri.parse("content://com.zz.myadsplayer.contentprovider.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);

                if (cursor == null) {
                    Log.d(TAG, "query cursor is mull");
                }

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
                Uri uri = Uri.parse("content://com.zz.myadsplayer.contentprovider.provider/book" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of swords");
                values.put("author", "George Martin");
                values.put("pages", 1216);
                values.put("price", 24.05);
                getContentResolver().update(uri, values, null, null);
            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.zz.myadsplayer.contentprovider.provider/book" + newId);
                getContentResolver().delete(uri, null, null);
            }
        });


    }

}
