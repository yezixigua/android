package com.zz.filereader.contentviewer.vieweractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.zz.filereader.R;
import com.zz.filereader.contentviewer.FileJudge;

import java.io.File;

/**     
  *
  * @package:        com.zz.filereader.contentviewer.vieweractivity
  * @className:      ImageReaderActivity
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/4/26 0026
  * @updateUser:     更新者：
  * @updateDate:     2020/4/26 0026
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class ImageReaderActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_reader);

        imageView = findViewById(R.id.image_reader);
        File file = (File)(getIntent().getSerializableExtra("file"));

        Uri uri = Uri.fromFile(file);
        imageView.setImageURI(uri);

    }


}
