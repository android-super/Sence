package com.sence.adapter;

import android.graphics.Color;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PKindBean;

/**
 * Created by zwy on 2019/3/13.
 * package_name is com.sence.adapter
 * 描述:Sence
 */
public class KindLeftAdapter extends BaseQuickAdapter<PKindBean, BaseViewHolder> {
    public void setPosition(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    private int position = -1;

    public KindLeftAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PKindBean item) {
        TextView item_name = helper.getView(R.id.item_name);
        if (position == helper.getAdapterPosition()) {
            helper.setGone(R.id.item_tag, true);
            helper.itemView.setSelected(true);
            item_name.setTextColor(Color.parseColor("#16a5af"));
            item_name.setTextSize(16);
        } else {
            helper.setGone(R.id.item_tag, false);
            helper.itemView.setSelected(false);
            item_name.setTextColor(Color.parseColor("#666666"));
            item_name.setTextSize(14);
        }
        item_name.setText(item.getName());
    }
}
