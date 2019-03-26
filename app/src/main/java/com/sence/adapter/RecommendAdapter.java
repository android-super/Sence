package com.sence.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.activity.ContentDetailActivity;
import com.sence.view.NiceImageView;

/**
 * Created by zwy on 2019/3/21.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class RecommendAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RecommendAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        final NiceImageView item_img = helper.getView(R.id.item_img);
        final Activity activity = (Activity) helper.itemView.getContext();
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        item_img, activity.getResources().getString(R.string.translation_recommend_name));
                activity.startActivity(new Intent(activity, ContentDetailActivity.class), activityOptions.toBundle());
            }
        });
    }
}
