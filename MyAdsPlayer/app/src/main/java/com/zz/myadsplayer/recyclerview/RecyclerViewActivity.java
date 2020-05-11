package com.zz.myadsplayer.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.zz.myadsplayer.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**     
  *
  * @package:        com.zz.myadsplayer.recyclerview
  * @className:      RecyclerViewActivity
  * @description:     java类作用描述 
  * @author:         liuzhuang
  * @createDate:     2020/5/11 0011
  * @updateUser:     更新者：
  * @updateDate:     2020/5/11 0011
  * @updateRemark:   更新说明：
  * @version:        1.0
 */


public class RecyclerViewActivity extends AppCompatActivity {

    private List<Fruit> fruitList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initFruitList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }

    private void initFruitList() {
        fruitList = new LinkedList<>();

        fruitList.add(new Fruit("苹果"));
        fruitList.add(new Fruit("香蕉"));
        fruitList.add(new Fruit("橙子"));
        fruitList.add(new Fruit("西瓜"));
        fruitList.add(new Fruit("桃子"));
    }
}
