package com.sence.adapter;

import android.graphics.Typeface;
import android.widget.TextView;
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
        TextView item_name = helper.getView(R.id.item_name);
        helper.setText(R.id.item_name, item);

        if (position == helper.getAdapterPosition()) {
            helper.setGone(R.id.item_point, true);
            item_name.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            item_name.setTextSize(23);
        } else {
            helper.setGone(R.id.item_point, false);
            item_name.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            item_name.setTextSize(15);
        }
    }

    public void setPosition(int position) {
        this.position = position;
        notifyDataSetChanged();

    }
}
