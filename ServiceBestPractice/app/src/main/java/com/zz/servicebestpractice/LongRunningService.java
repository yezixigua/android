package com.zz.servicebestpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

import androidx.annotation.Nullable;

/**
 * @ProjectName: ServiceBestPractice
 * @Package: com.zz.servicebestpractice
 * @ClassName: LongRunningService
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/6 0006 17:43
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/6 0006 17:43
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class LongRunningService extends Service {

    private static final String TAG = "zz";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: executed at" + new Date().toString());
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        int anHour = 60 * 60 * 1000;
        int anHour = 2 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }
}
