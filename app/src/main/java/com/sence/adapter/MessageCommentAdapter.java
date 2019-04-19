package com.sence.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PMessageHdBean;
import com.sence.utils.GlideUtils;

public class MessageCommentAdapter extends BaseQuickAdapter<PMessageHdBean, BaseViewHolder> {
    public MessageCommentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PMessageHdBean item) {
        GlideUtils.getInstance().loadHead(item.getAvatar(), (ImageView) helper.getView(R.id.item_head));
        helper.setText(R.id.item_name, item.getNick_name());
        helper.setText(R.id.item_time, item.getAdd_time());
        GlideUtils.getInstance().loadHead(item.getAlbum_url(), (ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_describe, item.getContent());
    }
}
