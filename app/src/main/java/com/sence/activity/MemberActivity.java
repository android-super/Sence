package com.sence.activity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.blankj.utilcode.util.ConvertUtils;
import com.sence.R;
import com.sence.adapter.MemberAdapter;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;
import com.sence.view.GridSpacingItemDecoration;

/**
 * V群成员
 */
public class MemberActivity extends BaseActivity {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private MemberAdapter adapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_member;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        recycleView.setLayoutManager(gridLayoutManager);
        recycleView.addItemDecoration(new GridSpacingItemDecoration(5, ConvertUtils.dp2px(20), true));
        adapter = new MemberAdapter(R.layout.rv_item_member);
        recycleView.setAdapter(adapter);
    }
}
