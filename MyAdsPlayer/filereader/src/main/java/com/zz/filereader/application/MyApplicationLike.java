package com.zz.filereader.application;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.entry.DefaultApplicationLike;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.zz.filereader.log.MyLogImp;
import com.zz.filereader.utils.ApplicationContext;
import com.zz.filereader.utils.TinkerManager;

import androidx.multidex.MultiDex;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.application
 * @ClassName: ApplicationLike
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/7 0007 15:59
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/7 0007 15:59
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@SuppressWarnings("unused")
@DefaultLifeCycle(application = ".MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class MyApplicationLike extends DefaultApplicationLike {
    private static final String TAG = "ApplicationLike";

    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                                 long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    /**
     * install multiDex before install tinker
     * so we don't need to put the tinker lib classes in the main dex
     *
     * @param base
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        ApplicationContext.application = getApplication();
        ApplicationContext.context = getApplication();
        TinkerManager.setTinkerApplicationLike(this);

//        TinkerManager.initFastCrashProtect();
//        //should set before tinker is installed
//        TinkerManager.setUpgradeRetryEnable(true);
//
//        //optional set logIml, or you can use default debug log
//        TinkerInstaller.setLogIml(new MyLogImp());

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        Log.d(TAG, "onBaseContextAttached: install tinker");
        TinkerManager.installTinker(this);
        Tinker tinker = Tinker.with(getApplication());
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }

}