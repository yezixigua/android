package com.zz.myadsplayer.mytest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zz.myadsplayer.R;

/**     
  *
  * @package:        com.zz.myadsplayer.mytest
  * @className:      nameButtonFragment
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/3/5 0005
  * @updateUser:     更新者：
  * @updateDate:     2020/3/5 0005
  * @updateRemark:   更新说明：
  * @version:        1.0
 */

public class NameButtonFragment extends Fragment {

    private static final String TAG = "zz";

    Button zz;
    Button yy;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TextFragment textFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_name_button, container, false);

        zz = view.findViewById(R.id.zz);
        yy = view.findViewById(R.id.yy);

        fragmentManager = getActivity().getSupportFragmentManager();
        /*FragmentManager要管理fragment（添加，替换以及其他的执行动作）
         *的一系列的事务变化，需要通过fragmentTransaction来操作执行
         */
        fragmentTransaction = fragmentManager.beginTransaction();
        //实例化要管理的fragment
        textFragment = TextFragment.newInstance("zz", "yy");

        zz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textFragment.isAdded()) {
                    textFragment.refreshText("zz");
                } else {
                    //通过添加（事务处理的方式）将fragment加到对应的布局中
                    fragmentTransaction.add(R.id.right_fragment,textFragment);
                    //事务处理完需要提交
                    fragmentTransaction.commit();
                }

                Log.d(TAG, "onClick: zz");
            }
        });

        yy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textFragment.isAdded()) {
                    textFragment.refreshText("yy");
                } else {
                    //通过添加（事务处理的方式）将fragment加到对应的布局中
                    fragmentTransaction.add(R.id.right_fragment,textFragment);
                    //事务处理完需要提交
                    fragmentTransaction.commit();
                }

                Log.d(TAG, "onClick: yy");
            }
        });

        return view;
    }




}
