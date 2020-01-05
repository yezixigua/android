package com.zz.notificationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
  *
  * @package:        com.zz.notificationtest
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/4 0004
  * @updateUser:     更新者：
  * @updateDate:     2020/1/4 0004
  * @updateRemark:   更新说明：
  * @version:        1.0
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendNotice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.send_notice:
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(this);
                builder.setSmallIcon(android.R.drawable.stat_sys_warning);
                builder.setTicker("显示第一个通知");
                builder.setWhen(System.currentTimeMillis());
                builder.setContentTitle("title");
                builder.setContentText("describe");
                Notification notification = builder.build();
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
