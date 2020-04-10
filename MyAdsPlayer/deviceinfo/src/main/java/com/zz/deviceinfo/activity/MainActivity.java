package com.zz.deviceinfo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.zz.deviceinfo.R;
import com.zz.deviceinfo.floatingwindow.FloatingWindow;
import com.zz.deviceinfo.floatingwindow.FloatingWindowService;

/**     
  *
  * @package:        com.zz.deviceinfo
  * @className:      MainActivity
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/4/10 0010
  * @updateUser:     更新者：
  * @updateDate:     2020/4/10 0010
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startFloatingWindowServer();

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG, "onCreate: 未申请到权限，尝试申请权限");
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 1);
//
//        } else {
//            Log.d(TAG, "onCreate: 已申请到权限");
//            requestDrawOverLays();
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限成功");
                    startFloatingWindowServer();
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限失败");
                    Toast.makeText(this, "Your permission denied", Toast.LENGTH_SHORT).show();
                    requestDrawOverLays();
                }
                break;
            default:
                break;
        }
    }

    public void requestDrawOverLays() {

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(MainActivity.this)) {
                Log.d(TAG, "requestDrawOverLays: can not DrawOverlays");
                Toast.makeText(this, "can not DrawOverlays", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + MainActivity.this.getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            } else {
                // Already hold the SYSTEM_ALERT_WINDOW permission, do addview or something.
                startFloatingWindowServer();
            }
        }

    }

    public void startFloatingWindowServer() {
        Intent intent = new Intent(MainActivity.this, FloatingWindowService.class);
        startService(intent);
    }


}
