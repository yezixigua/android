package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
  *
  * @package:        com.example.broadcastbestpractice
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2019/12/30 0030
  * @updateUser:     更新者：
  * @updateDate:     2019/12/30 0030
  * @updateRemark:   更新说明：
  * @version:        1.0
 */

public class MainActivity extends BasicActivity {

    private static final String TAG = "zz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button forceOffline = findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: click button");
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE");
//                intent.setComponent(new ComponentName("com.example.broadcastbestpractic", "com.example.broadcastbestpractic.ForceOfflineReceiver"));
                sendBroadcast(intent);
                Log.d(TAG, "onClick: send broadcast");
            }
        });
    }
}
