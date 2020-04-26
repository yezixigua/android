package com.zz.filereader.openningadvertisement;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.zz.libcommon.net.okhttp.CommonOkHttpClient;
import com.zz.libcommon.net.okhttp.listener.DisposeDataHandle;
import com.zz.libcommon.net.okhttp.listener.DisposeDataListener;
import com.zz.libcommon.net.okhttp.listener.DisposeDownloadListener;
import com.zz.libcommon.net.okhttp.request.CommonRequest;
import com.zz.libcommon.net.okhttp.response.ResponseContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.openningadvertisement
 * @ClassName: AdvertismentPlay
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/23 0023 18:37
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/23 0023 18:37
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AdvertisementPlay {

    private static final String TAG = "AdvertisementPlay";
    static final String baseUrl = "http://www.yezixigua.cn:3000";
    static final String configUrl = baseUrl + "/ads";
    static final String imgCache = "imgCache";
    File imgCacheFolder;

    private Context mContext;


    public AdvertisementPlay(Context context) {
        mContext = context;
        imgCacheFolder = new File(mContext.getFilesDir().getPath() + File.separator + imgCache);

        if(!imgCacheFolder.exists()) {
            Log.d(TAG, "cache folder not exist ");
            imgCacheFolder.mkdir();
        }
    }


    public void getAdsConfig() {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(configUrl, null), new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.d(TAG, "getAdsConfig Success");
                ResponseContent responseContent = (ResponseContent) responseObj;
                parseConfig(responseContent.body);
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.d(TAG, "onFailure: getAdsConfig");
            }
        }));
    }

    public void parseConfig(String config) {
        try {
            JSONObject jsonObject = new JSONObject(config);
            Iterator iterator = jsonObject.keys();
            while(iterator.hasNext()){

                String url = (String) iterator.next();
                String md5 = jsonObject.getString(url);
                Log.d(TAG, "url:" + url + "\n" + "md5:" + md5 + "\n");

                if (hasCache(md5) == false) {
                    downloadAds(md5, url);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Boolean hasCache(String md5) {
        String[] fileNames = imgCacheFolder.list();

        if (fileNames == null) {
            Log.d(TAG, "hasCache: false");
            return false;
        }

        List<String> fileNamesList= Arrays.asList(fileNames);

        if (fileNamesList.contains(md5)) {
            Log.d(TAG, "hasCache: 有这个缓存");
            return true;
        }
        Log.d(TAG, "hasCache: false");
        return false;
    }

    public void downloadAds(String name, String url) {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(url, null), new DisposeDataHandle(new DisposeDownloadListener() {
            @Override
            public void onProgress(int progrss) {
                Log.d(TAG, "onProgress: " + progrss);
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
        }, imgCacheFolder.getPath() + File.separator + name));
    }

    public void writeCache(String name, String content) {

        FileOutputStream stream = null;
        if(!imgCacheFolder.exists()) {
            Log.d(TAG, "cache folder not exist ");
            imgCacheFolder.mkdir();
        }
        Log.d(TAG, "cache folder exist ");
        File imgCacheFile = new File(imgCacheFolder.getPath() + File.separator + name);

        try {
            imgCacheFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            stream = new FileOutputStream(imgCacheFile);
            stream.write(content.getBytes());
            stream.flush();
            Log.d(TAG, imgCacheFile.getName() + "writeCache");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Uri getRandomImg() {

        File[] files = imgCacheFolder.listFiles();
        Random random = new Random();

        if(files == null || files.length == 0) {
            return null;
        }

        Uri imgUri = Uri.fromFile(files[random.nextInt(files.length)]);
        Log.d(TAG, "getRandomImg: " + imgUri);
        return imgUri;
    }
}
