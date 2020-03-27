package com.zz.myadsplayer;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zz.myadsplayer.activity.MediaPlayerTest;
import com.zz.myadsplayer.activity.NetworkState;
import com.zz.myadsplayer.broadcast.MyService;
import com.zz.myadsplayer.contentprovider.ReadContacts;
import com.zz.myadsplayer.contentprovider.TestMyProvider;
import com.zz.myadsplayer.mytest.FragmentTest;
import com.zz.myadsplayer.mytest.MyTest;
import com.zz.myadsplayer.request.RequestServer;
import com.zz.myadsplayer.service.TimeService;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
  *
  * @package:        com.zz.myadsplayer
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/16 0016
  * @updateUser:     更新者：
  * @updateDate:     2020/1/16 0016
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class MainActivity extends AppCompatActivity {

    private BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

    private Button videoButton;
    private Button imageButton;
    private Button downloadButton;
    private Button netButton;
    private Button myTestButton;
    private Button myMediaPlayer;
    private Button startFragment;
    private Button startService;
    private Button startBroadcastService;
    private Button sendBroadcast;

    private Button readContacts;
    private Button provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoButton = findViewById(R.id.video_player);
        imageButton = findViewById(R.id.image_player);
        downloadButton = findViewById(R.id.download);
        netButton = findViewById(R.id.net);
        myTestButton = findViewById(R.id.test);
        myMediaPlayer = findViewById(R.id.media_player);
        startFragment = findViewById(R.id.start_fragment);
        startService = findViewById(R.id.service);

        startBroadcastService = findViewById(R.id.start_broadcast_receive_service);
        sendBroadcast = findViewById(R.id.send_broadcast);

        readContacts = findViewById(R.id.read_contacts);
        provider = findViewById(R.id.content_provider);

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Video.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Image.class);
                startActivity(intent);
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestServer requestServer = new RequestServer(queue);
                requestServer.getAdsUrl(RequestServer.AdsType.VIDEO);

            }
        });

        netButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NetworkState.class);
                startActivity(intent);
            }
        });

        myMediaPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MediaPlayerTest.class);
                startActivity(intent);
            }
        });

        myTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyTest.class);
                startActivity(intent);
            }
        });

        startFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentTest.class);
                startActivity(intent);
            }
        });

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent(MainActivity.this, TimeService.class);
                startService(sIntent);
            }
        });

        startBroadcastService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent("com.zz.myadsplayer.broadcast");
                intent.putExtra("zz","zz");
                sendBroadcast(intent);
            }
        });

        readContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ReadContacts.class);
                startActivity(intent);
            }
        });

        provider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TestMyProvider.class);
                startActivity(intent);
            }
        });


    }


}
