package com.zz.libcommon.file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.libcommon.file
 * @ClassName: FileUtils
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/24 0024 14:18
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/24 0024 14:18
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FileUtils {

    private byte[] readFile(File file) {
        // 需要读取的文件，参数是文件的路径名加文件名
        if (file.isFile()) {
            // 以字节流方法读取文件

            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                // 设置一个，每次 装载信息的容器
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                // 开始读取数据
                // 每次读取到的数据的长度
                int len = 0;
                // len值为-1时，表示没有数据了
                while ((len = fis.read(buffer)) != -1) {
                    // append方法往sb对象里面添加数据
                    outputStream.write(buffer, 0, len);
                }
                // 输出字符串
                return outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件不存在！");
        }
        return null;
    }

    public static void writeBytesToFile() {
        String s = "aaaaaaaa";
        byte[] bs= s.getBytes();
        OutputStream out = null;
        InputStream is = null;
        try {
            out = new FileOutputStream("/storage/sdcard0/aaa");

            is = new ByteArrayInputStream(bs);
            byte[] buff = new byte[1024];
            int len = 0;
            while((len=is.read(buff))!=-1){
                out.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取指定目录下的所有TXT文件的文件内容
    public static String getFileContent(File file) {
        StringBuilder contentBuilder = new StringBuilder();
        //检查此路径名的文件是否是一个目录(文件夹)
        if (!file.isDirectory()) {
            //文件格式为""文件
            try {
                InputStream inStream = new FileInputStream(file);
                if (inStream != null) {
                    InputStreamReader inputReader = new InputStreamReader(inStream, "UTF-8");
                    BufferedReader buffReader = new BufferedReader(inputReader);
                    String line = "";
                    //分行读取
                    while ((line = buffReader.readLine()) != null) {
                        contentBuilder.append(line + "\n");
                    }
                    inStream.close();//关闭输入流
                }
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contentBuilder.toString();
    }




}
