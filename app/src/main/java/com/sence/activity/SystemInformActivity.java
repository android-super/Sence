package com.sence.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.sence.R;
import com.sence.adapter.SystemInformAdapter;
import com.sence.base.BaseActivity;
import com.sence.utils.StatusBarUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 系统通知
 */

public class SystemInformActivity extends BaseActivity {

    @BindView(R.id.recycle_systeminform)
    RecyclerView recycleSysteminform;
    @BindView(R.id.iv_img_systeminform)
    ImageView ivImgSysteminform;
    private SystemInformAdapter mSystemInformAdapter;
    private int page = 1;

    @Override
    public int onActLayout() {
        return R.layout.activity_system_inform;
    }

    @Override
    public void initView() {
        StatusBarUtil.setLightMode(this);
        mSystemInformAdapter = new SystemInformAdapter(SystemInformActivity.this);
        LinearLayoutManager linearLayout = new LinearLayoutManager(SystemInformActivity.this);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recycleSysteminform.setLayoutManager(linearLayout);
        recycleSysteminform.setAdapter(mSystemInformAdapter);
    }

}
