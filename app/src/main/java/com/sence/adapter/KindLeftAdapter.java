package com.sence.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;

/**
 * Created by zwy on 2019/3/13.
 * package_name is com.sence.adapter
 * 描述:Sence
 */
public class KindLeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public void setPosition(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    private int position = -1;

    public KindLeftAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (position == helper.getAdapterPosition()) {
            helper.setGone(R.id.item_tag, true);
            helper.itemView.setSelected(true);
        } else {
            helper.setGone(R.id.item_tag, false);
            helper.itemView.setSelected(false);
        }
        helper.setText(R.id.item_name, item);
    }
}
