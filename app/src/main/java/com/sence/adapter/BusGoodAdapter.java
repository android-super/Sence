package com.sence.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.activity.ShopDetailsActivity;
import com.sence.bean.response.PBusBean;
import com.sence.utils.GlideUtils;

/**
 * Created by zwy on 2019/3/28.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class BusGoodAdapter extends BaseQuickAdapter<PBusBean.CartBean.GoodsBean, BaseViewHolder> {
    private String isMember;
    public BusGoodAdapter(int layoutResId,String isMember) {
        super(layoutResId);
        this.isMember = isMember;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PBusBean.CartBean.GoodsBean item) {
        helper.addOnClickListener(R.id.item_select);
        helper.addOnClickListener(R.id.item_cut);
        helper.addOnClickListener(R.id.item_add);
        GlideUtils.getInstance().loadNormal(item.getImg(), (ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_content, item.getName());
        if (isMember.equals("1")){
            if("0".equals(item.getVprice())){
                helper.setText(R.id.item_price, "￥"+item.getPrice());
            }else{
                helper.setText(R.id.item_price, "￥"+item.getVprice());
            }
        }else{
            helper.setText(R.id.item_price, "￥"+item.getPrice());
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(helper.itemView.getContext(),ShopDetailsActivity.class);
                intent.putExtra("id",item.getId());
                helper.itemView.getContext().startActivity(intent);
            }
        });
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
