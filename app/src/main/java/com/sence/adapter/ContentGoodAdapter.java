package com.sence.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PContentDetailBean;
import com.sence.utils.GlideUtils;

/**
 * Created by zwy on 2019/3/30.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class ContentGoodAdapter extends BaseQuickAdapter<PContentDetailBean.NoteInfoBean.GoodsInfoBean,
        BaseViewHolder> {

    public ContentGoodAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PContentDetailBean.NoteInfoBean.GoodsInfoBean item) {
        GlideUtils.getInstance().loadNormal(item.getImg(), (ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_content, item.getName());
        if("0".equals(item.getVprice())||item.getVprice().equals(item.getPrice())){
            helper.setGone(R.id.item_pre_price,false);
            helper.setGone(R.id.item_pre_price_describe,false);
            helper.setText(R.id.item_price, item.getPrice());

        }else{
            helper.setText(R.id.item_price, item.getVprice());
            helper.setText(R.id.item_pre_price, item.getPrice());
        }
        TextView item_pre_price_describe = helper.getView(R.id.item_pre_price_describe);
        TextView item_pre_price = helper.getView(R.id.item_pre_price);
        item_pre_price_describe.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        item_pre_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.addOnClickListener(R.id.item_bus);
    }
}
