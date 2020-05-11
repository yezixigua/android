package com.zz.filereader.adapter;

import com.zz.filereader.view.ListItem;

import java.util.List;

/**
 * @ProjectName: MyAdsPlayer
 * @Package: com.zz.filereader.adapter
 * @ClassName: OnRecyclerItemClickListener
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020/5/11 0011 23:01
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/11 0011 23:01
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public interface OnRecyclerItemClickListener {

    /***
     * RecyclerView的点击事件，将信息回调给view
     * @param position
     * @param listItems
     */
    void onItemClick(int position, List<ListItem> listItems);
}
