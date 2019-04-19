package com.sence.activity;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.luck.picture.lib.entity.LocalMedia;
import com.sence.R;
import com.sence.bean.request.tag.RTagMultiItem;
import com.sence.utils.GlideUtils;

import java.util.List;

/**
 * Created by zwy on 2019/4/15.
 * package_name is com.sence.activity
 * 描述:SenceGit
 */
public class TagAdapter extends BaseMultiItemQuickAdapter<RTagMultiItem, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TagAdapter(List<RTagMultiItem> data) {
        super(data);
        addItemType(RTagMultiItem.NORMAL, R.layout.rv_item_tag);
        addItemType(RTagMultiItem.LAST, R.layout.rv_item_tag_last);
    }

    @Override
    protected void convert(BaseViewHolder helper, RTagMultiItem item) {
        if (item.getItemType() == RTagMultiItem.NORMAL) {
            GlideUtils.getInstance().loadNormal(item.getLocalMedia().getPath(),
                    (ImageView) helper.getView(R.id.item_img),
                    true);
            helper.addOnClickListener(R.id.item_close);
        }

    }
}
