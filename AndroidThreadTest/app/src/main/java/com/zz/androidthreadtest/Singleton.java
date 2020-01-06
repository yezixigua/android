package com.zz.androidthreadtest;

import android.content.Context;

/**
 * @ProjectName: AndroidThreadTest
 * @Package: com.zz.androidthreadtest
 * @ClassName: Singleten
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/1/6 0006 10:42
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/1/6 0006 10:42
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class Singleton {
    private static Singleton singleton;
    private Context context;
    private Singleton(Context context) {
        this.context = context;
    }

    public static Singleton newInstance(Context context) {
        if (singleton == null) {
            synchronized (Singleton.class) {
                //双重检查锁定
                if (singleton == null){
                    singleton = new Singleton(context);
                }
            }
        }
        return singleton;
    }
}
