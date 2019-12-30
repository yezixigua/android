package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
  *
  * @package:        com.example.broadcastbestpractice
  * @className:      ActivityController
  * @description:     java类作用描述
  * @author:         liuzhuang
  * @createDate:     2019/12/30 0030
  * @updateUser:     更新者：
  * @updateDate:     2019/12/30 0030
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class ActivityController {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity: activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

    }
}
