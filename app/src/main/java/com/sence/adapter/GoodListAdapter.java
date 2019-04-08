package com.sence.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PGoodBean;
import com.sence.net.Urls;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class GoodListAdapter extends BaseQuickAdapter<PGoodBean, BaseViewHolder> {
    public GoodListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PGoodBean item) {
        Glide.with(helper.itemView.getContext()).load(Urls.base_url + item.getImg()).into((ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_name, item.getName());
        helper.setText(R.id.item_now_price, "￥" + item.getPrice());
        helper.setText(R.id.item_pre_price, "省￥" + item.getSaveMoney());
    }
}
