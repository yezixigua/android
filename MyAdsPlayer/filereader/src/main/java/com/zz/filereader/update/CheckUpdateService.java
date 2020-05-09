package com.zz.filereader.update;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**     
  *
  * @package:        com.zz.filereader.update
  * @className:      CheckUpdateService
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/5/8 0008
  * @updateUser:     更新者：
  * @updateDate:     2020/5/8 0008
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class CheckUpdateService extends Service {

    private static final String TAG = "CheckUpdateService";

    public CheckUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "CheckUpdateService: 启动");

        Updater updater = new Updater(this);
        ScheduledExecutorService timeRecordService = new ScheduledThreadPoolExecutor(1);

        timeRecordService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 30min 运行一次");
                updater.checkLatestVersion();
            }
        },0,30, TimeUnit.MINUTES);

        return super.onStartCommand(intent, flags, startId);
    }
}
