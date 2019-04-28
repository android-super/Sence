package com.sence.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.activity.ContentDetailActivity;
import com.sence.bean.response.PMainBean;
import com.sence.bean.response.PMainRecommendBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import androidx.core.app.ActivityOptionsCompat;

/**
 * Created by zwy on 2019/3/21.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class RecommendAdapter extends BaseQuickAdapter<PMainBean.NoteListBean, BaseViewHolder> {
    public RecommendAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PMainBean.NoteListBean item) {
        final NiceImageView item_img = helper.getView(R.id.item_img);
        final Activity activity = (Activity) helper.itemView.getContext();
        GlideUtils.getInstance().loadHead(item.getAvatar(), (ImageView) helper.getView(R.id.item_head));
        GlideUtils.getInstance().loadNormal(item.getAlbum_url(), item_img);
        helper.setText(R.id.item_title, item.getTitle());
        helper.setText(R.id.item_content, item.getDescribe());
        helper.setText(R.id.item_name, item.getNick_name());
        if ("1".equals(item.getIs_kol())) {
            helper.setGone(R.id.item_tag, true);
        } else {
            helper.setGone(R.id.item_tag, false);
        }
        ImageView item_support_img = helper.getView(R.id.item_support_img);
        TextView item_support = helper.getView(R.id.item_support);
        if (item.getIs_like().equals("1")) {
            item_support_img.setSelected(true);
            item_support.setTextColor(Color.parseColor("#16a5af"));
        } else {
            item_support_img.setSelected(false);
            item_support.setTextColor(Color.parseColor("#666666"));
        }
        helper.setText(R.id.item_support, item.getPraise_count());
        helper.setText(R.id.item_comment, item.getMessage_count());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        item_img, activity.getResources().getString(R.string.translation_recommend_name));
                Intent intent = new Intent(activity, ContentDetailActivity.class);
                intent.putExtra("nid", item.getNid());
                activity.startActivity(intent, activityOptions.toBundle());
            }
        });
        helper.addOnClickListener(R.id.item_support_img);
        helper.addOnClickListener(R.id.item_support);
    }
}
