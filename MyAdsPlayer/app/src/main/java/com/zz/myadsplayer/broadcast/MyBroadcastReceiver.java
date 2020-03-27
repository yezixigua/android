package com.zz.myadsplayer.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.broadcast
 * @ClassName: MyBroadcastReceiver
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/3/12 0012 14:34
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/3/12 0012 14:34
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "zz";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive broadcast: ");
    }
}
