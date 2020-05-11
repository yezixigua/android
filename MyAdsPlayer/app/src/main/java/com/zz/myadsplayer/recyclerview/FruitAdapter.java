package com.zz.myadsplayer.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zz.myadsplayer.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.myadsplayer.recyclerview
 * @ClassName: FruitAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/5/11 0011 11:22
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/11 0011 11:22
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    List<Fruit> mList;

    public FruitAdapter(List<Fruit> fruitList) {
        mList = fruitList;
    }

    /***
     * 拿到单个item所需要的view，将其交给holder，返回holder关联view界面控件的holder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    /***
     * 加载当个item时调用，会将Holder中记录的界面元素配置完毕
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mList.get(position);
        holder.fruitName.setText(fruit.mName);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /***
     * 将holder与xml界面联系起来
     * 记录xml的view信息
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fruitName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitName = itemView.findViewById(R.id.list_name);
        }
    }


}
