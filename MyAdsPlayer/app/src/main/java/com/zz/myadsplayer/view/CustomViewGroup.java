package com.zz.myadsplayer.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.zz.myadsplayer.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.view
 * @ClassName: CustomViewGroup
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/5/7 0007 16:38
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/7 0007 16:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class CustomViewGroup extends FrameLayout {

    private static final String TAG = "zzz_CustomViewGroup";
    
    public CustomViewGroup(@NonNull Context context) {
        super(context);
    }

    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.custom_view_group_layout, this);
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: 点击 Group");
//            }
//        });
    }

    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean flag = super.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent: " + ev.getAction() + flag);
        return flag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean flag = super.onInterceptTouchEvent(ev);
        Log.d(TAG, "onInterceptTouchEvent: " + ev.getAction() + flag);
//        Log.d(TAG, "onInterceptTouchEvent: " + ev.getAction() + true);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent: " + event.getAction() + flag);
        return flag;
    }
}
