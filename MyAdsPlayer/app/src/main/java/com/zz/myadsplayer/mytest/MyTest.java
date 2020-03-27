package com.zz.myadsplayer.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.zz.myadsplayer.R;

/**
  *
  * @package:        com.zz.myadsplayer.mytest
  * @className:      MyTest
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2020/2/16 0016
  * @updateUser:     更新者：
  * @updateDate:     2020/2/16 0016
  * @updateRemark:   更新说明：
  * @version:        1.0
 */



public class MyTest extends AppCompatActivity {

    private static final String TAG = "zz_test";

    private boolean shutDownFlag = false;
    private long num = 0;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);

        test();
    }



    public void test() {


        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "thread0: " + shutDownFlag);
                long currentTime = System.currentTimeMillis();
                Log.d(TAG, "thread0启动时间: " + (currentTime - startTime) + "ms");
                while (shutDownFlag == false) {
//                    System.out.println("thread1: " + shutDownFlag);
//                    num++;
                }
                Log.d(TAG, "thread0: " + num);
                Log.d(TAG, "thread0: " + "停止" + shutDownFlag);

                currentTime = System.currentTimeMillis();
                Log.d(TAG, "thread0耗时: " + (currentTime - startTime) + "ms");
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "thread1: " + shutDownFlag);
                long currentTime = System.currentTimeMillis();
                Log.d(TAG, "thread1启动时间: " + (currentTime - startTime) + "ms");
                while (shutDownFlag == false) {
//                    System.out.println("thread1: " + shutDownFlag);
//                    synchronized ("") {
//
//                    }
                    num++;
                }
                Log.d(TAG, "thread1: " + num);
                Log.d(TAG, "thread1: " + "停止" + shutDownFlag);
                currentTime = System.currentTimeMillis();
                Log.d(TAG, "thread1耗时: " + (currentTime - startTime) + "ms");

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "thread2: " + shutDownFlag);
                long currentTime = System.currentTimeMillis();
                Log.d(TAG, "thread2启动时间: " + (currentTime - startTime) + "ms");

                while (true) {
                    shutDownFlag = !shutDownFlag;
                }

//                System.out.println("thread2: " + "改变flag " + shutDownFlag);

//                long currentTime = System.currentTimeMillis();
//                System.out.println("thread2耗时: " + (currentTime - startTime) + "ms");
            }
        });

        startTime = System.currentTimeMillis();
        t1.start();
        t0.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
//        try {
//            t0.join();
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
