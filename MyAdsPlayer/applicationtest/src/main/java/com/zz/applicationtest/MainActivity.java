package com.zz.applicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.zz.libcommon.utils.DeviceInfoUtils;

/**     
  *
  * @package:        com.zz.applicationtest
  * @className:      MainActivity
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/4/14 0014
  * @updateUser:     更新者：
  * @updateDate:     2020/4/14 0014
  * @updateRemark:   更新说明：
  * @version:        1.0
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeviceInfoUtils.INSTANCE.infoCheck();
    }
}
