package com.zz.filereader.view;

import com.zz.filereader.R;

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

    public static final int folderType = 0;
    public static final int fileType = 1;

    private String name;
    private int mType;
    private String createTime;
    private String size;

    public ListItem(File file) {
        this.name = file.getName();
        if (file.isDirectory()) {
            this.mType = folderType;
            this.size = file.list().length + " 项";

        } else {
            this.mType = fileType;
            this.size = file.length()/1024 + "KB";
        }
        Date date = new Date(file.lastModified());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.createTime = formatter.format(date);


    }

    public String getName() {
        return this.name;
    }

    public int getImageId() {

        switch (mType) {
            case folderType :
                return R.drawable.folder;
//            case fileType :
//                return R.drawable.page;
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
}