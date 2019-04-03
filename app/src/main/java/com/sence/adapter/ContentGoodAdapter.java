package com.sence.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PContentDetailBean;
import com.sence.net.Urls;

/**
 * Created by zwy on 2019/3/30.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class ContentGoodAdapter extends BaseQuickAdapter<PContentDetailBean.NoteInfoBean.GoodsInfoBean, BaseViewHolder> {

    public ContentGoodAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PContentDetailBean.NoteInfoBean.GoodsInfoBean item) {
        Glide.with(helper.itemView.getContext()).load(Urls.base_url+item.getImg()).into((ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_content,item.getName());
        helper.setText(R.id.item_price,item.getVprice());
        helper.setText(R.id.item_pre_price,item.getPrice());
    }
}
