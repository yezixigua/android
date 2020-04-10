package com.zz.deviceinfo.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.deviceinfo.utils
 * @ClassName: NetUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/10 0010 21:19
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/10 0010 21:19
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public enum NetUtils {
    INSTANCE;

    /**
     * Android 6.0 之前（不包括6.0）获取mac地址
     * 必须的权限 <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
     * @param context * @return
     */
    public static String getMacBefore23(Context context) {
        String mac = "";
        if (context == null) {
            return mac;
        }
        WifiManager wifi = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = null;
        try {
            info = wifi.getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (info == null) {
            return null;
        }
        mac = info.getMacAddress();
        if (!TextUtils.isEmpty(mac)) {
            mac = mac.toUpperCase(Locale.ENGLISH);
        }
        return mac;
    }

    /**
     * Android 6.0-Android 7.0 获取mac地址
     */
    public static String getMac23To23(Context context) {
        String macSerial = null;
        String str = "";

        try {
            Process pp = Runtime.getRuntime().exec("cat/sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            while (null != str) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();//去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }

        return macSerial;
    }

    /**
     * Android 7.0之后获取Mac地址
     * 遍历循环所有的网络接口，找到接口是 wlan0
     * 必须的权限 <uses-permission android:name="android.permission.INTERNET"></uses-permission>
     * @return
     */
//    public static String getMacFromHardware() {
//        try {
//            Enumeration<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface nif : all) {
//                if (!nif.name.equals("wlan0", ignoreCase = true))
//                    continue;
//                Byte macBytes[] = nif.getHardwareAddress();
//                if (macBytes == null) return "";
//                StringBuilder res1 = new StringBuilder();
//                for (Byte b : macBytes) {
//                    res1.append(String.format("%02X:", b));
//                }
//                if (!TextUtils.isEmpty(res1)) {
//                    res1.deleteCharAt(res1.length - 1);
//                }
//                return res1.toString();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return ""
//    }

    /**
     * 获取mac地址（适配所有Android版本）
     * @return
     */
    public static getMac(context: Context): String? {
        String mac: String? = ""
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mac = getMacDefault(context)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mac = getMacAddress()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mac = getMacFromHardware()
        }
        return mac
    }
}
