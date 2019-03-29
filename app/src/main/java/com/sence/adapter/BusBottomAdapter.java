package com.sence.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.bean.response.PBusRecommendBean;

/**
 * Created by zwy on 2019/3/13.
 * package_name is com.sence.adapter
 * 描述:Sence
 */
public class BusBottomAdapter extends BaseQuickAdapter<PBusRecommendBean, BaseViewHolder> {
    public BusBottomAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PBusRecommendBean item) {

    }
}
