package com.zz.filereader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zz.filereader.R;
import com.zz.filereader.view.ListItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.adapter
 * @ClassName: ListItemAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/3/27 0027 16:05
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/3/27 0027 16:05
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class ListItemAdapter extends ArrayAdapter<ListItem> {

    private int resourceId;

    public ListItemAdapter(@NonNull Context context, int textViewResourceId, List<ListItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ListItem listItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        ImageView itemImage = view.findViewById(R.id.item_image);
        TextView itemNameText = view.findViewById(R.id.item_name);
        TextView itemTimeText = view.findViewById(R.id.item_create_time);
        TextView itemSizeText = view.findViewById(R.id.item_size);



        itemImage.setImageResource(listItem.getImageId());
        itemNameText.setText(listItem.getName());
        itemTimeText.setText(listItem.getCreateTime());
        itemSizeText.setText(listItem.getSize());

        return view;
    }
}
