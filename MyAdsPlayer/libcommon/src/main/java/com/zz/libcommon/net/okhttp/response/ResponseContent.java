package com.zz.libcommon.net.okhttp.response;

import androidx.annotation.NonNull;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.libcommon.net.okhttp.response
 * @ClassName: ResponseContent
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/16 0016 9:57
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/16 0016 9:57
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ResponseContent {

    public String body;
    public String header;
    public String protocol;
    public int code;
    public String message;
    public  String handshake;
    public  String networkResponse;
    public  String cacheResponse;
    public  String priorResponse;
    public long sentRequestAtMillis;
    public long receivedResponseAtMillis;

    public String getAllPropertyString() {
        StringBuilder sb = new StringBuilder();
        sb.append("code:\n" + code + "\n");
        sb.append("protocol:\n" + protocol + "\n");
        sb.append("message:\n" + message + "\n");
        sb.append("handshake:\n" + handshake + "\n");
        sb.append("networkResponse:\n" + networkResponse + "\n");
        sb.append("cacheResponse:\n" + cacheResponse + "\n");
        sb.append("priorResponse:\n" + priorResponse + "\n");
        sb.append("sentRequestAtMillis:\n" + sentRequestAtMillis + "\n");
        sb.append("receivedResponseAtMillis:\n" + receivedResponseAtMillis + "\n");
        sb.append("header:\n" + header + "\n");
        sb.append("body:\n" + body + "\n");

        return sb.toString();
    }

    @NonNull
    @Override
    public String toString() {
        return getAllPropertyString();
    }
}
