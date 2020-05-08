package com.zz.filereader.update;

import java.io.Serializable;

import androidx.annotation.NonNull;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.update
 * @ClassName: ApkInfo
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/5/8 0008 16:56
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/8 0008 16:56
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ApkInfo implements Serializable {

    public static final int VERSION_NUMBER = 4;

    public String title;
    public String content;
    public String url;
    public String md5;
    public String version;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder infoBuilder = new StringBuilder();
        infoBuilder.append("title: " + title + "\n");
        infoBuilder.append("content: " + content + "\n");
        infoBuilder.append("url: " + url + "\n");
        infoBuilder.append("md5: " + md5 + "\n");
        infoBuilder.append("version: " + version + "\n");
        return infoBuilder.toString();
    }
}
