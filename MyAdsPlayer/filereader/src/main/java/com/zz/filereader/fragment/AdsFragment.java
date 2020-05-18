package com.zz.filereader.fragment;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zz.filereader.R;
import com.zz.filereader.activity.MainActivity;
import com.zz.filereader.openningadvertisement.AdvertisementPlay;

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



public class AdsFragment extends Fragment {

    ImageView imageView;
    TextView timeText;

    ScheduledExecutorService mService;

    private static final String TAG = "AdsFragment";
    int timeRemain = 4;
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            timeRemain -= 1;
            timeText.setText(timeRemain + "s");
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_ads, container, false);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView = view.findViewById(R.id.ads_image);
        timeText = view.findViewById(R.id.remain_time);
        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.shutdown();
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onCreate: 未申请到权限，关闭");
                    getActivity().finish();
                } else {
                    Log.d(TAG, "onCreate: 已申请到权限");
                    ((MainActivity)getActivity()).dismissAds();
                }
            }
        });

        AdvertisementPlay advertisementPlay = new AdvertisementPlay(getActivity());
        advertisementPlay.getAdsConfig();

        Uri imgUri = advertisementPlay.getRandomImg();
        if (imgUri == null) {
//            imageView.setImageResource(R.drawable.default_ads);
            Glide.with(this).load(R.drawable.default_ads).into(imageView);
        } else {
            imageView.setImageURI(imgUri);
            Glide.with(this).load(imgUri).into(imageView);
        }

        mService = new ScheduledThreadPoolExecutor(1);

        mService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {

                if (timeRemain > 0) {
                    Message message = new Message();
                    message.what = timeRemain;
                    handler.sendMessage(message);
                } else {
                    mService.shutdown();
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "onCreate: 未申请到权限，关闭");
                        getActivity().finish();
                    } else {
                        Log.d(TAG, "onCreate: 已申请到权限");
                        ((MainActivity)getActivity()).dismissAds();
                    }
                }



            }

        }, 0, 1, TimeUnit.SECONDS);

        return view;
    }
}
