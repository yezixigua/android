package com.zz.adsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baidu.adsdk.controller.IController;
import com.baidu.adsdk.interfaces.IAdListener;
import com.baidu.adsdk.model.RequestInfo;
import com.baidu.adsdk.view.BDVideoView;

public class MainActivity extends AppCompatActivity  implements IAdListener {

    private IController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BDVideoView bdVideoView = findViewById(R.id.img);
        controller = bdVideoView.getController();
        controller.addAdListener(this);


    }

    @Override
    public void onAdClick() {

    }

    @Override
    public void onAdDismissed() {

    }

    @Override
    public void onAdFailed(RequestInfo requestInfo, int i, String s) {

    }

    @Override
    public void onAdFinish(RequestInfo requestInfo) {

    }

    @Override
    public void onAdPrepared(RequestInfo requestInfo) {

    }

    @Override
    public void onAdStart() {

    }
}
