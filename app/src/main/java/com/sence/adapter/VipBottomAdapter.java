package com.sence.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PUserBean;
import com.sence.bean.response.PUserVipBean;
import com.sence.net.Urls;
import com.sence.utils.GlideUtils;

/**
 * Created by zwy on 2019/3/14.
 * package_name is com.sence.adapter
 * 描述:Sence
 */
public class VipBottomAdapter extends BaseQuickAdapter<PUserVipBean.ServiceBean, BaseViewHolder> {
    public VipBottomAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PUserVipBean.ServiceBean item) {
        GlideUtils.getInstance().loadNormal(item.getImg(), (ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_name, item.getTitle());
        helper.setText(R.id.item_content, item.getContent());
    }
}
