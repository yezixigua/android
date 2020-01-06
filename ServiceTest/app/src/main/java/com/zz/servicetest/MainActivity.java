package com.zz.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
  *
  * @package:        com.zz.servicetest
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/6 0006
  * @updateUser:     更新者：
  * @updateDate:     2020/1/6 0006
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "zz";

    private Button startService;
    private Button stopService;

    private Button bindService;
    private Button unbindService;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = findViewById(R.id.start_service);
        stopService = findViewById(R.id.stop_service);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);

        bindService = findViewById(R.id.bind_service);
        unbindService = findViewById(R.id.unbind_service);

        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Log.d(TAG, "onClick: start");
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stop_service:
                Log.d(TAG, "onClick: stop");
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            default:
                break;

        }
    }


}
