package com.example.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * @ProjectName: BroadcastBestPractice
 * @Package: com.example.broadcastbestpractice
 * @ClassName: ForceOfflineReceiver
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/30 0030 17:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/30 0030 17:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ForceOfflineReceiver extends BroadcastReceiver {

    private static final String TAG = "zz";
    
    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d(TAG, "onReceive: received broadcast");

        Log.d(TAG, "finish all activities");

        ActivityController.finishAll();
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//        dialogBuilder.setTitle("Warning");
//        dialogBuilder.setMessage("You are forced offline. Please try ro login again");
//        dialogBuilder.setCancelable(false);
//        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                ActivityController.finishAll();
//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            }
//
//        });

//        AlertDialog alertDialog = dialogBuilder.create();
//
//        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//
//        if (Build.VERSION.SDK_INT >= 23) {
//            if(!Settings.canDrawOverlays(MainActivity.this)) {
////                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
////                startActivity(intent);
//                return;
//            } else {
//                //Android6.0以上
//                if (alertDialog!=null) {
//                    alertDialog.show();
//                }
//            }
//        } else {
//            //Android6.0以下，不用动态声明权限
//            if (alertDialog!=null) {
//                alertDialog.show();
//            }
//        }
//


//        alertDialog.show();
    }

}

