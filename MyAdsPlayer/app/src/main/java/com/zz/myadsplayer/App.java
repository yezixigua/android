package com.zz.myadsplayer;

import android.app.Application;
import android.content.Context;

import leakcanary.LeakCanary;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer
 * @ClassName: App
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/16 0016 11:02
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/16 0016 11:02
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();


    }

    public static Context getContext() {
        return context;
    }


}
