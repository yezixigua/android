package com.zz.myadsplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.zz.myadsplayer.R;

/**     
  *
  * @package:        com.zz.myadsplayer.activity
  * @className:      CustomViewActivity
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/5/7 0007
  * @updateUser:     更新者：
  * @updateDate:     2020/5/7 0007
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class CustomViewActivity extends AppCompatActivity {

    private static final String TAG = "zzz_CustomViewActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean flag = super.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent: " + ev.getAction() + flag);
        return flag;
    }

    @Override
    public void onUserInteraction() {
        Log.d(TAG, "onUserInteraction: ");
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean flag = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent: " + event.getAction() + flag);
        
        return flag;
    }
}
