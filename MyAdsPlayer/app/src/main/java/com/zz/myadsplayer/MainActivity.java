package com.zz.myadsplayer;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.view.View;
import android.widget.Button;

import com.zz.myadsplayer.utils.MD5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
  *
  * @package:        com.zz.myadsplayer
  * @className:      MainActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/1/16 0016
  * @updateUser:     更新者：
  * @updateDate:     2020/1/16 0016
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class MainActivity extends AppCompatActivity {

    private final String DOWNLOAD_CACHE = "DOWNLOAD_CACHE";

    private Button videoButton;
    private Button imageButton;
    private Button downloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoButton = findViewById(R.id.video_player);
        imageButton = findViewById(R.id.image_player);
        downloadButton = findViewById(R.id.download);

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Video.class);
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Image.class);
                startActivity(intent);
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //创建OkHttpClient对象
                            OkHttpClient client = new OkHttpClient();
                            //创建Request
                            Request request = new Request.Builder()
                                    .url("http://192.168.1.107:8080/download")//访问连接
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
                                    fileOutputStream = openFileOutput(DOWNLOAD_CACHE, Context.MODE_PRIVATE);
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

                            String renamePath = getFilesDir().getPath() + File.separator + DOWNLOAD_CACHE;
                            File renameFile = new File(renamePath);
                            String fileMD5 = MD5.getFileMD5(renameFile);
                            renameFile.renameTo(new File(getFilesDir().getPath() + File.separator + fileMD5));


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
