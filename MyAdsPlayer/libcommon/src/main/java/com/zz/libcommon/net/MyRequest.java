package com.zz.libcommon.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.libcommon.net
 * @ClassName: MyRequest
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/14 0014 15:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/14 0014 15:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyRequest {
    private static final String TAG = "zz";

    public final String httpHeader = "http://";
    public final String httpsHeader = "https://";



    public String gteRequestBody(String url) {
        String content = "";
        String requestUrl = url;

        if (requestUrl == null) {
            return "";
        }

        if (!requestUrl.contains(httpHeader) && !requestUrl.contains(httpsHeader)) {
            requestUrl = httpsHeader + requestUrl;
        }

        //创建OkHttpClient对象
        try {
            OkHttpClient client = new OkHttpClient();

            //创建Request
            Request request = new Request.Builder()
                                         .url(requestUrl)
                                         .get()
                                         .build();
            //创建Call对象
            Call call = client.newCall(request);
            //通过execute()方法获得请求响应的Response对象
            Response response = null;

            response = call.execute();


            if (response.isSuccessful()) {
                //获取输入流
                content = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;


    }

}
