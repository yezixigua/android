package com.zz.myadsplayer.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * @ProjectName: DatabaseTest
 * @Package: com.example.contactstest
 * @ClassName: MyDatabaseHelper
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/1 0001 11:16
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/1 0001 11:16
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "zz";

    public static final String CREATE_BOOK = "create table book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Log.d(TAG, "onCreate: database create suc");
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();

        ContentValues values = new ContentValues();
        values.put("name", "zz");
        values.put("author", "zz");
        values.put("pages", 22);
        values.put("price", 22.22);
        db.insert("Book", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
        Toast.makeText(mContext, "Upgrade succeeded", Toast.LENGTH_SHORT).show();
    }
}
