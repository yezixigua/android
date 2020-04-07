package com.zz.filereader.utils;

import android.os.Environment;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader
 * @ClassName: Utlis
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/3/23 0023 10:36
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/3/23 0023 10:36
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Utils {

    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "不适用";
        }
        return sdpath;

    }

    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
}
