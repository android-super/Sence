package com.sence.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PBusBean;
import com.sence.net.Urls;

/**
 * Created by zwy on 2019/3/28.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class BusGoodAdapter extends BaseQuickAdapter<PBusBean.CartBean.GoodsBean, BaseViewHolder> {
    public BusGoodAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PBusBean.CartBean.GoodsBean item) {
        helper.addOnClickListener(R.id.item_select);
        helper.addOnClickListener(R.id.item_cut);
        helper.addOnClickListener(R.id.item_add);
        Glide.with(helper.itemView.getContext()).load(Urls.base_url + item.getImg()).into((ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_content, item.getName());
        helper.setText(R.id.item_price, item.getPrice());
        final TextView item_num = helper.getView(R.id.item_num);
        item_num.setText(item.getNum() + "");
        ImageView item_select = helper.getView(R.id.item_select);
        if (item.isSelect()) {
            item_select.setSelected(true);
        } else {
            item_select.setSelected(false);
        }
    }
}
