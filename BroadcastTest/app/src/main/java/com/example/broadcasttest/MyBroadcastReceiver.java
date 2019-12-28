package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

/**
 * @ProjectName: BroadcastTest
 * @Package: com.example.broadcasttest
 * @ClassName: MyBroadcastReceiver
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/28 0028 16:34
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/28 0028 16:34
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "zz";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in myBroadcastReceiver", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onReceive: received in myBroadcastReceiver");

    }


}
