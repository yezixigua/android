package com.zz.myadsplayer.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.zz.myadsplayer.R;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.view
 * @ClassName: CustomView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/5/7 0007 17:02
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/7 0007 17:02
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class CustomView extends View {

    private static final String TAG = "zzz_CustomView";
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int color = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getResources().getColor(R.color.colorLightGreen, null);
        }
        setBackgroundColor(color);
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: 点击 view");
//            }
//        });
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean flag = super.dispatchTouchEvent(event);
        Log.d(TAG, "dispatchTouchEvent: " + event.getAction() + flag);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent: " + event.getAction() + flag);
        return flag;
    }
    
    
}
