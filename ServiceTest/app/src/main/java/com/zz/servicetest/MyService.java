package com.zz.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ServiceConfigurationError;

import androidx.annotation.Nullable;

/**
 * @ProjectName: ServiceTest
 * @Package: com.zz.servicetest
 * @ClassName: MyhService
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/6 0006 15:27
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/6 0006 15:27
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyService extends Service {

    private static final String TAG = "zz";
    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d(TAG, "startDownload: ");
        }

        public int getProgress() {
            Log.d(TAG, "download on progress: ");
            return 0;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: myService");

//        Notification notification = new Notification(R.mipmap.ic_launcher, "Notification comes", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,notificationIntent, 0);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: myService destroy");
    }
}
