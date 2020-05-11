package com.zz.filereader.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.filereader.R;
import com.zz.filereader.view.ListItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.adapter
 * @ClassName: RecyclerListItemAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/5/11 0011 22:10
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/11 0011 22:10
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class RecyclerListItemAdapter extends RecyclerView.Adapter<RecyclerListItemAdapter.ViewHolder>  {

    private List<ListItem> mList;
    private OnRecyclerItemClickListener mItemClickListener;

    public RecyclerListItemAdapter(List<ListItem> itemList) {
        mList = itemList;
    }

    /***
     * 提供set方法供Activity或Fragment调用
     * @param listener
     */
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        mItemClickListener = listener;
    }


    /***
     * 拿到单个item所需要的view，将其交给holder，返回holder关联view界面控件的holder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerListItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerListItemAdapter.ViewHolder holder, int position) {
        ListItem listItem = mList.get(position);

        holder.itemImage.setImageResource(listItem.getImageId());
        holder.itemNameText.setText(listItem.getName());
        holder.itemTimeText.setText(listItem.getCreateTime());
        holder.itemSizeText.setText(listItem.getSize());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /***
     * 将holder与xml界面联系起来
     * 记录xml的view信息
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemNameText;
        TextView itemTimeText;
        TextView itemSizeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemNameText = itemView.findViewById(R.id.item_name);
            itemTimeText = itemView.findViewById(R.id.item_create_time);
            itemSizeText = itemView.findViewById(R.id.item_size);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener!=null){

                        mItemClickListener.onItemClick(getAdapterPosition(), mList);
                    }
                }
            });
        }
    }
}
