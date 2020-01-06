package com.zz.androidthreadtest;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @ProjectName: AndroidThreadTest
 * @Package: com.zz.androidthreadtest
 * @ClassName: CustomApplication
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/6 0006 10:23
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/6 0006 10:23
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class CustomApplication extends Application {
    private static final String VALUE = "Harvey";

    private static RefWatcher sRefWatcher;

    private static final String TAG = "zz";

    private String value;

    @Override
    public void onCreate()
    {
        super.onCreate();
        // 初始化全局变量
        setValue(VALUE);
        Log.d(TAG, "onCreate: app");

        if (LeakCanary.isInAnalyzerProcess(this)) {
            Log.d(TAG, "onCreate: LeakCanary install fail");
            return;
        }
        sRefWatcher = LeakCanary.install(this);
        Log.d(TAG, "onCreate: LeakCanary install succeed");
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }
}
