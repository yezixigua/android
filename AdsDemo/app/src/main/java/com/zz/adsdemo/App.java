package com.zz.adsdemo;

import android.app.Application;

import com.baidu.adsdk.AdWrapper;

/**
 * @ProjectName: AdsDemo
 * @Package: com.zz.adsdemo
 * @ClassName: App
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/15 0015 17:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/15 0015 17:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AdWrapper.INSTANCE.init(this, "123", "123", "123");
    }
}
