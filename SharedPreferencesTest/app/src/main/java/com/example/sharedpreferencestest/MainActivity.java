package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
  *
  * @package:        com.example.sharedpreferencestest
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2019/12/31 0031
  * @updateUser:     更新者：
  * @updateDate:     2019/12/31 0031
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "zz";

    private Button saveData;
    private Button restoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveData = findViewById(R.id.save_data);
        restoreData = findViewById(R.id.restore_data);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.commit();
            }
        });

        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", -1);
                boolean married = pref.getBoolean("married", false);
                Log.d(TAG, "name is: " + name);
                Log.d(TAG, "age is: " + age);
                Log.d(TAG, "married is: " + married);

            }
        });


    }
}
