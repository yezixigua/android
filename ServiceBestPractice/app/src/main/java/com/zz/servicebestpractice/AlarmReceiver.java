package com.zz.servicebestpractice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @ProjectName: ServiceBestPractice
 * @Package: com.zz.servicebestpractice
 * @ClassName: AlarmReceiver
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/6 0006 17:49
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/6 0006 17:49
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, LongRunningService.class);
        context.startService(i);
    }
}
