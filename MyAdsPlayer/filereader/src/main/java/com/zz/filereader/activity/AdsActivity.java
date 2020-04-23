package com.zz.filereader.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.zz.filereader.R;

/**     
  *
  * @package:        com.zz.filereader.activity
  * @className:      AdsActivity
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/4/23 0023
  * @updateUser:     更新者：
  * @updateDate:     2020/4/23 0023
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class AdsActivity extends AppCompatActivity {

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        imageView = findViewById(R.id.ads_image);
        imageView.setImageResource(R.drawable.default_ads);
    }
}
