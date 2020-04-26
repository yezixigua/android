package com.zz.filereader.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zz.filereader.R;
import com.zz.filereader.adapter.ListItemAdapter;
import com.zz.filereader.contentviewer.FileJudge;
import com.zz.filereader.utils.ApplicationContext;
import com.zz.filereader.view.ListItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.zz.filereader.utils.Utils.getSdCardPath;
import static com.zz.filereader.utils.Utils.isSdCardExist;

/**     
  *
  * @package:        com.zz.filereader.fragment
  * @className:      FileListFragment
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/4/24 0024
  * @updateUser:     更新者：
  * @updateDate:     2020/4/24 0024
  * @updateRemark:   更新说明：
  * @version:        1.0
 */

public class FileListFragment extends Fragment {

    private static final String TAG = "FileListFragment";

    View mView;
    ListView listView;
    ArrayAdapter<ListItem> adapter;
    List<ListItem> fileList = new ArrayList<ListItem>();

    String currentDirStr = "";
    File currentDirFile;
    File[] currentFiles;

    private Toolbar toolbar;
    TextView pathText;
    Button topBack;

    FileJudge fileJudge;

//    Context mContext = ApplicationContext.context;
    Context mContext;

    public FileListFragment() {
        // Required empty public constructor
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        fileJudge = new FileJudge(mContext);

        mView = inflater.inflate(R.layout.fragment_file_list, container, false);
        setHasOptionsMenu(true);

        initFileListFragment();

        // Inflate the layout for this fragment
        return mView;
    }

    private void initFileListFragment() {

        initToolBar();

        pathText = mView.findViewById(R.id.path_text);

        // listView用法
        listView = mView.findViewById(R.id.content_list);
        adapter = new ListItemAdapter(mContext, R.layout.list_item, fileList);
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

                } else {
                    fileJudge.setFile(tmpFile);
                    fileJudge.viewFile();
                }

            }
        });

        listInit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main,menu);
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


    public void initToolBar() {
        toolbar = (Toolbar) mView.findViewById(R.id.toolbar);

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
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        topBack = mView.findViewById(R.id.top_back);
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
            Toast.makeText(getActivity(), "无sdcard", Toast.LENGTH_SHORT).show();
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


    /***
     *
     * @param keyCode
     * @param event
     * @return 0 false 1 true 2 super
     */
    public int onFragmentKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
//            moveTaskToBack(true);
            if (getSdCardPath().equals(currentDirFile.getPath())) {
                return 2;
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
            return 1;
        }
        return 2;
    }





}
