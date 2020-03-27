package com.zz.myadsplayer.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.service
 * @ClassName: TimeService
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/3/8 0008 16:04
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/3/8 0008 16:04
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class TimeService extends Service {

    private static final String TAG = "zz";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        ScheduledExecutorService mService = Executors.newScheduledThreadPool(5);

//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder() .setNameFormat("demo-pool-%d").build();
//        ExecutorService singleThreadPool = new ThreadPoolExecutor(1,
//                3,
//                0L,
//                TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue(1024),
//                namedThreadFactory,
//                new ThreadPoolExecutor.AbortPolicy());

        ScheduledExecutorService mService = new ScheduledThreadPoolExecutor(2);
//        ScheduledExecutorService mService = new ScheduledThreadPoolExecutor(2, namedThreadFactory);

        mService.scheduleAtFixedRate(new Runnable() {

            @Override

            public void run() {

                Log.d(TAG, "run: zz time service");

            }

        }, 1, 3, TimeUnit.SECONDS);

        return super.onStartCommand(intent, flags, startId);
    }
}
