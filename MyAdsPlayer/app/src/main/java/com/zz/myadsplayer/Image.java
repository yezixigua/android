package com.zz.myadsplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
  *
  * @package:        com.zz.myadsplayer
  * @className:      Image
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/16 0016
  * @updateUser:     更新者：
  * @updateDate:     2020/1/16 0016
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class Image extends AppCompatActivity {

    private static final String TAG = "zz";

    private ImageView imageView;
    private Button next;
    private int index = 1;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            changePicture();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.image_view);
        next = findViewById(R.id.next);

        final Runnable changePic = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: timer");

                Message message = new Message();
                handler.sendMessage(message);

            }
        };
        final ScheduledFuture changePicHandle =
                scheduler.scheduleAtFixedRate(changePic, 0, 5, TimeUnit.SECONDS);


        scheduler.schedule(new Runnable() {
            @Override
            public void run() { changePicHandle.cancel(true); }
        }, 60 * 60, TimeUnit.SECONDS);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePicture();
            }
        });


    }

    private void changePicture(){
        if (index == 1) {
            imageView.setImageResource(R.drawable.pic1);
            index = 2;
        } else {
            imageView.setImageResource(R.drawable.pic2);
            index = 1;
        }
    }
}
