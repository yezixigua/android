package com.zz.myadsplayer.broadcast;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
  *
  * @package:        com.zz.myadsplayer.broadcast
  * @className:      MyService
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/3/12 0012
  * @updateUser:     更新者：
  * @updateDate:     2020/3/12 0012
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class MyService extends Service {

    MyBroadcastReceiver receiver;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter filter=new IntentFilter("com.zz.myadsplayer.broadcast");
        receiver=new MyBroadcastReceiver();
        registerReceiver(receiver,filter);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
