package com.sence.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import androidx.core.app.ActivityOptionsCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.activity.NoteDetailActivity;
import com.sence.view.NiceImageView;

/**
 * Created by zwy on 2019/3/18.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class NoteAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public NoteAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        final NiceImageView imageView = helper.getView(R.id.item_img);
        final Activity context = (Activity) helper.itemView.getContext();
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView,
                        context.getResources().getString(R.string.translation_note_name));
                context.startActivity(new Intent(context, NoteDetailActivity.class), activityOptions.toBundle());
            }
        });
    }
}
