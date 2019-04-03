package com.sence.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.bean.response.PCommentBean;

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

    }
}
