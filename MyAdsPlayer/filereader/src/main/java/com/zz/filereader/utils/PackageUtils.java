package com.zz.filereader.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.zz.libcommon.utils.AndroidVersion;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.utils
 * @ClassName: PackageUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/5/9 0009 9:05
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/9 0009 9:05
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class PackageUtils {

    /**
     * 获取版本名称
     *
     * @param context 上下文
     *
     * @return 版本名称
     */
    public static String getVersionName(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     *
     * @return 版本号
     */
    public static long getVersionCode(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            if (Build.VERSION.SDK_INT < AndroidVersion.INSTANCE.android28) {
                return (long)(packageInfo.versionCode);
            } else {
                return packageInfo.getLongVersionCode();
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0L;

    }

    /**
     * 获取App的名称
     *
     * @param context 上下文
     *
     * @return 名称
     */
    public static String getAppName(Context context) {
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //获取应用 信息
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            //获取albelRes
            int labelRes = applicationInfo.labelRes;
            //返回App的名称
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}

