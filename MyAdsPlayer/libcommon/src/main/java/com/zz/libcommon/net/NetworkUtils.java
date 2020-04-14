package com.zz.libcommon.net;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.utils
 * @ClassName: NetworkUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/2/26 0026 16:09
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/2/26 0026 16:09
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public enum NetworkUtils {
    // 枚举单例
    INSTANCE;

    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_2G = 2;
    public static final int NETWORK_3G = 3;
    public static final int NETWORK_4G = 4;
    public static final int NETWORK_MOBILE = 5;
    public static final int NETWORK_UNKNOWN = 6;

    public boolean checkNetworkPermision(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return false;
        } else {
            return true;
        }
    }

}
