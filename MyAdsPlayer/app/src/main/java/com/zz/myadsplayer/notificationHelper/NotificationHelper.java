package com.zz.myadsplayer.notificationHelper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.zz.libcommon.utils.AndroidVersion;
import com.zz.myadsplayer.R;

import androidx.core.app.NotificationCompat;


/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.notificationHelper
 * @ClassName: NotificationHelper
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/21 0021 16:48
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/21 0021 16:48
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class NotificationHelper {

    public NotificationManager notificationManager;
    private Context mContext;
    private Intent mIntent;
    private PendingIntent mPendingIntent;

    public NotificationHelper(Context context, Intent intent) {
        mContext = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mIntent = intent;
        mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);


    }

    public void initNotification() {
        if (Build.VERSION.SDK_INT >= AndroidVersion.INSTANCE.android26) {
            NotificationChannel channel = new NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void sendNotification() {
        Notification notification = new NotificationCompat
                                                        .Builder(mContext, "normal")
                                                        .setContentTitle("this is content title")
                                                        .setContentText("this is content text")
                                                        .setSmallIcon(R.drawable.small_icon)
                                                        .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.large_icon))
                                                        .setContentIntent(mPendingIntent)
                                                        .setAutoCancel(true)
                                                        .build();

        notificationManager.notify(1, notification);
    }
}
