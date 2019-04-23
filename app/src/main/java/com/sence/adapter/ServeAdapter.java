package com.sence.adapter;

import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PServeBean;
import com.sence.utils.GlideUtils;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class ServeAdapter extends BaseQuickAdapter<PServeBean, BaseViewHolder> {
    public ServeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PServeBean item) {
        GlideUtils.getInstance().loadNormal(item.getImg(), (ImageView) helper.getView(R.id.item_img));
        TextView item_tag_one = helper.getView(R.id.item_tag_one);
        TextView item_tag_two = helper.getView(R.id.item_tag_two);
        TextView[] item_tags = new TextView[]{item_tag_one, item_tag_two};
        for (int i = 0; i < item.getTag().size(); i++) {
            if (i < item_tags.length) {
                item_tags[i].setText(item.getTag().get(i));
            }
        }
        ImageView item_star_one = helper.getView(R.id.item_star_one);
        ImageView item_star_two = helper.getView(R.id.item_star_two);
        ImageView item_star_three = helper.getView(R.id.item_star_three);
        ImageView item_star_four = helper.getView(R.id.item_star_four);
        ImageView item_star_five = helper.getView(R.id.item_star_five);
        ImageView[] item_stars = new ImageView[]{item_star_one, item_star_two, item_star_three, item_star_four,
                item_star_five};
        for (int i = 0; i < 5; i++) {
            if (i < Integer.parseInt(item.getStar())) {
                item_stars[i].setVisibility(View.VISIBLE);
            } else {
                item_stars[i].setVisibility(View.GONE);
            }
        }
        helper.setText(R.id.item_num, "体验 " + item.getNum() + " 次");
    }
}
