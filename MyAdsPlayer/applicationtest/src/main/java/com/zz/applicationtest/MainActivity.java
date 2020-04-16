package com.zz.applicationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.zz.libcommon.net.MyRequest;
import com.zz.libcommon.net.okhttp.CommonOkHttpClient;
import com.zz.libcommon.net.okhttp.listener.DisposeDataHandle;
import com.zz.libcommon.net.okhttp.listener.DisposeDataListener;
import com.zz.libcommon.net.okhttp.request.CommonRequest;
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

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeviceInfoUtils.INSTANCE.infoCheck();

        textView = findViewById(R.id.text);

        CommonOkHttpClient.get(CommonRequest.createGetRequest("https://www.baidu.com", null), new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                textView.setText(responseObj.toString());
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        }));



    }
}
