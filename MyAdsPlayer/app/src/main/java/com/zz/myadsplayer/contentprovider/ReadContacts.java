package com.zz.myadsplayer.contentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zz.myadsplayer.MainActivity;
import com.zz.myadsplayer.R;

import java.util.ArrayList;
import java.util.List;

/**     
  *
  * @package:        com.zz.myadsplayer.contentprovider
  * @className:      ReadContacts
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/3/16 0016
  * @updateUser:     更新者：
  * @updateDate:     2020/3/16 0016
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class ReadContacts extends AppCompatActivity {

    private static final String TAG = "zz";

    ListView listView;

    ArrayAdapter<String> adapter;
    List<String> contactsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contacts);

        listView = findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        listView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: 未申请到权限，尝试申请权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            Log.d(TAG, "onCreate: 已申请到权限");
            readContacts();
        }


    }

    private void readContacts() {
        Cursor cursor = null;

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String displayNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.d(TAG, "readContacts: " + displayName + "\n" + displayNumber);
            contactsList.add(displayName + "\n" + displayNumber);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限成功");
                    readContacts();
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限失败");
                    Toast.makeText(this, "Your permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
