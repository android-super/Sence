package com.sence.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PUserVipBean;
import com.sence.net.Urls;

/**
 * Created by zwy on 2019/3/14.
 * package_name is com.sence.adapter
 * 描述:Sence
 */
public class VipTopAdapter extends BaseQuickAdapter<PUserVipBean.GoodsBean, BaseViewHolder> {
    public VipTopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PUserVipBean.GoodsBean item) {
        Glide.with(helper.itemView.getContext()).load(Urls.base_url+item.getImg()).into((ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_name,item.getName());
        helper.setText(R.id.item_now_price,"￥"+item.getPrice());
        helper.setText(R.id.item_pre_price,"省￥"+item.getSaveMoney());
    }
}
