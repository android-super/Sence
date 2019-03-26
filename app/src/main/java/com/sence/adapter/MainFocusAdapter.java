package com.sence.adapter;

import android.app.Activity;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by zwy on 2019/3/12.
 * package_name is com.sence.adapter
 * 描述:Sence
 */
public class MainFocusAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MainFocusAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        final Activity activity= (Activity) helper.itemView.getContext();
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                activity.startActivity(new Intent(activity, ScrollingActivity.class));
            }
        });
    }
}
