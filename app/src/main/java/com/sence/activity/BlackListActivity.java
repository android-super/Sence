package com.sence.activity;

import android.os.Bundle;

import com.sence.R;
import com.sence.adapter.BlackListAdapter;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BlackListActivity extends BaseActivity {


    @BindView(R.id.recycle_blacklist)
    RecyclerView recycleBlacklist;
    private BlackListAdapter blackListAdapter;

    @Override
    public int onActLayout() {
        return R.layout.activity_black_list;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        blackListAdapter = new BlackListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleBlacklist.setLayoutManager(linearLayoutManager);
        recycleBlacklist.setAdapter(blackListAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
