package com.zz.myadsplayer.request;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.request
 * @ClassName: AdsReturnJson
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/19 0019 15:38
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/19 0019 15:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AdsReturnJson {

    public final String IMAGE_TYPE = "pic";
    public final String VIDEO_TYPE = "video";

    public String type;
    public String name;
    public String md5;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileMd5() {
        return md5;
    }

    public void setFileMd5(String fileMd5) {
        this.md5 = fileMd5;
    }



}
