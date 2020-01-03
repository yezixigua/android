package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
  *
  * @package:        com.example.databasetest
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/1 0001
  * @updateUser:     更新者：
  * @updateDate:     2020/1/1 0001
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    private static final String TAG = "zz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);
            }
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 15.96);
                db.update("Book", values, "name = ?", new String[] {"The Da Vinci Code"});
            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[] {"400"});
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null , null, null, null, null);

                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        Double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d(TAG, "name: " + name);
                        Log.d(TAG, "author: " + author);
                        Log.d(TAG, "pages: " + pages);
                        Log.d(TAG, "price: " + price);
                    } while (cursor.moveToNext());

                }
                cursor.close();
            }

        });

        Button replaceData = findViewById(R.id.replace_data);
        replaceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    db.delete("Book", null, null);
                    if (false) {
                        throw new NullPointerException();
                    }
                    ContentValues values = new ContentValues();
                    values.put("name", "Game of Thrones");
                    values.put("author", "George Martin");
                    values.put("pages", 720);
                    values.put("price", 20.25);
                    db.insert("Book", null, values);
                    db.setTransactionSuccessful();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });
    }
}
