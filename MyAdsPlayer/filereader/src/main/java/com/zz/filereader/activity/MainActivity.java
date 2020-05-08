package com.zz.filereader.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.zz.filereader.R;
import com.zz.filereader.adapter.ListItemAdapter;
import com.zz.filereader.fragment.AdsFragment;
import com.zz.filereader.fragment.FileListFragment;
import com.zz.filereader.update.CheckUpdateService;
import com.zz.filereader.view.ListItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.zz.filereader.utils.Utils.getSdCardPath;
import static com.zz.filereader.utils.Utils.isSdCardExist;

/**     
  *
  * @package:        com.zz.filereader
  * @className:      MainActivity
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/3/22 0022
  * @updateUser:     更新者：
  * @updateDate:     2020/3/22 0022
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "zz";

    private FragmentManager frManager;
    private AdsFragment fmAds;
    private FileListFragment fmFileList;
    private FrameLayout frameLayout;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        File patchFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
        if (patchFile.exists()) {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patchFile.getAbsolutePath());
            Log.d(TAG, "onCreate: 补丁已安装");

        } else {
            Log.d(TAG, "onCreate: 补丁不存在");

        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: 未申请到权限，尝试申请权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
        } else {
            Log.d(TAG, "onCreate: 已申请到权限");

        }
        initView();
        checkUpdateService();


    }

    private void checkUpdateService() {
        Intent intent = new Intent(this, CheckUpdateService.class);
        startService(intent);
    }

    private void initView() {
        frManager = getSupportFragmentManager();
        fmAds = (AdsFragment) frManager.findFragmentById(R.id.fragment_Ads);
        transaction = frManager.beginTransaction();
        frameLayout =  findViewById(R.id.frame);
        showAds();
    }

    private void showAds(){
        frManager.beginTransaction().show(fmAds).commit();
    }

    public void dismissAds(){
        frManager.beginTransaction().hide(fmAds).commit();
        fmFileList = new FileListFragment();
        transaction.replace(R.id.frame, fmFileList);
        transaction.commit();

    }

    /***
     * 交给子类处理
     * 0 false 1 true 2 super
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int result = fmFileList.onFragmentKeyDown(keyCode, event);
        switch (result){
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return super.onKeyDown(keyCode, event);

        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限成功");
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限失败");
                    finish();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }



}
