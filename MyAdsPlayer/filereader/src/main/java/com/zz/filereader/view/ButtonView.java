package com.zz.filereader.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.view
 * @ClassName: ButtonView
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/3/25 0025 15:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/3/25 0025 15:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ButtonView extends LinearLayout {

    private ImageView imageView;
    private TextView textView;

    public ButtonView(Context context) {
        super(context);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //创建对象
        imageView = new ImageView(context, attrs);

        //设置内边距
        imageView.setPadding(0, 0, 0, 0);
        textView = new TextView(context, attrs);

        //text居中
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setPadding(0, 0, 0, 0);
        //获取系统自带控件包
        setBackgroundResource(android.R.drawable.btn_default);

        //获取焦点
        setFocusable(true);
        //可否点击
        setClickable(true);
        //设置背景颜色
        setBackgroundColor(Color.WHITE);
        //指定方向
        setOrientation(LinearLayout.VERTICAL);

        //添加子View(有顺序，先添加先显示)
        addView(imageView);
        addView(textView);
    }


}
