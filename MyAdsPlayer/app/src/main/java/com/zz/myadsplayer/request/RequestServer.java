package com.zz.myadsplayer.request;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.zz.myadsplayer.App;
import com.zz.myadsplayer.utils.MD5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.request
 * @ClassName: RequestServer
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/19 0019 15:23
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/19 0019 15:23
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RequestServer {

    private static final String TAG = "zz";

    public final String SERVER_URL = "http://192.168.1.107:8080";
    public final String IMAGE_SUB_ADDRESS = "/pic";
    public final String VIDEO_SUB_ADDRESS = "/video";
    public final String DOWNLOAD_SUB_ADDRESS = "/download";

    private final String DOWNLOAD_CACHE = "DOWNLOAD_CACHE";

    public enum AdsType {
        VIDEO, IMAGE
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            AdsReturnJson adsReturnJson = (AdsReturnJson)msg.obj;
            String uri = "";

            if(adsReturnJson.getType().equals(adsReturnJson.IMAGE_TYPE)) {
                uri += IMAGE_SUB_ADDRESS;
            } else if (adsReturnJson.getType().equals(adsReturnJson.VIDEO_TYPE)) {
                uri += VIDEO_SUB_ADDRESS;
            }

            uri += "/" + adsReturnJson.getName();
            Log.d(TAG, "uri: " + uri);
            downloadAds(uri);

        }
    };

    public void getAdsUrl(AdsType adsType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建OkHttpClient对象
                    OkHttpClient client = new OkHttpClient();

                    String requestUrl = SERVER_URL;

                    if(adsType.equals(AdsType.IMAGE)) {
                        requestUrl += IMAGE_SUB_ADDRESS;
                    } else if (adsType.equals(AdsType.VIDEO)) {
                        requestUrl += VIDEO_SUB_ADDRESS;
                    }

                    //创建Request
                    Request request = new Request.Builder()
                            .url(requestUrl)//访问连接
                            .get()
                            .build();
                    //创建Call对象
                    Call call = client.newCall(request);
                    //通过execute()方法获得请求响应的Response对象
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        //处理网络请求的响应，处理UI需要在UI线程中处理
//                                InputStream is = response.getInputStream();//获取输入流
                        String content = response.body().string();//获取输入流

                        Gson gson = new Gson();
                        AdsReturnJson adsReturnJson = gson.fromJson(content, AdsReturnJson.class);

                        String type = adsReturnJson.getType();
                        String name = adsReturnJson.getName();

                        Log.d(TAG, "Type: " + type);
                        Log.d(TAG, "Name: " + name);

                        Message message = new Message();
                        message.obj = adsReturnJson;
                        handler.sendMessage(message);

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void downloadAds(String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //创建OkHttpClient对象
                    OkHttpClient client = new OkHttpClient();

                    //创建Request
                    Log.d(TAG, "download completed url: " + SERVER_URL + DOWNLOAD_SUB_ADDRESS + name);
                    Request request = new Request.Builder()
                            .url(SERVER_URL + DOWNLOAD_SUB_ADDRESS + name)//访问连接
                            .get()
                            .build();
                    //创建Call对象
                    Call call = client.newCall(request);
                    //通过execute()方法获得请求响应的Response对象
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        //处理网络请求的响应，处理UI需要在UI线程中处理
//                                InputStream is = response.getInputStream();//获取输入流
                        InputStream is = response.body().byteStream();//获取输入流
                        FileOutputStream fileOutputStream = null;//文件输出流
                        if (is != null) {
                            fileOutputStream = App.getContext().openFileOutput(DOWNLOAD_CACHE, Context.MODE_PRIVATE);
                            byte[] buf = new byte[1024];
                            int ch;
                            while ((ch = is.read(buf)) != -1) {
                                fileOutputStream.write(buf, 0, ch);//将获取到的流写入文件中
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }


                    }

                    String renamePath = App.getContext().getFilesDir().getPath() + File.separator + DOWNLOAD_CACHE;
                    File renameFile = new File(renamePath);
                    String fileMD5 = MD5.getFileMD5(renameFile);
                    renameFile.renameTo(new File(App.getContext().getFilesDir().getPath() + File.separator + fileMD5));

                    Log.d(TAG, "download " + fileMD5);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
