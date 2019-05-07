package com.sence.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PPrivateChatBean;
import com.sence.utils.DateUtils;
import com.sence.utils.GlideUtils;

public class MessagePrivateAdapter extends BaseQuickAdapter<PPrivateChatBean, BaseViewHolder> {

    public MessagePrivateAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PPrivateChatBean item) {
        GlideUtils.getInstance().loadHead(item.getAvatar(), (ImageView) helper.getView(R.id.item_head));
        helper.setText(R.id.item_name, item.getNick_name());
        helper.setText(R.id.item_describe, item.getContent());
        helper.setText(R.id.item_time, item.getUpdate_time());
        if (item.getRead().equals("1")) {
            helper.setGone(R.id.item_point, true);
        } else {
            helper.setGone(R.id.item_point, false);
        }
    }
}
