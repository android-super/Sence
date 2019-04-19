package com.sence.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.activity.NoteDetailActivity;
import com.sence.bean.response.PMainBean;
import com.sence.bean.response.PMainNoteBean;
import com.sence.net.Urls;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import androidx.core.app.ActivityOptionsCompat;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

/**
 * Created by zwy on 2019/3/18.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class NoteAdapter extends BaseQuickAdapter<PMainBean.NoteListBean, BaseViewHolder> {
    public NoteAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PMainBean.NoteListBean item) {
        final NiceImageView item_img = helper.getView(R.id.item_img);
        final Activity activity = (Activity) helper.itemView.getContext();
        final NiceImageView item_head = helper.getView(R.id.item_head);
        GlideUtils.getInstance().loadHead(item.getAvatar(), item_head);
        Glide.with(item_img).load(Urls.base_url + item.getAlbum_url()).apply(new RequestOptions().override(0,
                SIZE_ORIGINAL).fitCenter().placeholder(R.drawable.shape_loading_error).error(R.drawable.shape_loading_error)).into(item_img);
        helper.setText(R.id.item_describe, item.getContent());
        helper.setText(R.id.item_name, item.getNick_name());
        TextView item_support = helper.getView(R.id.item_support);
        item_support.setText(item.getPraise_count());
        if ("1".equals(item.getIs_like())) {
            item_support.setSelected(true);
        } else {
            item_support.setSelected(false);
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        item_img,
                        activity.getResources().getString(R.string.translation_note_name));
                Intent intent = new Intent(activity, NoteDetailActivity.class);
                intent.putExtra("nid", item.getNid());
                activity.startActivity(intent, activityOptions.toBundle());
            }
        });
        helper.addOnClickListener(R.id.item_support);
    }
}
