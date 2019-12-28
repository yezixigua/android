package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @ProjectName: BroadcastTest
 * @Package: com.example.broadcasttest
 * @ClassName: BootCompleteReceiver
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/28 0028 16:03
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/28 0028 16:03
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class BootCompleteReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "boot complete", Toast.LENGTH_SHORT).show();
    }
}
