package com.sence.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.activity.NoteDetailActivity;
import com.sence.bean.response.PNoteDetailBean;
import com.sence.net.Urls;
import com.sence.view.NiceImageView;

import androidx.core.app.ActivityOptionsCompat;

/**
 * Created by zwy on 2019/3/18.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class NoteRecommendAdapter extends BaseQuickAdapter<PNoteDetailBean.RecommendNoteBean, BaseViewHolder> {
    public NoteRecommendAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PNoteDetailBean.RecommendNoteBean item) {
        final NiceImageView imageView = helper.getView(R.id.item_img);
        final Activity activity = (Activity) helper.itemView.getContext();

        Glide.with(activity).load(Urls.base_url + item.getAlbum_url()).into(imageView);
        helper.setText(R.id.item_describe, item.getContent());
        Glide.with(activity).load(Urls.base_url + item.getAvatar()).into((ImageView) helper.getView(R.id.item_head));
        helper.setText(R.id.item_name, item.getNick_name());
        TextView item_support = helper.getView(R.id.item_support);
        item_support.setText(item.getPraise_count());
        if (item.getIs_like().equals("1")) {
            item_support.setSelected(true);
        } else {
            item_support.setSelected(false);
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        imageView,
                        activity.getResources().getString(R.string.translation_note_name));
                Intent intent = new Intent(activity, NoteDetailActivity.class);
                intent.putExtra("nid", item.getNid());
                activity.startActivity(intent, activityOptions.toBundle());
            }
        });
    }
}
