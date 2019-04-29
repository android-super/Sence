package com.sence.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.response.PBankCardBean;
import com.sence.net.Urls;
import com.sence.utils.GlideUtils;

/**
 * Created by zwy on 2019/4/8.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class BankCardAdapter extends BaseQuickAdapter<PBankCardBean, BaseViewHolder> {
    public BankCardAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PBankCardBean item) {
        helper.setText(R.id.item_name, item.getBank_name());
        helper.setText(R.id.item_num, item.getCard_num());
    }
}
