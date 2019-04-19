package com.sence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PMessageHdBean;
import com.sence.utils.GlideUtils;
import com.sence.view.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagelikeAdapter extends BaseQuickAdapter<PMessageHdBean, BaseViewHolder> {

    public MessagelikeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PMessageHdBean item) {
        GlideUtils.getInstance().loadHead(item.getAvatar(), (ImageView) helper.getView(R.id.item_head));
        helper.setText(R.id.item_name, item.getNick_name());
        helper.setText(R.id.item_time, item.getAdd_time());
        GlideUtils.getInstance().loadHead(item.getAlbum_url(), (ImageView) helper.getView(R.id.item_img));
    }
}
