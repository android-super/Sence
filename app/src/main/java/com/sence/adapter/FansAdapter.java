package com.sence.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PFansBean;
import com.sence.net.Urls;

/**
 * Created by zwy on 2019/3/22.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class FansAdapter extends BaseQuickAdapter<PFansBean, BaseViewHolder> {
    public FansAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PFansBean item) {
        helper.setText(R.id.item_name, item.getNick_name());
        Glide.with(helper.itemView.getContext()).load(Urls.base_url + item.getAvatar()).into((ImageView) helper.getView(R.id.item_head));
        helper.setText(R.id.item_content, item.getAutograph());
        TextView item_focus = helper.getView(R.id.item_focus);
        if (item.getIs_focus().equals("1")) {
            item_focus.setBackgroundResource(R.drawable.shape_round_gray);
            item_focus.setText("已关注");
            item_focus.setTextColor(Color.parseColor("#999999"));
        } else {
            item_focus.setBackgroundResource(R.drawable.shape_rect_theme_green_kuang);
            item_focus.setText("+ 关注");
            item_focus.setTextColor(Color.parseColor("#16a5af"));
        }
        if (item.getIs_kol().equals("1")){
            helper.setGone(R.id.item_vip,true);
        }else {
            helper.setGone(R.id.item_vip,false);
        }
    }
}
