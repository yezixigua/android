package com.zz.libcommon.utils;

import android.util.Log;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.deviceinfo.utils
 * @ClassName: DeviceInfoUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/11 0011 13:38
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/11 0011 13:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public enum DeviceInfoUtils {
    INSTANCE;

    private static final String TAG = "DeviceInfoUtils";

    /**
     * 获取厂商名
     * **/
    public String getDeviceManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取产品名
     * **/
    public String getDeviceProduct() {
        return android.os.Build.PRODUCT;
    }

    /**
     * 获取手机品牌
     */
    public String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机主板名
     */
    public String getDeviceBoard() {
        return android.os.Build.BOARD;
    }

    /**
     * 设备名
     * **/
    public String getDeviceDevice() {
        return android.os.Build.DEVICE;
    }

    /**
     *
     *
     * fingerprit 信息
     * **/
    public String getDeviceFubgerprint() {
        return android.os.Build.FINGERPRINT;
    }

    public void infoCheck() {

        Log.d(TAG, "getDeviceManufacturer: " + getDeviceManufacturer());
        Log.d(TAG, "getDeviceProduct: " + getDeviceProduct());
        Log.d(TAG, "getDeviceBrand: " + getDeviceBrand());
        Log.d(TAG, "getDeviceModel: " + getDeviceModel());
        Log.d(TAG, "getDeviceBoard: " + getDeviceBoard());
        Log.d(TAG, "getDeviceDevice: " + getDeviceDevice());
        Log.d(TAG, "getDeviceFubgerprint: " + getDeviceFubgerprint());
    }
}
