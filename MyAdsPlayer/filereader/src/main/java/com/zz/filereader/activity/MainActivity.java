package com.zz.filereader.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.zz.filereader.R;
import com.zz.filereader.adapter.ListItemAdapter;
import com.zz.filereader.view.ListItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_MUSIC;
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
    ListView listView;
    ArrayAdapter<ListItem> adapter;
    List<ListItem> fileList = new ArrayList<ListItem>();

    String currentDirStr = "";
    File currentDirFile;
    File[] currentFiles;

    private Toolbar toolbar;
    TextView pathText;
    Button topBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        startWithAds();

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

        Toast.makeText(this, "补丁版本5", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate: 补丁版本6");

        initToolBar();

        pathText = findViewById(R.id.path_text);

        // listView用法
        listView = findViewById(R.id.content_list);
        adapter = new ListItemAdapter(this, R.layout.list_item, fileList);
        listView.setAdapter(adapter);

        // list中的元素的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 拿到当前点击的元素
                ListItem currentFile = fileList.get(position);

                // 判断当前元素是否是目录，如果是目录，就可以下一步展开路径，如果是文件，就不展开了
                String tmp = currentDirStr + File.separator + currentFile.getName();
                File tmpFile = new File(tmp);
                Log.d(TAG, "onItemClick: " + tmpFile.getPath());
                if (tmpFile.isDirectory()) {
                    currentDirFile = tmpFile;
                    currentDirStr = tmp;
                    currentFiles = currentDirFile.listFiles();

                    pathText.setText(currentDirStr);

                    fileList.clear();
                    for (File fileItem: currentFiles) {
                        fileList.add(new ListItem(fileItem));
                    }
                    adapter.notifyDataSetChanged();

                }



            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: 未申请到权限，尝试申请权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
        } else {
            Log.d(TAG, "onCreate: 已申请到权限");
            listInit();

        }


    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        /**
         * 设置标题
         * 这里默认显示在左上角，当前填空，不显示标题
         */
        toolbar.setTitle("");
//        /**
//         * 设置子标题
//         */
//        toolbar.setSubtitle("子标题");
//        /**
//         * 设置App的logo
//         */
//        toolbar.setLogo(R.mipmap.launcher);
//        /**
//         * 设置导航按钮
//         */
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);

        topBack = findViewById(R.id.top_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当按返回键的时候，将当前目录显示改为父目录的文件
                if (getSdCardPath().equals(currentDirFile.getPath())) {
                    return;
                }

                File currentFile = currentDirFile.getParentFile();
                currentFiles = currentFile.listFiles();
                currentDirFile = currentFile;
                currentDirStr = currentDirFile.getPath();
                pathText.setText(currentDirStr);

                fileList.clear();
                for (File fileItem: currentFiles) {

                    fileList.add(new ListItem(fileItem));

                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    /**
        初始化时将内容置为sdcard下面的文件内容
        **/
    public void listInit() {

        if (!isSdCardExist()) {
            Toast.makeText(this, "无sdcard", Toast.LENGTH_SHORT).show();
            return;
        }

        File sdcardFolder;
        currentDirStr = getSdCardPath();
        Log.d(TAG, "listInit: currentDirStr" + currentDirStr);
        pathText.setText(currentDirStr);
        sdcardFolder = new File(currentDirStr);
        currentDirFile = sdcardFolder;
        currentFiles = sdcardFolder.listFiles();

        for (File fileItem: currentFiles) {
            fileList.add(new ListItem(fileItem));
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限成功");
                    listInit();
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: 申请权限失败");
                    Toast.makeText(this, "Your permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
//            moveTaskToBack(true);
            if (getSdCardPath().equals(currentDirFile.getPath())) {
                return super.onKeyDown(keyCode, event);
            }

            File currentFile = currentDirFile.getParentFile();
            currentFiles = currentFile.listFiles();
            currentDirFile = currentFile;
            currentDirStr = currentDirFile.getPath();
            pathText.setText(currentDirStr);

            fileList.clear();
            for (File fileItem: currentFiles) {
                fileList.add(new ListItem(fileItem));
            }
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch ( id ){
//            case R.id.menu_1 :
//                Toast.makeText(MainActivity.this,"you click menu_1" ,Toast.LENGTH_LONG).show();
//                break;
//            case R.id.menu_2 :
//                Toast.makeText(MainActivity.this,"you click menu_2" ,Toast.LENGTH_LONG).show();
//                break;
            default:
                break;
        }
        return true;
    }

    private void startWithAds() {
        Intent intent = new Intent(this, AdsActivity.class);
        startActivity(intent);
    }
}
