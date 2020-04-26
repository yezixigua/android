package com.zz.filereader.view;

import android.app.admin.SystemUpdateInfo;

import com.zz.filereader.R;
import com.zz.filereader.application.MyApplication;
import com.zz.filereader.contentviewer.FileJudge;
import com.zz.filereader.utils.ApplicationContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.view
 * @ClassName: ListItemView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/3/27 0027 16:02
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/3/27 0027 16:02
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ListItem {

    public static final String PICTURE_TYPE = "picture";
    public static final String TEXT_TYPE = "text";
    public static final String VIDEO_TYPE = "video";
    public static final String AUDIO_TYPE = "audio";
    public static final String UNKNOWN_TYPE = "unknown";
    public static final String FOLDER_TYPE = "folder";

    public static final int folderType = 0;
    public static final int fileType = 1;

    private String name;
    private String mType;
    private String createTime;
    private String size;

    public ListItem(File file) {
        this.name = file.getName();
        if (file.isDirectory()) {
            this.mType = FOLDER_TYPE;
            this.size = file.list().length + " 项";

        } else {
            this.mType = getType(this.name);
            this.size = calcSizeString(file.length());
        }
        Date date = new Date(file.lastModified());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.createTime = formatter.format(date);


    }

    private String getType(String name) {
        FileJudge fileJudge = new FileJudge(ApplicationContext.context);
        return fileJudge.checkFileType(name);
    }

    public String getName() {
        return this.name;
    }

    public int getImageId() {

        switch (mType) {
            case FOLDER_TYPE :
                return R.drawable.folder;
            case PICTURE_TYPE :
                return R.drawable.image;
            case TEXT_TYPE :
                return R.drawable.text;
            case VIDEO_TYPE :
                return R.drawable.video;
            case AUDIO_TYPE :
                return R.drawable.audio;
//            case fileType :
//                return R.drawable.page;
//            unknown type
            default:
                return R.drawable.document_file;
        }
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getSize() {
        return size;
    }

    public String calcSizeString (long size) {

        int unit = 0;
        long tmpSize = size;
        String result = "";

        while (tmpSize > 1024) {
            tmpSize = tmpSize / 1024;
            unit ++;
        }

        switch (unit) {
            case 0:
                result = tmpSize + "B";
                break;
            case 1:
                result = tmpSize + "KB";
                break;
            case 2:
                result = tmpSize + "MB";
                break;
            case 3:
                result = tmpSize + "GB";
                break;
            case 4:
                result = tmpSize + "TB";
                break;
            default:
                result = "err";
                break;
        }
        return result;

    }
}
