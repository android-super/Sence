package com.sence.adapter;

import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sence.R;
import com.sence.bean.request.RMemberBean;
import com.sence.utils.GlideUtils;

/**
 * Created by zwy on 2019/3/25.
 * package_name is com.sence.adapter
 * 描述:SenceGit
 */
public class MemberAdapter extends BaseQuickAdapter<RMemberBean.ListBean, BaseViewHolder> {
    private boolean ismygroup = false;
    public MemberAdapter(int layoutResId,boolean ismygroup) {
        super(layoutResId);
        this.ismygroup= ismygroup;
    }

    @Override
    protected void convert(BaseViewHolder helper, RMemberBean.ListBean item) {
        GlideUtils.getInstance().loadHead(item.getAvatar(), (ImageView) helper.getView(R.id.item_head));
        if(ismygroup){
            if (item.getState().equals("1")){
                helper.setGone(R.id.item_ban,true);
            }
        }else {
            helper.setGone(R.id.item_ban,false);
        }
        if ("1".equals(item.getIs_service())) {
            helper.setGone(R.id.item_serve, true);
        } else {
            helper.setGone(R.id.item_serve, false);
        }

        helper.setText(R.id.item_name, item.getNick_name());
    }
}
