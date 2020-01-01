package com.example.sharedpreferencestest;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @ProjectName: BroadcastBestPractice
 * @Package: com.example.broadcastbestpractice
 * @ClassName: BasicActivity
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/30 0030 16:47
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/30 0030 16:47
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class BasicActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
