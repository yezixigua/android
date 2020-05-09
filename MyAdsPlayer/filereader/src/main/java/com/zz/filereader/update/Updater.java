package com.zz.filereader.update;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zz.filereader.utils.PackageUtils;
import com.zz.libcommon.net.okhttp.CommonOkHttpClient;
import com.zz.libcommon.net.okhttp.listener.DisposeDataHandle;
import com.zz.libcommon.net.okhttp.listener.DisposeDataListener;
import com.zz.libcommon.net.okhttp.listener.DisposeDownloadListener;
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
    private static final String APK = "filereader-release.apk";
    private File apkCacheFolder;
    private File apkInfoFile;
    public File apkFile;

    public Updater(Context context) {
        this.mContext = context;

        apkCacheFolder = new File(mContext.getExternalCacheDir().getPath() + File.separator + APK_CACHE);
        apkInfoFile = new File(apkCacheFolder.getPath() + File.separator + APK_INFO);
        apkFile = new File(apkCacheFolder.getPath() + File.separator + APK);

        if(!apkCacheFolder.exists()) {
            Log.d(TAG, "apk folder not exist ");
            apkCacheFolder.mkdir();
        }

        if(!apkInfoFile.exists()) {
            Log.d(TAG, "apk folder not exist ");
            try {
                apkInfoFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(!apkFile.exists()) {
            Log.d(TAG, "apk not exist ");
            try {
                apkFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkLatestVersion() {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(CHECK_UPDATE_URL, null), new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d(TAG, "getApkVersion Success");
                ResponseContent responseContent = (ResponseContent) responseObj;
                ApkInfo apkInfo = parseConfig(responseContent.body);

                ApkInfo existApkInfo = readInfoFromFile(apkInfoFile);

                if (existApkInfo != null) {
                    Log.d(TAG, "checkLatestVersion existApkInfo: " + existApkInfo.toString());
                }

                Log.d(TAG, "checkLatestVersion 读取网络的: \n" + apkInfo.toString());

                // 如果为空，即需要更新信息
                if (existApkInfo == null) {
                    Log.d(TAG, "existApkInfo为空，需要更新信息 ");
                    serialToFile(apkInfo);
                    getLatestApk(apkInfo);

                } else if (isInfoUpdated(apkInfo.version, existApkInfo.version)) {
                    // 如果不是最新版
                    // 旧版本删除，重新写入新版本信息

                    Log.d(TAG, "删除原来的apkInfoFile，重新写入");
                    apkInfoFile.delete();
                    serialToFile(apkInfo);
                    getLatestApk(apkInfo);
                }

                Log.d(TAG, "已经是最新版，不需要更新");

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

    public synchronized File serialToFile(ApkInfo apkInfo) {

        try {
            // 文件若不存在，则新建一个
            if (!apkInfoFile.exists()) {
                apkInfoFile.createNewFile();
            }

            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(apkInfoFile));
            os.writeObject(apkInfo);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apkInfoFile;
    }

    public void getLatestApk(ApkInfo apkInfo) {

        if (apkFile.exists()) {
            apkFile.delete();
        }

        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(apkInfo.url, null), new DisposeDataHandle(new DisposeDownloadListener() {
            @Override
            public void onProgress(int progress) {
                Log.d(TAG, "onProgress: " + progress);
            }

            @Override
            public void onSuccess(Object responseObj) {
                Log.d(TAG, "downloadAds Success");
                Log.d(TAG, "downloadAds path: " + ((File)responseObj).getPath());
//                ResponseContent responseContent = (ResponseContent) responseObj;

//                writeCache(name, responseContent.body);
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.d(TAG, "onFailure: downloadAds");
            }
        }, apkFile.getPath()));

    }

    /***
     *
     * @param newInfo
     * @param oldInfo
     * @return true if need update false if not
     */
    public boolean isInfoUpdated(String newInfo, String oldInfo) {

        List<String> newVersion = Arrays.asList(newInfo.split("[.]"));
        List<String> oldVersion = Arrays.asList(oldInfo.split("[.]"));

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

    /***
     *
     * @return true if is latest false if not
     */
    public boolean checkCurrentPackageLatest() {
        ApkInfo apkInfo = readInfoFromFile(apkInfoFile);

        String currentPackageVersion = PackageUtils.getVersionName(mContext);

        Log.d(TAG, "checkCurrentPackageLatest currentPackageVersion: " + currentPackageVersion);
        Log.d(TAG, "checkCurrentPackageLatest 读取的当前缓存apkInfo: \n" + apkInfo.toString());

        if (isInfoUpdated(apkInfo.version, currentPackageVersion)) {
            Log.d(TAG, "checkCurrentPackageLatest: 需要更新");
            return false;
        } else {
            Log.d(TAG, "checkCurrentPackageLatest: 已经是最新的了");
            return true;
        }


    }


}
