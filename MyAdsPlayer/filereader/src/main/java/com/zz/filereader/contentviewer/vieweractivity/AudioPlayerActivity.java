package com.zz.filereader.contentviewer.vieweractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.zz.filereader.R;

import java.io.File;
import java.io.IOException;

/**
  *
  * @package:        com.zz.filereader.contentviewer.vieweractivity
  * @className:      AudioPlayerActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/4/26 0026
  * @updateUser:     更新者：
  * @updateDate:     2020/4/26 0026
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class AudioPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AudioPlayerActivity";

    private Button play;
    private Button pause;
    private Button stop;

    private MediaPlayer mMediaPlayer;
    private SurfaceView mAudioPlaySurfaceView;
    private SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        play = findViewById(R.id.audio_play);
        pause = findViewById(R.id.audio_pause);
        stop = findViewById(R.id.audio_stop);
        mAudioPlaySurfaceView = findViewById(R.id.audio_player);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        File file = (File)(getIntent().getSerializableExtra("file"));

        initVideoPlayer(file);
    }

    private void initVideoPlayer(File audioFile) {

        mSurfaceHolder = mAudioPlaySurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                if (mMediaPlayer == null) {
                    mMediaPlayer = new MediaPlayer();
                }

                try {
                    mMediaPlayer.setDataSource(audioFile.getPath());
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
            case R.id.audio_play:
                if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();
                }
                break;
            case R.id.audio_pause:
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                } else {
                    mMediaPlayer.start();
                }
                break;
            case R.id.audio_stop:
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
                break;
            default:
                break;

        }
    }

    /**
     * 此Activity销毁后，一定要
     *              mediaPlayer.release();
     *              mediaPlayer = null;
     * 因为 MediaPlayer 是操作硬件在播放，所以一定要释放资源
     */
    @Override
    protected void onDestroy() {
        mMediaPlayer.release();
        mMediaPlayer = null;
        System.gc();
        super.onDestroy();
    }

}
