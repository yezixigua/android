package com.zz.deviceinfo.floatingwindow;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.zz.deviceinfo.activity.MainActivity;

import static androidx.core.app.ActivityCompat.startActivityForResult;

/**     
  *
  * @package:        com.zz.deviceinfo.floatingwindow
  * @className:      FloatingWindowService
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/4/10 0010
  * @updateUser:     更新者：
  * @updateDate:     2020/4/10 0010
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class FloatingWindowService extends Service {

    FloatingWindow floatingWindow;
    private static final String TAG = "FloatingWindowService";

    public FloatingWindowService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        floatingWindow = FloatingWindow.getWindowHandler();
        requestDrawOverLays();



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        floatingWindow.destroyView();
        super.onDestroy();
    }

    public void requestDrawOverLays() {

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Log.d(TAG, "requestDrawOverLays: can not DrawOverlays");
                Toast.makeText(this, "can not DrawOverlays", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + this.getPackageName()));
                startActivity(intent);
            } else {
                // Already hold the SYSTEM_ALERT_WINDOW permission, do addview or something.
                floatingWindow.initWindow();
            }
        }

    }
}
