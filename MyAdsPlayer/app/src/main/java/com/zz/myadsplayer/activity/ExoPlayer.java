package com.zz.myadsplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zz.myadsplayer.R;

/**
  *
  * @package:        com.zz.myadsplayer.activity
  * @className:      ExoPlayer
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/2/28 0028
  * @updateUser:     更新者：
  * @updateDate:     2020/2/28 0028
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class ExoPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exoplayer);

//        SimpleExoPlayer player = new SimpleExoPlayer.Builder(context).build();
    }
}
