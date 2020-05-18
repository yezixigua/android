package com.zz.myadsplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.zz.myadsplayer.R;

/**     
  *
  * @package:        com.zz.myadsplayer.activity
  * @className:      MoveView
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/5/19 0019
  * @updateUser:     更新者：
  * @updateDate:     2020/5/19 0019
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class MoveView extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_view);

        imageView1 = findViewById(R.id.image_view_move_1);
        imageView2 = findViewById(R.id.image_view_move_2);

        imageView2.setTranslationX(dip2px(this, 100));
    }

    // private static final int CNNGO_METRICS_WIDTH = 480;
// private static final int CNNGO_METRICS_HEIGHT = 800;

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
