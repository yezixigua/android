package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @ProjectName: BroadcastTest2
 * @Package: com.example.broadcasttest2
 * @ClassName: AnotherBroadcastReceiver
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/30 0030 11:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/30 0030 11:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "received in AnotherBroadcastReceiver", Toast.LENGTH_SHORT).show();

    }
}
