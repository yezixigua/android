package com.zz.deviceinfo.application;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.deviceinfo.application
 * @ClassName: App
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/10 0010 13:21
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/10 0010 13:21
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class App extends Application {
    private static Context mContext;
    private static WindowManager mWindowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取应用的Context
        mContext = getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);


    }

    public static Context getContext() {
        return mContext;
    }
}
