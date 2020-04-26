package com.zz.filereader.contentviewer;

import android.content.Context;
import android.content.Intent;

import com.zz.filereader.contentviewer.vieweractivity.AudioPlayerActivity;
import com.zz.filereader.contentviewer.vieweractivity.ImageReaderActivity;
import com.zz.filereader.contentviewer.vieweractivity.TextReaderActivity;
import com.zz.filereader.contentviewer.vieweractivity.VideoPlayerActivity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.contentviewer
 * @ClassName: FileJudge
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/26 0026 14:50
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/26 0026 14:50
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FileJudge {

    private static final String PICTURE_TYPE = "picture";
    private static final String TEXT_TYPE = "text";
    private static final String VIDEO_TYPE = "video";
    private static final String AUDIO_TYPE = "audio";
    private static final String UNKNOWN_TYPE = "unknown";

    private Map<String, String> fileJudgeMap = new HashMap<String, String>();

    public File mFile;
    public Context mContext;
    public String fileType = UNKNOWN_TYPE;

    public FileJudge(Context context) {

        fileJudgeMap.put(".jpg", PICTURE_TYPE);
        fileJudgeMap.put(".png", PICTURE_TYPE);
        fileJudgeMap.put(".bmp", PICTURE_TYPE);

        fileJudgeMap.put(".txt", TEXT_TYPE);
        fileJudgeMap.put(".log", TEXT_TYPE);

        fileJudgeMap.put(".mp4", VIDEO_TYPE);
        fileJudgeMap.put(".avi", VIDEO_TYPE);

        fileJudgeMap.put(".mp3", AUDIO_TYPE);
        fileJudgeMap.put(".flac", AUDIO_TYPE);

        mContext = context;

     }

    public FileJudge(Context context,  String path) {
        this(context);
        mFile = new File(path);
    }

    public void setFile(File mFile) {
        this.mFile = mFile;
    }

    public String getFileEndFix(String name) {
        if (!name.contains(".")) {
            return "";
        }
        String endFix = "";
        endFix = name.substring(name.lastIndexOf("."));
        return endFix;
    }

    public String checkFileType(String name) {

        String endFix = getFileEndFix(name);
        if(endFix.equals("")) {
            fileType = UNKNOWN_TYPE;
        } else if(fileJudgeMap.containsKey(endFix)) {
            fileType = fileJudgeMap.get(endFix);
        } else {
            fileType = UNKNOWN_TYPE;
        }
        return fileType;
    }

    public void viewFile() {
        Intent intent;
        String type = checkFileType(mFile.getName());

        switch (type) {
            case PICTURE_TYPE:
                intent = new Intent(mContext, ImageReaderActivity.class);
                intent.putExtra("file", mFile);
                mContext.startActivity(intent);
                break;
            case TEXT_TYPE:
                intent = new Intent(mContext, TextReaderActivity.class);
                intent.putExtra("file", mFile);
                mContext.startActivity(intent);
                break;
            case VIDEO_TYPE:
                intent = new Intent(mContext, VideoPlayerActivity.class);
                intent.putExtra("file", mFile);
                mContext.startActivity(intent);
                break;
            case AUDIO_TYPE:
                intent = new Intent(mContext, AudioPlayerActivity.class);
                intent.putExtra("file", mFile);
                mContext.startActivity(intent);
                break;
            case UNKNOWN_TYPE:
                break;
            default:
                break;
        }
    }

}
