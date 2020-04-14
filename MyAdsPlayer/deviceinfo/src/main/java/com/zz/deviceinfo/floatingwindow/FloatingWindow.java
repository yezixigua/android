package com.zz.deviceinfo.floatingwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zz.deviceinfo.R;
import com.zz.deviceinfo.application.App;
import com.zz.deviceinfo.utils.DeviceInfoUtils;
import com.zz.deviceinfo.utils.NetUtils;

import static android.view.Gravity.apply;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.deviceinfo.floatingwindow
 * @ClassName: FloatingWindow
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/4/10 0010 13:20
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/4/10 0010 13:20
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FloatingWindow {

    private static final String TAG = "FloatingWindow";

    private static View mView = null;
    private static WindowManager mWindowManager = null;
    private static Context mContext = null;

    public static Boolean isShown = false;
    private static FloatingWindow mFloatingWindow = new FloatingWindow();

    private FloatingWindow() {}

    public static FloatingWindow getWindowHandler() {
        return mFloatingWindow;
    }

    public void initWindow() {

        if (isShown) {
            Log.d(TAG, "initWindow: return cause already shown");
            return;
        }

        isShown = true;
        Log.d(TAG, "initWindow: showPopupWindow");

        // 获取应用的Context
        mContext = App.getContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        mView = setUpView(mContext);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        // 类型
        if (android.os.Build.VERSION.SDK_INT > 26) {
            Log.d(TAG, "initWindow: TYPE_APPLICATION_OVERLAY");
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            Log.d(TAG, "initWindow: TYPE_SYSTEM_ALERT");
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

//        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        // WindowManager.LayoutParams.TYPE_SYSTEM_ALERT

        // 设置flag

//        int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        int flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
        // 不设置这个flag的话，home页的划屏会有问题

//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = WindowManager.LayoutParams.MATCH_PARENT;

        mView.measure(0, 0);
        params.width = mView.getMeasuredWidth();
        params.height = mView.getMeasuredHeight();

        params.gravity = Gravity.RIGHT |Gravity.TOP;
        params.x = 0;
        params.y = 0;
        params.alpha = 0.5f;

        Log.d(TAG, "initWindow: add view");
        mWindowManager.addView(mView, params);



    }

    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
        Log.d(TAG, "hidePopupWindow: " + "hide " + isShown + ", " + mView);
        if (isShown && null != mView) {
            Log.d(TAG, "hidePopupWindow: hidePopupWindow");
            mWindowManager.removeView(mView);
            isShown = false;
        }

    }

    public void destroyView() {
        mFloatingWindow.destroyView();
    }

    private static View setUpView(final Context context) {

        Log.d(TAG, "setUpView: ");

        View view = LayoutInflater.from(context).inflate(R.layout.floating_window,null);
        TextView macInfo = view.findViewById(R.id.mac_info);
        TextView ipInfo = view.findViewById(R.id.ip_info);
        TextView modelInfo = view.findViewById(R.id.model_info);


        macInfo.setText("mac: " + NetUtils.INSTANCE.getMac(mContext));
        ipInfo.setText("ip: " + NetUtils.INSTANCE.getIPAddress(mContext));
        modelInfo.setText("model: " + DeviceInfoUtils.INSTANCE.getDeviceModel());

        DeviceInfoUtils.INSTANCE.infoCheck();


        Button negativeBtn = (Button) view.findViewById(R.id.negativeBtn);
        negativeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: cancel on click");
                FloatingWindow.hidePopupWindow();

            }
        });

        // 点击窗口外部区域可消除
        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
        // 所以点击内容区域外部视为点击悬浮窗外部
        // 非透明的内容区域
        final View popupWindowView = view.findViewById(R.id.popup_window);

        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d(TAG, "onTouch: ");
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();
                popupWindowView.getGlobalVisibleRect(rect);
                if (!rect.contains(x, y)) {
//                    FloatingWindow.hidePopupWindow();
                }
                Log.d(TAG, "onTouch : " + x + ", " + y + ", rect: " + rect);

                return false;
            }
        });

        // 点击back键可消除
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
//                        FloatingWindow.hidePopupWindow();
                        return true;
                    default:
                        return false;
                }
            }
        });

        return view;

    }

}
