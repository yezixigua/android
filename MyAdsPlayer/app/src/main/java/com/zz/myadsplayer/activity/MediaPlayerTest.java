package com.zz.myadsplayer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.Toast;

import com.zz.myadsplayer.R;
import com.zz.myadsplayer.Video;

import java.io.File;

public class MediaPlayerTest extends AppCompatActivity {

    private static final String TAG = "zz_player";

    private SurfaceView mySurface;

    private Button startButton;
    private Button pauseButton;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_test);

        mySurface = findViewById(R.id.media_player_surface);
        startButton = findViewById(R.id.media_player_surface_start);
        pauseButton = findViewById(R.id.media_player_surface_pause);
        stopButton = findViewById(R.id.media_player_surface_stop);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: 未申请到权限，尝试申请权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Log.d(TAG, "onCreate: 已申请到权限");
            initPlayerAndStart();
        }


    }

    public void initPlayerAndStart() {
//        Uri movieUri = Uri.fromFile(new File("/sdcard/z1.mp4"));
        Uri movieUri = Uri.fromFile(new File("/sdcard/a.mp4"));
        SurfaceHolder surfaceHolder = mySurface.getHolder();
        surfaceHolder.addCallback( new SurfaceHolder.Callback() {
            MediaPlayer mediaPlayer;

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer = MediaPlayer.create(MediaPlayerTest.this, movieUri, surfaceHolder);

                if (mediaPlayer == null) {
                    Log.d(TAG, "onCreate: mediaPlayer is null");
                }

                mediaPlayer.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mediaPlayer.release();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限成功");
                    initPlayerAndStart();
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
