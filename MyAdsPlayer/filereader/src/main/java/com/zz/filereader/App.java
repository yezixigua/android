package com.zz.filereader;

import android.app.Application;
import android.content.Context;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader
 * @ClassName: App
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/3/22 0022 14:53
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/3/22 0022 14:53
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
