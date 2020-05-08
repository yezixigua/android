package com.zz.filereader.update;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zz.libcommon.net.okhttp.CommonOkHttpClient;
import com.zz.libcommon.net.okhttp.listener.DisposeDataHandle;
import com.zz.libcommon.net.okhttp.listener.DisposeDataListener;
import com.zz.libcommon.net.okhttp.request.CommonRequest;
import com.zz.libcommon.net.okhttp.response.ResponseContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.update
 * @ClassName: Updater
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/5/8 0008 16:36
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/8 0008 16:36
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Updater {

    private static final String TAG = "Updater";

    private Context mContext;
//    public static final String CHECK_UPDATE_URL = "http://www.yezixigua.cn:3000/apk_version";
//    public static final String DOWNLOAD_UURL = "http://www.yezixigua.cn:3000/apk";

    public static final String CHECK_UPDATE_URL = "http://192.168.1.107/apk_version";
    public static final String DOWNLOAD_UURL = "http://192.168.1.107/apk";
    private static final String APK_CACHE = "apkCache";
    private static final String APK_INFO = "apkInfo";
    private File apkCacheFolder;

    public Updater(Context context) {
        this.mContext = context;

        apkCacheFolder = new File(mContext.getFilesDir().getPath() + File.separator + APK_CACHE);

        if(!apkCacheFolder.exists()) {
            Log.d(TAG, "apk folder not exist ");
            apkCacheFolder.mkdir();
        }
    }

    public void checkLatestVersion() {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(CHECK_UPDATE_URL, null), new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d(TAG, "getApkVersion Success");
                ResponseContent responseContent = (ResponseContent) responseObj;
                ApkInfo apkInfo = parseConfig(responseContent.body);
                Log.d(TAG, "apkInfo: \n" + apkInfo.toString());
                serialToFile(apkInfo);
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.d(TAG, "onFailure: getAdsConfig");
            }
        }));
    }

    public ApkInfo parseConfig(String version) {

        Gson gson = new Gson();
        ApkInfo apkInfo = gson.fromJson(version, ApkInfo.class);
        return apkInfo;

    }

    public File serialToFile(ApkInfo apkInfo) {
        File apkInfoFile = new File(mContext.getFilesDir().getPath() + File.separator + APK_INFO);
        // 文件若不存在，则新建一个
        if (apkInfoFile.exists()) {
            ApkInfo existApkInfo = readInfoFromFile(apkInfoFile);

            // 若文件存在，则比较版本号
            // 旧版本删除，重新写入新版本信息
            if (isInfoUpdated(apkInfo, existApkInfo)) {
                apkInfoFile.delete();
                try {
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(apkInfoFile));
                    os.writeObject(apkInfo);
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            try {
                apkInfoFile.createNewFile();
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(apkInfoFile));
                os.writeObject(apkInfo);
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return apkInfoFile;
    }

    public boolean isInfoUpdated(ApkInfo newInfo, ApkInfo oldInfo) {

        List<String> newVersion = Arrays.asList(newInfo.getVersion().split("."));
        List<String> oldVersion = Arrays.asList(newInfo.getVersion().split("."));

        if (newVersion.size() != ApkInfo.VERSION_NUMBER || oldVersion.size() != ApkInfo.VERSION_NUMBER) {
            // 在长度不对时不更新
            return false;
        }

        for (int i = 0; i < ApkInfo.VERSION_NUMBER; i++) {
            if (Integer.parseInt(newVersion.get(i)) > Integer.parseInt(oldVersion.get(i))) {
                return true;
            }
        }
        return false;
    }

    public ApkInfo readInfoFromFile(File apkInfoFile) {
        ApkInfo apkInfo = null;

        if (apkInfoFile.exists()) {
            ObjectInputStream is = null;
            try {
                is = new ObjectInputStream(new FileInputStream(apkInfoFile));
                apkInfo = (ApkInfo)(is.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return apkInfo;
        }

        return null;

    }
}
