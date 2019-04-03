package com.sence.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;

/**
 * Created by zwy on 2019/4/1.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class MyFNAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int position = 0;

    public MyFNAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_name, item);
        if (position == helper.getAdapterPosition()) {
            helper.setGone(R.id.item_point, true);
        } else {
            helper.setGone(R.id.item_point, false);
        }
    }

    public void setPosition(int position) {
        this.position = position;
        notifyDataSetChanged();

    }
}
