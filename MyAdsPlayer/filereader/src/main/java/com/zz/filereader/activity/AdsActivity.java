package com.zz.filereader.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.zz.filereader.R;
import com.zz.filereader.openningadvertisement.AdvertisementPlay;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**     
  *
  * @package:        com.zz.filereader.activity
  * @className:      AdsActivity
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/4/23 0023
  * @updateUser:     更新者：
  * @updateDate:     2020/4/23 0023
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class AdsActivity extends AppCompatActivity {

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        imageView = findViewById(R.id.ads_image);

        AdvertisementPlay advertisementPlay = new AdvertisementPlay(this);
        advertisementPlay.getAdsConfig();

        Bitmap imgBitmap = advertisementPlay.getRandomImg();
        if (imgBitmap == null) {
            imageView.setImageResource(R.drawable.default_ads);
        } else {
            imageView.setImageBitmap(imgBitmap);
        }

        ScheduledExecutorService mService = new ScheduledThreadPoolExecutor(1);

        mService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                mService.shutdown();
                finish();
            }

        }, 5, 3, TimeUnit.SECONDS);

    }
}
