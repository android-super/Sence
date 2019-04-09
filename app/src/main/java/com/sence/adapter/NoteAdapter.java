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
import com.sence.bean.response.PMainNoteBean;
import com.sence.net.Urls;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import androidx.core.app.ActivityOptionsCompat;

/**
 * Created by zwy on 2019/3/18.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class NoteAdapter extends BaseQuickAdapter<PMainNoteBean, BaseViewHolder> {
    public NoteAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PMainNoteBean item) {
        final NiceImageView imageView = helper.getView(R.id.item_img);
        final Activity activity = (Activity) helper.itemView.getContext();
        GlideUtils.getInstance().loadHead(item.getAvatar(), (ImageView) helper.getView(R.id.item_head));
        GlideUtils.getInstance().loadNormal(item.getAlbum_url(), imageView);
        helper.setText(R.id.item_describe, item.getContent());
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
        helper.addOnClickListener(R.id.item_support);
    }
}
