package com.zz.myadsplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.zz.myadsplayer.request.RequestServer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
  *
  * @package:        com.zz.myadsplayer
  * @className:      Video
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/16 0016
  * @updateUser:     更新者：
  * @updateDate:     2020/1/16 0016
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class Video extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "zz";
    private Button play;
    private Button pause;
    private Button stop;

    private MediaPlayer mMediaPlayer;
    private SurfaceView mVideoPlaySurfaceView;
    private SurfaceHolder mSurfaceHolder;

    private BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        mVideoPlaySurfaceView = findViewById(R.id.video_view);



        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(Video.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: 未申请到权限，尝试申请权限");
            ActivityCompat.requestPermissions(Video.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Log.d(TAG, "onCreate: 已申请到权限");
            initVideoPath();
        }

    }

    private void initVideoPath() {

        RequestServer requestServer = new RequestServer(queue);
        requestServer.getAdsUrl(RequestServer.AdsType.VIDEO);

        mSurfaceHolder = mVideoPlaySurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            if (mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
            }

            String videoToPlay = null;
            try {
                videoToPlay = queue.poll(2, TimeUnit.SECONDS);

                if (videoToPlay == null) {
                    Log.d(TAG, "首次取到视频为空");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

                String uri = "android.resource://" + getPackageName() + "/" + R.raw.zz1;
//          videoView.setVideoPath(Uri.parse(uri).toString());
                try {
                    mMediaPlayer.setDataSource(videoToPlay);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                mMediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_
//                SCALE_TO_FIT);//缩放模式


//            mMediaPlayer.prepare();//同步准备,因为是同步在一些性能较差的设备上会导致UI卡顿
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayer.setLooping(false);//设置循环播放
//                        当准备好后不播放，注掉这里
                        mMediaPlayer.start();
                    }
                });

                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
//                        String uri = "";
//                        if (index == 0) {
//                            uri = "android.resource://" + getPackageName() + "/" + R.raw.zz2;
//                            index = 1;
//                        } else {
//                            uri = "android.resource://" + getPackageName() + "/" + R.raw.zz1;
//                            index = 0;
//                        }
//                        mMediaPlayer.reset();
//                        try {
//                            mMediaPlayer.setDataSource(Video.this, Uri.parse(uri));
//                            mMediaPlayer.prepare();//异步准备
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                        requestServer.getAdsUrl(RequestServer.AdsType.VIDEO);
                        mMediaPlayer.reset();
                        String videoToPlay = null;
                        try {
                            videoToPlay = queue.poll(2, TimeUnit.SECONDS);

                            if (videoToPlay == null) {
                                Log.d(TAG, "取到视频为空");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            mMediaPlayer.setDataSource(videoToPlay);
                            mMediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        mMediaPlayer.start();

                    }
                });

                mMediaPlayer.setDisplay(mSurfaceHolder);//给mMediaPlayer添加预览的SurfaceHolder
                mMediaPlayer.prepareAsync();//异步准备

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.e(TAG, "surfaceChanged触发: width=" + width + "height" + height);

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.play:
                if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                } else {
                    mMediaPlayer.start();
                }
                break;
            case R.id.stop:
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限成功");
                    initVideoPath();
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限失败");
                    Toast.makeText(Video.this, "Your permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
