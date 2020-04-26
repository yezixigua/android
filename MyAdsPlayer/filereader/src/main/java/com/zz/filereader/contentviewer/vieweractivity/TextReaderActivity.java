package com.zz.filereader.contentviewer.vieweractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.zz.filereader.R;
import com.zz.filereader.contentviewer.FileJudge;

import java.io.File;
import java.util.Map;

import static com.zz.libcommon.file.FileUtils.getFileContent;

/**
  *
  * @package:        com.zz.filereader.contentviewer.vieweractivity
  * @className:      TextReaderActivity
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/4/26 0026
  * @updateUser:     更新者：
  * @updateDate:     2020/4/26 0026
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class TextReaderActivity extends AppCompatActivity {

    TextView textView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String content = (String) (msg.obj);
            textView.setText(content);

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_reader);

        File file = (File)(getIntent().getSerializableExtra("file"));
        AsyncReadFile(file);

        textView = findViewById(R.id.text_reader);
    }

    private void AsyncReadFile(File file) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String content = getFileContent(file);

                Message message = new Message();
                message.obj = content;
                handler.sendMessage(message);
            }
        }).run();


    }

}
