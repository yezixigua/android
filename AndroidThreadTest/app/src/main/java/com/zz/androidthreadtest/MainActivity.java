package com.zz.androidthreadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.leakcanary.LeakCanary;

/**
  *
  * @package:        com.zz.androidthreadtest
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/5 0005
  * @updateUser:     更新者：
  * @updateDate:     2020/1/5 0005
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class MainActivity extends Activity implements View.OnClickListener {

    private CustomApplication app;
    public static final int UPDATE_TEXT = 1;

    private TextView text;
    private Button changeText;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomApplication.getRefWatcher().watch(this);
        Singleton singleton = Singleton.newInstance(this);

        setContentView(R.layout.activity_main);

        app = (CustomApplication) getApplication();



        text = findViewById(R.id.text);
        changeText = findViewById(R.id.change_text);
        changeText.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message); // 将Message对象发送出去
                    }
                }).start();
                break;
            default:
                break;
        }

    }
}
