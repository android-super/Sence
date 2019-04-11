package com.sence.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PServeBean;
import com.sence.utils.GlideUtils;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class ServeAdapter extends BaseQuickAdapter<PServeBean, BaseViewHolder> {
    public ServeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PServeBean item) {
        GlideUtils.getInstance().loadHead( item.getImg(),(ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_name, item.getTitle());
        helper.setText(R.id.item_content, item.getContent());
    }
}
