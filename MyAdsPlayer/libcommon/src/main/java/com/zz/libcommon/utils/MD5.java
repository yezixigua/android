package com.zz.libcommon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.utils
 * @ClassName: MD5
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/19 0019 12:07
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/19 0019 12:07
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MD5 {

    public static String getFileMD5(File file) {
        String s = null;

        if (!file.exists()) {
            return null;
        }

        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, len);
            }
            in.close();

            BigInteger bigInt = new BigInteger(1, md.digest());
            s = String.format("%032x", bigInt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return s;
    }
}
