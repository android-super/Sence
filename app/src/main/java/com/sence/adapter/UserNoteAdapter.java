package com.sence.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityOptionsCompat;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.activity.NoteDetailActivity;
import com.sence.activity.NoteVideoDetailActivity;
import com.sence.bean.response.PMyInfoBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

/**
 * Created by zwy on 2019/3/18.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class UserNoteAdapter extends BaseQuickAdapter<PMyInfoBean.ListBean, BaseViewHolder> {
    public UserNoteAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PMyInfoBean.ListBean item) {
        final NiceImageView item_img = helper.getView(R.id.item_img);
        final Activity activity = (Activity) helper.itemView.getContext();
        final NiceImageView item_head = helper.getView(R.id.item_head);
        GlideUtils.getInstance().loadHead(item.getAvatar(), item_head);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) item_img.getLayoutParams();
        if (item.getHeight() > (1030 / 2)) {
            layoutParams.height = ConvertUtils.dp2px(1030 / 4);
        } else {
            layoutParams.height = ConvertUtils.dp2px(item.getHeight()) / 2;
        }
        GlideUtils.getInstance().loadNormal(item.getAlbum_url(), item_img);
        helper.setText(R.id.item_describe, item.getContent());
        helper.setText(R.id.item_name, item.getNick_name());
        TextView item_support = helper.getView(R.id.item_support);
        item_support.setText(item.getPraise_count());
        if (item.getIs_like().equals("1")) {
            item_support.setSelected(true);
            item_support.setTextColor(Color.parseColor("#16a5af"));
        } else {
            item_support.setSelected(false);
            item_support.setTextColor(Color.parseColor("#5f5f5f"));
        }
        if (item.getNote_type().equals("1")) {
            helper.setGone(R.id.item_video, false);
        } else {
            helper.setGone(R.id.item_video, true);
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        item_img,
                        activity.getResources().getString(R.string.translation_note_name));
                Intent intent;
                if (item.getNote_type().equals("1")) {
                    intent = new Intent(activity, NoteDetailActivity.class);
                } else {
                    intent = new Intent(activity, NoteVideoDetailActivity.class);
                }
                intent.putExtra("nid", item.getNid());
                intent.putExtra("width", item.getWidth());
                intent.putExtra("height", item.getHeight());
                activity.startActivity(intent, activityOptions.toBundle());
            }
        });
        helper.addOnClickListener(R.id.item_support);
    }
}
