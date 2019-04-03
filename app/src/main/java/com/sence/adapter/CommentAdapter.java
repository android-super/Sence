package com.sence.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PCommentBean;
import com.sence.net.Urls;
import com.sence.utils.DateUtils;
import com.sence.view.MyTextView;

/**
 * Created by zwy on 2019/3/25.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class CommentAdapter extends BaseQuickAdapter<PCommentBean, BaseViewHolder> {
    public CommentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PCommentBean item) {
        Glide.with(helper.itemView.getContext()).load(Urls.base_url+item.getUser().getAvatar()).into((ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_name,item.getUser().getNick_name());
        helper.setText(R.id.item_time,
                DateUtils.convertTimeNew(item.getDiff_time(),item.getNow_time(),item.getAdd_time()));
        helper.setText(R.id.item_content,item.getContent());
        helper.setText(R.id.item_replay_content,item.getOld_message());
        MyTextView item_content = helper.getView(R.id.item_content);
        MyTextView item_replay_content = helper.getView(R.id.item_replay_content);
        ImageView item_support_img = helper.getView(R.id.item_support_img);
        if (item.getIs_like().equals("1")){
            item_support_img.setSelected(true);
        }else{
            item_support_img.setSelected(false);
        }
        item_content.setText(item.getContent());
        item_replay_content.setText(item.getOld_message());
//        item_content.setSpecifiedTextsColor(item_content.getText().toString(), item.getReply().getNick_name(), Color.parseColor(
//                "#507daf"));

    }
}
